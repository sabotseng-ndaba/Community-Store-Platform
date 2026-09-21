import { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import { productApi } from '../api/productApi';
import { reviewApi } from '../api/reviewApi';
import type { Product } from '../types/product';
import type { Review } from '../types/review';
import ReviewList from '../components/store/ReviewList';
import ReviewForm from '../components/store/ReviewForm';
import '../components/store/store.css';

// TODO(auth integration): replace with the real auth/user context once
// it exists. Left as null so the review form correctly prompts sign-in
// rather than silently submitting reviews as a fake user.
const CURRENT_USER_ID: number | string | null = null;

export default function ProductDetails() {
  const { id } = useParams<{ id: string }>();
  const productId = Number(id);

  const [product, setProduct] = useState<Product | null>(null);
  const [productLoading, setProductLoading] = useState(true);
  const [productError, setProductError] = useState<string | null>(null);

  const [reviews, setReviews] = useState<Review[]>([]);
  const [reviewsLoading, setReviewsLoading] = useState(true);
  const [reviewsError, setReviewsError] = useState<string | null>(null);

  useEffect(() => {
    if (!Number.isFinite(productId)) return;
    setProductLoading(true);
    setProductError(null);
    productApi
      .getById(productId)
      .then(setProduct)
      .catch(() => setProductError("Couldn't load this product."))
      .finally(() => setProductLoading(false));
  }, [productId]);

  function loadReviews() {
    setReviewsLoading(true);
    setReviewsError(null);
    reviewApi
      .getByProduct(productId)
      .then(setReviews)
      .catch(() => setReviewsError("Couldn't load reviews."))
      .finally(() => setReviewsLoading(false));
  }

  useEffect(() => {
    if (!Number.isFinite(productId)) return;
    loadReviews();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [productId]);

  if (productLoading) {
    return (
      <div className="product-details">
        <div className="skeleton" style={{ height: 320 }} />
      </div>
    );
  }

  if (productError || !product) {
    return (
      <div className="product-details">
        <div className="state-box error">{productError ?? 'Product not found.'}</div>
        <Link to="/store" className="back-link">
          &larr; Back to store
        </Link>
      </div>
    );
  }

  return (
    <div className="product-details">
      <Link to="/store" className="back-link">
        &larr; Back to store
      </Link>

      <div className="product-details__layout">
        <div className="product-details__image">No image available</div>

        <div>
          <div className="product-details__title-row">
            <h1 style={{ margin: 0, fontSize: 28 }}>{product.productName}</h1>
            {product.ecoFriendly && (
              <span className="badge badge-eco">Eco-friendly</span>
            )}
          </div>

          {product.categoryName && (
            <p style={{ fontSize: 14, opacity: 0.7, marginTop: 8 }}>
              Category: {product.categoryName}
            </p>
          )}

          <p className="product-details__price">R{product.price.toFixed(2)}</p>

          <p style={{ fontSize: 15 }}>{product.description}</p>

          <dl className="spec-list">
            <dt>Condition</dt>
            <dd>{product.condition}</dd>

            <dt>Listing type</dt>
            <dd>{product.listingType}</dd>

            <dt>Availability</dt>
            <dd>
              {product.status}
              {product.quantity > 0 ? ` (${product.quantity} available)` : ''}
            </dd>

            <dt>Listed</dt>
            <dd>{product.dateCreated}</dd>
          </dl>
        </div>
      </div>

      <section className="reviews-section">
        <h2 style={{ fontSize: 20 }}>Reviews</h2>
        <ReviewList reviews={reviews} loading={reviewsLoading} error={reviewsError} />
        <ReviewForm productId={product.productId} currentUserId={CURRENT_USER_ID} onSubmitted={loadReviews} />
      </section>
    </div>
  );
}
