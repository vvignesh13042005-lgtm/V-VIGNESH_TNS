import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Customer } from './Customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  API = "http://localhost:8082/customerdetails";  // Correct API base path

  constructor(private http: HttpClient) {}

  /**
   * Registers a new customer.
   * @param customerData - The customer data to register.
   */
  public registerCustomer(customerData: any) {
    return this.http.post(${this.API}, customerData);  // Register a new customer
  }

  /**
   * Fetches all customers from the backend.
   */
  public getCustomers() {
    return this.http.get<Customer[]>(${this.API});  // Get list of all customers
  }

  /**
   * Deletes a customer by ID.
   * @param id - The ID of the customer to delete.
   */
  public deleteCustomer(id: number) {
    return this.http.delete(${this.API}/${id});  // Delete a specific customer by ID
  }

  /**
   * Updates a customer's details.
   * @param customer - The updated customer data.
   */
  public updateCustomer(customer: any) {
    const id = customer.id || customer.id;  // Handle both lowercase and uppercase IDs
    if (!id) {
      throw new Error('Customer ID is required for updating customer data.');
    }
    return this.http.put(${this.API}/${id}, customer);  // Update customer details
  }
}