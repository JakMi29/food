package Food_app.infrastructure.database.repository;

import Food_app.business.dao.OrderDAO;
import Food_app.domain.Order;
import Food_app.domain.Restaurant;
import Food_app.infrastructure.database.entity.OrderEntity;
import Food_app.infrastructure.database.repository.jpa.OrderJpaRepository;
import Food_app.infrastructure.database.repository.mapper.OrderEntityMapper;
import Food_app.infrastructure.database.repository.mapper.RestaurantEntityMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class OrderRepository implements OrderDAO {
    private final OrderJpaRepository orderJpaRepository;
    private final RestaurantEntityMapper restaurantEntityMapper;
    private final OrderEntityMapper orderEntityMapper;


    @Override
    public List<Order> findByRestaurant(Restaurant restaurant) {
        return orderJpaRepository
                .findAllByRestaurant(restaurantEntityMapper.map(restaurant))
                .stream().map(orderEntityMapper::mapWithRestaurantAndCustomer)
                .toList();
    }

    @Override
    public Order findByOrderNumberWithDetails(String orderName) {
        return orderEntityMapper
                .mapWithRestaurantAndCustomer(orderJpaRepository.findByOrderNumberWithRelatedOrderMeals(orderName));
    }

    @Override
    public Optional<Order> findByOrderNumber(String orderNumber) {
        return orderJpaRepository.findByOrderNumber(orderNumber).map(orderEntityMapper::mapWithRestaurantAndCustomer);
    }

    @Override
    public void completeOrder(String orderName) {
        orderJpaRepository.findByOrderNumber(orderName);
        OrderEntity order = orderJpaRepository.findByOrderNumber(orderName).orElseThrow();
        order.setCompletedDateTime(OffsetDateTime.now());
        order.setComplete(true);
        orderJpaRepository.save(order);
    }

    public void cancelOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.mapAllOrder(order);
        orderJpaRepository.delete(orderEntity);
    }

    @Override
    public void createOrder(Order order) {
        OrderEntity orderEntity = orderEntityMapper.mapAllOrder(order);
        orderJpaRepository.saveAndFlush(orderEntity);
    }
}
