import { apiDelete, apiGet, apiPost, apiPut } from './client';
import type { CreateReviewPayload, Review } from '../types/review';

// Wraps za.ac.cput.communitystoreplatform.controller.ReviewController
export const reviewApi = {
  getAll: () => apiGet<Review[]>('/reviews/getAll'),

  getById: (reviewId: string) =>
    apiGet<Review>(`/reviews/read/${encodeURIComponent(reviewId)}`),

  create: (payload: CreateReviewPayload) =>
    apiPost<Review>('/reviews/create', payload),

  update: (payload: CreateReviewPayload) =>
    apiPut<Review>('/reviews/update', payload),

  delete: (reviewId: string) =>
    apiDelete<boolean>(`/reviews/delete/${encodeURIComponent(reviewId)}`),

  // The backend has no "reviews by product" endpoint yet, so we fetch
  // everything and filter client-side. Swap this for a dedicated endpoint
  // (e.g. GET /reviews/product/{productId}) once one exists.
  getByProduct: async (productId: number): Promise<Review[]> => {
    const all = await reviewApi.getAll();
    return all.filter((r) => r.product?.productId === productId);
  },
};
