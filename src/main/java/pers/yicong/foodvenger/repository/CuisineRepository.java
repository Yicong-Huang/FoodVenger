package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Cuisine;

import java.util.List;


@Repository("cuisineRepository")
public interface CuisineRepository extends CrudRepository<Cuisine, Integer> {

    List<Cuisine> findAllByTypeContains(String pattern);

}