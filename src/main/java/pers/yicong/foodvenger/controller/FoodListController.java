package pers.yicong.foodvenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.service.ListService;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Controller
public class FoodListController {


    @Autowired
    private ListService listService;


    @RequestMapping("/search")
    ModelAndView search() {
        return new ModelAndView("search");
    }

    @RequestMapping("/list")
    ModelAndView list(Pageable pageable, @RequestParam String pattern, @RequestParam String sort) {


        long startTime = System.nanoTime();
        ModelAndView modelAndView = new ModelAndView("list");
        if (sort.equals("rating")) {
            modelAndView.addObject("page", listService.listAllByPageWithRating(pageable, pattern));

        } else if (sort.equals("name")) {
            modelAndView.addObject("page", listService.listAllByPageWithName(pageable, pattern));
        } else {
            modelAndView.addObject("page", listService.listAllByPage(pageable, pattern));
        }

        modelAndView.addObject("pattern", pattern);


        try {

            BufferedWriter writer = new BufferedWriter(new FileWriter("time.log", true));
            writer.append(String.valueOf(System.nanoTime() - startTime));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return modelAndView;
    }

    @RequestMapping("/browse/cuisines")
    ModelAndView browseByCuisine() {
        ModelAndView modelAndView = new ModelAndView("cuisines");
        modelAndView.addObject("cuisines", listService.listAllCuisines());


        return modelAndView;
    }

    @RequestMapping("/search/cuisine")
    ModelAndView searchCuisine(Pageable pageable, @RequestParam String cuisine) {
        ModelAndView modelAndView = new ModelAndView("cuisine");
        modelAndView.addObject("cuisineInfo", cuisine);
        modelAndView.addObject("page", listService.listRestaurantsByCuisine(pageable, cuisine));


        return modelAndView;
    }

    @RequestMapping("/browse/dishes")
    ModelAndView browseByDish() {
        ModelAndView modelAndView = new ModelAndView("dishes");
        modelAndView.addObject("cuisines", listService.listAllDishes());
        return modelAndView;
    }

    @RequestMapping("/search/dish")
    ModelAndView searchDish(Pageable pageable, @RequestParam String cuisine) {
        ModelAndView modelAndView = new ModelAndView("cuisine");
        modelAndView.addObject("cuisineInfo", cuisine);
        modelAndView.addObject("page", listService.listRestaurantsByCuisine(pageable, cuisine));


        return modelAndView;
    }


}
