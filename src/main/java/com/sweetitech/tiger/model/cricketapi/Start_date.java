package com.sweetitech.tiger.model.cricketapi;
public class Start_date
{
    private int timestamp;

    private String iso;

    private String str;

    public void setTimestamp(int timestamp){
        this.timestamp = timestamp;
    }
    public int getTimestamp(){
        return this.timestamp;
    }
    public void setIso(String iso){
        this.iso = iso;
    }
    public String getIso(){
        return this.iso;
    }
    public void setStr(String str){
        this.str = str;
    }
    public String getStr(){
        return this.str;
    }
}