package pers.yicong.foodvenger.service;

import pers.yicong.foodvenger.model.Restaurant;

public interface RestaurantService {


    Restaurant getRestaurant(Integer id);

    int save(Restaurant restaurant);

    long count();
}
