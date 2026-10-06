import { API_BASE_URL } from '../api/client';
import type { Product } from '../types/product';

const KEY_PREFIX = 'communityStore.productImage.';
const MAX_FILE_BYTES = 5 * 1024 * 1024;
const MAX_SIDE_PX = 800;

// Returns an error message, or null when the file is acceptable.
export function validateImageFile(file: File): string | null {
  if (!file.type.startsWith('image/')) return 'Please choose an image file (JPG, PNG, WebP...).';
  if (file.size > MAX_FILE_BYTES) return 'That picture is larger than 5 MB. Please choose a smaller one.';
  return null;
}

// Shrinks the picture and returns it as a JPEG data URL. Keeps the size small
// enough for browser storage and for fast loading on the store page.
export async function resizeImage(file: File): Promise<string> {
  const bitmap = await createImageBitmap(file);
  const scale = Math.min(1, MAX_SIDE_PX / Math.max(bitmap.width, bitmap.height));
  const canvas = document.createElement('canvas');
  canvas.width = Math.round(bitmap.width * scale);
  canvas.height = Math.round(bitmap.height * scale);
  const ctx = canvas.getContext('2d');
  if (!ctx) throw new Error('Canvas not supported');
  ctx.drawImage(bitmap, 0, 0, canvas.width, canvas.height);
  bitmap.close();
  return canvas.toDataURL('image/jpeg', 0.8);
}

// --- Browser-only demo storage -------------------------------------------
// Used when the backend has no upload endpoint yet. The picture lives in THIS
// browser only: other users and other devices will not see it.

export function getLocalImage(productId: number): string | undefined {
  try {
    return localStorage.getItem(KEY_PREFIX + productId) ?? undefined;
  } catch {
    return undefined;
  }
}

// Returns false if the browser refused to store it (storage full or blocked).
export function setLocalImage(productId: number, dataUrl: string): boolean {
  try {
    localStorage.setItem(KEY_PREFIX + productId, dataUrl);
    return true;
  } catch {
    return false;
  }
}

// The picture to display for a product: the backend's imageUrl when there is
// one, otherwise the browser-only demo copy.
export function productImageSrc(product: Pick<Product, 'productId' | 'imageUrl'>): string | undefined {
  const url = product.imageUrl?.trim();
  if (url) {
    // Uploaded files come back as a path on the backend, e.g. /CommunityStore/uploads/x.jpg.
    // Other paths (e.g. /images/products/...) are files served by the frontend itself.
    return url.startsWith('/CommunityStore/') ? `${API_BASE_URL}${url}` : url;
  }
  return getLocalImage(product.productId);
}
