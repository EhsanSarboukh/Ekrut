package entity;

import java.io.Serializable;

public class Product implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name;
	private int price;
	private double sale;
	private int amount;
	private double priceAfterSale;
	public Product(String id, String name, int price, double sale, int amount,double priceAfterSale) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.sale = sale;
		this.amount = amount;
		this.priceAfterSale = priceAfterSale;
	}
	public double getPriceAfterSale() {
		return priceAfterSale;
	}
	public void setPriceAfterSale(double priceAfterSale) {
		this.priceAfterSale = priceAfterSale;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public double getSale() {
		return sale;
	}
	public void setSale(double sale) {
		this.sale = sale;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
