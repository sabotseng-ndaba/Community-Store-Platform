package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.Order;

import java.util.List;

public interface IOrderService extends IService<Order, Integer> {

    List<Order> getAll();

    List<Order> getOrdersByBuyer(int buyerId);
}
