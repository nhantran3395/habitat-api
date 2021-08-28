package com.epam.hackathongood.model;

public class Profiles {
    private String profileId;
    private String userId;
    private String type;
    private String name;
    private String description;
    private String title;
    private String personality1;
    private String personality2;
    private String personality3;
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPersonality1() {
		return personality1;
	}
	public void setPersonality1(String personality1) {
		this.personality1 = personality1;
	}
	public String getPersonality2() {
		return personality2;
	}
	public void setPersonality2(String personality2) {
		this.personality2 = personality2;
	}
	public String getPersonality3() {
		return personality3;
	}
	public void setPersonality3(String personality3) {
		this.personality3 = personality3;
	}
	@Override
	public String toString() {
		return "ProfilesModel [optionId=" + getProfileId() + ", userId=" + userId + ", type=" + type + ",name=" + name + ",description=" + description + ",title=" + title + ",personality1=" + personality1 + ",personality2=" + personality2 + ",personality3=" + personality3 + "]";
	}
}
