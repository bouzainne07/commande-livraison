import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { Observable } from 'rxjs';

// Interface = la structure d'un Client
export interface Client {
  id?: number;
  nom: string;
  email: string;
  adresse: string;
  telephone: string;
}

@Injectable({
  providedIn: 'root'
})
export class ClientService {

  private http = inject(HttpClient);

  // L'adresse de ton API Spring Boot
  private apiUrl = 'http://localhost:8080/api/clients';

  // GET tous les clients
  getAll(): Observable<Client[]> {
    return this.http.get<Client[]>(this.apiUrl);
  }

  // GET un client par id
  getById(id: number): Observable<Client> {
    return this.http.get<Client>(`${this.apiUrl}/${id}`);
  }

  // POST créer un client
  create(client: Client): Observable<Client> {
    return this.http.post<Client>(this.apiUrl, client);
  }

  // PUT modifier un client
  update(id: number, client: Client): Observable<Client> {
    return this.http.put<Client>(`${this.apiUrl}/${id}`, client);
  }

  // DELETE supprimer un client
  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
