import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LignesCommande } from './lignes-commande';

describe('LignesCommande', () => {
  let component: LignesCommande;
  let fixture: ComponentFixture<LignesCommande>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LignesCommande],
    }).compileComponents();

    fixture = TestBed.createComponent(LignesCommande);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
