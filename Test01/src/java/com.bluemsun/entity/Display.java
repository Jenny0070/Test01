package com.bluemsun.entity;

public class Display {
	private int id;
	private String username;
	private String title;
	private String pictures;
	private String description;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPictures() {
		return pictures;
	}
	
	public void setPictures(String pictures) {
		this.pictures = pictures;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Display{" +
				"id=" + id +
				", username='" + username + '\'' +
				", pictures='" + pictures + '\'' +
				", description='" + description + '\'' +
				'}';
	}
//	//构造器
//
//	public Display(int id, String username, String pictures, String description) {
//		this.id = id;
//		this.username = username;
//		this.pictures = pictures;
//		this.description = description;
//	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
