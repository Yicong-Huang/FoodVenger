package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Cuisine;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.Rating;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.repository.CuisineRepository;
import pers.yicong.foodvenger.repository.DishRepository;
import pers.yicong.foodvenger.repository.RatingRepository;
import pers.yicong.foodvenger.repository.RestaurantRepository;

import java.util.HashSet;
import java.util.Set;

@Service("restaurantService")
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private CuisineRepository cuisineRepository;
    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private RatingRepository ratingRepository;
//    @Autowired
//    private CuisineInRestRepository cuisineInRestRepository;


    @Override
    public Restaurant getRestaurant(Integer id) {
        return restaurantRepository.findOne(id);
    }

    @Override
    public int save(Restaurant restaurant) {
        Restaurant r = restaurantRepository.findByName(restaurant.getName());
        if (r != null) {
            return -1;
        } else {


            Cuisine c = cuisineRepository.findByType(restaurant.getaCuisine());
            if (c == null) {
                c = cuisineRepository.save(new Cuisine(restaurant.getaCuisine()));
            }
            Set<Cuisine> cuisines = new HashSet<>();
            cuisines.add(c);
            restaurant.setCuisines(cuisines);


            Dish d = dishRepository.findByNameEquals(restaurant.getaDish());
            if (d == null) {
                d = new Dish(restaurant.getaDish());
                d.setCid(c.getId());
                d = dishRepository.save(d);
            }
            Set<Dish> dishes = new HashSet<>();
            dishes.add(d);
            restaurant.setDishes(dishes);


            r = restaurantRepository.saveAndFlush(restaurant);
            ratingRepository.save(new Rating(r.getId(), restaurant.getaRating(), 1));
        }

        return r.getId();
    }

    @Override
    public long count() {
        return restaurantRepository.count();
    }


}
