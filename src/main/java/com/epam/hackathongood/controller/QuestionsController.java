package com.epam.hackathongood.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.epam.hackathongood.model.QuestionsWrapper;
import com.epam.hackathongood.service.QuestionsService;

@RequestMapping("/questions")
@RestController
public class QuestionsController {
	
	@Autowired
    private QuestionsService questionsService;
	
	@RequestMapping(value="/getAllQuestions",method = RequestMethod.GET)
    public List<QuestionsWrapper> getAllQuestions(HttpServletResponse response){
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,SessionToken,X-TOKEN");
		return questionsService.getAllQuestions();
    }
}
