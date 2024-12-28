import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TripFinishedComponent } from './trip-finished.component';

describe('TripFinishedComponent', () => {
  let component: TripFinishedComponent;
  let fixture: ComponentFixture<TripFinishedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TripFinishedComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TripFinishedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
