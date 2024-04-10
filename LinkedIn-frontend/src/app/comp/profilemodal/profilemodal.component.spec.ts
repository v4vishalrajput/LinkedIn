import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfilemodalComponent } from './profilemodal.component';

describe('ProfilemodalComponent', () => {
  let component: ProfilemodalComponent;
  let fixture: ComponentFixture<ProfilemodalComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ProfilemodalComponent]
    });
    fixture = TestBed.createComponent(ProfilemodalComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
