package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.model.Cuisine;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.service.AdminService;
import pers.yicong.foodvenger.service.CuisineService;
import pers.yicong.foodvenger.service.DishService;
import pers.yicong.foodvenger.service.RestaurantService;

import javax.validation.Valid;

@Controller
public class AdminController {
    @Autowired
    DishService dishService;


    @Autowired
    RestaurantService restaurantService;


    @Autowired
    CuisineService cuisineService;


    @Autowired
    AdminService adminService;

    @RequestMapping("/admin")
    public ModelAndView admin() {

        ModelAndView modelAndView = new ModelAndView("admin");
        modelAndView.addObject("totalRestaurant", restaurantService.count());
        modelAndView.addObject("totalDish", dishService.count());
        modelAndView.addObject("totalCuisine", cuisineService.count());
        return modelAndView;
    }


    @RequestMapping("/admin/delAllRestaurants")
    public ModelAndView delAllRestaurants() {

        ModelAndView modelAndView = new ModelAndView("feedback");
        modelAndView.addObject("msg", adminService.delAllRestaurants());
        return modelAndView;
    }


    @RequestMapping("/admin/add/dish")
    public ModelAndView addDish() {

        ModelAndView modelAndView = new ModelAndView("addDish");
        modelAndView.addObject("dish", new Dish(""));
        modelAndView.addObject("cuisine", new Cuisine(""));
        return modelAndView;
    }


    @RequestMapping(value = "/admin/add/dish", method = RequestMethod.POST)
    public ModelAndView createDish(@Valid Dish dish, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("addDish");
        dish.setCid(100);
        int id = dishService.save(dish);
        if (id == -1) {

            modelAndView.addObject("error", true);
        } else {
            modelAndView.addObject("success", true);
        }
        return modelAndView;
    }

    @RequestMapping("/admin/add/restaurant")
    public ModelAndView addRestaurant() {

        ModelAndView modelAndView = new ModelAndView("addRestaurant");
        modelAndView.addObject("restaurant", new Restaurant());

        return modelAndView;
    }


    @RequestMapping(value = "/admin/add/restaurant", method = RequestMethod.POST)
    public ModelAndView createRestaurant(@Valid Restaurant restaurant, BindingResult bindingResult) {

        ModelAndView modelAndView = new ModelAndView("addRestaurant");

        int id = restaurantService.save(restaurant);


        if (id == -1) {

            modelAndView.addObject("error", true);
        } else {
            modelAndView.addObject("success", true);
        }
        return modelAndView;
    }


    @RequestMapping("/admin/addAllRestaurants")
    public ModelAndView addAllRestaurants() {

        ModelAndView modelAndView = new ModelAndView("feedback");
        modelAndView.addObject("msg", adminService.addAllRestaurants("data/csv/restaurants.xml"));
        return modelAndView;
    }

    @RequestMapping("/admin/addAllDishes")
    public ModelAndView addAllDishes() {

        ModelAndView modelAndView = new ModelAndView("feedback");
        modelAndView.addObject("msg", adminService.addAllDishes("data/csv/dishes.xml"));
        return modelAndView;
    }

    @RequestMapping("/admin/addAllIns")
    public ModelAndView addAllIns() {

        ModelAndView modelAndView = new ModelAndView("feedback");
        modelAndView.addObject("msg", adminService.addAllDishInRest("data/csv/dishes_in_restau.xml"));
        return modelAndView;
    }

}
