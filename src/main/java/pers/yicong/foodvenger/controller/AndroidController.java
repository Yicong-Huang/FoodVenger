package pers.yicong.foodvenger.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.yicong.foodvenger.service.UserService;

@RestController
public class AndroidController {

    @Autowired
    private UserService userService;


    @RequestMapping(path = "/android/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean isAuthorized(@RequestParam("email") String email, @RequestParam("password") String password) {

        return userService.findUserByEmailAndPassword(email, password) != null;
    }

}
