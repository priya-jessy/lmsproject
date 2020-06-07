import { TestBed } from '@angular/core/testing';

import { RoutingguardService } from './routingguard.service';

describe('RoutingguardService', () => {
  let service: RoutingguardService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RoutingguardService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
