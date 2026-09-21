import type { Review } from '../../types/review';
import StarRating from './StarRating';
import './store.css';

interface ReviewListProps {
  reviews: Review[];
  loading: boolean;
  error: string | null;
}

export default function ReviewList({ reviews, loading, error }: ReviewListProps) {
  if (loading) {
    return (
      <div style={{ display: 'flex', flexDirection: 'column', gap: 12, marginBottom: 24 }}>
        {Array.from({ length: 3 }).map((_, i) => (
          <div key={i} className="skeleton" style={{ height: 64 }} />
        ))}
      </div>
    );
  }

  if (error) {
    return <div className="state-box error">{error}</div>;
  }

  if (reviews.length === 0) {
    return <p style={{ fontSize: 14, opacity: 0.7 }}>No reviews yet. Be the first to share your thoughts.</p>;
  }

  const average = reviews.reduce((sum, r) => sum + r.ratings, 0) / reviews.length;

  return (
    <div>
      <div className="reviews-summary">
        <StarRating value={average} />
        <span>
          {average.toFixed(1)} out of 5 &middot; {reviews.length} review{reviews.length === 1 ? '' : 's'}
        </span>
      </div>

      <ul className="review-list">
        {reviews.map((review) => (
          <li key={review.reviewId} className="review-item">
            <div className="review-item__top">
              <span className="review-item__author">
                {review.user?.name ?? review.user?.username ?? 'Anonymous'}
              </span>
              <span className="review-item__date">{review.reviewDate}</span>
            </div>
            <StarRating value={review.ratings} size="sm" />
            <p className="review-item__comment">{review.comment}</p>
          </li>
        ))}
      </ul>
    </div>
  );
}
