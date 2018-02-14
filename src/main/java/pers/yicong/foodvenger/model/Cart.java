package pers.yicong.foodvenger.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Scope("session")
public class Cart {

    private Set<Dish> dishes = new HashSet<>();

    public Set<Dish> getDishes() {

        return dishes;
    }

    public void clear() {
        this.dishes.clear();
    }

    public void addDish(Dish dish) {

        for (Dish d : dishes) {
            if (d.getId().equals(dish.getId())) {
                d.setNum(d.getNum() + 1);
                return;
            }
        }
        dish.setNum(dish.getNum() + 1);
        dishes.add(dish);

    }

    public int size() {
        return dishes.size();
    }

    public void delDish(Dish dish) {
        for (Dish d : dishes) {
            if (d.getId().equals(dish.getId())) {
                dishes.remove(d);
                return;
            }
        }


    }
}