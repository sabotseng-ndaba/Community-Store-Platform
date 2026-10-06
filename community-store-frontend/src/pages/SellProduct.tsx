import { useEffect, useState } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { productApi } from '../api/productApi';
import type { Product, ProductPayload } from '../types/product';
import ProductForm, { type ProductFormValues } from '../components/store/ProductForm';
import { resizeImage, setLocalImage } from '../utils/productImages';
import '../components/store/store.css';

// Used only to suggest values in the form; the user can type anything.
const DEFAULT_CONDITIONS = ['New', 'Like new', 'Good', 'Fair'];
const DEFAULT_STATUSES = ['Available', 'Sold'];

function unique(values: string[]): string[] {
  return Array.from(new Set(values.filter(Boolean)));
}

export default function SellProduct() {
  const { id } = useParams<{ id: string }>();
  // key remounts the content when switching between create and edit.
  return <SellProductContent key={id ?? 'new'} productId={id === undefined ? null : Number(id)} />;
}

// TODO(auth integration): the backend Product has no seller field and there
// is no logged-in user yet, so anyone can create or edit a listing and
// listings are not tied to a seller. Revisit once FE-01 auth and a
// seller link on Product exist.
function SellProductContent({ productId }: { productId: number | null }) {
  const navigate = useNavigate();
  const editing = productId !== null;
  const invalidId = productId !== null && !Number.isFinite(productId);

  const [existing, setExisting] = useState<Product | null>(null);
  const [allProducts, setAllProducts] = useState<Product[]>([]);
  const [loading, setLoading] = useState(!invalidId);
  const [loadError, setLoadError] = useState<string | null>(null);

  const [submitting, setSubmitting] = useState(false);
  const [submitError, setSubmitError] = useState<string | null>(null);

  useEffect(() => {
    if (invalidId) return;
    let cancelled = false;

    Promise.all([
      productApi.getAll(),
      productId !== null ? productApi.getById(productId) : Promise.resolve(null),
    ])
      .then(([list, product]) => {
        if (cancelled) return;
        setAllProducts(list);
        setExisting(product);
      })
      .catch(() => {
        if (!cancelled) setLoadError("Couldn't load the listing form. Please try again.");
      })
      .finally(() => {
        if (!cancelled) setLoading(false);
      });

    return () => {
      cancelled = true;
    };
  }, [productId, invalidId]);

  async function handleSubmit(values: ProductFormValues) {
    setSubmitting(true);
    setSubmitError(null);
    const today = new Date().toISOString().slice(0, 10);

    try {
      let saved: Product;
      const { imageFile, ...productValues } = values;

      if (existing) {
        const payload: ProductPayload = {
          productId: existing.productId,
          ...productValues,
          dateCreated: existing.dateCreated,
          dateUpdated: today,
        };
        saved = await productApi.update(payload);
      } else {
        // The backend does not generate product ids (plain @Id int), so
        // take the next one after the highest id currently in the store.
        const latest = await productApi.getAll();
        const nextId = latest.reduce((max, p) => Math.max(max, p.productId), 0) + 1;
        const payload: ProductPayload = {
          productId: nextId,
          ...productValues,
          dateCreated: today,
          dateUpdated: today,
        };
        saved = await productApi.create(payload);
      }

      // Picture from the device: try the server first; if it has no upload
      // endpoint yet, keep a demo copy in this browser only.
      let demoImage = false;
      if (imageFile) {
        try {
          await productApi.uploadImage(saved.productId, imageFile);
        } catch {
          const stored = setLocalImage(saved.productId, await resizeImage(imageFile));
          if (!stored) throw new Error('storage');
          demoImage = true;
        }
      }

      navigate(`/products/${saved.productId}`, { state: { demoImage } });
    } catch {
      setSubmitError("Couldn't save the listing. Please try again.");
      setSubmitting(false);
    }
  }

  const back = (
    <Link to={editing && !invalidId ? `/products/${productId}` : '/store'} className="back-link">
      &larr; Back
    </Link>
  );

  if (invalidId) {
    return (
      <div className="sell-page">
        <div className="state-box error">Invalid listing link.</div>
        {back}
      </div>
    );
  }

  if (loading) {
    return (
      <div className="sell-page">
        <div className="skeleton" style={{ height: 320 }} />
      </div>
    );
  }

  if (loadError || (editing && !existing)) {
    return (
      <div className="sell-page">
        <div className="state-box error">{loadError ?? 'Listing not found.'}</div>
        {back}
      </div>
    );
  }

  return (
    <div className="sell-page">
      {back}
      <h1 style={{ fontSize: 28, margin: '16px 0 24px' }}>{editing ? 'Edit listing' : 'Sell an item'}</h1>
      <ProductForm
        initial={existing ?? undefined}
        conditionOptions={unique([...DEFAULT_CONDITIONS, ...allProducts.map((p) => p.condition)])}
        listingTypeOptions={unique(allProducts.map((p) => p.listingType))}
        statusOptions={unique([...DEFAULT_STATUSES, ...allProducts.map((p) => p.status)])}
        submitLabel={editing ? 'Save changes' : 'List item'}
        submitting={submitting}
        error={submitError}
        onSubmit={handleSubmit}
      />
    </div>
  );
}
