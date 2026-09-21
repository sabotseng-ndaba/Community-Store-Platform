package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Cart;
import za.ac.cput.communitystoreplatform.domain.Order;

import java.util.List;

public interface ICartService extends IService<Cart, Integer> {

    List<Cart> getAll();

    List<Cart> getCartsByBuyer(int buyerId);

    Order checkout(int cartId);
}
