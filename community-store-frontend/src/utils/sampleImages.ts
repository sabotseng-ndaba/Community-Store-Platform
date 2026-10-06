// 18 sample product photos that ship with the frontend (public/images/products).
// They are only for demos and for filling the "sample picture" dropdown in the
// sell form. Categories here are just for grouping the dropdown.
export interface SampleImage {
  label: string;
  src: string;
}

export interface SampleGroup {
  category: string;
  images: SampleImage[];
}

const p = (name: string) => `/images/products/${name}.webp`;

export const SAMPLE_IMAGES: SampleGroup[] = [
  {
    category: 'Electronics',
    images: [
      { label: 'Gaming laptop', src: p('electronics-laptop-gaming') },
      { label: 'Laptop (black)', src: p('electronics-laptop-black') },
      { label: 'Laptop (blue)', src: p('electronics-laptop-blue') },
      { label: 'Headphones', src: p('electronics-headphones') },
    ],
  },
  {
    category: 'Clothing',
    images: [
      { label: 'Hoodie (grey)', src: p('clothing-hoodie-grey') },
      { label: 'Hoodie (cream)', src: p('clothing-hoodie-cream') },
      { label: 'Shirt (cream)', src: p('clothing-shirt-cream') },
    ],
  },
  {
    category: 'Bags',
    images: [
      { label: 'Backpack (black)', src: p('bags-backpack-black') },
      { label: 'Backpack (cream)', src: p('bags-backpack-cream') },
      { label: 'Backpack (leather)', src: p('bags-backpack-leather') },
    ],
  },
  {
    category: 'Kitchen & Home',
    images: [
      { label: 'Kettle (black)', src: p('kitchen-kettle-black') },
      { label: 'Kettle (glass)', src: p('kitchen-kettle-glass') },
      { label: 'Kettle (steel)', src: p('kitchen-kettle-steel') },
      { label: 'Mugs (navy)', src: p('kitchen-mugs-navy') },
      { label: 'Mugs (patterned)', src: p('kitchen-mugs-patterned') },
      { label: 'Mugs (white)', src: p('kitchen-mugs-white') },
      { label: 'Cookware set', src: p('kitchen-cookware-set') },
      { label: 'Potted plant', src: p('home-plant') },
    ],
  },
];
