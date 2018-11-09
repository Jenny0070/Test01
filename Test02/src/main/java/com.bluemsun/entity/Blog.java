package com.bluemsun.entity;

public class Blog {
	private int id;
	private String username ;
	private String title ;
	private String content ;
	private String keyWord1 ;
	private String keyWord2 ;
	private String date;
	private int hits;
	public  int like;
	
	public int getHits() {
		return hits;
	}
	
	public void setHits(int hits) {
		this.hits = hits;
	}
	
	public int getLike() {
		return like;
	}
	
	public void setLike(int like) {
		this.like = like;
	}
	
	
	
	public String getKeyWord1() {
		return keyWord1;
	}
	
	public void setKeyWord1(String keyWord1) {
		this.keyWord1 = keyWord1;
	}
	
	public String getKeyWord2() {
		return keyWord2;
	}
	
	public void setKeyWord2(String keyWord2) {
		this.keyWord2 = keyWord2;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
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
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	
}
