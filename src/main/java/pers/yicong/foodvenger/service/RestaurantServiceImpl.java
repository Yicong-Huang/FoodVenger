package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.repository.CuisineRepository;
import pers.yicong.foodvenger.repository.DishRepository;
import pers.yicong.foodvenger.repository.RestaurantRepository;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private DishRepository dishRepository;

    @Override
    public Restaurant getRestaurant(Integer id) {
        return restaurantRepository.findOne(id);
    }


}
