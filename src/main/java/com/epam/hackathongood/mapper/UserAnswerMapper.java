package com.epam.hackathongood.mapper;

import java.util.List;
import java.util.Map;

import com.epam.hackathongood.model.UserAnswer;

public interface UserAnswerMapper {
	List<UserAnswer> answerQuestion(List<UserAnswer> userAnswer);
	Map<String,Object> getUserProfiles(String userId);
}
