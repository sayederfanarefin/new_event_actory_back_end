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
@Table(name = "video")
public class Video {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	 @Column(name = "created_at")
	    public Date createdAt;
	 
	private String title;

	 @Column(columnDefinition="LONGTEXT")
	private String url;

	 @Column(columnDefinition="LONGTEXT")
	private String description;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "video_video_category", joinColumns = @JoinColumn(name = "video_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "video_category_id", referencedColumnName = "id"))
	private VideoCategory videoCategory;

	
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

	
	public Video() {
		super();

	}

	public VideoCategory getVideoCategory() {
		return videoCategory;
	}

	public void setVideoCategory(VideoCategory videoCategory) {
		this.videoCategory = videoCategory;
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

	public Video(String title, String url, String description, VideoCategory videoCategory) {
		super();
		this.title = title;
		this.url = url;
		this.description = description;
		this.videoCategory = videoCategory;
	}

	@Override
	public String toString() {
		return "Video [id=" + id + ", title=" + title + ", url=" + url + ", description=" + description + ", videoCategory=" + videoCategory + "]";
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}