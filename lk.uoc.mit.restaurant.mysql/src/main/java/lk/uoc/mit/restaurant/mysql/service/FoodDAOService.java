package lk.uoc.mit.restaurant.mysql.service;

import lk.uoc.mit.restaurant.mysql.model.Food;
import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.model.OrderFood;
import lk.uoc.mit.restaurant.mysql.config.OrderStatus;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by nilan on 8/21/15.
 */
public interface FoodDAOService {

    public List<Food> getAllFood();
    public List<Order> getAllActiveOrder(HttpSession httpSession) throws Exception;
    public List<Order> getAllOnDeliveryOrder(HttpSession httpSession) throws Exception;
    public Long addFoodItem(Food food);
    public Long addOrderItem(OrderFood orderFood);
    public Long addOrder(Order order);
    public List<OrderFood> getOrderFood(long orderNo);
    public Food getFoodById(int foodId);
    public Long editFoodItem(Food food);
    public void changeOrderStatus(long orderNo,OrderStatus orderStatus);
    public void deleteOrderItem(long orderNo,int foodNo);
    public List<Order> getAllOrderByDate(String Date);
    public Order getOrderById(Long orderId);

}
