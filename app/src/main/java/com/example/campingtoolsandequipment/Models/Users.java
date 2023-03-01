package com.example.campingtoolsandequipment.Models;

public class Users {
    String profile_pic;
    String userName;
    String mail;
    String phoneNo;
    String password;
    String userId;
    int usertype ;


    public Users(){}
    //signup constructor
    public Users(String userName, String mail,String phoneNo, String password, int usertype) {
        this.userName = userName;
        this.mail = mail;
        this.phoneNo = phoneNo;
        this.password = password;
        this.usertype = usertype;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getUsertype() {
        return usertype;
    }

    public void setUsertype(int usertype) {
        this.usertype = usertype;
    }
}
