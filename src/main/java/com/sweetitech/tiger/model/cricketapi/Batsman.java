package com.sweetitech.tiger.model.cricketapi;
public class Batsman
{
    private int runs;

    private int dotball;

    private int six;

    private int four;

    private int ball_count;

    private String key;

    public void setRuns(int runs){
        this.runs = runs;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setDotball(int dotball){
        this.dotball = dotball;
    }
    public int getDotball(){
        return this.dotball;
    }
    public void setSix(int six){
        this.six = six;
    }
    public int getSix(){
        return this.six;
    }
    public void setFour(int four){
        this.four = four;
    }
    public int getFour(){
        return this.four;
    }
    public void setBall_count(int ball_count){
        this.ball_count = ball_count;
    }
    public int getBall_count(){
        return this.ball_count;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
}