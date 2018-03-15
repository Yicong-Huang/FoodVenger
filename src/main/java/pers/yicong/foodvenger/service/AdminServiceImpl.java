package pers.yicong.foodvenger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Element;
import pers.yicong.foodvenger.model.Dish;
import pers.yicong.foodvenger.model.DishInRest;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.repository.DishRepository;
import pers.yicong.foodvenger.repository.RestaurantRepository;
import pers.yicong.foodvenger.xmlParser.DomParser;

import java.util.HashSet;
import java.util.Set;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    static Set<Restaurant> allRestaurants;
    static Set<Dish> allDishes;
    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DishRepository dishRepository;

    public String addAllRestaurants(String fileName) {
        DomParser<Restaurant> domParser = new DomParser<Restaurant>(fileName) {
            @Override
            protected Restaurant getObject(Element ele) {
                //for each <employee> element get text or int values of
                //name ,id, age and name
                int id = getIntValue(ele, "id");
                String name = getTextValue(ele, "name");
                String addr = getTextValue(ele, "addr");
                String image = getTextValue(ele, "image");

                //Create a new Employee with the value read from the xml nodes
                System.out.println(id + ", " + name + ", " + addr + ", " + image);
                return new Restaurant(id, name, addr, image);

            }
        };
        Set<Restaurant> restaurants = domParser.getObjects();
        allRestaurants = new HashSet<>();
        int i = 0;
        int j = 0;
        int k = 0;
        for (Restaurant restaurant : restaurants) {
            if (restaurantRepository.findByNameEquals(restaurant.getName()) != null) {
//                restaurants.remove(restaurant);
                System.out.println("record " + restaurant.getName() + " is existed!");
                k++;
            } else {

                try {
                    restaurantRepository.save(restaurant);
                    allRestaurants.add(restaurant);
                } catch (Exception e) {
                    j++;
                }
            }

            if (i++ >= 20) {
                restaurantRepository.flush();
                i = 0;
            }


        }


        return "Success, inserted " + allRestaurants.size() + " restaurants\n" + "Total duplicates:" + k + '\n' + "Total bad data:" + j + "\n";


    }

    public String addAllDishInRest(String fileName) {
        DomParser<DishInRest> domParser = new DomParser<DishInRest>(fileName) {
            @Override
            protected DishInRest getObject(Element ele) {
                //for each <employee> element get text or int values of
                //name ,id, age and name
                int did = getIntValue(ele, "did");
                int rid = getIntValue(ele, "rid");

                return new DishInRest(did, rid);

            }
        };
        Set<DishInRest> ins = domParser.getObjects();
        Set<DishInRest> inserted = new HashSet<>();
        int i = 0;
        int j = 0;
        int k = 0;
        for (DishInRest dishInRest : ins) {

            try {

                Restaurant restaurant = findRestaurant(dishInRest.getRid());
                Dish dish = findDish(dishInRest.getDid());

                Set<Dish> dishes = restaurant.getDishes();
                if (dishes == null) {
                    dishes = new HashSet<>();
                }
                dishes.add(dish);
                restaurant.setDishes(dishes);
                restaurantRepository.save(restaurant);
                inserted.add(dishInRest);


            } catch (Exception e) {
                e.printStackTrace();
                j++;
            }


            if (i++ >= 20) {
                restaurantRepository.flush();
                i = 0;
            }


        }


        return "Success, inserted " + inserted.size() + " in relations\n" + "Total duplicates:" + k + '\n' + "Total bad data:" + j + "\n";


    }

    private Dish findDish(int did) {
        for (Dish dish : allDishes) {
            if (dish.getId() == did) {
                return dishRepository.findByNameEquals(dish.getName());
            }
        }
        return null;
    }

    @Override
    public String delAllRestaurants() {

        restaurantRepository.deleteAll();
        return "Success";
    }

    @Override
    public String addAllDishes(String fileName) {


        DomParser<Dish> domParser = new DomParser<Dish>(fileName) {
            @Override
            protected Dish getObject(Element ele) {
                //for each <employee> element get text or int values of
                //name ,id, age and name
                int id = getIntValue(ele, "id");
                String name = getTextValue(ele, "name");
                int cid = getIntValue(ele, "cid");
                float price = getFloatValue(ele, "price");
                int num = getIntValue(ele, "num");

                //Create a new Employee with the value read from the xml nodes
                System.out.println(name + ", " + cid + ", " + price + ", " + num);

                return new Dish(id, name, cid, price, num);

            }
        };

        Set<Dish> dishes = domParser.getObjects();
        allDishes = new HashSet<>();
        int i = 0;
        int j = 0;
        int k = 0;
        for (Dish dish : dishes) {
            if (dishRepository.findByNameEquals(dish.getName()) != null) {
////                dishes.remove(dish);
//                System.out.println( "record " + dish.getName() + " is existed!");
                k++;
            } else {
                try {
                    dishRepository.save(dish);
                    allDishes.add(dish);
                } catch (Exception e) {
                    j++;
                }
            }


        }
        return "Success, inserted " + allDishes.size() + " dishes\n" + "Total duplicates:" + k + '\n' + "Total bad data:" + j + "\n";
    }

    private Restaurant findRestaurant(int id) {
        for (Restaurant restaurant : allRestaurants) {
            if (restaurant.getId() == id) {
                return restaurantRepository.findByNameEquals(restaurant.getName());

            }
        }
        return null;
    }
}
