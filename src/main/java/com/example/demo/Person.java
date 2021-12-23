package com.example.demo;

public class Person {
    private int ID;
    private String PIP;
    private String PHONE;

    public Person(){

    }

    public Person(int ID, String PIP, String PHONE){
        this.ID = ID;
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

    public int getId(){
        return ID;
    }

    public void setID(int ID){
        this.ID = ID;
    }
}



