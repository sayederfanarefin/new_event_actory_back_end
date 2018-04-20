package com.sweetitech.tiger.model.cricketapi;
public class Next_ball
{
    private String nonstriker;

    private String bowler;

    private int over;

    private String striker;

    private int ball;

    private String over_str;

    public void setNonstriker(String nonstriker){
        this.nonstriker = nonstriker;
    }
    public String getNonstriker(){
        return this.nonstriker;
    }
    public void setBowler(String bowler){
        this.bowler = bowler;
    }
    public String getBowler(){
        return this.bowler;
    }
    public void setOver(int over){
        this.over = over;
    }
    public int getOver(){
        return this.over;
    }
    public void setStriker(String striker){
        this.striker = striker;
    }
    public String getStriker(){
        return this.striker;
    }
    public void setBall(int ball){
        this.ball = ball;
    }
    public int getBall(){
        return this.ball;
    }
    public void setOver_str(String over_str){
        this.over_str = over_str;
    }
    public String getOver_str(){
        return this.over_str;
    }
}