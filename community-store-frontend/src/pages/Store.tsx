import { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import { productApi } from '../api/productApi';
import { categoryApi } from '../api/categoryApi';
import type { Product } from '../types/product';
import type { Category } from '../types/category';
import ProductGrid from '../components/store/ProductGrid';
import SearchBar from '../components/store/SearchBar';
import ProductFilter from '../components/store/ProductFilter';
import '../components/store/store.css';

export default function Store() {
  const [searchQuery, setSearchQuery] = useState('');
  const [result, setResult] = useState<{
    query: string;
    products: Product[];
    error: string | null;
  } | null>(null);

  const [categories, setCategories] = useState<Category[]>([]);
  const [categoriesLoading, setCategoriesLoading] = useState(true);
  const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null);

  useEffect(() => {
    categoryApi
      .getAll()
      .catch(() => [])
      .then((data) => setCategories(data ?? []))
      .finally(() => setCategoriesLoading(false));
  }, []);

  useEffect(() => {
    let cancelled = false;

    const request = searchQuery ? productApi.searchByName(searchQuery) : productApi.getAll();

    request
      .then((data) => {
        if (!cancelled) setResult({ query: searchQuery, products: data, error: null });
      })
      .catch(() => {
        if (!cancelled)
          setResult({
            query: searchQuery,
            products: [],
            error: "Couldn't load products. Please try again.",
          });
      });

    return () => {
      cancelled = true;
    };
  }, [searchQuery]);

  // Still loading until the result on screen belongs to the current search.
  const loading = result === null || result.query !== searchQuery;
  const products = result?.products ?? [];
  const error = loading ? null : result.error;

  // The backend Product entity has no category link yet, so filtering is only
  // enabled once products actually carry a categoryId. Until then the
  // categories are shown but selecting one must not empty the grid.
  const categoryFilterSupported = products.some((p) => p.categoryId != null);

  const visibleProducts =
    categoryFilterSupported && selectedCategoryId
      ? products.filter((p) => p.categoryId === selectedCategoryId)
      : products;

  return (
    <div className="store-page">
      <div className="store-header">
        <h1>Store</h1>
        <SearchBar initialValue={searchQuery} onSearch={setSearchQuery} />
        <Link to="/sell" className="btn btn-primary">
          Sell an item
        </Link>
      </div>

      <ProductFilter
        categories={categories}
        loading={categoriesLoading}
        filterSupported={categoryFilterSupported}
        selectedCategoryId={selectedCategoryId}
        onSelect={setSelectedCategoryId}
      />

      <ProductGrid products={visibleProducts} loading={loading} error={error} />
    </div>
  );
}
