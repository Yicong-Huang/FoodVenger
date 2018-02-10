package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Dish;


@Repository("cuisineRepository")
public interface DishRepository extends CrudRepository<Dish, Integer> {


}