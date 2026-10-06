import { useEffect, useState } from 'react';
import { Link, useLocation, useParams } from 'react-router-dom';
import { productApi } from '../api/productApi';
import { reviewApi } from '../api/reviewApi';
import type { Product } from '../types/product';
import type { Review } from '../types/review';
import ReviewList from '../components/store/ReviewList';
import ReviewForm from '../components/store/ReviewForm';
import ProductImage from '../components/store/ProductImage';
import { productImageSrc } from '../utils/productImages';
import '../components/store/store.css';

// TODO(auth integration): replace with the real auth/user context once
// it exists. Left as null so the review form correctly prompts sign-in
// rather than silently submitting reviews as a fake user.
const CURRENT_USER_ID: string | null = null;

export default function ProductDetails() {
  const { id } = useParams<{ id: string }>();
  // key remounts the content when the product changes, resetting its state.
  return <ProductDetailsContent key={id} productId={Number(id)} />;
}

function ProductDetailsContent({ productId }: { productId: number }) {
  const invalidId = !Number.isFinite(productId);
  const location = useLocation();
  const demoImageNote = (location.state as { demoImage?: boolean } | null)?.demoImage === true;

  const [product, setProduct] = useState<Product | null>(null);
  const [productLoading, setProductLoading] = useState(true);
  const [productError, setProductError] = useState<string | null>(null);

  const [reviews, setReviews] = useState<Review[]>([]);
  const [reviewsLoading, setReviewsLoading] = useState(true);
  const [reviewsError, setReviewsError] = useState<string | null>(null);

  const [reviewsVersion, setReviewsVersion] = useState(0);

  useEffect(() => {
    if (invalidId) return;
    let cancelled = false;
    productApi
      .getById(productId)
      .then((data) => {
        if (!cancelled) setProduct(data);
      })
      .catch(() => {
        if (!cancelled) setProductError("Couldn't load this product.");
      })
      .finally(() => {
        if (!cancelled) setProductLoading(false);
      });
    return () => {
      cancelled = true;
    };
  }, [productId, invalidId]);

  useEffect(() => {
    if (invalidId) return;
    let cancelled = false;
    reviewApi
      .getByProduct(productId)
      .then((data) => {
        if (!cancelled) {
          setReviews(data);
          setReviewsError(null);
        }
      })
      .catch(() => {
        if (!cancelled) setReviewsError("Couldn't load reviews.");
      })
      .finally(() => {
        if (!cancelled) setReviewsLoading(false);
      });
    return () => {
      cancelled = true;
    };
  }, [productId, invalidId, reviewsVersion]);

  if (invalidId) {
    return (
      <div className="product-details">
        <div className="state-box error">Invalid product link.</div>
        <Link to="/store" className="back-link">
          &larr; Back to store
        </Link>
      </div>
    );
  }

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

      {demoImageNote && (
        <div className="state-box">
          The picture was saved in this browser only, because the server can't store uploads yet.
          Other people won't see it.
        </div>
      )}

      <div className="product-details__layout">
        <ProductImage
          src={productImageSrc(product)}
          alt={product.productName}
          className="product-details__image"
        />

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

          <Link to={`/sell/${product.productId}`} className="btn btn-secondary edit-link">
            Edit listing
          </Link>

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
        <ReviewForm productId={product.productId} currentUserId={CURRENT_USER_ID} onSubmitted={() => setReviewsVersion((v) => v + 1)} />
      </section>
    </div>
  );
}
