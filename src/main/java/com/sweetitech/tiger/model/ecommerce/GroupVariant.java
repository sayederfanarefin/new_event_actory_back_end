package com.sweetitech.tiger.model.ecommerce;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "group_variants")
public class GroupVariant {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	 
    private String variantName;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_product_group")
    @JsonBackReference
    private ProductGroup group;

    @Column(name = "created_at")
    private Date createdAt;
    
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
	

    @Column(name = "variant_name")
    public String getVariantName() {
        return variantName;
    }

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVariantName(String variantName) {
        this.variantName = variantName;
    }

    public ProductGroup getGroup() {
        return group;
    }

    public void setGroup(ProductGroup group) {
        this.group = group;
    }

    public String toString() {
        return getVariantName();
    }

    
    
	public GroupVariant() {
		super();
		
	}

	public GroupVariant(String variantName) {
		super();
		this.variantName = variantName;
	}
    
}
