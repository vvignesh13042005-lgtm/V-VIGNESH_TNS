export interface Customer {
    id: number | null;  // Matches backend primary key
    name: string;
    email: string;
    phone: string;
    orderId: number ;
  }