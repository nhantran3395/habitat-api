package com.epam.hackathongood.model;

import java.util.List;

public class QuestionsWrapper {
    private Questions questions;
    private List<Options> listOptions;
	public Questions getQuestions() {
		return questions;
	}
	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	public List<Options> getListOptions() {
		return listOptions;
	}
	public void setListOptions(List<Options> listOptions) {
		this.listOptions = listOptions;
	}
   
}
