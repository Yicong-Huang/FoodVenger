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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

        Set<Restaurant> r1 = getAll(pattern);
        List<Restaurant> r2 = new ArrayList<>();
        r2.addAll(r1);

        int start = pageable.getOffset();

        int end = (start + pageable.getPageSize()) > r2.size() ? r2.size() : (start + pageable.getPageSize());

        System.out.println("start:::" + start + "end:::" + end);

        return new PageImpl<>(r2.subList(start, end), pageable, r2.size());
    }

    @Override
    public String getCuisineInfo() {
        return "Cuisine Info";
    }

    @Override
    public Iterable<Cuisine> listAllCuisines() {
        return cuisineRepository.findAll();

    }

    @Override
    public Iterable<Dish> listAllDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Iterable<Restaurant> listRestaurantsByCuisine(Pageable pageable, String type) {

        Set<Restaurant> a = cuisineRepository.findAllByType(type).iterator().next().getRestaurants();
        List<Restaurant> result = new ArrayList<>();
        result.addAll(a);

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        return new PageImpl<>(result.subList(start, end), pageable, result.size());
    }

    @Override
    public Page<Restaurant> listAllByPageWithRating(Pageable pageable, String pattern) {
        Set<Restaurant> raw = getAll(pattern);
        List<Restaurant> result = new ArrayList<>(raw);

        Collections.sort(result, (Restaurant lhs, Restaurant rhs) -> {
            return Float.compare(rhs.getRating().getRating(), lhs.getRating().getRating());
        });

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        return new PageImpl<>(result.subList(start, end), pageable, result.size());

    }

    @Override
    public Page<Restaurant> listAllByPageWithName(Pageable pageable, String pattern) {
        Set<Restaurant> raw = getAll(pattern);
        List<Restaurant> result = new ArrayList<>(raw);

        Collections.sort(result, (Restaurant lhs, Restaurant rhs) -> lhs.getName().compareTo(rhs.getName()));

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > result.size() ? result.size() : (start + pageable.getPageSize());
        return new PageImpl<>(result.subList(start, end), pageable, result.size());

    }


    private Set<Restaurant> getAll(String pattern) {

        Set<Restaurant> r1 = restaurantRepository.findAllByAddrContainsOrNameContains(pattern, pattern);

        List<Cuisine> c1 = cuisineRepository.findAllByTypeContains(pattern);

        for (Cuisine c : c1) {


            r1.addAll(c.getRestaurants());
        }

        List<Dish> d1 = dishRepository.findAllByNameContains(pattern);

        for (Dish d : d1) {
            r1.addAll(d.getRestaurants());
        }
        return r1;
    }


}