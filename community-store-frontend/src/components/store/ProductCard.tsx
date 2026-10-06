import { Link } from 'react-router-dom';
import type { Product } from '../../types/product';
import ProductImage from './ProductImage';
import { productImageSrc } from '../../utils/productImages';
import './store.css';

interface ProductCardProps {
  product: Product;
}

export default function ProductCard({ product }: ProductCardProps) {
  const isAvailable = product.status?.toLowerCase() === 'available';

  return (
    <Link to={`/products/${product.productId}`} className="product-card">
      <ProductImage src={productImageSrc(product)} alt={product.productName} className="product-card__image" />

      <div className="product-card__top">
        <h3>{product.productName}</h3>
        {product.ecoFriendly && (
          <span className="badge badge-eco" title="Eco-friendly">
            Eco
          </span>
        )}
      </div>

      <p className="product-card__desc">{product.description}</p>

      <div className="product-card__bottom">
        <span className="product-card__price">R{product.price.toFixed(2)}</span>
        <span className={`badge badge-status${isAvailable ? ' available' : ''}`}>
          {product.status}
        </span>
      </div>

      <div className="product-card__meta">
        <span>{product.condition}</span>
        <span>&middot;</span>
        <span>{product.listingType}</span>
        {product.quantity <= 0 && (
          <>
            <span>&middot;</span>
            <span className="badge-oos">Out of stock</span>
          </>
        )}
      </div>
    </Link>
  );
}
