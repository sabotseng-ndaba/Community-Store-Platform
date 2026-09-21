package za.ac.cput.communitystoreplatform.service;

import za.ac.cput.communitystoreplatform.domain.OrderItem;

import java.util.List;

public interface IOrderItemService extends IService<OrderItem, Integer> {

    List<OrderItem> getAll();

    List<OrderItem> getItemsByOrder(int orderId);
}
