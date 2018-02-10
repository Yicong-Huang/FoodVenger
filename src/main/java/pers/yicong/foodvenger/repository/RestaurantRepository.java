package pers.yicong.foodvenger.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import pers.yicong.foodvenger.model.Restaurant;

import org.springframework.stereotype.Repository;



@Repository("restaurantRepository")
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, String> {


}
