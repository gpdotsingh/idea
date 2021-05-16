import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GetHealthComponent } from './get-health.component';

describe('GetHealthComponent', () => {
  let component: GetHealthComponent;
  let fixture: ComponentFixture<GetHealthComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GetHealthComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GetHealthComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
