import { CommonModule } from '@angular/common';
import { Component, OnInit, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Transporteur, TransporteurService } from '../../services/transporteur';

@Component({
  selector: 'app-transporteurs',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './transporteurs.html',
  styleUrl: './transporteurs.css'
})
export class TransporteursComponent implements OnInit {
  private transporteurService = inject(TransporteurService);

  transporteurs: Transporteur[] = [];
  newTransporteur: Transporteur = { nom: '', telephone: '', note: 0 };
  editingTransporteur: Transporteur | null = null;
  message = '';
  loading = false;

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.transporteurService.getAll().subscribe({
      next: data => { this.transporteurs = data; this.loading = false; },
      error: () => { this.loading = false; }
    });
  }

  create(): void {
    this.transporteurService.create(this.newTransporteur).subscribe(() => {
      this.message = 'Transporteur ajouté !';
      this.newTransporteur = { nom: '', telephone: '', note: 0 };
      setTimeout(() => this.load(), 500);
    });
  }

  edit(t: Transporteur): void { this.editingTransporteur = { ...t }; }

  update(): void {
    if (this.editingTransporteur?.id) {
      this.transporteurService.update(this.editingTransporteur.id, this.editingTransporteur).subscribe(() => {
        this.message = 'Transporteur modifié !';
        this.editingTransporteur = null;
        setTimeout(() => this.load(), 500);
      });
    }
  }

  delete(id: number): void {
    if (confirm('Supprimer ?')) {
      this.transporteurService.delete(id).subscribe(() => {
        this.message = 'Supprimé !';
        setTimeout(() => this.load(), 500);
      });
    }
  }
}
