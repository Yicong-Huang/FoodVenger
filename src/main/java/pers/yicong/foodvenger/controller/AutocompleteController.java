package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.service.DishService;
import pers.yicong.foodvenger.service.RestaurantService;

import java.util.HashSet;
import java.util.Set;

@RestController
public class AutocompleteController {

    @Autowired
    RestaurantService restaurantService;
    @Autowired
    DishService dishService;
    // http://localhost:8080
    // basic site


    @RequestMapping(path = "/suggestions")
    @ResponseBody
    public Set<Suggestion> suggestions(@RequestParam String pattern) {


        Set<Suggestion> suggestions = new HashSet<>();

        if (pattern == null || pattern.trim().isEmpty()) {

            return suggestions;
        }
        StringBuilder searchTerm = new StringBuilder();
        for (String s : pattern.split(" ")) {
            searchTerm.append("+").append(s).append("* ");
        }
        searchTerm = new StringBuilder(searchTerm.toString().trim());


//        System.out.println(restaurantService.findByFullText(searchTerm).size());

        Set<Restaurant> restaurants = restaurantService.findByFullText(searchTerm.toString());
        Set<Dish> dishes = dishService.findByFullText(searchTerm.toString());

        int restSize = restaurants.size();
        int dishSize = dishes.size();
        int restTotal, dishTotal;

        if (restSize > 5 && dishSize <= 5) {
            restTotal = 10 - dishSize;

            dishTotal = dishSize;
        } else if (restSize <= 5 && dishSize > 5) {
            restTotal = restSize;

            dishTotal = 10 - restSize;
        } else {
            restTotal = dishTotal = 5;
        }

        int i = 0;
        for (Restaurant restaurant : restaurants)

        {
            suggestions.add(new Suggestion(restaurant.getName(), restaurant.getId(), "restaurant"));
            if (++i == restTotal) {
                break;
            }

        }
        i = 0;
        for (Dish dish : dishes) {
            suggestions.add(new Suggestion(dish.getName(), dish.getId(), "dish"));
            if (++i == dishTotal) {
                break;
            }
        }

        return suggestions;
    }
}

class Meta {
    private String type;
    private int id;

    Meta(String type, int id) {
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

class Suggestion {
    private String value;
    private Meta data;

    Suggestion(String value, int id, String type) {
        this.value = value;

        this.data = new Meta(type, id);


    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


    public Meta getData() {
        return data;
    }

    public void setData(Meta data) {
        this.data = data;
    }
}



