package pers.yicong.foodvenger.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Restaurant;

import java.util.Set;


@Repository("restaurantRepository")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>, PagingAndSortingRepository<Restaurant, Integer> {
    @Query(value = "SELECT * FROM restaurants WHERE MATCH (name) AGAINST (:searchTerm IN BOOLEAN MODE)", nativeQuery = true)
    Set<Restaurant> findByFullText(@Param("searchTerm") String searchTerm);


    Set<Restaurant> findAllByAddrContainsOrNameContains(String name, String addr);

    Restaurant findByName(String name);

    Restaurant findByNameEquals(String name);


    Restaurant findByIdEquals(int i);
}
