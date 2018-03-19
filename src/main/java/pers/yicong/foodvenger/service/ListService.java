package pers.yicong.foodvenger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.yicong.foodvenger.model.Cuisine;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.Restaurant;

import java.util.Set;

public interface ListService {
    Set<Restaurant> findRestaurantsByNameWithOffset(String pattern, int offset);

    Iterable<Restaurant> findAllRestaurants();


    Page<Restaurant> listAllByPage(Pageable pageable, String pattern);


    String getCuisineInfo();

    Iterable<Cuisine> listAllCuisines();

    Iterable<Dish> listAllDishes();

    Iterable<Restaurant> listRestaurantsByCuisine(Pageable pageable, String type);

    Page<Restaurant> listAllByPageWithRating(Pageable pageable, String pattern);

    Page<Restaurant> listAllByPageWithName(Pageable pageable, String pattern);
}
