package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.config.OrderStatus;
import lk.uoc.mit.restaurant.mysql.model.Food;
import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.model.OrderFood;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/23/15.
 */
@Controller
public class OrderController {
    @Autowired
    FoodDAOService foodDAOService;

    @RequestMapping(value = "/order", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:orderhome";
    }

    @RequestMapping(value = "/orderhome", method = RequestMethod.GET)
    public String finalPage(Model model) {
        List<Order> orderList=foodDAOService.getAllActiveOrder();
        model.addAttribute("orderList", orderList);
        return "Order";
    }


    @RequestMapping(value = "/activeorder", method = RequestMethod.GET)
    public String viewOrderFood(Model model) {
        List<Order> orderList=foodDAOService.getAllActiveOrder();
        model.addAttribute("orderList", orderList);
        return "food/ViewOrderFood";
    }

    @RequestMapping(value = "/canfirmorder", method = RequestMethod.GET)
    public String confirmOrder(Model model,@RequestParam("id") Long orderId) {
        foodDAOService.changeOrderStatus(orderId, OrderStatus.Confirm);
        List<Order> orderList=foodDAOService.getAllActiveOrder();
        model.addAttribute("orderList", orderList);
        return "food/ViewOrderFood";
    }

    @RequestMapping(value = "/deletefood", method = RequestMethod.GET)
    public String deleteOrderFoodItem(@ModelAttribute("orderFoodItem") @Valid OrderFood orderFood,@RequestParam("id") Long orderNo,@RequestParam("foodid") int foodId,Model model) {
        List<Food> foodList;
        foodDAOService.deleteOrderItem(orderNo,foodId);
        foodList=foodDAOService.getAllFood();
        List <OrderFood> orderFoods=foodDAOService.getOrderFood(orderNo);
        Map referenceData = new HashMap();

        Map<String,String> count = new LinkedHashMap<String,String>();
        count.put("1", "1");
        count.put("2", "2");
        count.put("3", "3");
        count.put("4", "4");
        model.addAttribute("countList", count);
        model.addAttribute("orderNo", orderNo);
        model.addAttribute("foodList", foodList);
        model.addAttribute("orderList", orderFoods);
        return "food/AddOrderFood";
    }

    @RequestMapping(value = "/viewallorder", method = RequestMethod.GET)
    public String viewOrder(Model model,@RequestParam("date") String date,@ModelAttribute("order")Order order) {
        List<Order> orderList=foodDAOService.getAllOrderByDate(date);
        model.addAttribute("enumValues", OrderStatus.values());
        model.addAttribute("orderList", orderList);
        model.addAttribute("dateselect", date);
        return "admin/ViewOrder";
    }

    @RequestMapping(value = "/chnagestatus", method = RequestMethod.POST)
    public String chnageOrderStatus(Model model,@ModelAttribute("order")Order order) {
        foodDAOService.changeOrderStatus(order.getOrderNo(),order.getOrderStatus());
        List<Order> orderList=foodDAOService.getAllActiveOrder();
        model.addAttribute("enumValues", OrderStatus.values());
        model.addAttribute("orderList", orderList);
        return "admin/ViewOrder";
    }
}

