import { useEffect, useState } from 'react';
import { productApi } from '../api/productApi';
import { categoryApi } from '../api/categoryApi';
import type { Product } from '../types/product';
import type { Category } from '../types/category';
import ProductGrid from '../components/store/ProductGrid';
import SearchBar from '../components/store/SearchBar';
import ProductFilter from '../components/store/ProductFilter';
import '../components/store/store.css';

export default function Store() {
  const [products, setProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const [categories, setCategories] = useState<Category[]>([]);
  const [categoriesLoading, setCategoriesLoading] = useState(true);
  const [selectedCategoryId, setSelectedCategoryId] = useState<number | null>(null);

  const [searchQuery, setSearchQuery] = useState('');

  useEffect(() => {
    categoryApi
      .getAll()
      .catch(() => [])
      .then((data) => setCategories(data ?? []))
      .finally(() => setCategoriesLoading(false));
  }, []);

  useEffect(() => {
    let cancelled = false;
    setLoading(true);
    setError(null);

    const request = searchQuery ? productApi.searchByName(searchQuery) : productApi.getAll();

    request
      .then((data) => {
        if (!cancelled) setProducts(data);
      })
      .catch(() => {
        if (!cancelled) setError("Couldn't load products. Please try again.");
      })
      .finally(() => {
        if (!cancelled) setLoading(false);
      });

    return () => {
      cancelled = true;
    };
  }, [searchQuery]);

  // Client-side only until the backend links products to categories.
  const visibleProducts = selectedCategoryId
    ? products.filter((p) => p.categoryId === selectedCategoryId)
    : products;

  return (
    <div className="store-page">
      <div className="store-header">
        <h1>Store</h1>
        <SearchBar initialValue={searchQuery} onSearch={setSearchQuery} />
      </div>

      <ProductFilter
        categories={categories}
        loading={categoriesLoading}
        selectedCategoryId={selectedCategoryId}
        onSelect={setSelectedCategoryId}
      />

      <ProductGrid products={visibleProducts} loading={loading} error={error} />
    </div>
  );
}
