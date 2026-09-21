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
}
