package com.sweetitech.tiger.model.ecommerce;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.sweetitech.tiger.model.ecommerce.Product;

import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "order_items")
public class OrderItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String price;
	
	private long quantity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_order")
	@JsonBackReference
	private Order order;

	@ManyToOne
	@JoinColumn(name = "fk_order_product")
	private Product product;
	
	@ManyToOne
	@JoinColumn(name = "product_variant_id")
	private GroupVariant groupVariant;

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

	
	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public GroupVariant getGroupVariant() {
		return groupVariant;
	}

	public void setGroupVariant(GroupVariant groupVariant) {
		this.groupVariant = groupVariant;
	}
}
