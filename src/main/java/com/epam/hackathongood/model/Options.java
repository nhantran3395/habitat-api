package com.epam.hackathongood.model;

public class Options {
    private String optionId;
    private String questionId;
    private String content;
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "OptionsModel [optionId=" + getOptionId() + ", questionId=" + questionId + ", content=" + content + "]";
	}
}
