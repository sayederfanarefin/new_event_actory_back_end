package com.sweetitech.tiger.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "tag")
public class Tag {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "created_at")
	public Date createdAt;

	@Column(columnDefinition = "LONGTEXT")
	private String tag;

	
    @ManyToMany(fetch = FetchType.EAGER)	
	@JoinTable(name = "gallery_image_tags", joinColumns = @JoinColumn(name = "gallery_image_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))
	private List<GalleryImage> galleryImages;


	@Override
	public String toString() {
		return "Tag [id=" + id + ", createdAt=" + createdAt + ", tag=" + tag + ", galleryImages=" + galleryImages + "]";
	}

	public Tag(String tag, List<GalleryImage> galleryImages) {
		super();
		this.tag = tag;
		this.galleryImages = galleryImages;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public List<GalleryImage> getGalleryImages() {
		return galleryImages;
	}

	public void setGalleryImages(List<GalleryImage> galleryImages) {
		this.galleryImages = galleryImages;
	}

	public Tag() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@PrePersist
	void createdAt() {
		this.createdAt = new Date();
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.000 ", timezone = "UTC")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

}