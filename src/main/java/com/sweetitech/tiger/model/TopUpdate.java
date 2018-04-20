package com.sweetitech.tiger.model;



import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
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
@Table(name = "top_updates")
public class TopUpdate implements Serializable {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "top_updates_news", joinColumns = @JoinColumn(name = "top_update_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "news_id", referencedColumnName = "id"))   
    private News news;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "top_updates_video", joinColumns = @JoinColumn(name = "top_update_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "video_id", referencedColumnName = "id"))
	   
    private Video video;
    
	
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "top_updates_gallery_image", joinColumns = @JoinColumn(name = "top_update_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "gallery_image_id", referencedColumnName = "id"))
	   
    private GalleryImage galleryImage;
    
    @Column(name = "created_at")
    public Date createdAt;
    
    
    
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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public GalleryImage getGalleryImage() {
		return galleryImage;
	}

	public void setGalleryImage(GalleryImage galleryImage) {
		this.galleryImage = galleryImage;
	}

	@Override
	public String toString() {
		return "TopUpdate [id=" + id + ", news=" + news + ", video=" + video + ", galleryImage=" + galleryImage
				+ ", createdAt=" + createdAt + "]";
	}

	public TopUpdate(News news) {
		super();
		this.news = news;
		
		
	}
	
	public TopUpdate(Video video) {
		super();
		
		this.video = video;
		
	}
	
	public TopUpdate(GalleryImage galleryImage) {
		super();
		
		this.galleryImage = galleryImage;
	}

	public TopUpdate() {
		super();
		
	}

    
}