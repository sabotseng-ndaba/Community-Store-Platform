import { useState } from 'react';
import '../components/account/account.css';

export default function Register() {
  const [userId, setUserId] = useState('');
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [email, setEmail] = useState('');
  const [phone, setPhone] = useState('');
  const [altPhone, setAltPhone] = useState('');
  const [message, setMessage] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (
      !userId.trim() ||
      !firstName.trim() ||
      !lastName.trim() ||
      !email.trim() ||
      !phone.trim()
    ) {
      setMessage('Please complete all required fields.');
      return;
    }

    // API connection will be added after the role structure is configured.
    setMessage('Registration details are ready to be submitted.');
  };

  return (
    <main className="account-page">
      <section className="account-card account-card-large">
        <div className="account-heading">
          <span className="account-label">COMMUNITY STORE</span>

          <h1>Create your account</h1>

          <p>
            Join the community and start buying or selling products.
          </p>
        </div>

        <form className="account-form" onSubmit={handleSubmit}>
          <div className="form-row">
            <div className="form-group">
              <label htmlFor="firstName">First name</label>

              <input
                id="firstName"
                type="text"
                placeholder="Enter your first name"
                value={firstName}
                onChange={(event) => setFirstName(event.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="lastName">Last name</label>

              <input
                id="lastName"
                type="text"
                placeholder="Enter your last name"
                value={lastName}
                onChange={(event) => setLastName(event.target.value)}
              />
            </div>
          </div>

          <div className="form-group">
            <label htmlFor="userId">User ID</label>

            <input
              id="userId"
              type="text"
              placeholder="Create your user ID"
              value={userId}
              onChange={(event) => setUserId(event.target.value)}
            />
          </div>

          <div className="form-group">
            <label htmlFor="registerEmail">Email address</label>

            <input
              id="registerEmail"
              type="email"
              placeholder="Enter your email address"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              autoComplete="email"
            />
          </div>

          <div className="form-row">
            <div className="form-group">
              <label htmlFor="phone">Phone number</label>

              <input
                id="phone"
                type="tel"
                placeholder="e.g. 0712345678"
                value={phone}
                onChange={(event) => setPhone(event.target.value)}
              />
            </div>

            <div className="form-group">
              <label htmlFor="altPhone">
                Alternative phone <span className="optional-text">(optional)</span>
              </label>

              <input
                id="altPhone"
                type="tel"
                placeholder="Alternative number"
                value={altPhone}
                onChange={(event) => setAltPhone(event.target.value)}
              />
            </div>
          </div>

          {message && <p className="form-message">{message}</p>}

          <button type="submit" className="account-primary-button">
            Create account
          </button>
        </form>

        <div className="account-footer">
          <span>Already have an account?</span>
          <a href="/login">Sign in</a>
        </div>
      </section>
    </main>
  );
}