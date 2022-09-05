package com.ffds.user;

public class User {

    private String name;

    private String phoneNumber;

    private String gender;

    private String pinCode;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    private void register_user(String name, String gender, String phoneNumber, String pinCode){
        this.gender = gender;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.pinCode = pinCode;
    }
}
