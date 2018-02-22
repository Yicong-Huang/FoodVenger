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

}
