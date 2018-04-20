package com.sweetitech.tiger.model.cricketapi;
import java.util.ArrayList;
import java.util.List;
public class Last_ball
{
    private String comment;

    private String wicket_type;

    private int over;

    private String ball;

    private List<List<String>> highlight_names_keys;

    private String innings;

    private String wagon_position;

    private String wicket;

    private String match;

    private String status;

    private String updated;

    private String ball_type;

    private Batsman batsman;

    private String striker;

    private String batting_team;

    private String key;

    private Fielder fielder;

    private String nonstriker;

    private int runs;

    private Bowler bowler;

    private String over_str;

    private String extras;

    private Team team;

    public void setComment(String comment){
        this.comment = comment;
    }
    public String getComment(){
        return this.comment;
    }
    public void setWicket_type(String wicket_type){
        this.wicket_type = wicket_type;
    }
    public String getWicket_type(){
        return this.wicket_type;
    }
    public void setOver(int over){
        this.over = over;
    }
    public int getOver(){
        return this.over;
    }
    public void setBall(String ball){
        this.ball = ball;
    }
    public String getBall(){
        return this.ball;
    }
    public void setHighlight_names_keys(List<List<String>> highlight_names_keys){
        this.highlight_names_keys = highlight_names_keys;
    }
    public List<List<String>> getHighlight_names_keys(){
        return this.highlight_names_keys;
    }
    public void setInnings(String innings){
        this.innings = innings;
    }
    public String getInnings(){
        return this.innings;
    }
    public void setWagon_position(String wagon_position){
        this.wagon_position = wagon_position;
    }
    public String getWagon_position(){
        return this.wagon_position;
    }
    public void setWicket(String wicket){
        this.wicket = wicket;
    }
    public String getWicket(){
        return this.wicket;
    }
    public void setMatch(String match){
        this.match = match;
    }
    public String getMatch(){
        return this.match;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setUpdated(String updated){
        this.updated = updated;
    }
    public String getUpdated(){
        return this.updated;
    }
    public void setBall_type(String ball_type){
        this.ball_type = ball_type;
    }
    public String getBall_type(){
        return this.ball_type;
    }
    public void setBatsman(Batsman batsman){
        this.batsman = batsman;
    }
    public Batsman getBatsman(){
        return this.batsman;
    }
    public void setStriker(String striker){
        this.striker = striker;
    }
    public String getStriker(){
        return this.striker;
    }
    public void setBatting_team(String batting_team){
        this.batting_team = batting_team;
    }
    public String getBatting_team(){
        return this.batting_team;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    public void setFielder(Fielder fielder){
        this.fielder = fielder;
    }
    public Fielder getFielder(){
        return this.fielder;
    }
    public void setNonstriker(String nonstriker){
        this.nonstriker = nonstriker;
    }
    public String getNonstriker(){
        return this.nonstriker;
    }
    public void setRuns(int runs){
        this.runs = runs;
    }
    public int getRuns(){
        return this.runs;
    }
    public void setBowler(Bowler bowler){
        this.bowler = bowler;
    }
    public Bowler getBowler(){
        return this.bowler;
    }
    public void setOver_str(String over_str){
        this.over_str = over_str;
    }
    public String getOver_str(){
        return this.over_str;
    }
    public void setExtras(String extras){
        this.extras = extras;
    }
    public String getExtras(){
        return this.extras;
    }
    public void setTeam(Team team){
        this.team = team;
    }
    public Team getTeam(){
        return this.team;
    }
}
