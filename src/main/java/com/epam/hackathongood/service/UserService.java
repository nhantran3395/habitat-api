package com.epam.hackathongood.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.hackathongood.mapper.UseInfoMapper;
import com.epam.hackathongood.model.UserInfo;

@Service
public class UserService {

    @Autowired
    UseInfoMapper userInfoMapper;

    public Map<String,Object> createUser(UserInfo user) {
        return userInfoMapper.createUser(user);
    }

    public List<Map<String, Object>> getUser(UserInfo user) {
        return userInfoMapper.getUser(user);
    }

    public int updateUser(UserInfo user) {
        return userInfoMapper.updateUser(user);
    }

    public int deleteUserByUserId(String id) {
        return userInfoMapper.deleteUserByUserId(id);
    }
    
    public UserInfo login(UserInfo user) {
    	return userInfoMapper.login(user);
    }
}
