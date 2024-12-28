import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripOngoingComponent } from './trip-ongoing.component';

describe('TripOngoingComponent', () => {
  let component: TripOngoingComponent;
  let fixture: ComponentFixture<TripOngoingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripOngoingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TripOngoingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
