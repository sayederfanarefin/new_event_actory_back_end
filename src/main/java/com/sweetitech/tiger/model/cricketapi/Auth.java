package com.sweetitech.tiger.model.cricketapi;

public class Auth
{
    private String expires;

    private String access_token;

    public String getExpires ()
    {
        return expires;
    }

    public void setExpires (String expires)
    {
        this.expires = expires;
    }

    public String getAccess_token ()
    {
        return access_token;
    }

    public void setAccess_token (String access_token)
    {
        this.access_token = access_token;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [expires = "+expires+", access_token = "+access_token+"]";
    }
}