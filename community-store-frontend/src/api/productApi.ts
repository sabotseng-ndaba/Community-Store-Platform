import { API_BASE_URL, ApiError, apiGet, apiPost, apiPut } from './client';
import type { Product, ProductPayload } from '../types/product';

// Wraps za.ac.cput.communitystoreplatform.controller.ProductController
// The backend runs under server.servlet.context-path=/CommunityStore.
const PRODUCT_PATH = '/CommunityStore/products';

export const productApi = {
  getAll: () => apiGet<Product[]>(`${PRODUCT_PATH}/all`),

  getById: (id: number) => apiGet<Product>(`${PRODUCT_PATH}/${id}`),

  searchByName: (name: string) =>
    apiGet<Product[]>(`${PRODUCT_PATH}/search?name=${encodeURIComponent(name)}`),

  getByStatus: (status: string) =>
    apiGet<Product[]>(`${PRODUCT_PATH}/status/${encodeURIComponent(status)}`),

  create: (payload: ProductPayload) =>
    apiPost<Product>(`${PRODUCT_PATH}/create`, payload),

  update: (payload: ProductPayload) =>
    apiPut<Product>(`${PRODUCT_PATH}/update`, payload),

  // Expects POST /products/{id}/image with a multipart field named "file"
  // that returns the updated Product. See BACKEND_IMAGE_UPLOAD.md.
  uploadImage: async (productId: number, file: File): Promise<Product> => {
    const body = new FormData();
    body.append('file', file);
    const res = await fetch(`${API_BASE_URL}${PRODUCT_PATH}/${productId}/image`, {
      method: 'POST',
      body,
    });
    if (!res.ok) throw new ApiError(`Upload failed with status ${res.status}`, res.status);
    return res.json() as Promise<Product>;
  },
};
