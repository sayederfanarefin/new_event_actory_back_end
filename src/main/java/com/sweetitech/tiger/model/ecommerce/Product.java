package com.sweetitech.tiger.model.ecommerce;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sweetitech.tiger.model.Image;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private String price;
	private String description;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "product_product_group", joinColumns = { @JoinColumn(name = "product_group_id") }, inverseJoinColumns = {
			@JoinColumn(name = "product_id") })
	@JsonManagedReference
	private List<ProductGroup> groups =  new ArrayList<ProductGroup>();;
	

	@OneToMany(cascade= CascadeType.ALL)
	@JoinColumn(name = "fk_product_image")
	private List<Image> images;


	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "product_ptags", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "ptag_id") })
	@JsonManagedReference
	private List<Ptag> ptags = new ArrayList<Ptag>();

	@Column(name = "created_at")
	public Date createdAt;

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

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}
	
	public List<Ptag> getTags() {
		return ptags;
	}

	public void setTags(List<Ptag> tags) {
		this.ptags = tags;
	}

	public Product() {
		super();
	}

	public Product(String name, String price, String description) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductGroup> getGroup() {
		return groups;
	}

	public void setGroup(List<ProductGroup> groups) {
		this.groups = groups;
	}

	
	public String toString() {
		return getName();
	}
}