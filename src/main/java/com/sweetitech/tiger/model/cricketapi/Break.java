package com.sweetitech.tiger.model.cricketapi;
public class Break
{
    private String duration;

    private String reason;

    public void setDuration(String duration){
        this.duration = duration;
    }
    public String getDuration(){
        return this.duration;
    }
    public void setReason(String reason){
        this.reason = reason;
    }
    public String getReason(){
        return this.reason;
    }
}