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

    public void updateUser(String user_id, String nickName) {
        userInfoMapper.updateUser(user_id,nickName);
    }

    public void deleteUserByUserId(Integer id) {
        userInfoMapper.deleteUserByUserId(id);
    }
}