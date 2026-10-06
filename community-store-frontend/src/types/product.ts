// Mirrors za.ac.cput.communitystoreplatform.domain.Product
// NOTE: the backend Product entity (as provided) has no category reference.
// categoryId/categoryName are included here as optional so the UI is ready
// the moment the backend adds that relationship, but nothing currently
// populates them.
export interface Product {
  productId: number;
  productName: string;
  description: string;
  price: number;
  quantity: number;
  condition: string;
  listingType: string;
  ecoFriendly: boolean;
  status: string;
  dateCreated: string; // ISO date string, e.g. "2026-09-20"
  dateUpdated: string;
  categoryId?: number;
  categoryName?: string;
  // The backend Product has no image field yet. Optional so the UI is ready
  // as soon as it adds one (expected name: imageUrl).
  imageUrl?: string;
}

// Shape sent to POST /products/create and PUT /products/update.
// Mirrors the backend entity exactly (no category fields).
export interface ProductPayload {
  productId: number;
  productName: string;
  description: string;
  price: number;
  quantity: number;
  condition: string;
  listingType: string;
  ecoFriendly: boolean;
  status: string;
  dateCreated: string;
  dateUpdated: string;
  imageUrl?: string;
}
