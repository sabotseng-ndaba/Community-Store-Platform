import type { Category } from '../../types/category';
import './store.css';

interface ProductFilterProps {
  categories: Category[];
  loading: boolean;
  filterSupported: boolean;
  selectedCategoryId: number | null;
  onSelect: (categoryId: number | null) => void;
}

// The backend Product entity has no category reference and there is no
// "products by category" endpoint, so filterSupported is false until
// products start carrying a categoryId. Chips are then shown disabled.
export default function ProductFilter({
  categories,
  loading,
  filterSupported,
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
        disabled={!filterSupported}
        onClick={() => onSelect(null)}
      >
        All
      </button>
      {categories.map((category) => (
        <button
          key={category.categoryId}
          className={`chip${selectedCategoryId === category.categoryId ? ' active' : ''}`}
          title={
            filterSupported
              ? category.description
              : `${category.description} (filtering not available yet)`
          }
          disabled={!filterSupported}
          onClick={() => onSelect(category.categoryId)}
        >
          {category.categoryName}
        </button>
      ))}
    </div>
  );
}
