import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface Paiement {
  id?: number;
  commande: { id: number };
  datePaiement?: string;
  statut?: string;
  mode?: string;
}

@Injectable({ providedIn: 'root' })
export class PaiementService {
  private http = inject(HttpClient);
  private apiUrl = 'https://commande-livraison-production.up.railway.app/api/paiements';

  getAll(): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(this.apiUrl);
  }

  create(p: Paiement): Observable<Paiement> {
    return this.http.post<Paiement>(this.apiUrl, p);
  }

  valider(id: number): Observable<Paiement> {
    return this.http.put<Paiement>(`${this.apiUrl}/${id}/valider`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
