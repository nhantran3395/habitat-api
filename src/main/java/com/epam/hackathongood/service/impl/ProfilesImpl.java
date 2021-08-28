package com.epam.hackathongood.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.hackathongood.mapper.ProfilesMapper;

@Repository
public class ProfilesImpl implements ProfilesMapper{
	@Autowired
    private JdbcTemplate jdbcTemplate;

	@Override
	public List<Map<String, Object>> getProfiles() {
		String sqlStr = "select p.profileId,p.type,p.name,p.title,p.description,p.personality1,p.personality2,p.personality3 from profiles p where 1=1 ;";
		List<Map<String, Object>> sqlRtn = jdbcTemplate.queryForList(sqlStr.toString());
		return sqlRtn;
	}

	@Override
	public List<Map<String, Object>> getProfiles(String profileId) {
		String sqlStr = "select p.profileId,p.type,p.name,p.title,p.description,p.personality1,p.personality2,p.personality3  from profiles p where 1=1 ";
		if(profileId!=null && profileId.length() > 0) {
			sqlStr += " and p.profileId=" + "\"" + profileId + "\"";
		}
		List<Map<String, Object>> sqlRtn = jdbcTemplate.queryForList(sqlStr.toString());
		return sqlRtn;
	}

}
