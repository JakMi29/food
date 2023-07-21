package Food_app.business.dao;

import Food_app.domain.Order;
import Food_app.domain.Restaurant;

import java.util.List;
import java.util.Optional;

public interface OrderDAO {
    List<Order> findByRestaurant(Restaurant restaurant);

    Order findByOrderNumberWithDetails(String orderName);

    Optional<Order> findByOrderNumber(String orderNumber);

    void completeOrder(String orderName);

    void cancelOrder(Order order);

    void createOrder(Order order);
}
