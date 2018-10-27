package com.bluemsun.entity;

public class Blog {
	private int id;
	private String username ;
	private String title ;
	private String content ;
	private String keyWord ;
	
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
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	@Override
	public String toString() {
		return "Blog{" +
				"id=" + id +
				", username='" + username + '\'' +
				", title='" + title + '\'' +
				", content='" + content + '\'' +
				", keyWord='" + keyWord + '\'' +
				'}';
	}
	
//	public Blog(int id, String username, String title, String content, String keyWord) {
//		this.id = id;
//		this.username = username;
//		this.title = title;
//		this.content = content;
//		this.keyWord = keyWord;
//	}
}
