import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RequestedbooksComponent } from './requestedbooks.component';

describe('RequestedbooksComponent', () => {
  let component: RequestedbooksComponent;
  let fixture: ComponentFixture<RequestedbooksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RequestedbooksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RequestedbooksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
