package com.sweetitech.tiger.model.cricketapi;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Now
{
    private String bowling_team;

    private String nonstriker;

    private String runs_str;

    private String batting_team;

    private int runs;

    private String innings;

    //private List<Over> recent_overs;
    private List<List<List<String>>> recent_overs;
    

    private String run_rate;

    private Req req;

    private Last_ball last_ball;

    private String striker;

    private Break break_;

    private String bowler;

    private String lead_by_str;

    private Next_ball next_ball;

    private int wicket;

    private String trail_by_str;

    private List<List<List<String>>> recent_overs_str;

    private int balls;

    public void setBowling_team(String bowling_team){
        this.bowling_team = bowling_team;
    }
    public String getBowling_team(){
        return this.bowling_team;
    }
    public void setNonstriker(String nonstriker){
        this.nonstriker = nonstriker;
    }
    public String getNonstriker(){
        return this.nonstriker;
    }
    public void setRuns_str(String runs_str){
        this.runs_str = runs_str;
    }
    public String getRuns_str(){
        return this.runs_str;
    }
    public void setBatting_team(String batting_team){
        this.batting_team = batting_team;
    }
    public String getBatting_team(){
        return this.batting_team;
    }
    public void setRuns(int runs){
        this.runs = runs;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setInnings(String innings){
        this.innings = innings;
    }
    public String getInnings(){
        return this.innings;
    }
    public void setRecent_overs(List<List<List<String>>> recent_overs){
        this.recent_overs = recent_overs;
    }
    public List<List<List<String>>> getRecent_overs(){
        return this.recent_overs;
    }
    public void setRun_rate(String run_rate){
        this.run_rate = run_rate;
    }
    public String getRun_rate(){
        return this.run_rate;
    }
    public void setReq(Req req){
        this.req = req;
    }
    public Req getReq(){
        return this.req;
    }
    public void setLast_ball(Last_ball last_ball){
        this.last_ball = last_ball;
    }
    public Last_ball getLast_ball(){
        return this.last_ball;
    }
    public void setStriker(String striker){
        this.striker = striker;
    }
    public String getStriker(){
        return this.striker;
    }
    public void setBreak(Break break_){
        this.break_ = break_;
    }
    public Break getBreak(){
        return this.break_;
    }
    public void setBowler(String bowler){
        this.bowler = bowler;
    }
    public String getBowler(){
        return this.bowler;
    }
    public void setLead_by_str(String lead_by_str){
        this.lead_by_str = lead_by_str;
    }
    public String getLead_by_str(){
        return this.lead_by_str;
    }
    public void setNext_ball(Next_ball next_ball){
        this.next_ball = next_ball;
    }
    public Next_ball getNext_ball(){
        return this.next_ball;
    }
    public void setWicket(int wicket){
        this.wicket = wicket;
    }
    public int getWicket(){
        return this.wicket;
    }
    public void setTrail_by_str(String trail_by_str){
        this.trail_by_str = trail_by_str;
    }
    public String getTrail_by_str(){
        return this.trail_by_str;
    }
    public void setRecent_overs_str(List<List<List<String>>> recent_overs_str){
        this.recent_overs_str = recent_overs_str;
    }
    
    
    public List<List<List<String>>> getRecent_overs_str(){
        return this.recent_overs_str;
    }
    public void setBalls(int balls){
        this.balls = balls;
    }
    public int getBalls(){
        return this.balls;
    }
}