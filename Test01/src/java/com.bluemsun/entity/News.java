package com.bluemsun.entity;

public class News {
	
	private int id;
	private String title;
	private String keyWord;
	private String promulgator;
	private String content;
	private String type;
	private String date;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPromulgator() {
		return promulgator;
	}
	
	public void setPromulgator(String promulgator) {
		this.promulgator = promulgator;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
}
