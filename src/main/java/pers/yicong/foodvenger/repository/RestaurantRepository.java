package pers.yicong.foodvenger.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Restaurant;

import java.util.List;


@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, String>, PagingAndSortingRepository<Restaurant, String> {
    List<Restaurant> findAllByAddrContainsOrNameContains(String name, String addr);


}
