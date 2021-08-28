package com.epam.hackathongood.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.hackathongood.mapper.QuestionsMapper;
import com.epam.hackathongood.model.QuestionsWrapper;

@Service
public class QuestionsService {
	
	@Autowired
    QuestionsMapper questionsMapper;
	
	public List<QuestionsWrapper> getAllQuestions() {
        return questionsMapper.getAllQuestions();
    }
}
