package com.sweetitech.tiger.model.cricketapi;
public class Team
{
    private int runs;

    private int extras;

    private int ball_count;

    private int wicket;

    public void setRuns(int runs){
        this.runs = runs;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setExtras(int extras){
        this.extras = extras;
    }
    public int getExtras(){
        return this.extras;
    }
    public void setBall_count(int ball_count){
        this.ball_count = ball_count;
    }
    public int getBall_count(){
        return this.ball_count;
    }
    public void setWicket(int wicket){
        this.wicket = wicket;
    }
    public int getWicket(){
        return this.wicket;
    }
}