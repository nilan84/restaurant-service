package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.model.Food;
import lk.uoc.mit.restaurant.mysql.model.Order;
import lk.uoc.mit.restaurant.mysql.model.OrderFood;
import lk.uoc.mit.restaurant.mysql.service.FoodDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/23/15.
 */
@Controller
public class FoodController {
    @Autowired
    FoodDAOService foodDAOService;

    @RequestMapping(value = "/food", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:foodPage";
    }

    @RequestMapping(value = "/foodPage", method = RequestMethod.GET)
    public String finalPage(@ModelAttribute("orderFoodItem") @Valid OrderFood orderFood,Model model) {
        List<Food> foodList;
        Map referenceData = new HashMap();
        Map<String,String> count = new LinkedHashMap<String,String>();
        count.put("1", "1");
        count.put("2", "2");
        count.put("3", "3");
        count.put("4", "4");
        model.addAttribute("countList", count);
        long foodOrderId=orderFood.getOrderNo();
        if(foodOrderId==0) {
            Order order = new Order();
            order.setDescription("This is order");
            Customer customer = new Customer();
            customer.setCustomerId(0);
            Food food = new Food();
            food.setFoodNo(1);
            order.setCustomer(customer);
            foodOrderId = foodDAOService.addOrder(order);
        }
        foodList=foodDAOService.getAllFood();
        model.addAttribute("foodList", foodList);
        model.addAttribute("orderNo", foodOrderId);
        return "food/AddOrderFood";
    }

    @RequestMapping(value = "/orderfood", method = RequestMethod.POST)
    public String orderFoodItem(@ModelAttribute("orderFoodItem") @Valid OrderFood orderFood,Model model,BindingResult result) {
        List<Food> foodList;
        long orderNo=orderFood.getOrderNo();
        orderFood.setOrderNo(orderNo);
        foodDAOService.addOrderItem(orderFood);
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

    @RequestMapping(value = "/vieworder", method = RequestMethod.GET)
    public String viewOrder(Model model) {
        List<Food> foodList;
        foodList=foodDAOService.getAllFood();
        model.addAttribute("foodList", foodList);
        return "food/AddOrderFood";
    }


    @RequestMapping(value = "/addfood", method = RequestMethod.GET)
    public String addFood() {
        return "redirect:addfoodPage";
    }

    @RequestMapping(value = "/addfoodPage", method = RequestMethod.GET)
    public ModelAndView addFoodPage(HttpServletRequest request,
                                    HttpServletResponse response) {
        List<Food> foodList;
        foodList=foodDAOService.getAllFood();
        ModelAndView mav = new ModelAndView("admin/AddFood", "command", new Food());
        mav.addObject("foodList", foodList);
        return mav;
    }

    @RequestMapping(value = "/addfooditem", method = RequestMethod.POST)
    public String addFoodItem(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("food") @Valid Food food,BindingResult result,Map<String, Object> model) {
        if(result.hasErrors()) {
        return "admin/AddFoodItem";
        }else{
            try {
                food.setItemImage(food.getFile().getBytes());
            }catch(Exception ex){ex.printStackTrace();}
        foodDAOService.addFoodItem(food);
            List<Food> foodList;
            foodList=foodDAOService.getAllFood();
            model.put("foodList", foodList);
            return  "admin/AddFood";}

    }


    @RequestMapping(value = "/fooditemadd", method = RequestMethod.GET)
    public String addFoodItem(HttpServletRequest request,
                                    HttpServletResponse response,@ModelAttribute("food") Food food) {

        return "admin/AddFoodItem";
    }

    @RequestMapping(value = "/fooditemedit", method = RequestMethod.GET)
    public String editFoodItem(Map<String, Object> model,@ModelAttribute("food") Food food,@ModelAttribute("foodId") int foodId) {
        Food foodItem=foodDAOService.getFoodById(foodId);

        model.put("foodItem", foodItem);
        return "admin/EditFoodItem";
    }

    @RequestMapping(value = "/editfooditem", method = RequestMethod.POST)
    public String editFoodItem(HttpServletRequest request, HttpServletResponse response,@ModelAttribute("food") @Valid Food food,BindingResult result,Map<String, Object> model) {
        if(result.hasErrors()) {
            return "admin/EditFoodItem";
        }else{
            try {
                MultipartFile file = food.getFile();
                String fileName = null;
                InputStream inputStream = null;
                OutputStream outputStream = null;
                if (file.getSize() > 0) {
                    inputStream = file.getInputStream();
                    System.out.println("size::" + file.getSize());
                    fileName = request.getRealPath("") + "/resources/image/food/"
                            + food.getFoodNo()+".jpg";
                    outputStream = new FileOutputStream(fileName);
                    System.out.println("fileName:" + file.getOriginalFilename());

                    int readBytes = 0;
                    byte[] buffer = new byte[10000];
                    while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
                        outputStream.write(buffer, 0, readBytes);
                    }
                    outputStream.close();
                    inputStream.close();
                }

                // ..........................................

            } catch (Exception e) {
                e.printStackTrace();
            }
            foodDAOService.editFoodItem(food);
            List<Food> foodList;
            foodList=foodDAOService.getAllFood();
            model.put("foodList", foodList);
            return  "admin/AddFood";}

    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer foodid, HttpServletResponse response,HttpServletRequest request)
            throws ServletException, IOException {
        Food food = foodDAOService.getFoodById(foodid);
        response.setContentType("image/jpg, image/png");
        response.getOutputStream().write(food.getItemImage());
        response.getOutputStream().close();}
}
