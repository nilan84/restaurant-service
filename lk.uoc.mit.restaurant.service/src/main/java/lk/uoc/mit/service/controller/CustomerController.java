package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.service.CustomerDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by nilan on 9/21/15.
 */

@Controller
public class CustomerController {

    @Autowired
    CustomerDAOService customerDAOService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String customerPage(Model model) {
        List<Customer> customerList=customerDAOService.getAllCustomer();
        model.addAttribute("customers", customerList);
        return "admin/Customer";
    }

    @RequestMapping(value = "/addcustomer", method = RequestMethod.POST)
    public String addCustomer(@ModelAttribute("customer") @Valid Customer customer,BindingResult result,Model model) {
        if(result.hasErrors()) {
            return "admin/AddCustomer";
        }else{
        customerDAOService.addCustomer(customer);
        List<Customer> customerList=customerDAOService.getAllCustomer();
        model.addAttribute("customers", customerList);
        return "admin/Customer";}
    }

    @RequestMapping(value = "/editcustomer", method = RequestMethod.POST)
    public String editCustomer(@ModelAttribute("customer") @Valid Customer customer,BindingResult result,Model model) {
        if(result.hasErrors()) {
            return "admin/EditCustomer";
        }else{
        customerDAOService.editCustomer(customer);
        List<Customer> customerList=customerDAOService.getAllCustomer();
        model.addAttribute("customers", customerList);
        return "admin/Customer";}
    }

    @RequestMapping(value = "/editcustomerview", method = RequestMethod.GET)
    public String editCustomerView(@RequestParam("custid") Integer custid,@ModelAttribute("customer") @Valid Customer customer,Model model) {
        Customer customerObject=customerDAOService.getCustomerById(custid);
        model.addAttribute("customer", customerObject);
        return "admin/EditCustomer";
    }

    @RequestMapping(value = "/addcustomerview", method = RequestMethod.GET)
    public String addCustomerView(@ModelAttribute("customer") @Valid Customer customer,Model model) {
        return "admin/AddCustomer";
    }
}


