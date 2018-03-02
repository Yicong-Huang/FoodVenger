package pers.yicong.foodvenger.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.repository.CuisineRepository;


@Service("cuisineService")
public class CuisineServiceImpl implements CuisineService {
    @Autowired
    private CuisineRepository cuisineRepository;

    @Override
    public Long count() {
        return cuisineRepository.count();

    }
}
