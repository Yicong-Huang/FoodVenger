package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Sale;
import pers.yicong.foodvenger.repository.SaleRepository;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    @Autowired
    SaleRepository saleRepository;

    @Override
    public void save(Sale sale) {
        saleRepository.save(sale);
    }
}
