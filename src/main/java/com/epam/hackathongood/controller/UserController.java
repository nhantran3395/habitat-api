package com.epam.hackathongood.controller;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.hackathongood.model.UserInfo;
import com.epam.hackathongood.service.UserService;

@RequestMapping("/user")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value="/createUser",method = RequestMethod.POST, consumes="application/json")
    public Map<String,Object> createUser(@RequestBody UserInfo user){
        return userService.createUser(user);
    }

    @RequestMapping(value="/updateUser/{id}",method = RequestMethod.PUT, consumes="application/json")
    public void updateUser(@PathVariable("id") String user_id, @RequestParam("nickName") String nickName){
        userService.updateUser(user_id,nickName);
    }

    @RequestMapping(value="/getUser",method = RequestMethod.POST, consumes="application/json")
    public List<Map<String, Object>> getUser(@RequestBody UserInfo user){
        return userService.getUser(user);
    }

    @RequestMapping(value="/deleteUserByUserId/{id}",method = RequestMethod.DELETE, consumes="application/json")
    public void deleteUserByUserId(@PathVariable("id")  Integer id){
        userService.deleteUserByUserId(id);
    }
}
