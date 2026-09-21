package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.CartItem;

import java.util.List;

public interface ICartItemService extends IService<CartItem, Integer> {

    List<CartItem> getAll();

    List<CartItem> getItemsByCart(int cartId);
}
