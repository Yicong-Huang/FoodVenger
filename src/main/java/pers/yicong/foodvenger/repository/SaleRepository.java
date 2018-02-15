package pers.yicong.foodvenger.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pers.yicong.foodvenger.model.Sale;


@Repository("saleRepository")
public interface SaleRepository extends CrudRepository<Sale, Integer> {

}