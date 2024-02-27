package com.srsoft.saptagirimess.ui.modal;


public class User {
    private String userName;
    private String userAge;
    private String userEmail;
    private String userId;
    private String userPhoneNumber;
    private int totalBalance;
    private String status;

    public User(String userName, String userAge, String userEmail, String userId, String userPhoneNumber, int totalBalance, String status) {
        this.userName = userName;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userId = userId;
        this.userPhoneNumber = userPhoneNumber;
        this.totalBalance = totalBalance;
    }

    public User() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }

    public int gettotalBalance() {
        return totalBalance;
    }

    public void settotalBalance(int totalBalance) {
        this.totalBalance = totalBalance;
    }
}
