package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Dish;

import java.util.List;


@Repository("dishRepository")
public interface DishRepository extends CrudRepository<Dish, String> {
    List<Dish> findAllByNameContains(String pattern);

}