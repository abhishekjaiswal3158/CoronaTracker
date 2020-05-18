package com.example.networking;

public class Corona {
   private String total;
   private String active;
   private String recover;
   private String state;
   private String death;

    public Corona(String mtotal,String mactive,String mrecover,String mdeath,String mstate){
        total=mtotal;
        active=mactive;
        recover=mrecover;
        state=mstate;
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
    public String getState(){
        return state;
    }
    public String getDeath(){
        return death;
    }
}
