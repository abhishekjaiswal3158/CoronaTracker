package com.example.networking;

public class Global {
    private String total;
    private String  active;
    private String recover;
    private String country;
    private String death;


    public Global(String mtotal,String mactive,String mrecover,String mdeath,String mcountry){
        total=mtotal;
        active=mactive;
        recover=mrecover;
        country=mcountry;
        death=mdeath;
    }


    public String getTotal(){
        return total;
    }
    public String getActive(){
        return active;
    }
    public String getRecover(){
        return recover;
    }
    public String getCountry(){
        return country;
    }
    public String getDeath(){
        return death;
    }

}
