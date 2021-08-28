package com.epam.hackathongood.model;

public class UserAnswer {
    private String answerId;
    private String userId;
    private String questionId;
    private String optionId;
    private String answerTime;
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getQuestionId() {
		return questionId;
	}
	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}
	public String getOptionId() {
		return optionId;
	}
	public void setOptionId(String optionId) {
		this.optionId = optionId;
	}
	public String getAnswerTime() {
		return answerTime;
	}
	public void setAnswerTime(String answerTime) {
		this.answerTime = answerTime;
	}
	@Override
	public String toString() {
		return "UserAnswerModel [answerId=" + getAnswerId() + ", userId=" + userId + ", questionId=" + questionId + ", optionId=" + optionId + ", answerTime=" + answerTime + "]";
	}
}
