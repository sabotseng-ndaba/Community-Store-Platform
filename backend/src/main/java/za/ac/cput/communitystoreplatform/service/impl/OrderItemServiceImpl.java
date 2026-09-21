package za.ac.cput.communitystoreplatform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.communitystoreplatform.domain.OrderItem;
import za.ac.cput.communitystoreplatform.repository.OrderItemRepository;
import za.ac.cput.communitystoreplatform.service.IOrderItemService;

import java.util.List;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    private final OrderItemRepository repository;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository repository) {
        this.repository = repository;
    }

    @Override
    public OrderItem create(OrderItem orderItem) {
        return this.repository.save(orderItem);
    }

    @Override
    public OrderItem read(Integer orderItemId) {
        return this.repository.findById(orderItemId).orElse(null);
    }

    @Override
    public OrderItem update(OrderItem orderItem) {
        if (this.repository.existsById(orderItem.getOrderItemId())) {
            return this.repository.save(orderItem);
        }
        return null;
    }

    @Override
    public boolean delete(Integer orderItemId) {
        this.repository.deleteById(orderItemId);
        return true;
    }

    @Override
    public List<OrderItem> getAll() {
        return this.repository.findAll();
    }

    @Override
    public List<OrderItem> getItemsByOrder(int orderId) {
        return this.repository.findByOrderId(orderId);
    }
}
