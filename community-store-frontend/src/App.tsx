import { Navigate, Route, Routes } from 'react-router-dom'
import Store from './pages/Store'
import ProductDetails from './pages/ProductDetails'

// FE-02 only wires up the routes it owns (Store + Product details).
// Routes for Home ("/"), About, Cart, Payment and Delivery are left for
// their respective tickets to add here as those pages get built — same
// for wrapping everything in the shared Navbar/Footer/Newsletter layout,
// which are still empty stub files as of this commit. "/" temporarily
// redirects to "/store" so the app has somewhere to land until Home
// exists; swap that for a real Home route in FE-01/whichever ticket
// owns it.
function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/store" replace />} />
      <Route path="/store" element={<Store />} />
      <Route path="/products/:id" element={<ProductDetails />} />
    </Routes>
  )
}

export default App
