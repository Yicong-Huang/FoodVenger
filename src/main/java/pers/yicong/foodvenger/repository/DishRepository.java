package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Dish;

import java.util.List;

//import pers.yicong.foodvenger.model.DishInRest;


@Repository("dishRepository")
public interface DishRepository extends CrudRepository<Dish, Integer> {
    List<Dish> findAllByNameContains(String pattern);


    Dish findByNameEquals(String name);

    Dish findByIdEquals(Integer did);


//    void save(DishInRest dishInRest);
}