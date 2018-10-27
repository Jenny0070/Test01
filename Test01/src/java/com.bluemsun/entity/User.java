package com.bluemsun.entity;

import org.apache.commons.dbutils.ResultSetHandler;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String gender;
	private String identity;
	private String phoneNum;
	
	public User( String username, String password, String email, String gender, String identity, String phoneNum) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.identity = identity;
		this.phoneNum = phoneNum;
	}
	public User( String username, String password, String email, String gender, String identity, String phoneNum,int id) {
		
		this.username = username;
		this.password = password;
		this.email = email;
		this.gender = gender;
		this.identity = identity;
		this.phoneNum = phoneNum;
		this.id=id;
	}
	
	public User() {
	
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
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getIdentity() {
		return identity;
	}
	
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	
	public String getPhoneNum() {
		return phoneNum;
	}
	
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
}
