package com.example.elasticsearch.entity;

public class Product {
	private Integer id;
	private String title;
	private Double price;
	private String description;
	
	public Product(Integer id, String title, Double price, String description) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
	}
	
	public Product() {
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return "Product{" +
				"id=" + id +
				", title='" + title + '\'' +
				", price=" + price +
				", description='" + description + '\'' +
				'}';
	}
}
