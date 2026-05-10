import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Commande, CommandeService } from '../../services/commande';
import { Paiement, PaiementService } from '../../services/paiement';

@Component({
  selector: 'app-paiements',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './paiements.html',
  styleUrl: './paiements.css'
})
export class PaiementsComponent implements OnInit {
  private paiementService = inject(PaiementService);
  private commandeService = inject(CommandeService);

  paiements: Paiement[] = [];
  commandes: Commande[] = [];
  selectedCommandeId: number = 0;
  selectedMode: string = 'CARTE';
  message = '';
  modeOptions = ['CARTE', 'VIREMENT', 'ESPECES', 'CHEQUE'];

  ngOnInit(): void {
    this.load();
    this.commandeService.getAll().subscribe(data => this.commandes = data);
  }

  load(): void {
    this.paiementService.getAll().subscribe(data => this.paiements = data);
  }

  create(): void {
    if (this.selectedCommandeId === 0) { this.message = 'Sélectionne une commande !'; return; }
    const paiement: Paiement = {
      commande: { id: this.selectedCommandeId },
      mode: this.selectedMode
    };
    this.paiementService.create(paiement).subscribe(() => {
      this.message = 'Paiement créé !';
      this.load();
    });
  }

  valider(id: number): void {
    this.paiementService.valider(id).subscribe(() => {
      this.message = 'Paiement validé !';
      this.load();
    });
  }

  delete(id: number): void {
    if (confirm('Supprimer ?')) {
      this.paiementService.delete(id).subscribe(() => { this.message = 'Supprimé !'; this.load(); });
    }
  }
}
