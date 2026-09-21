package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.Order;
import za.ac.cput.communitystoreplatform.repository.OrderItemRepository;
import za.ac.cput.communitystoreplatform.repository.OrderRepository;
import za.ac.cput.communitystoreplatform.service.IOrderService;

import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository repository;
    private final OrderItemRepository orderItemRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository, OrderItemRepository orderItemRepository) {
        this.repository = repository;
        this.orderItemRepository = orderItemRepository;
    }

    private Order attachOrderDetails(Order order) {
        if (order != null) {
            order.setOrderDetails(this.orderItemRepository.findByOrderId(order.getOrderId()));
        }
        return order;
    }

    @Override
    public Order create(Order order) {
        return this.repository.save(order);
    }

    @Override
    public Order read(Integer orderId) {
        return attachOrderDetails(this.repository.findById(orderId).orElse(null));
    }

    @Override
    public Order update(Order order) {
        if (this.repository.existsById(order.getOrderId())) {
            return attachOrderDetails(this.repository.save(order));
        }
        return null;
    }

    @Override
    public boolean delete(Integer orderId) {
        this.repository.deleteById(orderId);
        return true;
    }

    @Override
    public List<Order> getAll() {
        return this.repository.findAll()
                .stream()
                .map(this::attachOrderDetails)
                .toList();
    }

    @Override
    public List<Order> getOrdersByBuyer(int buyerId) {
        return this.repository.findByBuyerId(buyerId)
                .stream()
                .map(this::attachOrderDetails)
                .toList();
    }
}
