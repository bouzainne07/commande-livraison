import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface Commande {
  id?: number;
  client: { id: number; nom?: string };
  dateCommande?: string;
  statut?: string;
  montantTotal?: number;
}

@Injectable({ providedIn: 'root' })
export class CommandeService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/commandes';

  getAll(): Observable<Commande[]> {
    return this.http.get<Commande[]>(this.apiUrl);
  }

  getById(id: number): Observable<Commande> {
    return this.http.get<Commande>(`${this.apiUrl}/${id}`);
  }

  create(commande: Commande): Observable<Commande> {
    return this.http.post<Commande>(this.apiUrl, commande);
  }

  update(id: number, commande: Commande): Observable<Commande> {
    return this.http.put<Commande>(`${this.apiUrl}/${id}`, commande);
  }

  updateStatut(id: number, statut: string): Observable<Commande> {
    return this.http.put<Commande>(`${this.apiUrl}/${id}/statut?statut=${statut}`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  recalculerMontant(id: number): Observable<Commande> {
    return this.http.put<Commande>(`${this.apiUrl}/${id}/recalculer`, {});
  }
}
