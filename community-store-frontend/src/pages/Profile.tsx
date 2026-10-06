import { useState } from 'react';
import '../components/account/account.css';

export default function Profile() {
  const [showAddressForm, setShowAddressForm] = useState(false);

  const [addressLine, setAddressLine] = useState('');
  const [city, setCity] = useState('');
  const [province, setProvince] = useState('');
  const [postalCode, setPostalCode] = useState('');
  const [showVerificationForm, setShowVerificationForm] = useState(false);
  const [verificationType, setVerificationType] = useState('');
  const [verificationDocument, setVerificationDocument] = useState('');
  const [showNotifications, setShowNotifications] = useState(false);
  const [isEditingProfile, setIsEditingProfile] = useState(false);
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [altPhone, setAltPhone] = useState('');
  return (
    <main className="profile-page">
      <div className="profile-container">

        <header className="profile-header">
        <div>
        <span className="account-label">MY ACCOUNT</span>
        <h1>My Profile</h1>
          <p>Manage your Community Store account and personal information.</p>
         </div>
        </header>

        <div className="profile-grid">

          {/* User summary */}
        <section className="profile-card profile-summary-card">
        <div className="profile-avatar">
        {firstName || lastName
        ? `${firstName.charAt(0)}${lastName.charAt(0)}`.toUpperCase()
        : 'U'}
        </div>

        <div>
        <h2>
         {firstName || lastName
        ? `${firstName} ${lastName}`.trim()
        : 'Community Store User'}
         </h2>

          <p>{email || 'Complete your profile information'}</p>
           </div>

           <span className="profile-status">
           Active
          </span>
          </section>

          {/* Personal Information */}
    <section className="profile-card profile-info-card">
      <div className="profile-card-header">
      <div>
      <h2>Personal Information</h2>
      <p>Manage your personal and contact details.</p>
      </div>

      {!isEditingProfile && (
      <button
        type="button"
        className="profile-edit-button"
        onClick={() => setIsEditingProfile(true)}
      >
        Edit profile
      </button>
      )}
      </div>

    {!isEditingProfile ? (
    <div className="profile-details-grid">
      <div className="profile-detail">
        <span>First name</span>
        <strong>{firstName || 'Not provided'}</strong>
      </div>

      <div className="profile-detail">
        <span>Last name</span>
        <strong>{lastName || 'Not provided'}</strong>
      </div>

      <div className="profile-detail">
        <span>Email address</span>
        <strong>{email || 'Not provided'}</strong>
      </div>

      <div className="profile-detail">
        <span>Phone number</span>
        <strong>{phone || 'Not provided'}</strong>
      </div>

      <div className="profile-detail">
        <span>Alternative phone</span>
        <strong>{altPhone || 'Not provided'}</strong>
      </div>
      </div>
      ) : (
      <form
      className="profile-edit-form"
      onSubmit={(event) => {
        event.preventDefault();

        // userApi.update() will be connected later.
        setIsEditingProfile(false);
      }}
       >
      <div className="profile-edit-grid">
        <div className="form-group">
          <label htmlFor="profileFirstName">First name</label>
          <input
            id="profileFirstName"
            type="text"
            value={firstName}
            onChange={(event) => setFirstName(event.target.value)}
            placeholder="Enter your first name"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="profileLastName">Last name</label>
          <input
            id="profileLastName"
            type="text"
            value={lastName}
            onChange={(event) => setLastName(event.target.value)}
            placeholder="Enter your last name"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="profileEmail">Email address</label>
          <input
            id="profileEmail"
            type="email"
            value={email}
            onChange={(event) => setEmail(event.target.value)}
            placeholder="Enter your email address"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="profilePhone">Phone number</label>
          <input
            id="profilePhone"
            type="tel"
            value={phone}
            onChange={(event) => setPhone(event.target.value)}
            placeholder="Enter your phone number"
            required
          />
        </div>

        <div className="form-group">
          <label htmlFor="profileAltPhone">
            Alternative phone
            <span className="optional-text"> (optional)</span>
          </label>

          <input
            id="profileAltPhone"
            type="tel"
            value={altPhone}
            onChange={(event) => setAltPhone(event.target.value)}
            placeholder="Enter an alternative number"
          />
        </div>
      </div>

      <div className="profile-edit-actions">
        <button
          type="button"
          className="profile-secondary-button"
          onClick={() => setIsEditingProfile(false)}
        >
          Cancel
        </button>

        <button
          type="submit"
          className="profile-save-button"
        >
          Save changes
        </button>
      </div>
      </form>
      )}
      </section>

          {/* Address */}
<section
  className={`profile-card profile-small-card ${
    showAddressForm ? 'profile-card-expanded' : ''
  }`}
>
  <div className="profile-card-icon">⌂</div>

  <div className="profile-card-header">
    <div>
      <h2>Address</h2>
      <p>Manage your delivery address.</p>
    </div>
  </div>

  {!showAddressForm ? (
    <>
      <p className="profile-empty-text">
        No address has been added yet.
      </p>

      <button
        type="button"
        className="profile-secondary-button"
        onClick={() => setShowAddressForm(true)}
      >
        Add address
      </button>
    </>
  ) : (
    <form
      className="profile-address-form"
      onSubmit={(event) => {
        event.preventDefault();

        // Backend connection will be added once a user is available.
        setShowAddressForm(false);
      }}
    >
      <div className="form-group">
        <label htmlFor="addressLine">Address line</label>
        <input
          id="addressLine"
          type="text"
          placeholder="Street address"
          value={addressLine}
          onChange={(event) => setAddressLine(event.target.value)}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="city">City</label>
        <input
          id="city"
          type="text"
          placeholder="Enter your city"
          value={city}
          onChange={(event) => setCity(event.target.value)}
          required
        />
      </div>

      <div className="form-group">
        <label htmlFor="province">Province</label>
        <select
          id="province"
          value={province}
          onChange={(event) => setProvince(event.target.value)}
          required
        >
          <option value="">Select province</option>
          <option value="Eastern Cape">Eastern Cape</option>
          <option value="Free State">Free State</option>
          <option value="Gauteng">Gauteng</option>
          <option value="KwaZulu-Natal">KwaZulu-Natal</option>
          <option value="Limpopo">Limpopo</option>
          <option value="Mpumalanga">Mpumalanga</option>
          <option value="Northern Cape">Northern Cape</option>
          <option value="North West">North West</option>
          <option value="Western Cape">Western Cape</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="postalCode">Postal code</label>
        <input
          id="postalCode"
          type="text"
          inputMode="numeric"
          placeholder="e.g. 8001"
          value={postalCode}
          onChange={(event) => setPostalCode(event.target.value)}
          required
        />
      </div>

      <div className="address-form-actions">
        <button
          type="button"
          className="profile-secondary-button"
          onClick={() => setShowAddressForm(false)}
        >
          Cancel
        </button>

        <button type="submit" className="address-save-button">
          Save address
        </button>
      </div>
    </form>
  )}
</section>

          {/* Verification */}
        <section
        className={`profile-card profile-small-card ${
         showVerificationForm ? 'profile-card-expanded' : ''
         }`}
        >
         <div className="profile-card-icon">✓</div>

        <div className="profile-card-header">
        <div>
      <h2>Verification</h2>
      <p>Verify your account information.</p>
      </div>

      {!showVerificationForm && (
      <span className="verification-pending">Pending</span>
      )}
      </div>

      {!showVerificationForm ? (
      <>
      <p className="profile-empty-text">
        Your account has not been verified yet.
      </p>

      <button
        type="button"
        className="profile-secondary-button"
        onClick={() => setShowVerificationForm(true)}
      >
        Start verification
      </button>
      </>
      ) : (
      <form
      className="profile-verification-form"
      onSubmit={(event) => {
        event.preventDefault();

        // Backend submission will be connected later.
        setShowVerificationForm(false);
      }}
      >
      <div className="form-group">
        <label htmlFor="verificationType">
          Verification type
        </label>

        <select
          id="verificationType"
          value={verificationType}
          onChange={(event) =>
            setVerificationType(event.target.value)
          }
          required
        >
          <option value="">Select verification type</option>
          <option value="Identity">Identity</option>
          <option value="Business">Business</option>
          <option value="Address">Address</option>
        </select>
      </div>

      <div className="form-group">
        <label htmlFor="verificationDocument">
          Verification document
        </label>

        <input
          id="verificationDocument"
          type="text"
          placeholder="Enter document name or reference"
          value={verificationDocument}
          onChange={(event) =>
            setVerificationDocument(event.target.value)
          }
          required
        />

        <small className="verification-help">
          Document upload will be connected when backend file
          upload support is available.
        </small>
      </div>

      <div className="verification-form-actions">
        <button
          type="button"
          className="profile-secondary-button"
          onClick={() => setShowVerificationForm(false)}
        >
          Cancel
        </button>

        <button
          type="submit"
          className="verification-submit-button"
        >
          Submit verification
        </button>
        </div>
        </form>
        )}
        </section>

          {/* Seller profile */}
          <section className="profile-card profile-small-card">
            <div className="profile-card-icon">
              ◇
            </div>

            <div className="profile-card-header">
              <div>
                <h2>Seller Profile</h2>
                <p>Manage your store and seller information.</p>
              </div>
            </div>

            <p className="profile-empty-text">
              Start selling products in the Community Store.
            </p>

            <a
              href="/seller-profile"
              className="profile-secondary-link"
            >
              Seller settings
            </a>
          </section>

          {/* Notifications */}
      <section
      className={`profile-card profile-small-card ${
      showNotifications ? 'profile-card-expanded' : ''
       }`}
      >
      <div className="profile-card-icon">♢</div>

      <div className="profile-card-header">
      <div>
      <h2>Notifications</h2>
      <p>View your latest account updates.</p>
      </div>
      </div>

       {!showNotifications ? (
      <>
      <p className="profile-empty-text">
        Check messages and updates related to your account.
      </p>

      <button
        type="button"
        className="profile-secondary-button"
        onClick={() => setShowNotifications(true)}
      >
        View notifications
      </button>
      </>
      ) : (
      <div className="notifications-panel">
      <div className="notifications-panel-header">
        <h3>Your notifications</h3>

        <button
          type="button"
          className="notifications-close-button"
          onClick={() => setShowNotifications(false)}
        >
          Close
        </button>
      </div>

      <div className="notifications-empty">
        <div className="notifications-empty-icon">♢</div>

        <h4>No notifications yet</h4>

        <p>
          When you receive account updates, verification updates,
          or other notifications, they will appear here.
        </p>
      </div>
      </div>
      )}
      </section>

    

        </div>
      </div>
    </main>
  );
}