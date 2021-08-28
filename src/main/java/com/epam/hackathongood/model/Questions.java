package com.epam.hackathongood.model;

public class Questions {
    private String questionId;
    private String questionText;
    private String selectedOption;
    private String selectedTime;
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getQuestionText() {
		return questionText;
	}
	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}
	public String getSelectedOption() {
		return selectedOption;
	}
	public void setSelectedOption(String selectedOption) {
		this.selectedOption = selectedOption;
	}
	public String getSelectedTime() {
		return selectedTime;
	}
	public void setSelectedTime(String selectedTime) {
		this.selectedTime = selectedTime;
	}
	@Override
	public String toString() {
		return "QuestionsModel [questionId=" + getQuestionId() + ", questionText=" + questionText  + ", selectedOption="
				+ selectedOption + ", selectedTime=" + selectedTime + "]";
	}
    
}
