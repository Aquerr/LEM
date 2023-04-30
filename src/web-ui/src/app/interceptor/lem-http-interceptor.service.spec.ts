import { TestBed } from '@angular/core/testing';

import { LemHttpInterceptor } from './lem-http-interceptor.service';

describe('LemHttpInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    providers: [
      LemHttpInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: LemHttpInterceptor = TestBed.inject(LemHttpInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
