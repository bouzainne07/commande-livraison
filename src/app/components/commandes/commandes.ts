import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Client, ClientService } from '../../services/client';
import { Commande, CommandeService } from '../../services/commande';

@Component({
  selector: 'app-commandes',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './commandes.html',
  styleUrl: './commandes.css'
})
export class CommandesComponent implements OnInit {
  private commandeService = inject(CommandeService);
  private clientService = inject(ClientService);

  commandes: Commande[] = [];
  clients: Client[] = [];
  selectedClientId: number = 0;
  message = '';

  statutOptions = ['EN_ATTENTE', 'VALIDEE', 'EN_LIVRAISON', 'LIVREE', 'ANNULEE'];

  ngOnInit(): void {
    this.load();
    this.clientService.getAll().subscribe(data => this.clients = data);
  }

  load(): void {
    this.commandeService.getAll().subscribe(data => this.commandes = data);
  }

  create(): void {
    if (this.selectedClientId === 0) { this.message = 'Sélectionne un client !'; return; }
    const commande: Commande = { client: { id: this.selectedClientId } };
    this.commandeService.create(commande).subscribe(() => {
      this.message = 'Commande créée !';
      this.selectedClientId = 0;
      this.load();
    });
  }

  updateStatut(id: number, statut: string): void {
    this.commandeService.updateStatut(id, statut).subscribe(() => {
      this.message = 'Statut mis à jour !';
      this.load();
    });
  }

  delete(id: number): void {
    if (confirm('Supprimer ?')) {
      this.commandeService.delete(id).subscribe(() => { this.message = 'Supprimée !'; this.load(); });
    }
  }
}
