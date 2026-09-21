import { type FormEvent, useState } from 'react';
import './store.css';

interface SearchBarProps {
  initialValue?: string;
  onSearch: (query: string) => void;
  placeholder?: string;
}

export default function SearchBar({
  initialValue = '',
  onSearch,
  placeholder = 'Search products…',
}: SearchBarProps) {
  const [value, setValue] = useState(initialValue);

  function handleSubmit(e: FormEvent) {
    e.preventDefault();
    onSearch(value.trim());
  }

  return (
    <form onSubmit={handleSubmit} className="search-bar">
      <input
        type="text"
        value={value}
        onChange={(e) => setValue(e.target.value)}
        placeholder={placeholder}
        aria-label="Search products"
      />
      <button type="submit" className="btn btn-primary">
        Search
      </button>
      {value && (
        <button
          type="button"
          className="btn btn-secondary"
          onClick={() => {
            setValue('');
            onSearch('');
          }}
        >
          Clear
        </button>
      )}
    </form>
  );
}
