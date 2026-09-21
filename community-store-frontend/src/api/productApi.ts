import { apiGet } from './client';
import type { Product } from '../types/product';

// Wraps za.ac.cput.communitystoreplatform.controller.ProductController
export const productApi = {
  getAll: () => apiGet<Product[]>('/products/all'),

  getById: (id: number) => apiGet<Product>(`/products/${id}`),

  searchByName: (name: string) =>
    apiGet<Product[]>(`/products/search?name=${encodeURIComponent(name)}`),

  getByStatus: (status: string) =>
    apiGet<Product[]>(`/products/status/${encodeURIComponent(status)}`),
};
