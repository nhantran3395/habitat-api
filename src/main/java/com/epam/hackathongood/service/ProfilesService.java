package com.epam.hackathongood.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.hackathongood.mapper.ProfilesMapper;

@Service
public class ProfilesService {
	
	@Autowired
    ProfilesMapper profilesMapper;
	
	public List<Map<String, Object>> getProfiles() {
        return profilesMapper.getProfiles();
    }
	
	public List<Map<String, Object>> getProfiles(String profileId) {
        return profilesMapper.getProfiles(profileId);
    }
} 
