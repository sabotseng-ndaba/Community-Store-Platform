import './store.css';

interface StarRatingProps {
  value: number;
  max?: number;
  onChange?: (value: number) => void;
  size?: 'sm' | 'md';
}

// Read-only by default; pass onChange to make it an interactive picker
// (used in the review submission form).
export default function StarRating({
  value,
  max = 5,
  onChange,
  size = 'md',
}: StarRatingProps) {
  const stars = Array.from({ length: max }, (_, i) => i + 1);
  const interactive = Boolean(onChange);

  return (
    <div
      className={`star-rating${size === 'sm' ? ' sm' : ''}`}
      role={interactive ? 'radiogroup' : 'img'}
      aria-label={`Rating: ${value} out of ${max}`}
    >
      {stars.map((star) => {
        const filled = star <= Math.round(value);
        return (
          <button
            key={star}
            type="button"
            disabled={!interactive}
            onClick={() => onChange?.(star)}
            aria-label={`${star} star${star > 1 ? 's' : ''}`}
          >
            {filled ? '★' : '☆'}
          </button>
        );
      })}
    </div>
  );
}
