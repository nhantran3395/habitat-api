package com.epam.hackathongood.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.hackathongood.service.ProfilesService;

@RequestMapping("/profiles")
@RestController
public class ProfilesController {
	@Autowired
    private ProfilesService profilesService;
	
	@RequestMapping(value="/getProfiles",method = RequestMethod.GET)
    public List<Map<String, Object>> getProfiles(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return profilesService.getProfiles();
    }
	@RequestMapping(value="/getProfilesById",method = RequestMethod.GET)
    public List<Map<String, Object>> getProfiles(@RequestParam(required=false) String profileId,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		return profilesService.getProfiles(profileId);
    }
}