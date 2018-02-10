package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.repository.RestaurantRepository;

@Service("listService")
public class ListServiceImpl implements ListService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public Iterable<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    @Override
    public Page<Restaurant> listAllByPage(Pageable pageable) {
        Page<Restaurant> result = restaurantRepository.findAll(pageable);

        for (Restaurant r : result.getContent()) {
//            r.setCuisines(new HashSet<Cuisine>((Collection<? extends Cuisine>) cuisineRepository.findAll()));
            System.out.println(r.getRating());
        }

        return result;
    }


}