import { TestBed } from '@angular/core/testing';
import { CustomerComponent } from './Customer.component';
import { CustomerService } from './Customer.service';
import { of } from 'rxjs';

describe('CustomerComponent', () => {
  let mockCustomerService: jasmine.SpyObj<CustomerService>;

  beforeEach(async () => {
    mockCustomerService = jasmine.createSpyObj('CustomerService', ['getCustomers']);
    mockCustomerService.getCustomers.and.returnValue(of([])); // Mock response

    await TestBed.configureTestingModule({
      declarations: [CustomerComponent],
      providers: [{ provide: CustomerService, useValue: mockCustomerService }],
    }).compileComponents();
  });

  it('should create the component', () => {
    const fixture = TestBed.createComponent(CustomerComponent);
    const component = fixture.componentInstance;
    expect(component).toBeTruthy();
  });

  it('should have as title \'Customer\'', () => {
    const fixture = TestBed.createComponent(CustomerComponent);
    const component = fixture.componentInstance;
    expect(component.title).toEqual('Customer');
  });

  it('should call getCustomers on init', () => {
    const fixture = TestBed.createComponent(CustomerComponent);
    const component = fixture.componentInstance;
    spyOn(component, 'getCustomers');
    component.ngOnInit();
    expect(component.getCustomers).toHaveBeenCalled();
  });

  it('should fetch customers from the service', () => {
    const fixture = TestBed.createComponent(CustomerComponent);
    const component = fixture.componentInstance;
    component.getCustomers();
    expect(mockCustomerService.getCustomers).toHaveBeenCalled();
  });
});