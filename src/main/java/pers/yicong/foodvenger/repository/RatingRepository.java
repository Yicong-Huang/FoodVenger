package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Rating;

@Repository("ratingRepository")
public interface RatingRepository extends CrudRepository<Rating, Integer> {


}