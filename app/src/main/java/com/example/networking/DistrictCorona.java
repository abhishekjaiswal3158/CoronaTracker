package com.example.networking;

public class DistrictCorona {

    private String total;
    private String active;
    private String recover;
    private String district;
    private String death;


    public DistrictCorona(String mtotal,String mactive,String mrecover,String mdeath,String mdistrict){
        total=mtotal;
        active=mactive;
        recover=mrecover;
        district=mdistrict;
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
    public String getDistrict(){
        return district;
    }
    public String getDeath(){
        return death;
    }

}
