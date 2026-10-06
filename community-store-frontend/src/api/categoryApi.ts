import { apiGet } from './client';
import type { Category } from '../types/category';

// Wraps za.ac.cput.communitystoreplatform.controller.CategoryController
// The backend runs under server.servlet.context-path=/CommunityStore.
const CATEGORY_PATH = '/CommunityStore/categories';

export const categoryApi = {
  getAll: () => apiGet<Category[]>(`${CATEGORY_PATH}/all`),

  getById: (id: number) => apiGet<Category>(`${CATEGORY_PATH}/${id}`),

  getByName: (name: string) =>
    apiGet<Category>(`${CATEGORY_PATH}/name/${encodeURIComponent(name)}`),
};
