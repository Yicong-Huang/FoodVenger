package pers.yicong.foodvenger.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Restaurant;

import java.util.Set;


@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, PagingAndSortingRepository<Restaurant, Integer> {
    Set<Restaurant> findAllByAddrContainsOrNameContains(String name, String addr);

    Restaurant findByName(String name);

    Restaurant findByNameEquals(String name);


    Restaurant findByIdEquals(int i);
}
