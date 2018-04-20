package com.sweetitech.tiger.model.cricketapi;
public class Identified_roles
{
    private String keeper;

    private String bowler;

    private String batsman;

    public void setKeeper(String keeper){
        this.keeper = keeper;
    }
    public String getKeeper(){
        return this.keeper;
    }
    public void setBowler(String bowler){
        this.bowler = bowler;
    }
    public String getBowler(){
        return this.bowler;
    }
    public void setBatsman(String batsman){
        this.batsman = batsman;
    }
    public String getBatsman(){
        return this.batsman;
    }
}