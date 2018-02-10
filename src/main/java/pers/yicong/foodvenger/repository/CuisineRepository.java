package pers.yicong.foodvenger.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import pers.yicong.foodvenger.model.Cuisine;

import org.springframework.stereotype.Repository;



@Repository("cuisineRepository")
public interface CuisineRepository extends CrudRepository<Cuisine, Integer> {



}