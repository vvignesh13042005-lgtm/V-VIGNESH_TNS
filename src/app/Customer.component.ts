import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { CustomerService } from './Customer.service';

// Define the Customer interface to enforce structure
interface Customer {
  id: number | null;
  name: string;
  email: string;
  phone: string;
  orderId: number ;
}

@Component({
  selector: 'app-customer-root',
  templateUrl: './Customer.component.html',
  styleUrls: ['./Customer.component.scss']
})
export class CustomerComponent implements OnInit {
  title = 'customers';
  customers: Customer[] = [];
  customerToUpdate: Customer = {
    id: null,
    name: '',
    email: '',
    phone: '',
    orderId: 0
  };

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getCustomers();
  }

  /**
   * Registers a new Customer using the provided form data.
   * @param registerForm The form containing customer data.
   */
  register(registerForm: NgForm): void {
    this.customerService.registerCustomer(registerForm.value).subscribe(
      () => {
        registerForm.reset();
        this.getCustomers(); // Refresh customer list
      },
      error => {
        console.error('Error registering customer:', error);
        alert('There was an error registering the customer.');
      }
    );
  }

  /**
   * Fetches all customers from the backend and updates the local state.
   */
  getCustomers(): void {
    this.customerService.getCustomers().subscribe(
      (response: any) => {
        this.customers = response;  // Assume the response is in the format you need
        console.log('Loaded customers:', this.customers);
      },
      error => {
        console.error('Error fetching customers:', error);
        alert('Failed to fetch customers.');
      }
    );
  }

  /**
   * Deletes a customer by ID.
   * @param customer The customer to delete.
   */
  deleteCustomer(customer: Customer): void {
    if (!customer.id) {
      console.error('Customer ID is undefined. Cannot delete customer.', customer);
      return;
    }
    this.customerService.deleteCustomer(customer.id).subscribe(
      () => {
        console.log('Customer deleted successfully.');
        this.getCustomers();  // Refresh customer list
      },
      error => {
        console.error('Error deleting customer:', error);
        alert('Error deleting customer.');
      }
    );
  }

  /**
   * Sets the customerToUpdate object for editing.
   * @param customer The customer to edit.
   */
  edit(customer: Customer): void {
    this.customerToUpdate = { ...customer };
  }

  /**
   * Updates an existing customer with the current customerToUpdate values.
   */
  updateCustomer(): void {
    if (this.customerToUpdate.id) {
      this.customerService.updateCustomer(this.customerToUpdate).subscribe(
        () => {
          console.log('Customer updated successfully.');
          this.getCustomers();  // Refresh customer list
          this.customerToUpdate = {
            id: null,
            name: '',
            email: '',
            phone: '',
            orderId: 0
          }; // Reset after successful update
        },
        error => {
          console.error('Error updating customer:', error);
          alert('Error updating customer.');
        }
      );
    } else {
      console.error('Customer ID is undefined. Cannot update customer.');
      alert('Customer ID is undefined. Cannot update customer.');
    }
  }
}