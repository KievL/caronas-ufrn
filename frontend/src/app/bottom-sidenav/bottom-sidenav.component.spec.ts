import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BottomSidenavComponent } from './bottom-sidenav.component';

describe('BottomSidenavComponent', () => {
  let component: BottomSidenavComponent;
  let fixture: ComponentFixture<BottomSidenavComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BottomSidenavComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BottomSidenavComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
