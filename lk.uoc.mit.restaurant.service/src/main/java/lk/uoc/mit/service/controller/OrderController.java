package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.config.OrderStatus;
import lk.uoc.mit.restaurant.mysql.config.UserType;
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

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
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
    public String finalPage(Model model, HttpSession httpSession) {
        try {
            List<Order> orderList = foodDAOService.getAllOnDeliveryOrder(httpSession);
            model.addAttribute("orderList", orderList);
            return "Order";
        } catch (Exception ex) {

            return "admin/NoDataFoundOrder";
        }
    }


    @RequestMapping(value = "/activeorder", method = RequestMethod.GET)
    public String viewOrderFood(Model model, HttpSession httpSession) {
        try {
            List<Order> orderList = foodDAOService.getAllActiveOrder(httpSession);
            model.addAttribute("orderList", orderList);
            return "food/ViewOrderFood";
        } catch (Exception ex) {
            List<Order> orderList=null;
            model.addAttribute("orderList", orderList);
            return "food/ViewOrderFood";
        }
    }

    @RequestMapping(value = "/canfirmorder", method = RequestMethod.GET)
    public String confirmOrder(Model model, @RequestParam("id") Long orderId, HttpSession httpSession) {
        try {
            foodDAOService.changeOrderStatus(orderId, OrderStatus.Confirm);
            List<Order> orderList = foodDAOService.getAllActiveOrder(httpSession);
            model.addAttribute("orderList", orderList);
            return "food/ViewOrderFood";
        } catch (Exception ex) {

            return "admin/NoDataFoundOrder";
        }
    }

    @RequestMapping(value = "/deletefood", method = RequestMethod.GET)
    public String deleteOrderFoodItem(@ModelAttribute("orderFoodItem") @Valid OrderFood orderFood, @RequestParam("id") Long orderNo, @RequestParam("foodid") int foodId, Model model) {
        List<Food> foodList;
        foodDAOService.deleteOrderItem(orderNo, foodId);
        foodList = foodDAOService.getAllFood();
        List<OrderFood> orderFoods = foodDAOService.getOrderFood(orderNo);


        Map<String, String> count = new HashMap<String, String>();
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
    public String viewOrder(Model model, @RequestParam("date") String date, @ModelAttribute("order") Order order,HttpSession httpSession) {
        if(httpSession.getAttribute("usertype")!=null && httpSession.getAttribute("usertype")== UserType.Admin){
        List<Order> orderList = foodDAOService.getAllOrderByDate(date);
        model.addAttribute("enumValues", OrderStatus.values());
        model.addAttribute("orderList", orderList);
        model.addAttribute("dateselect", date);
        return "admin/ViewOrder";}else{
            return "admin/Unauthorized";
        }
    }

    @RequestMapping(value = "/chnagestatus", method = RequestMethod.POST)
    public String chnageOrderStatus(Model model, @ModelAttribute("order") Order order, HttpSession httpSession) {
        try {
            foodDAOService.changeOrderStatus(order.getOrderNo(), order.getOrderStatus());
            JavaMail javaMail=new JavaMail();
            order=foodDAOService.getOrderById(order.getOrderNo());
            javaMail.generateAndSendEmail(order.getCustomer().getCustomerEmail(),order.getCustomer().getCustomerName(),order.getOrderNo());
            List<Order> orderList = foodDAOService.getAllActiveOrder(httpSession);
            model.addAttribute("enumValues", OrderStatus.values());
            model.addAttribute("orderList", orderList);
            return "admin/ViewOrder";
        } catch (Exception ex) {

            return "admin/NoDataFoundOrder";
        }
    }
}

