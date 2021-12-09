package com.conan.dao;

public class Members2 {
String userId;
String userPwd;
String userName;
String phoneNo;
String address;
String email;
String regDate;
public Members2() {
	// TODO Auto-generated constructor stub
}
public Members2(String userId,String userPwd,String userName,String phoneNo,
		String address,String email) {
	this.address=address;
	this.userPwd=userPwd;
	this.userName=userName;
	this.phoneNo=phoneNo;
	this.address=address;
	this.email=email;
}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public String getUserPwd() {
	return userPwd;
}
public void setUserPwd(String userPwd) {
	this.userPwd = userPwd;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getPhoneNo() {
	return phoneNo;
}
public void setPhoneNo(String phoneNo) {
	this.phoneNo = phoneNo;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getRegDate() {
	return regDate;
}
public void setRegDate(String regDate) {
	this.regDate = regDate;
}
}
