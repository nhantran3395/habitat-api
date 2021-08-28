package com.epam.hackathongood.mapper;

import java.util.List;
import java.util.Map;


public interface ProfilesMapper {
	List<Map<String, Object>> getProfiles();
	List<Map<String, Object>> getProfiles(String profileId);
}
