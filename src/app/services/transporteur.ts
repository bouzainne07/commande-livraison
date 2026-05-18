import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

export interface Transporteur {
  id?: number;
  nom: string;
  telephone: string;
  note?: number;
}

@Injectable({ providedIn: 'root' })
export class TransporteurService {
  private http = inject(HttpClient);
  private apiUrl = 'https://commande-livraison-production.up.railway.app/api/transporteurs';

  getAll(): Observable<Transporteur[]> {
    return this.http.get<Transporteur[]>(this.apiUrl);
  }

  create(t: Transporteur): Observable<Transporteur> {
    return this.http.post<Transporteur>(this.apiUrl, t);
  }

  update(id: number, t: Transporteur): Observable<Transporteur> {
    return this.http.put<Transporteur>(`${this.apiUrl}/${id}`, t);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
