package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.model.Employee;
import lk.uoc.mit.restaurant.mysql.service.EmployeeDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public @ResponseBody
    String processAJAXRequest(
            @RequestParam("username") String firstname,
            @RequestParam("passward") String lastname,HttpSession httpSession) {
        String response = "Login ok";
        httpSession.setAttribute("username","nilan");
        // Process the request
        // Prepare the response string
        return response;
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpSession httpSession) {
        String response = "Logout Done";
        httpSession.invalidate();
        return "Home";
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String redirect() {
        return "redirect:homepage";
    }

    @RequestMapping(value = "/homepage", method = RequestMethod.GET)
    public String finalPage(Model model,HttpSession session) {
        session.setAttribute("uuid",123);
        session.getAttribute("uuid");
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

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String redirectUser() {
        return "redirect:userpage";
    }

    @RequestMapping(value = "/userpage", method = RequestMethod.GET)
    public String userPage(Model model) {
        model.addAttribute("users", "abc");
        return "admin/User";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String redirectEmployee() {
        return "redirect:employeepage";
    }

    @RequestMapping(value = "/employeepage", method = RequestMethod.GET)
    public String employeePage(@ModelAttribute("employee") @Valid Employee employee,Model model) {
        List<Employee> employeeList=employeeDAOService.getAllEmployee();
        model.addAttribute("employees", employeeList);
        return "admin/Employee";
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
