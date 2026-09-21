import type { Product } from '../../types/product';
import ProductCard from './ProductCard';
import './store.css';

interface ProductGridProps {
  products: Product[];
  loading: boolean;
  error: string | null;
}

export default function ProductGrid({ products, loading, error }: ProductGridProps) {
  if (loading) {
    return (
      <div className="product-grid">
        {Array.from({ length: 6 }).map((_, i) => (
          <div key={i} className="skeleton" style={{ height: 176 }} />
        ))}
      </div>
    );
  }

  if (error) {
    return <div className="state-box error">{error}</div>;
  }

  if (products.length === 0) {
    return (
      <div className="state-box">No products found. Try a different search or filter.</div>
    );
  }

  return (
    <div className="product-grid">
      {products.map((product) => (
        <ProductCard key={product.productId} product={product} />
      ))}
    </div>
  );
}
