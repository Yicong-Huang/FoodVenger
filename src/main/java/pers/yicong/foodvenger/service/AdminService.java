package pers.yicong.foodvenger.service;

public interface AdminService {

    String addAllRestaurants(String fileName);

    String delAllRestaurants();

    String addAllDishes(String fileName);

    String addAllDishInRest(String fileName);
}
