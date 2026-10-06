import { apiDelete, apiGet, apiPost, apiPut } from './client';
import type { CreateReviewPayload, Review } from '../types/review';

// Wraps za.ac.cput.communitystoreplatform.controller.ReviewController
// The backend runs under server.servlet.context-path=/CommunityStore.
const REVIEW_PATH = '/CommunityStore/reviews';

export const reviewApi = {
  getAll: () => apiGet<Review[]>(`${REVIEW_PATH}/getAll`),

  getById: (reviewId: string) =>
    apiGet<Review>(`${REVIEW_PATH}/read/${encodeURIComponent(reviewId)}`),

  create: (payload: CreateReviewPayload) =>
    apiPost<Review>(`${REVIEW_PATH}/create`, payload),

  update: (payload: CreateReviewPayload) =>
    apiPut<Review>(`${REVIEW_PATH}/update`, payload),

  delete: (reviewId: string) =>
    apiDelete<boolean>(`${REVIEW_PATH}/delete/${encodeURIComponent(reviewId)}`),

  // The backend has no "reviews by product" endpoint yet, so we fetch
  // everything and filter client-side. Swap this for a dedicated endpoint
  // (e.g. GET /reviews/product/{productId}) once one exists.
  getByProduct: async (productId: number): Promise<Review[]> => {
    const all = await reviewApi.getAll();
    return all.filter((r) => r.product?.productId === productId);
  },
};
