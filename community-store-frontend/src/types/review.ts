// Mirrors za.ac.cput.communitystoreplatform.domain.Review
// The backend stores nested Product/User entities. For reads we only rely
// on the ids/names we actually display; for writes we send back minimal
// { productId } / { userId } wrapper objects, which is what Spring Data
// JPA expects when populating a @ManyToOne from JSON.

export interface ReviewProductRef {
  productId: number;
  productName?: string;
}

export interface ReviewUserRef {
  userId: number | string;
  name?: string;
  username?: string;
}

export interface Review {
  reviewId: string;
  product: ReviewProductRef;
  user: ReviewUserRef;
  ratings: number;
  comment: string;
  reviewDate: string; // ISO date string
}

// Shape sent to POST /reviews/create
export interface CreateReviewPayload {
  reviewId: string;
  product: { productId: number };
  user: { userId: number | string };
  ratings: number;
  comment: string;
  reviewDate: string;
}
