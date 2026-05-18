import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Commande, CommandeService } from '../../services/commande';
import { LigneCommande, LigneCommandeService } from '../../services/ligne-commande';

@Component({
  selector: 'app-lignes-commande',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './lignes-commande.html',
  styleUrl: './lignes-commande.css'
})
export class LignesCommandeComponent implements OnInit {
  private ligneService = inject(LigneCommandeService);
  private commandeService = inject(CommandeService);

  commandes: Commande[] = [];
  lignes: LigneCommande[] = [];
  selectedCommandeId: number = 0;
  newLigne: LigneCommande = { commande: { id: 0 }, produit: '', quantite: 1, prixUnitaire: 0 };
  message = '';
  total = 0;

  ngOnInit(): void {
    this.commandeService.getAll().subscribe(data => this.commandes = data);
  }

  loadLignes(): void {
    if (this.selectedCommandeId === 0) return;
    this.ligneService.getByCommande(this.selectedCommandeId).subscribe(data => {
      this.lignes = data;
      this.total = data.reduce((sum, l) => sum + (l.quantite * l.prixUnitaire), 0);
    });
  }

  create(): void {
    if (!this.newLigne.produit) { this.message = 'Entre un produit !'; return; }
    this.newLigne.commande = { id: this.selectedCommandeId };
    this.ligneService.create(this.newLigne).subscribe(() => {
      this.message = 'Ligne ajoutée !';
      this.newLigne = { commande: { id: 0 }, produit: '', quantite: 1, prixUnitaire: 0 };
      setTimeout(() => {
        this.loadLignes();
        this.commandeService.recalculerMontant(this.selectedCommandeId).subscribe();
      }, 500);
    });
  }

  delete(id: number): void {
    this.ligneService.delete(id).subscribe(() => {
      this.message = 'Ligne supprimée !';
      setTimeout(() => this.loadLignes(), 500);
    });
  }
}
