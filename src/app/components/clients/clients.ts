import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Client, ClientService } from '../../services/client';

@Component({
  selector: 'app-clients',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './clients.html',
  styleUrl: './clients.css'
})
export class ClientsComponent implements OnInit {

  private clientService = inject(ClientService);

  clients: Client[] = [];        // liste des clients
  newClient: Client = { nom: '', email: '', adresse: '', telephone: '' };
  editingClient: Client | null = null;
  message = '';

  ngOnInit(): void {
    this.loadClients();   // charge les clients au démarrage
  }

  loadClients(): void {
    this.clientService.getAll().subscribe(data => {
      this.clients = data;
    });
  }

  createClient(): void {
    this.clientService.create(this.newClient).subscribe(() => {
      this.message = 'Client créé avec succès !';
      this.newClient = { nom: '', email: '', adresse: '', telephone: '' };
      this.loadClients();
    });
  }

  editClient(client: Client): void {
    this.editingClient = { ...client };  // copie le client pour l'édition
  }

  updateClient(): void {
    if (this.editingClient && this.editingClient.id) {
      this.clientService.update(this.editingClient.id, this.editingClient).subscribe(() => {
        this.message = 'Client modifié !';
        this.editingClient = null;
        this.loadClients();
      });
    }
  }

  deleteClient(id: number): void {
    if (confirm('Supprimer ce client ?')) {
      this.clientService.delete(id).subscribe(() => {
        this.message = 'Client supprimé !';
        this.loadClients();
      });
    }
  }
}
