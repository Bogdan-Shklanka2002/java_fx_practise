package com.example.demo;

public class Person {
    private String PIP;
    private String PHONE;

    public Person(String PIP, String PHONE){
        this.PIP = PIP;
        this.PHONE = PHONE;
    }
    public String getPIP(){
        return PIP;
    }

    public void setPIP(String PIP){
        this.PIP = PIP;
    }

    public String getPhone(){
        return PHONE;
    }

    public void setPHONE(String PHONE){
        this.PHONE = PHONE;
    }
}



