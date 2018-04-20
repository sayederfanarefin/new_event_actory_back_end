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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sweetitech.tiger.model.ecommerce.ProductGroup;

@Entity
@Table(name = "banner")
public class Banner {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fk_banner_size")
	@JsonManagedReference
	public BannerSize bannerSize;

	@Column(columnDefinition = "LONGTEXT")
	public String url;

	@Column(name = "created_at")
	public Date createdAt;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "banner_image", joinColumns = @JoinColumn(name = "banner_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "id"))
	public Image image;

	public Image getImage() {
		return image;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	
	public Banner() {
		super();

	}

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Banner [id=" + id + ", url=" + url + ", createdAt=" + createdAt + ", image=" + image + "]";
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

	public Banner(BannerSize bannerSize, String url, Image image) {
		super();
		this.bannerSize = bannerSize;
		this.url = url;
		this.image = image;
	}

	public void setBannerSize(BannerSize bannerSize) {
		this.bannerSize = bannerSize;
	}

	public BannerSize getBannerSize() {
		return bannerSize;
	}

	
}