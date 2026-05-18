import { Routes } from '@angular/router';
import { ClientsComponent } from './components/clients/clients';
import { CommandesComponent } from './components/commandes/commandes';
import { LignesCommandeComponent } from './components/lignes-commande/lignes-commande';
import { LivraisonsComponent } from './components/livraisons/livraisons';
import { PaiementsComponent } from './components/paiements/paiements';
import { TransporteursComponent } from './components/transporteurs/transporteurs';

export const routes: Routes = [
  { path: '', redirectTo: 'clients', pathMatch: 'full' },
  { path: 'clients', component: ClientsComponent },
  { path: 'commandes', component: CommandesComponent },
  { path: 'lignes-commande', component: LignesCommandeComponent },
  { path: 'livraisons', component: LivraisonsComponent },
  { path: 'transporteurs', component: TransporteursComponent },
  { path: 'paiements', component: PaiementsComponent }
];
