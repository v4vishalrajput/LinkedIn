import { ComponentFixture, TestBed } from '@angular/core/testing';

import { JobmodalsComponent } from './jobmodals.component';

describe('JobmodalsComponent', () => {
  let component: JobmodalsComponent;
  let fixture: ComponentFixture<JobmodalsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [JobmodalsComponent]
    });
    fixture = TestBed.createComponent(JobmodalsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
