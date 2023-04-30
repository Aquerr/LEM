import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeaderTimeComponent } from './header-time.component';

describe('HeaderTimeComponent', () => {
  let component: HeaderTimeComponent;
  let fixture: ComponentFixture<HeaderTimeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeaderTimeComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HeaderTimeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
