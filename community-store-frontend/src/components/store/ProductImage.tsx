import { useState } from 'react';
import './store.css';

interface ProductImageProps {
  src?: string;
  alt: string;
  className?: string;
}

// Shows the product image, or a placeholder when there is no URL or the
// image fails to load. Remounted per src so a corrected URL gets a fresh try.
export default function ProductImage({ src, alt, className = '' }: ProductImageProps) {
  return <ProductImageInner key={src ?? ''} src={src} alt={alt} className={className} />;
}

function ProductImageInner({ src, alt, className }: Required<Pick<ProductImageProps, 'alt' | 'className'>> & { src?: string }) {
  const [failed, setFailed] = useState(false);

  if (!src || failed) {
    return <div className={`product-image product-image--empty ${className}`}>No image available</div>;
  }

  return (
    <img
      className={`product-image ${className}`}
      src={src}
      alt={alt}
      loading="lazy"
      onError={() => setFailed(true)}
    />
  );
}
