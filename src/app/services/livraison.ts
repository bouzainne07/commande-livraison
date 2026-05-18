import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface Livraison {
  id?: number;
  commande: { id: number };
  transporteur?: { id: number; nom?: string };
  dateLivraison?: string;
  cout?: number;
  statut?: string;
}

@Injectable({ providedIn: 'root' })
export class LivraisonService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/livraisons';

  getAll(): Observable<Livraison[]> {
    return this.http.get<Livraison[]>(this.apiUrl);
  }

  create(l: Livraison): Observable<Livraison> {
    return this.http.post<Livraison>(this.apiUrl, l);
  }

  updateStatut(id: number, statut: string): Observable<Livraison> {
    return this.http.put<Livraison>(`${this.apiUrl}/${id}/statut?statut=${statut}`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
