package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yicong.foodvenger.model.Restaurant;
import pers.yicong.foodvenger.service.ListService;
import pers.yicong.foodvenger.service.UserService;

import java.util.Set;

@RestController
public class AndroidController {

    @Autowired
    private UserService userService;

    @Autowired
    private ListService listService;



    @RequestMapping(path = "/android/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean isAuthorized(@RequestParam("email") String email, @RequestParam("password") String password) {

        System.out.println(email);
        System.out.println(password);
        System.out.println(userService.findUserByEmailAndPassword(email, password));
        return userService.findUserByEmailAndPassword(email, password) != null;
    }


    @RequestMapping(path = "/android/search", method = RequestMethod.GET)
    @ResponseBody
    public Set<Restaurant> search(@RequestParam("pattern") String pattern, @RequestParam("offset") int offset) {
//        JSONArray jsonArray = new JSONArray();
//        for (Restaurant restaurant : listService.findRestaurantsByNameWithOffset(pattern, offset)) {
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("name", restaurant.getName());
//            jsonObject.put("addr", restaurant.getAddr());
//            jsonObject.put("image", restaurant.getImage());
//            jsonArray.put(jsonObject);
//        }

        return listService.findRestaurantsByNameWithOffset(pattern, offset);


    }


}
