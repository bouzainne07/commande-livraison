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

  clients: Client[] = [];
  newClient: Client = { nom: '', email: '', adresse: '', telephone: '' };
  editingClient: Client | null = null;
  message = '';
  loading = false;

  ngOnInit(): void {
    this.loadClients();
  }

  loadClients(): void {
    this.loading = true;
    this.clientService.getAll().subscribe({
      next: (data) => { this.clients = data; this.loading = false; },
      error: (err) => { console.error('Erreur:', err); this.loading = false; }
    });
  }

  createClient(): void {
    this.clientService.create(this.newClient).subscribe(() => {
      this.message = 'Client créé avec succès !';
      this.newClient = { nom: '', email: '', adresse: '', telephone: '' };
      setTimeout(() => this.loadClients(), 500);
    });
  }

  editClient(client: Client): void {
    this.editingClient = { ...client };
  }

  updateClient(): void {
    if (this.editingClient && this.editingClient.id) {
      this.clientService.update(this.editingClient.id, this.editingClient).subscribe(() => {
        this.message = 'Client modifié !';
        this.editingClient = null;
        setTimeout(() => this.loadClients(), 500);
      });
    }
  }

  deleteClient(id: number): void {
    if (confirm('Supprimer ce client ?')) {
      this.clientService.delete(id).subscribe(() => {
        this.message = 'Client supprimé !';
        setTimeout(() => this.loadClients(), 500);
      });
    }
  }
}
