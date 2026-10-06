import { apiDelete, apiGet, apiPost, apiPut } from './client';
import type { Address } from '../types/address';

const ADDRESS_PATH = '/CommunityStore/address';

export const addressApi = {
  create(address: Address): Promise<Address> {
    return apiPost<Address>(
      `${ADDRESS_PATH}/create`,
      address
    );
  },

  getById(addressId: number): Promise<Address> {
    return apiGet<Address>(
      `${ADDRESS_PATH}/read/${addressId}`
    );
  },

  update(address: Address): Promise<Address> {
    return apiPut<Address>(
      `${ADDRESS_PATH}/update`,
      address
    );
  },

  delete(addressId: number): Promise<boolean> {
    return apiDelete<boolean>(
      `${ADDRESS_PATH}/delete/${addressId}`
    );
  },
};