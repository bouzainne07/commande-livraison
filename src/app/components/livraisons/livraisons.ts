import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Commande, CommandeService } from '../../services/commande';
import { Livraison, LivraisonService } from '../../services/livraison';
import { Transporteur, TransporteurService } from '../../services/transporteur';

@Component({
  selector: 'app-livraisons',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './livraisons.html',
  styleUrl: './livraisons.css'
})
export class LivraisonsComponent implements OnInit {
  private livraisonService = inject(LivraisonService);
  private commandeService = inject(CommandeService);
  private transporteurService = inject(TransporteurService);

  livraisons: Livraison[] = [];
  commandes: Commande[] = [];
  transporteurs: Transporteur[] = [];
  selectedCommandeId: number = 0;
  selectedTransporteurId: number = 0;
  message = '';
  loading = false;
  statutOptions = ['EN_ATTENTE', 'EN_COURS', 'LIVREE', 'ECHEC'];

  ngOnInit(): void {
    this.load();
    this.commandeService.getAll().subscribe(data => this.commandes = data);
    this.transporteurService.getAll().subscribe(data => this.transporteurs = data);
  }

  load(): void {
    this.loading = true;
    this.livraisonService.getAll().subscribe({
      next: data => { this.livraisons = data; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  create(): void {
    if (this.selectedCommandeId === 0) { this.message = 'Sélectionne une commande !'; return; }
    const livraison: Livraison = {
      commande: { id: this.selectedCommandeId },
      transporteur: this.selectedTransporteurId ? { id: this.selectedTransporteurId } : undefined
    };
    this.livraisonService.create(livraison).subscribe(() => {
      this.message = 'Livraison créée !';
      setTimeout(() => this.load(), 500);
    });
  }

  updateStatut(id: number, statut: string): void {
    this.livraisonService.updateStatut(id, statut).subscribe(() => {
      this.message = 'Statut mis à jour !';
      setTimeout(() => this.load(), 500);
    });
  }

  delete(id: number): void {
    if (confirm('Supprimer ?')) {
      this.livraisonService.delete(id).subscribe(() => {
        this.message = 'Supprimée !';
        setTimeout(() => this.load(), 500);
      });
    }
  }
}
