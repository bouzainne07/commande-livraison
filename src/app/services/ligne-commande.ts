import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface LigneCommande {
  id?: number;
  commande: { id: number };
  produit: string;
  quantite: number;
  prixUnitaire: number;
}

@Injectable({ providedIn: 'root' })
export class LigneCommandeService {
  private http = inject(HttpClient);
  private apiUrl = 'https://commande-livraison-production.up.railway.app/api/lignes-commande';

  getByCommande(commandeId: number): Observable<LigneCommande[]> {
    return this.http.get<LigneCommande[]>(`${this.apiUrl}/commande/${commandeId}`);
  }

  create(ligne: LigneCommande): Observable<LigneCommande> {
    return this.http.post<LigneCommande>(this.apiUrl, ligne);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
