package lk.uoc.mit.service.webservice;

import lk.uoc.mit.restaurant.mysql.config.OrderStatus;
import lk.uoc.mit.restaurant.mysql.config.UserType;
import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.model.HelthInformation;
import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.model.OrderFood;
import lk.uoc.mit.restaurant.mysql.service.CustomerDAOService;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import lk.uoc.mit.restaurant.mysql.service.HethInfomationDAOService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by nilan on 8/18/15.
 */
@Controller
public class FoodService {

    private static final Logger logger = LoggerFactory.getLogger(FoodService.class);
    @Autowired
    HethInfomationDAOService hethInfomationDAOService;
    @Autowired
    CustomerDAOService customerDAOService;
    @Autowired
    FoodDAOService foodDAOService;

    @RequestMapping(value = "/view/foodinfo/{barcode}", method = RequestMethod.GET)
    public @ResponseBody
    HelthInformation viewHethInformation(@PathVariable String barcode,ModelMap model){
        return hethInfomationDAOService.viewHethInfo(barcode);
    }


    @RequestMapping(value = "/food/add/{barcode}", method = RequestMethod.GET)
    public @ResponseBody
    HelthInformation addScanFood(@PathVariable String barcode,ModelMap model){
        return hethInfomationDAOService.viewHethInfo(barcode);
    }

    @RequestMapping(value = "/food/addorder", method = RequestMethod.POST)
    public
    @ResponseBody
    Long addOrder(@RequestBody OrderFood orderFood,HttpSession session) {
        logger.info("Start add Food itme service.");
        Customer customer=orderFood.getCustomer();
        Order order=new Order();
        Customer regCustomer=customerDAOService.getCustomerByMAC(customer.getMacAddress());
        long customerId=regCustomer.getCustomerId();
        long orderNo =0;
        try {
            orderNo =foodDAOService.getOrderByCusId(customerId).getOrderNo();
        }catch(Exception ex){
            orderNo=0;
            ex.printStackTrace();

        }
        order.setCustomer(regCustomer);
        order.setDescription(orderFood.getDescription());
        if(customerId==0){
            customerId=customerDAOService.addCustomer(customer);
            customer.setCustomerId((int) (long)customerId);
        }
        if(orderNo==0){
            orderNo=foodDAOService.addOrder(order);
            session.setAttribute("orderno",orderNo);
            foodDAOService.changeOrderStatus(orderNo, OrderStatus.Confirm);
        }
        orderFood.setOrderNo(orderNo);
        foodDAOService.addOrderItem(orderFood);

        session.setAttribute("customer_Id",customerId);
        session.setAttribute("username",customer.getCustomerName());
        session.setAttribute("usertype", UserType.Mobile);
        return orderNo;

    }
}
