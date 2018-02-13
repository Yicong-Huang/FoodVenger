package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Cuisine;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.repository.CuisineRepository;
import pers.yicong.foodvenger.repository.DishRepository;
import pers.yicong.foodvenger.repository.RestaurantRepository;

import java.util.List;

@Service("listService")
public class ListServiceImpl implements ListService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private CuisineRepository cuisineRepository;

    @Autowired
    private DishRepository dishRepository;


    @Override
    public Iterable<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Page<Restaurant> listAllByPage(Pageable pageable, String pattern) {

        List<Restaurant> r1 = restaurantRepository.findAllByAddrContainsOrNameContains(pattern, pattern);

        List<Cuisine> c1 = cuisineRepository.findAllByTypeContains(pattern);

        for (Cuisine c : c1) {
            r1.addAll(c.getRestaurants());
        }

        List<Dish> d1 = dishRepository.findAllByNameContains(pattern);

        for (Dish d : d1) {
            r1.addAll(d.getRestaurants());
        }


        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > r1.size() ? r1.size() : (start + pageable.getPageSize());


        return new PageImpl<>(r1.subList(start, end), pageable, r1.size());
    }

    @Override
    public String getCuisineInfo() {
        return "Cuisine Info";
    }


}