package pers.yicong.foodvenger.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Dish;

import java.util.List;
import java.util.Set;

//import pers.yicong.foodvenger.model.DishInRest;


@Repository("dishRepository")
public interface DishRepository extends CrudRepository<Dish, Integer> {


    @Query(value = "SELECT * FROM dishes WHERE MATCH (name) AGAINST (:searchTerm IN BOOLEAN MODE)", nativeQuery = true)
    Set<Dish> findByFullText(@Param("searchTerm") String searchTerm);



    List<Dish> findAllByNameContains(String pattern);


    Dish findByNameEquals(String name);

    Dish findByIdEquals(Integer did);


//    void save(DishInRest dishInRest);
}