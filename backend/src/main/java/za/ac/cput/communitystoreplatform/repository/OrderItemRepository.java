package za.ac.cput.communitystoreplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.communitystoreplatform.domain.OrderItem;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

    List<OrderItem> findByOrderId(int orderId);

    OrderItem findTopByOrderByOrderItemIdDesc();
}
