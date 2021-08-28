package com.epam.hackathongood.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.hackathongood.mapper.UseInfoMapper;
import com.epam.hackathongood.model.UserInfo;
import com.epam.hackathongood.util.EntityUtils;

import java.util.UUID;
@Repository
public class UserInfoImpl implements UseInfoMapper {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Map<String,Object> createUser(UserInfo user) {
    	Map<String,Object> result = new HashMap<String,Object>();
    	UUID uuid  =  UUID.randomUUID();
    	String userName = user.getUserName();
    	String department = user.getDepartment();
    	String email = user.getEmail();
    	String phone = user.getPhone();
    	String password = user.getPassword();
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String registerTime = formatter.format(currentTime);
		try {
			jdbcTemplate.update("INSERT INTO hackforgood_development.user(userId,userName,department,email,phone,password,registerTime) VALUES (?,?,?,?,?,?,?)",uuid.toString(),userName,department,email,phone,password,registerTime);
			result.put("result", "Success");
		}catch(Exception e) {
			result.put("result", e.getMessage());
		}
        return result;
    }

    @Override
    public List<Map<String, Object>> getUser(UserInfo user) {
    	StringBuilder sqlStr = new StringBuilder("select userId,userName,department,email,phone,password,registerTime FROM hackforgood_development.user where 1 = 1 and ");
    	String sql = "";
    	Map<String,Object> map = EntityUtils.entityToMap(user);
    	boolean flag = false;
    	for (Map.Entry<String,Object> entry : map.entrySet()) {
    		String value = entry.getValue()==null?"":entry.getValue().toString();
    		String key = entry.getKey();
    		if(!("serialVersionUID".equals(key)) && (value.length() > 0)) {
    			flag = true;
    			sqlStr.append(key + " = " + "\"" + value + "\"" + " and ");
    		}
    	}
    	if(flag) {
    		int andIndex = sqlStr.lastIndexOf("and");
    		sql = sqlStr.substring(0, andIndex);
    	}
    	List<Map<String, Object>> userList = jdbcTemplate.queryForList(sql);
        //List<UserInfo> userList = jdbcTemplate.query("select userId,userName,department,email,phone,password,registerTime FROM hackforgood_development.user WHERE userName = ? and email = ?",new Object[]{user.getUserName(),user.getEmail()},new BeanPropertyRowMapper(UserInfo.class));
        return userList;
    }

    @Override
    public void updateUser(String user_id, String nickName) {
        jdbcTemplate.update("UPDATE hackforgood_development.user SET nickname = ? WHERE user_id = ?",nickName,user_id);
    }

    @Override
    public void deleteUserByUserId(Integer id) {
        jdbcTemplate.update("DELETE FROM hackforgood_development.user WHERE userId = ?",id);
    }
}