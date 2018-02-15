package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.model.Cart;
import pers.yicong.foodvenger.model.Customer;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.Sale;
import pers.yicong.foodvenger.service.CheckoutService;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CheckoutController {
    @Autowired
    CheckoutService checkoutService;

    @RequestMapping("/checkout")
    public ModelAndView checkout(HttpSession httpSession) {

        return new ModelAndView("checkout");
    }

    @RequestMapping("/checkout/save")
    public ModelAndView save(HttpSession httpSession) {

        Cart cart = ((Cart) httpSession.getAttribute("cart"));
        Customer customer = ((Customer) httpSession.getAttribute("customer"));


        for (Dish dish : cart.getDishes()) {
            Sale sale = new Sale();
            sale.setCustomer(customer);
            sale.setDid(dish.getId());
            sale.setSaleDate(new Date());
            checkoutService.save(sale);
        }


        return new ModelAndView("confirmation");
    }

}
