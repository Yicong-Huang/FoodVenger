package pers.yicong.foodvenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.service.ListService;

import java.util.List;

@Controller
public class FoodListController {


    @Autowired
    private ListService listService;


    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Restaurant> restaurants = listService.findAllRestaurants();
        System.out.println(restaurants);
        modelAndView.addObject("rests", restaurants);
        modelAndView.setViewName("list");
        return modelAndView;
    }

    @RequestMapping("/list")
    ModelAndView list(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("page",  listService.listAllByPage(pageable));
        modelAndView.setViewName("list");
        return modelAndView;
    }

}
