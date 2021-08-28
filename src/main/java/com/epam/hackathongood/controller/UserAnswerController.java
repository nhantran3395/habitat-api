package com.epam.hackathongood.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.epam.hackathongood.model.UserAnswer;
import com.epam.hackathongood.service.UserAnswerService;

@RequestMapping("/answer")
@RestController
public class UserAnswerController {
	@Autowired
    private UserAnswerService userAnswerService;
	
	@RequestMapping(value="/answerQuestions",method = RequestMethod.POST, consumes="application/json")
    public Map<String,String> getAllQuestions(@RequestBody List<UserAnswer> listUserAnswer,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,SessionToken,X-TOKEN");
		Map<String,String> map = new HashMap<String,String>();
		List<UserAnswer> listAnswerQuestions = userAnswerService.answerQuestion(listUserAnswer);
		if(listAnswerQuestions != null && listAnswerQuestions.size() > 0) {
			UserAnswer userAnswer = listAnswerQuestions.get(0);
			map.put("userId", userAnswer.getUserId());
			return map;
		}
		return map;
    }
	
	@RequestMapping(value="/userProfiles",method = RequestMethod.GET)
    public Map<String,Object> getUserProfiles(@RequestParam(required=false) String userId,HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,SessionToken,X-TOKEN");
		return userAnswerService.getUserProfiles(userId);
    }
}
