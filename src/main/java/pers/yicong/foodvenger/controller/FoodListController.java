package pers.yicong.foodvenger.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import pers.yicong.foodvenger.service.ListService;

@Controller
public class FoodListController {


    @Autowired
    private ListService listService;


    @RequestMapping("/search")
    ModelAndView search() {
        return new ModelAndView("search");
    }

    @RequestMapping("/list")
    ModelAndView list(Pageable pageable, @RequestParam String pattern) {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("page", listService.listAllByPage(pageable, pattern));
        modelAndView.addObject("pattern", pattern);


        modelAndView.setViewName("list");

        return modelAndView;
    }

    @RequestMapping("/search/cuisine")
    ModelAndView search(@RequestParam String cuisine) {
        ModelAndView modelAndView = new ModelAndView("cuisine");
        modelAndView.addObject("cuisineInfo", listService.getCuisineInfo());


//        modelAndView.addObject("page", listService.listAllByPage(pageable, pattern));

        return modelAndView;
    }



}
