import type { User } from './user';

export interface Address {
  addressId: number;
  user: User;
  addressLine: string;
  city: string;
  province: string;
  postalCode: number;
}