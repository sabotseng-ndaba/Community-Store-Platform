import { Navigate, Route, Routes } from 'react-router-dom'
import Store from './pages/Store'
import ProductDetails from './pages/ProductDetails'
import SellProduct from './pages/SellProduct'
import Login from './pages/Login'
import Register from './pages/Register'
import Profile from './pages/Profile'
import SellerProfile from './pages/SellerProfile'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Navigate to="/store" replace />} />

      {/* FE-02 */}
      <Route path="/store" element={<Store />} />
      <Route path="/products/:id" element={<ProductDetails />} />
      <Route path="/sell" element={<SellProduct />} />
      <Route path="/sell/:id" element={<SellProduct />} />

      {/* FE-01 */}
      <Route path="/login" element={<Login />} />
      <Route path="/register" element={<Register />} />
      <Route path="/profile" element={<Profile />} />
      <Route path="/seller-profile" element={<SellerProfile />} />
    </Routes>
  )
}

export default App