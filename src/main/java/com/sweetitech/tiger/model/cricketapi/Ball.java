package com.sweetitech.tiger.model.cricketapi;

public class Ball {

    private String wagon_position;

    private String ball;

    private String innings;

    private String status;

    private Batsman batsman;

    private String wicket_type;

    private Team team;

    private Bowler bowler;

    private String ball_type;

    private String extras;

    private String over;

    private String over_str;

    private String updated;

    private String[] highlight_names_keys;

    private String striker;

    private String runs;

    private String match;

    private String nonstriker;

    private String comment;

    private String wicket;

    private String batting_team;

    private String fielder;

    public String getWagon_position ()
    {
        return wagon_position;
    }

    public void setWagon_position (String wagon_position)
    {
        this.wagon_position = wagon_position;
    }

    public String getBall ()
    {
        return ball;
    }

    public void setBall (String ball)
    {
        this.ball = ball;
    }

    public String getInnings ()
    {
        return innings;
    }

    public void setInnings (String innings)
    {
        this.innings = innings;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    public Batsman getBatsman ()
    {
        return batsman;
    }

    public void setBatsman (Batsman batsman)
    {
        this.batsman = batsman;
    }

    public String getWicket_type ()
    {
        return wicket_type;
    }

    public void setWicket_type (String wicket_type)
    {
        this.wicket_type = wicket_type;
    }

    public Team getTeam ()
    {
        return team;
    }

    public void setTeam (Team team)
    {
        this.team = team;
    }

    public Bowler getBowler ()
    {
        return bowler;
    }

    public void setBowler (Bowler bowler)
    {
        this.bowler = bowler;
    }

    public String getBall_type ()
    {
        return ball_type;
    }

    public void setBall_type (String ball_type)
    {
        this.ball_type = ball_type;
    }

    public String getExtras ()
    {
        return extras;
    }

    public void setExtras (String extras)
    {
        this.extras = extras;
    }

    public String getOver ()
    {
        return over;
    }

    public void setOver (String over)
    {
        this.over = over;
    }

    public String getOver_str ()
    {
        return over_str;
    }

    public void setOver_str (String over_str)
    {
        this.over_str = over_str;
    }

    public String getUpdated ()
    {
        return updated;
    }

    public void setUpdated (String updated)
    {
        this.updated = updated;
    }

    public String[] getHighlight_names_keys ()
    {
        return highlight_names_keys;
    }

    public void setHighlight_names_keys (String[] highlight_names_keys)
    {
        this.highlight_names_keys = highlight_names_keys;
    }

    public String getStriker ()
    {
        return striker;
    }

    public void setStriker (String striker)
    {
        this.striker = striker;
    }

    public String getRuns ()
    {
        return runs;
    }

    public void setRuns (String runs)
    {
        this.runs = runs;
    }

    public String getMatch ()
    {
        return match;
    }

    public void setMatch (String match)
    {
        this.match = match;
    }

    public String getNonstriker ()
    {
        return nonstriker;
    }

    public void setNonstriker (String nonstriker)
    {
        this.nonstriker = nonstriker;
    }

    public String getComment ()
    {
        return comment;
    }

    public void setComment (String comment)
    {
        this.comment = comment;
    }

    public String getWicket ()
    {
        return wicket;
    }

    public void setWicket (String wicket)
    {
        this.wicket = wicket;
    }

    public String getBatting_team ()
    {
        return batting_team;
    }

    public void setBatting_team (String batting_team)
    {
        this.batting_team = batting_team;
    }

    public String getFielder ()
    {
        return fielder;
    }

    public void setFielder (String fielder)
    {
        this.fielder = fielder;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [wagon_position = "+wagon_position+", ball = "+ball+", innings = "+innings+", status = "+status+", batsman = "+batsman+", wicket_type = "+wicket_type+", team = "+team+", bowler = "+bowler+", ball_type = "+ball_type+", extras = "+extras+", over = "+over+", over_str = "+over_str+", updated = "+updated+", highlight_names_keys = "+highlight_names_keys+", striker = "+striker+", runs = "+runs+", match = "+match+", nonstriker = "+nonstriker+", comment = "+comment+", wicket = "+wicket+", batting_team = "+batting_team+", fielder = "+fielder+"]";
    }
}