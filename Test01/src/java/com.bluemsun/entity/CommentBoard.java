package com.bluemsun.entity;

public class CommentBoard {
	private int id;
	private String content;
	private String username;
	private String target;
	private String keyWord;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
	@Override
	public String toString() {
		return "CommentBoard{" +
				"id=" + id +
				", content='" + content + '\'' +
				", username='" + username + '\'' +
				", target='" + target + '\'' +
				", keyWord='" + keyWord + '\'' +
				'}';
	}
	
//	public CommentBoard(int id, String content, String username, String target, String keyWord) {
//		this.id = id;
//		this.content = content;
//		this.username = username;
//		this.target = target;
//		this.keyWord = keyWord;
//	}
}
