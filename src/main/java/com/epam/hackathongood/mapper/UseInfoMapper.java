package com.epam.hackathongood.mapper;

import java.util.List;
import java.util.Map;

import com.epam.hackathongood.model.UserInfo;

public interface UseInfoMapper {
	Map<String,Object> createUser(UserInfo user);

	List<Map<String, Object>> getUser(UserInfo user);

    void updateUser(String user_id, String nickName);

    void deleteUserByUserId(Integer id);
}
