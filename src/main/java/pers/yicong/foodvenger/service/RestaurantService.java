package pers.yicong.foodvenger.service;

import pers.yicong.foodvenger.model.Restaurant;

import java.util.Set;

public interface RestaurantService {

    Set<Restaurant> findByFullText(String searchTerm);

    Restaurant getRestaurant(Integer id);

    int save(Restaurant restaurant);

    long count();
}
