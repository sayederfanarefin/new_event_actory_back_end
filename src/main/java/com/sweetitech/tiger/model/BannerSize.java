package com.sweetitech.tiger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "banner_sizes")
public class BannerSize {

	@Id
	@Column(unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	public String bannerSizeName;

	@Column(name = "created_at")
	public Date createdAt;

	public int bannerHeight ;
	public int bannerWidth;
	
	
	public BannerSize() {
		super();

	}
	

	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "fk_banner_size")
	@JsonBackReference
	private List<Banner> banners = new ArrayList<Banner>();
	

	public BannerSize(String bannerSizeName, int bannerHeight, int bannerWidth) {
		super();
		this.bannerSizeName = bannerSizeName;
		this.bannerHeight = bannerHeight;
		this.bannerWidth = bannerWidth;
	}



	public String getBannerSizeName() {
		return bannerSizeName;
	}



	public void setBannerSizeName(String bannerSizeName) {
		this.bannerSizeName = bannerSizeName;
	}



	public int getBannerHeight() {
		return bannerHeight;
	}



	public void setBannerHeight(int bannerHeight) {
		this.bannerHeight = bannerHeight;
	}



	public int getBannerWidth() {
		return bannerWidth;
	}



	public void setBannerWidth(int bannerWidth) {
		this.bannerWidth = bannerWidth;
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



	public List<Banner> getBanners() {
		return banners;
	}



	public void setBanners(List<Banner> banners) {
		this.banners = banners;
	}

	
}