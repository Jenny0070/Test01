package com.bluemsun.entity;

public class Inform {
	private int id;
	private String  title;
	private String content;
	private String issuer;
	private String hits;
	private String date;
	private String limit;
	private String keyWord;
	
	public Inform(){
	}
	
	public Inform(int id, String title, String content, String issuer, String hits, String date, String limit, String keyWord) {
		this.id = id;
		this.title = title;
		this.content = content;
		this.issuer = issuer;
		this.hits = hits;
		this.date = date;
		this.limit = limit;
		this.keyWord=keyWord;
	}
	
	public String getKeyWord() {
		return keyWord;
	}
	
	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
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
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getIssuer() {
		return issuer;
	}
	
	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}
	
	public String getHits() {
		return hits;
	}
	
	public void setHits(String hits) {
		this.hits = hits;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getLimit() {
		return limit;
	}
	
	public void setLimit(String limit) {
		this.limit = limit;
	}
}






