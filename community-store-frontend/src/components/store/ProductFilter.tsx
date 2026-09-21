import type { Category } from '../../types/category';
import './store.css';

interface ProductFilterProps {
  categories: Category[];
  loading: boolean;
  selectedCategoryId: number | null;
  onSelect: (categoryId: number | null) => void;
}

// NOTE: the current backend Product entity has no category reference, and
// there is no "products by category" endpoint. Selecting a category here
// does not yet filter the product grid — wire that up once the backend
// exposes the relationship (e.g. Product.categoryId + a
// GET /products/category/{id} endpoint, mirroring findByStatus).
export default function ProductFilter({
  categories,
  loading,
  selectedCategoryId,
  onSelect,
}: ProductFilterProps) {
  if (loading) {
    return (
      <div className="category-filter">
        {Array.from({ length: 4 }).map((_, i) => (
          <span key={i} className="chip skeleton">
            placeholder
          </span>
        ))}
      </div>
    );
  }

  if (categories.length === 0) return null;

  return (
    <div className="category-filter">
      <button
        className={`chip${selectedCategoryId === null ? ' active' : ''}`}
        onClick={() => onSelect(null)}
      >
        All
      </button>
      {categories.map((category) => (
        <button
          key={category.categoryId}
          className={`chip${selectedCategoryId === category.categoryId ? ' active' : ''}`}
          title={category.description}
          onClick={() => onSelect(category.categoryId)}
        >
          {category.categoryName}
        </button>
      ))}
    </div>
  );
}
