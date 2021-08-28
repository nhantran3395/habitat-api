package com.epam.hackathongood.mapper;

import java.util.List;

import com.epam.hackathongood.model.QuestionsWrapper;

public interface QuestionsMapper {
	List<QuestionsWrapper> getAllQuestions();
}
