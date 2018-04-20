


 
package com.sweetitech.tiger.model.cricketapi;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;




@JsonIgnoreProperties(ignoreUnknown = true)
public class Card
{
    private String related_name;

    private List<String> inactive_balls;

    private Msgs msgs;

    private List<List<String>> batting_order;

    private Toss toss;

    private String winner_team;

    private Winning_ratio winning_ratio;

    private String long_description;

    private Innings innings;

    private String title;

    private boolean dl_applied;

    private Start_date start_date;

    private String status;

    private String man_of_match;

    private String description;

    private String short_name;

    private String format;

    private Season season;

    private String expires;

    private String status_overview;

    private int match_overs;

    private String key;

    private Now now;

    private String day;

    private Balls balls;

    private String name;

    private String ref;

    private String venue;

    private Teams teams;

    private Players players;

    private String data_review_checkpoint;

    private String first_batting;

    private String result_by;

    private String cache_key;

    public void setRelated_name(String related_name){
        this.related_name = related_name;
    }
    public String getRelated_name(){
        return this.related_name;
    }
    public void setInactive_balls(List<String> inactive_balls){
        this.inactive_balls = inactive_balls;
    }
    public List<String> getInactive_balls(){
        return this.inactive_balls;
    }
    public void setMsgs(Msgs msgs){
        this.msgs = msgs;
    }
    public Msgs getMsgs(){
        return this.msgs;
    }
    public void setBatting_order(List<List<String>> batting_order){
        this.batting_order = batting_order;
    }
    public List<List<String>> getBatting_order(){
        return this.batting_order;
    }
    public void setToss(Toss toss){
        this.toss = toss;
    }
    public Toss getToss(){
        return this.toss;
    }
    public void setWinner_team(String winner_team){
        this.winner_team = winner_team;
    }
    public String getWinner_team(){
        return this.winner_team;
    }
    public void setWinning_ratio(Winning_ratio winning_ratio){
        this.winning_ratio = winning_ratio;
    }
    public Winning_ratio getWinning_ratio(){
        return this.winning_ratio;
    }
    public void setLong_description(String long_description){
        this.long_description = long_description;
    }
    public String getLong_description(){
        return this.long_description;
    }
    public void setInnings(Innings innings){
        this.innings = innings;
    }
    public Innings getInnings(){
        return this.innings;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return this.title;
    }
    public void setDl_applied(boolean dl_applied){
        this.dl_applied = dl_applied;
    }
    public boolean getDl_applied(){
        return this.dl_applied;
    }
    public void setStart_date(Start_date start_date){
        this.start_date = start_date;
    }
    public Start_date getStart_date(){
        return this.start_date;
    }
    public void setStatus(String status){
        this.status = status;
    }
    public String getStatus(){
        return this.status;
    }
    public void setMan_of_match(String man_of_match){
        this.man_of_match = man_of_match;
    }
    public String getMan_of_match(){
        return this.man_of_match;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setShort_name(String short_name){
        this.short_name = short_name;
    }
    public String getShort_name(){
        return this.short_name;
    }
    public void setFormat(String format){
        this.format = format;
    }
    public String getFormat(){
        return this.format;
    }
    public void setSeason(Season season){
        this.season = season;
    }
    public Season getSeason(){
        return this.season;
    }
    public void setExpires(String expires){
        this.expires = expires;
    }
    public String getExpires(){
        return this.expires;
    }
    public void setStatus_overview(String status_overview){
        this.status_overview = status_overview;
    }
    public String getStatus_overview(){
        return this.status_overview;
    }
    public void setMatch_overs(int match_overs){
        this.match_overs = match_overs;
    }
    public int getMatch_overs(){
        return this.match_overs;
    }
    public void setKey(String key){
        this.key = key;
    }
    public String getKey(){
        return this.key;
    }
    public void setNow(Now now){
        this.now = now;
    }
    public Now getNow(){
        return this.now;
    }
    public void setDay(String day){
        this.day = day;
    }
    public String getDay(){
        return this.day;
    }
    public void setBalls(Balls balls){
        this.balls = balls;
    }
    public Balls getBalls(){
        return this.balls;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setRef(String ref){
        this.ref = ref;
    }
    public String getRef(){
        return this.ref;
    }
    public void setVenue(String venue){
        this.venue = venue;
    }
    public String getVenue(){
        return this.venue;
    }
    public void setTeams(Teams teams){
        this.teams = teams;
    }
    public Teams getTeams(){
        return this.teams;
    }
    public void setPlayers(Players players){
        this.players = players;
    }
    public Players getPlayers(){
        return this.players;
    }
    public void setData_review_checkpoint(String data_review_checkpoint){
        this.data_review_checkpoint = data_review_checkpoint;
    }
    public String getData_review_checkpoint(){
        return this.data_review_checkpoint;
    }
    public void setFirst_batting(String first_batting){
        this.first_batting = first_batting;
    }
    public String getFirst_batting(){
        return this.first_batting;
    }
    public void setResult_by(String result_by){
        this.result_by = result_by;
    }
    public String getResult_by(){
        return this.result_by;
    }
    public void setCache_key(String cache_key){
        this.cache_key = cache_key;
    }
    public String getCache_key(){
        return this.cache_key;
    }
}
