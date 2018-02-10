package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.repository.RestaurantRepository;

import java.util.List;

@Service("listService")
public class ListServiceImpl implements ListService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Iterable<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Page<Restaurant> listAllByPage(Pageable pageable, String pattern) {
//        Page<Restaurant> result = restaurantRepository.findAll(pageable);
        List<Restaurant> r1 = restaurantRepository.findAllByAddrContains(pattern);
        List<Restaurant> r2 = restaurantRepository.findAllByNameContains(pattern);

        r1.addAll(r2);
        System.out.println("here!!!" + pageable.getPageNumber());
        System.out.println(pageable.getPageSize());

        int start = pageable.getOffset();
        int end = (start + pageable.getPageSize()) > r1.size() ? r1.size() : (start + pageable.getPageSize());
        Page<Restaurant> page = new PageImpl<Restaurant>(r1.subList(start, end), pageable, r1.size());


        return page;
    }


}