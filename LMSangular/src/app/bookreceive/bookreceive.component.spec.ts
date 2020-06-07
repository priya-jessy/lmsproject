import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BookreceiveComponent } from './bookreceive.component';

describe('BookreceiveComponent', () => {
  let component: BookreceiveComponent;
  let fixture: ComponentFixture<BookreceiveComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BookreceiveComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BookreceiveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
