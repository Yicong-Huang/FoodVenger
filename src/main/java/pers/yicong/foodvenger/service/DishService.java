
package pers.yicong.foodvenger.service;

import pers.yicong.foodvenger.model.Dish;

import java.util.Set;

public interface DishService {


    Dish getDish(Integer id);

    int save(Dish dish);

    Long count();

    Set<Dish> findByFullText(String s);

}
