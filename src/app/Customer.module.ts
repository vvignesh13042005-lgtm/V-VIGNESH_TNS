import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { CustomerComponent } from './Customer.component';

@NgModule({
  declarations: [
    CustomerComponent 
  ],
  imports: [
    BrowserModule,  
    FormsModule,    
    HttpClientModule 
  ],
  providers: [],
  bootstrap: [CustomerComponent] 
})
export class CustomerModule {}