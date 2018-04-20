package com.sweetitech.tiger.model.facebook;
public class FacebookProfileCustom
{
    private Picture picture;

    private String id;

    private String email;

    private String name;

    private String gender;
    
    
    private String first_name;
    private String last_name;
    
    

    public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Picture getPicture ()
    {
        return picture;
    }

    public void setPicture (Picture picture)
    {
        this.picture = picture;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [picture = "+picture+", id = "+id+", email = "+email+", name = "+name+", gender = "+gender+"]";
    }
}