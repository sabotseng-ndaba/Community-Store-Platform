import { useState } from 'react';
import '../components/account/account.css';

export default function SellerProfile() {
  const [sellerId, setSellerId] = useState('');
  const [storeName, setStoreName] = useState('');
  const [storeDescription, setStoreDescription] = useState('');
  const [businessRegistrationNo, setBusinessRegistrationNo] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!sellerId.trim() || !storeName.trim() || !storeDescription.trim()) {
      setMessage('Please complete all required fields.');
      return;
    }

    setMessage('Seller profile details are ready to be submitted.');
  };

  return (
    <main className="seller-page">
      <div className="seller-container">
        <a href="/profile" className="seller-back-link">
          ← Back to profile
        </a>

        <header className="seller-header">
          <span className="account-label">SELLER ACCOUNT</span>
          <h1>Seller Profile</h1>
          <p>
            Set up your seller profile and start selling products in the
            Community Store.
          </p>
        </header>

        <div className="seller-layout">
          <section className="seller-form-card">
            <div className="seller-section-heading">
              <h2>Store Information</h2>
              <p>Tell the community about your store.</p>
            </div>

            <form className="account-form" onSubmit={handleSubmit}>
              <div className="form-group">
                <label htmlFor="sellerId">Seller ID</label>

                <input
                  id="sellerId"
                  type="number"
                  min="1"
                  placeholder="Enter your seller ID"
                  value={sellerId}
                  onChange={(event) => setSellerId(event.target.value)}
                />
              </div>

              <div className="form-group">
                <label htmlFor="storeName">Store name</label>

                <input
                  id="storeName"
                  type="text"
                  placeholder="Enter your store name"
                  value={storeName}
                  onChange={(event) => setStoreName(event.target.value)}
                />
              </div>

              <div className="form-group">
                <label htmlFor="storeDescription">Store description</label>

                <textarea
                  id="storeDescription"
                  placeholder="Tell customers about your store and what you sell"
                  value={storeDescription}
                  onChange={(event) => setStoreDescription(event.target.value)}
                  rows={5}
                />
              </div>

              <div className="form-group">
                <label htmlFor="businessRegistrationNo">
                  Business registration number
                  <span className="optional-text"> (optional)</span>
                </label>

                <input
                  id="businessRegistrationNo"
                  type="text"
                  placeholder="Enter registration number"
                  value={businessRegistrationNo}
                  onChange={(event) =>
                    setBusinessRegistrationNo(event.target.value)
                  }
                />
              </div>

              {message && <p className="form-message">{message}</p>}

              <button type="submit" className="account-primary-button">
                Create seller profile
              </button>
            </form>
          </section>

          <aside className="seller-status-card">
            <div className="seller-status-icon">◇</div>

            <h2>Seller Status</h2>

            <p>
              Your seller verification information will appear here once your
              profile has been created.
            </p>

            <div className="seller-status-row">
              <span>Verification</span>
              <strong className="verification-pending">Not verified</strong>
            </div>

            <div className="seller-status-row">
              <span>Created</span>
              <strong>Not created yet</strong>
            </div>
          </aside>
        </div>
      </div>
    </main>
  );
}