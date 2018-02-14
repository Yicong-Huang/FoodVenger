package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.model.Cart;
import pers.yicong.foodvenger.service.DishService;
import pers.yicong.foodvenger.service.RestaurantService;

import javax.servlet.http.HttpSession;

@Controller
public class RestaurantController {


    @Autowired
    private RestaurantService restaurantService;
    @Autowired
    private DishService dishService;


    @RequestMapping("/restaurant")
    public ModelAndView restaurant(@RequestParam String id) {
        ModelAndView modelAndView = new ModelAndView("restaurant");
        modelAndView.addObject("rest", restaurantService.getRestaurant(id));
        return modelAndView;
    }


    @RequestMapping("/add")
    @ResponseBody
    public void add(@RequestParam("did") String did, HttpSession httpSession) {

        Cart cart;

        if (httpSession.getAttribute("cart") == null) {
            cart = new Cart();
            cart.addDish(dishService.getDish(did));
            httpSession.setAttribute("cart", cart);
        } else {
            cart = ((Cart) httpSession.getAttribute("cart"));
            cart.addDish(dishService.getDish(did));
            httpSession.setAttribute("cart", cart);
        }

    }

    @RequestMapping("/del")
    @ResponseBody
    public void del(@RequestParam("did") String did, HttpSession httpSession) {

        Cart cart = ((Cart) httpSession.getAttribute("cart"));
        cart.delDish(dishService.getDish(did));
        httpSession.setAttribute("cart", cart);


    }


}
