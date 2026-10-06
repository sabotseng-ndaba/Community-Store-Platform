import { type FormEvent, useState } from 'react';
import type { Product } from '../../types/product';
import ProductImage from './ProductImage';
import { productImageSrc, resizeImage, validateImageFile } from '../../utils/productImages';
import { SAMPLE_IMAGES } from '../../utils/sampleImages';
import './store.css';

export interface ProductFormValues {
  productName: string;
  description: string;
  price: number;
  quantity: number;
  condition: string;
  listingType: string;
  ecoFriendly: boolean;
  status: string;
  imageUrl?: string;
  // A picture chosen from the device. When set, it replaces any pasted link.
  imageFile?: File;
}

interface ProductFormProps {
  // Present when editing an existing listing.
  initial?: Product;
  // Suggestions for the free-text fields, taken from existing listings so
  // new ones stay consistent with what is already in the store.
  conditionOptions: string[];
  listingTypeOptions: string[];
  statusOptions: string[];
  submitLabel: string;
  submitting: boolean;
  error: string | null;
  onSubmit: (values: ProductFormValues) => void;
}

export default function ProductForm({
  initial,
  conditionOptions,
  listingTypeOptions,
  statusOptions,
  submitLabel,
  submitting,
  error,
  onSubmit,
}: ProductFormProps) {
  const [productName, setProductName] = useState(initial?.productName ?? '');
  const [description, setDescription] = useState(initial?.description ?? '');
  const [price, setPrice] = useState(initial ? String(initial.price) : '');
  const [quantity, setQuantity] = useState(initial ? String(initial.quantity) : '1');
  const [condition, setCondition] = useState(initial?.condition ?? '');
  const [listingType, setListingType] = useState(initial?.listingType ?? '');
  const [status, setStatus] = useState(initial?.status ?? 'Available');
  const [imageUrl, setImageUrl] = useState(initial?.imageUrl ?? '');
  const [ecoFriendly, setEcoFriendly] = useState(initial?.ecoFriendly ?? false);
  const [imageFile, setImageFile] = useState<File | null>(null);
  const [filePreview, setFilePreview] = useState<string | null>(null);
  const [validation, setValidation] = useState<string | null>(null);

  async function handleFileChange(file: File | undefined) {
    if (!file) {
      setImageFile(null);
      setFilePreview(null);
      return;
    }
    const problem = validateImageFile(file);
    if (problem) {
      setValidation(problem);
      return;
    }
    try {
      setFilePreview(await resizeImage(file));
      setImageFile(file);
      setValidation(null);
    } catch {
      setValidation("Couldn't read that picture. Please try a different file.");
    }
  }

  function handleSubmit(e: FormEvent) {
    e.preventDefault();

    const priceNum = Number(price);
    const quantityNum = Number(quantity);

    if (!productName.trim() || !description.trim()) {
      setValidation('Please enter a name and a description.');
      return;
    }
    if (!Number.isFinite(priceNum) || priceNum < 0) {
      setValidation('Price must be zero or more.');
      return;
    }
    if (!Number.isInteger(quantityNum) || quantityNum < 0) {
      setValidation('Quantity must be a whole number, zero or more.');
      return;
    }
    if (!condition.trim() || !listingType.trim() || !status.trim()) {
      setValidation('Please fill in condition, listing type and status.');
      return;
    }

    const trimmedUrl = imageUrl.trim();
    if (!imageFile && trimmedUrl && !/^(https?:\/\/|\/images\/)/i.test(trimmedUrl)) {
      setValidation('Image link must start with http:// or https://');
      return;
    }

    setValidation(null);
    onSubmit({
      productName: productName.trim(),
      description: description.trim(),
      price: priceNum,
      quantity: quantityNum,
      condition: condition.trim(),
      listingType: listingType.trim(),
      ecoFriendly,
      status: status.trim(),
      imageUrl: imageFile ? undefined : trimmedUrl || undefined,
      imageFile: imageFile ?? undefined,
    });
  }

  return (
    <form onSubmit={handleSubmit} className="product-form">
      <div>
        <label htmlFor="pf-name">Item name</label>
        <input id="pf-name" type="text" value={productName} onChange={(e) => setProductName(e.target.value)} />
      </div>

      <div>
        <label htmlFor="pf-desc">Description</label>
        <textarea id="pf-desc" rows={4} value={description} onChange={(e) => setDescription(e.target.value)} />
      </div>

      <div className="product-form__row">
        <div>
          <label htmlFor="pf-price">Price (R)</label>
          <input id="pf-price" type="number" min="0" step="0.01" value={price} onChange={(e) => setPrice(e.target.value)} />
        </div>
        <div>
          <label htmlFor="pf-qty">Quantity</label>
          <input id="pf-qty" type="number" min="0" step="1" value={quantity} onChange={(e) => setQuantity(e.target.value)} />
        </div>
      </div>

      <div className="product-form__row">
        <div>
          <label htmlFor="pf-condition">Condition</label>
          <input id="pf-condition" type="text" list="pf-condition-list" value={condition} onChange={(e) => setCondition(e.target.value)} />
          <datalist id="pf-condition-list">
            {conditionOptions.map((o) => (
              <option key={o} value={o} />
            ))}
          </datalist>
        </div>
        <div>
          <label htmlFor="pf-listing">Listing type</label>
          <input id="pf-listing" type="text" list="pf-listing-list" value={listingType} onChange={(e) => setListingType(e.target.value)} />
          <datalist id="pf-listing-list">
            {listingTypeOptions.map((o) => (
              <option key={o} value={o} />
            ))}
          </datalist>
        </div>
      </div>

      <div>
        <label htmlFor="pf-status">Status</label>
        <input id="pf-status" type="text" list="pf-status-list" value={status} onChange={(e) => setStatus(e.target.value)} />
        <datalist id="pf-status-list">
          {statusOptions.map((o) => (
            <option key={o} value={o} />
          ))}
        </datalist>
      </div>

      <div>
        <label htmlFor="pf-file">Picture from your device (optional)</label>
        <input
          id="pf-file"
          type="file"
          accept="image/*"
          onChange={(e) => void handleFileChange(e.target.files?.[0])}
        />
      </div>

      <div>
        <label htmlFor="pf-sample">Or choose a sample picture (optional)</label>
        <select
          id="pf-sample"
          value={SAMPLE_IMAGES.some((g) => g.images.some((i) => i.src === imageUrl)) ? imageUrl : ''}
          onChange={(e) => {
            setImageUrl(e.target.value);
            setImageFile(null);
            setFilePreview(null);
          }}
        >
          <option value="">None</option>
          {SAMPLE_IMAGES.map((group) => (
            <optgroup key={group.category} label={group.category}>
              {group.images.map((img) => (
                <option key={img.src} value={img.src}>
                  {img.label}
                </option>
              ))}
            </optgroup>
          ))}
        </select>
      </div>

      <div>
        <label htmlFor="pf-image">Or paste an image link (optional)</label>
        <input
          id="pf-image"
          type="text"
          placeholder="https://..."
          value={imageUrl}
          onChange={(e) => setImageUrl(e.target.value)}
        />
        <ProductImage
          src={filePreview ?? (imageUrl.trim() || (initial ? productImageSrc(initial) : undefined))}
          alt="Preview"
          className="product-form__preview"
        />
      </div>

      <div className="product-form__check">
        <input id="pf-eco" type="checkbox" checked={ecoFriendly} onChange={(e) => setEcoFriendly(e.target.checked)} />
        <label htmlFor="pf-eco">Eco-friendly</label>
      </div>

      {(validation || error) && <p className="form-error">{validation ?? error}</p>}

      <button type="submit" className="btn btn-primary" disabled={submitting} style={{ alignSelf: 'flex-start' }}>
        {submitting ? 'Saving…' : submitLabel}
      </button>
    </form>
  );
}
