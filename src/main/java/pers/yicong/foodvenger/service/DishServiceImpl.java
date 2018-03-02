package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.repository.DishRepository;

@Service("dishService")
public class DishServiceImpl implements DishService {


    @Autowired
    DishRepository dishRepository;

    @Override
    public Dish getDish(Integer id) {
        return dishRepository.findOne(id);
    }

    @Override
    public int save(Dish dish) {
        if (dishRepository.findByNameEquals(dish.getName()) == null) {
            Dish d = dishRepository.save(dish);
            return d.getId();
        }
        return -1;

    }

    @Override
    public Long count() {
        return dishRepository.count();
    }

}
