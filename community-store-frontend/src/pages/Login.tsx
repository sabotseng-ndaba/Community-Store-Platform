import { useState } from 'react';
import '../components/account/account.css';

export default function Login() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [showPassword, setShowPassword] = useState(false);
  const [message, setMessage] = useState('');

  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault();

    if (!email.trim() || !password.trim()) {
      setMessage('Please enter your email address and password.');
      return;
    }

    // Backend authentication endpoint has not been implemented yet.
    setMessage('Login authentication is not available yet.');
  };

  return (
    <main className="account-page">
      <section className="account-card">
        <div className="account-heading">
          <span className="account-label">COMMUNITY STORE</span>

          <h1>Welcome back</h1>

          <p>
            Sign in to manage your account, purchases and seller profile.
          </p>
        </div>

        <form className="account-form" onSubmit={handleSubmit}>
          <div className="form-group">
            <label htmlFor="email">Email address</label>

            <input
              id="email"
              type="email"
              placeholder="Enter your email address"
              value={email}
              onChange={(event) => setEmail(event.target.value)}
              autoComplete="email"
            />
          </div>

          <div className="form-group">
            <div className="password-label">
              <label htmlFor="password">Password</label>

              <button
                type="button"
                className="text-button"
                onClick={() => setShowPassword((current) => !current)}
              >
                {showPassword ? 'Hide' : 'Show'}
              </button>
            </div>

            <input
              id="password"
              type={showPassword ? 'text' : 'password'}
              placeholder="Enter your password"
              value={password}
              onChange={(event) => setPassword(event.target.value)}
              autoComplete="current-password"
            />
          </div>

          {message && <p className="form-message">{message}</p>}

          <button type="submit" className="account-primary-button">
            Sign in
          </button>
        </form>

        <div className="account-footer">
          <span>Don't have an account?</span>
          <a href="/register">Create an account</a>
        </div>
      </section>
    </main>
  );
}