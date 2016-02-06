package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.config.UserType;
import lk.uoc.mit.restaurant.mysql.model.Customer;
import lk.uoc.mit.restaurant.mysql.model.Employee;
import lk.uoc.mit.restaurant.mysql.model.User;
import lk.uoc.mit.restaurant.mysql.service.CustomerDAOService;
import lk.uoc.mit.restaurant.mysql.service.EmployeeDAOService;
import lk.uoc.mit.restaurant.mysql.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Created by nilan on 8/23/15.
 */
@Controller
public class UserLoginController {

    @Autowired
    EmployeeDAOService employeeDAOService;

    @Autowired
    CustomerDAOService customerDAOService;

    @Autowired
    UserDAOService userDAOService;


    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public @ResponseBody
    void processAJAXRequest(
            @RequestParam("username") String firstname,
            @RequestParam("passward") String password,@RequestParam("id") String id,HttpSession httpSession,HttpServletResponse response) {

        User user=userDAOService.getUserByName(firstname);
        if(user.getUserType()!=null){
            if(password.equalsIgnoreCase(user.getPassword())){
            httpSession.setAttribute("username",user.getUsername());
            httpSession.setAttribute("usertype",user.getUserType());
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("text/plain;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");
            }
            else{
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response.setContentType("text/plain;charset=UTF-8");
                response.setCharacterEncoding("UTF-8");

            }

        }
        else if(id!=null) {
            Customer customer=customerDAOService.getCustomerByemail(password);
            long customerId=customer.getCustomerId();
            if(customer.getCustomerId()==0) {
                customer.setCustomerEmail(password);
                customer.setCustomerName(firstname);
                customer.setCustomerMob("Mob No");
                customer.setMacAddress(password);
                customerId = customerDAOService.addCustomer(customer);
            }
            httpSession.setAttribute("username", firstname);
            httpSession.setAttribute("usertype", UserType.Web);
            httpSession.setAttribute("customer_Id",customerId);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("text/plain;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
        }
        else{
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("text/plain;charset=UTF-8");
        response.setCharacterEncoding("UTF-8");}


    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        String response = "Logout Done";
        httpSession.invalidate();
        return "home/Home";
    }


    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:homepage";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String finalPage(Model model,HttpSession session) {
        
        session.setAttribute("uuid",123);
        session.getAttribute("uuid");
        return "home/Home";
    }

    @RequestMapping(value = "/apphome", method = RequestMethod.GET)
    public String androidAppHome(Model model,@RequestParam("cusname") String cusname,@RequestParam("mac") String mac,HttpSession session) {
        Customer customer=customerDAOService.getCustomerByMAC(mac);
      if(customer!=null){
            session.setAttribute("username", customer.getCustomerName());
            session.setAttribute("mac",mac);
            session.setAttribute("customer_Id",customer.getCustomerId());
            session.setAttribute("usertype",UserType.Mobile);}
        return "Home";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String redirectAdmin() {
        return "redirect:adminpage";
    }

    @RequestMapping(value = "/adminpage", method = RequestMethod.GET)
    public String adminPage(Model model) {
        model.addAttribute("users", "abc");
        return "Admin";
    }



    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String redirectEmployee() {
        return "redirect:employeepage";
    }

    @RequestMapping(value = "/employeepage", method = RequestMethod.GET)
    public String employeePage(@ModelAttribute("employee") @Valid Employee employee,Model model,HttpSession httpSession) {
        if(httpSession.getAttribute("usertype")!=null && httpSession.getAttribute("usertype")==UserType.Admin){
        List<Employee> employeeList=employeeDAOService.getAllEmployee();
        model.addAttribute("employees", employeeList);
        return "admin/Employee";}
        else{
            return "admin/Unauthorized";
        }
    }

    @RequestMapping(value = "/employeeadd", method = RequestMethod.GET)
    public String addEmployee(@ModelAttribute("employee") @Valid Employee employee,Model model) {
        List<Employee> employeeList=employeeDAOService.getAllEmployee();
        return "admin/AddEmployee";
    }

    @RequestMapping(value = "/employeeedit", method = RequestMethod.GET)
    public String editEmployee(@RequestParam("empid") Integer empId,@ModelAttribute("employee") @Valid Employee employee,Model model) {
        List<Employee> employeeList=employeeDAOService.getAllEmployee();
        Employee employeeObject=new Employee();
        employeeObject=employeeDAOService.getEmployeeById(empId);
        model.addAttribute("employee", employeeObject);
        return "admin/EditEmployee";
    }

    @RequestMapping(value = "/addemployee", method = RequestMethod.POST)
    public String employeeAdd(@ModelAttribute("employee") @Valid Employee employee,BindingResult result,Map<String, Object> model) {
        if(result.hasErrors()) {
            return "admin/AddEmployee";
        }else{
        employeeDAOService.addEmployee(employee);
        List<Employee> employeeList=employeeDAOService.getAllEmployee();
        model.put("employees", employeeList);
        return "admin/Employee";}
    }

    @RequestMapping(value = "/editemployee", method = RequestMethod.POST)
    public String employeeEdit(@ModelAttribute("employee") @Valid Employee employee,BindingResult result,Model model) {
        if(result.hasErrors()) {
            return "admin/EditEmployee";
        }else{
        employeeDAOService.editEmployee(employee);
        List<Employee> employeeList=employeeDAOService.getAllEmployee();
        model.addAttribute("employees", employeeList);
        return "admin/Employee";}
    }

}
