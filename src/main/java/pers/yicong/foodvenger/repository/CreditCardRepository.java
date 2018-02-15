package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.CreditCard;


@Repository("creditCardRepository")
public interface CreditCardRepository extends CrudRepository<CreditCard, String> {

}