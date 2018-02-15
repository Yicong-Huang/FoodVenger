package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.service.CheckoutService;

import javax.servlet.http.HttpSession;

@Controller
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;

    @RequestMapping("/checkout")
    public ModelAndView checkout(HttpSession httpSession) {

        return new ModelAndView("checkout");
    }

}
