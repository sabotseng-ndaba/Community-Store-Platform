import { apiGet } from './client';
import type { Category } from '../types/category';

// Wraps za.ac.cput.communitystoreplatform.controller.CategoryController
export const categoryApi = {
  getAll: () => apiGet<Category[]>('/categories/all'),

  getById: (id: number) => apiGet<Category>(`/categories/${id}`),

  getByName: (name: string) =>
    apiGet<Category>(`/categories/name/${encodeURIComponent(name)}`),
};
