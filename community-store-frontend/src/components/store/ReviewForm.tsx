import { type FormEvent, useState } from 'react';
import { reviewApi } from '../../api/reviewApi';
import type { CreateReviewPayload } from '../../types/review';
import StarRating from './StarRating';
import './store.css';

interface ReviewFormProps {
  productId: number;
  // Supplied by whatever the team's auth/user-context module ends up
  // being. Passing null disables submission with a prompt to sign in,
  // rather than guessing at an auth shape that doesn't exist yet.
  currentUserId: number | string | null;
  onSubmitted: () => void;
}

export default function ReviewForm({ productId, currentUserId, onSubmitted }: ReviewFormProps) {
  const [rating, setRating] = useState(0);
  const [comment, setComment] = useState('');
  const [submitting, setSubmitting] = useState(false);
  const [error, setError] = useState<string | null>(null);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    if (!currentUserId) return;
    if (rating === 0) {
      setError('Please select a star rating.');
      return;
    }

    setSubmitting(true);
    setError(null);

    const payload: CreateReviewPayload = {
      reviewId: crypto.randomUUID(),
      product: { productId },
      user: { userId: currentUserId },
      ratings: rating,
      comment,
      reviewDate: new Date().toISOString().slice(0, 10),
    };

    try {
      await reviewApi.create(payload);
      setRating(0);
      setComment('');
      onSubmitted();
    } catch {
      setError("Couldn't submit your review. Please try again.");
    } finally {
      setSubmitting(false);
    }
  }

  if (!currentUserId) {
    return <p className="state-box">Sign in to leave a review.</p>;
  }

  return (
    <form onSubmit={handleSubmit} className="review-form">
      <div>
        <label>Your rating</label>
        <StarRating value={rating} onChange={setRating} />
      </div>

      <div>
        <label htmlFor="review-comment">Your review</label>
        <textarea
          id="review-comment"
          value={comment}
          onChange={(e) => setComment(e.target.value)}
          rows={3}
          required
          placeholder="What did you think of this product?"
        />
      </div>

      {error && <p className="form-error">{error}</p>}

      <button type="submit" className="btn btn-primary" disabled={submitting} style={{ alignSelf: 'flex-start' }}>
        {submitting ? 'Submitting…' : 'Submit review'}
      </button>
    </form>
  );
}
