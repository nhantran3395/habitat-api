package com.epam.hackathongood.service;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.hackathongood.mapper.UserAnswerMapper;
import com.epam.hackathongood.model.UserAnswer;

@Service
public class UserAnswerService {
	
	@Autowired
    UserAnswerMapper userAnswerMapper;
	
	public List<UserAnswer> answerQuestion(List<UserAnswer> userAnswer) {
        return userAnswerMapper.answerQuestion(userAnswer);
    }
	public Map<String,Object> getUserProfiles(String userId){
		return userAnswerMapper.getUserProfiles(userId);
	}
	
}
