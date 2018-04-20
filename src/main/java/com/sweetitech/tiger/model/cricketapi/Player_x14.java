package com.sweetitech.tiger.model.cricketapi;
public class Player_x14
{
    private String fullname;

    private Match match;

    private String name;

    private String seasonal_role;

    private Identified_roles identified_roles;

    public void setFullname(String fullname){
        this.fullname = fullname;
    }
    public String getFullname(){
        return this.fullname;
    }
    public void setMatch(Match match){
        this.match = match;
    }
    public Match getMatch(){
        return this.match;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setSeasonal_role(String seasonal_role){
        this.seasonal_role = seasonal_role;
    }
    public String getSeasonal_role(){
        return this.seasonal_role;
    }
    public void setIdentified_roles(Identified_roles identified_roles){
        this.identified_roles = identified_roles;
    }
    public Identified_roles getIdentified_roles(){
        return this.identified_roles;
    }
}