package pers.yicong.foodvenger.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pers.yicong.foodvenger.model.Restaurant;

public interface ListService {
    Iterable<Restaurant> findAllRestaurants();


    Page<Restaurant> listAllByPage(Pageable pageable);


}
