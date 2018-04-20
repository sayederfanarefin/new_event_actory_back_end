package com.sweetitech.tiger.model.facebook;

public class Data
{
    private String is_silhouette;

    private String height;

    private String width;

    private String url;

    public String getIs_silhouette ()
    {
        return is_silhouette;
    }

    public void setIs_silhouette (String is_silhouette)
    {
        this.is_silhouette = is_silhouette;
    }

    public String getHeight ()
    {
        return height;
    }

    public void setHeight (String height)
    {
        this.height = height;
    }

    public String getWidth ()
    {
        return width;
    }

    public void setWidth (String width)
    {
        this.width = width;
    }

    public String getUrl ()
    {
        return url;
    }

    public void setUrl (String url)
    {
        this.url = url;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [is_silhouette = "+is_silhouette+", height = "+height+", width = "+width+", url = "+url+"]";
    }
}
			
