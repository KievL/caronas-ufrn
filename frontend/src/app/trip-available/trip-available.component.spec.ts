import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripAvailableComponent } from './trip-available.component';

describe('TripAvailableComponent', () => {
  let component: TripAvailableComponent;
  let fixture: ComponentFixture<TripAvailableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripAvailableComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TripAvailableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
