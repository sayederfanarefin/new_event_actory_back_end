package com.sweetitech.tiger.model;



import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "trivia_corner")
public class TriviaCorner {

    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    
    private Long id;

    private String title;


    @Column(columnDefinition="LONGTEXT")
    private String description;

    @Column(name = "created_at")
    public Date createdAt;
    
    @Column(name = "live_at")
    public Date liveAt;

    @OneToOne(fetch = FetchType.EAGER)
   @JoinTable(name = "trivia_corner_image", joinColumns = @JoinColumn(name = "trivia_corner_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
   private Image image;
   

	public Image getImage() {
	return image;
}

public void setImage(Image featruedImage) {
	this.image = featruedImage;
}



	public TriviaCorner(String title, String description, Date liveAt, Image image) {
	super();
	this.title = title;
	this.description = description;
	this.liveAt = liveAt;
	this.image = image;
}
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.000 ", timezone="UTC")
	public Date getLiveAt() {
	return liveAt;
}

	
public void setLiveAt(Date liveAt) {
	this.liveAt = liveAt;
}

	public TriviaCorner() {
        super();
       
    }

    public Long getId() {
        return id;
    }

    
    public void setId(final Long id) {
        this.id = id;
    }
    

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", title=" + title  + ", description=" + description + "]";
	}
	 @PrePersist
	  void createdAt() {
	    this.createdAt = new Date();
	  }

	 @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss.000 ", timezone="UTC")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}


}