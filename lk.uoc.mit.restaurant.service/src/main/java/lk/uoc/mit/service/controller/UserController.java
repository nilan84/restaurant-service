package lk.uoc.mit.service.controller;

import lk.uoc.mit.restaurant.mysql.config.UserType;
import lk.uoc.mit.restaurant.mysql.model.User;
import lk.uoc.mit.restaurant.mysql.service.UserDAOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by nilan on 9/20/15.
 */

@Controller
public class UserController {

    @Autowired
    UserDAOService userDAOService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String redirectUser() {
        return "redirect:userpage";
    }

    @RequestMapping(value = "/userpage", method = RequestMethod.GET)
    public String userPage(Model model,HttpSession httpSession) {
        if(httpSession.getAttribute("usertype")!=null && httpSession.getAttribute("usertype")== UserType.Admin) {
        List<User> userList=userDAOService.getAllUser();
        model.addAttribute("users", userList);
        return "admin/User";

        }else{
            return "admin/Unauthorized";
        }
    }

    @RequestMapping(value = "/adduser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") @Valid User user,BindingResult result,Model model,HttpSession httpSession) {
        if(httpSession.getAttribute("usertype")!=null && httpSession.getAttribute("usertype")== UserType.Admin) {
        if(result.hasErrors()) {
            model.addAttribute("enumValues", UserType.values());
            return "admin/AddUser";
        }else{
        userDAOService.addUser(user);
        List<User> userList=userDAOService.getAllUser();
        model.addAttribute("users", userList);
        return "admin/User";

        }}else{
                return "admin/Unauthorized";
            }

    }

    @RequestMapping(value = "/edituser", method = RequestMethod.POST)
    public String editUser(@ModelAttribute("user") @Valid User user,BindingResult result,Model model) {
        if(result.hasErrors()) {
            model.addAttribute("enumValues", UserType.values());
            return "admin/EditUser";
        }else{
        userDAOService.editUser(user);
        List<User> userList=userDAOService.getAllUser();
        model.addAttribute("users", userList);
        return "admin/User";}
    }

    @RequestMapping(value = "/edituserview", method = RequestMethod.GET)
    public String editUserView(@RequestParam("userid") Integer userid,@ModelAttribute("user") @Valid User user,Model model) {
        User userObject=userDAOService.getUserById(userid);
        model.addAttribute("enumValues", UserType.values());
        model.addAttribute("user", userObject);
        return "admin/EditUser";
    }

    @RequestMapping(value = "/adduserview", method = RequestMethod.GET)
    public String addUserView(@ModelAttribute("user") @Valid User user,Model model) {
        model.addAttribute("enumValues", UserType.values());
        return "admin/AddUser";
    }

}
