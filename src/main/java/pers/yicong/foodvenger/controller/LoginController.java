package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.model.Customer;
import pers.yicong.foodvenger.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }


    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Customer customer = new Customer();
        modelAndView.addObject("customer", customer);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid Customer customer, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Customer userExists = userService.findUserByEmail(customer.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.customer",
                            "There is already a customer registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("registration");
        } else {
            userService.saveCustomer(customer);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("customer", new Customer());
            modelAndView.setViewName("registration");

        }
        return modelAndView;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView home(HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = userService.findUserByEmail(auth.getName());
        httpSession.setAttribute("customer", customer);
        httpSession.setAttribute("userName", customer.getName() + " " + customer.getLastName());
        httpSession.setAttribute("userEmail", customer.getEmail());

        modelAndView.addObject("userName", "Welcome " + customer.getName() + " " + customer.getLastName() + " (" + customer.getEmail() + ")");
//        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        modelAndView.setViewName("index");
        return modelAndView;
    }


    @RequestMapping(value = {"/admin/hello"}, method = RequestMethod.GET)

    public ModelAndView loginAdmin() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("hello");
        return modelAndView;
    }

    @RequestMapping(value = {"/user/hello"}, method = RequestMethod.GET)

    public ModelAndView loginUser() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userhello");
        return modelAndView;
    }


//    @RequestMapping(value = "/error")
//    public ModelAndView error() {
//        return new ModelAndView("error");
//    }


}
