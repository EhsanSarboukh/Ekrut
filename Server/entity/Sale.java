package entity;

import java.io.Serializable;

public class Sale  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productName;
	private String status;
	private double sale;
	private String area;
	
	public Sale(String productName, String status, double sale, String area) {
		super();
		this.productName = productName;
		this.status = status;
		this.sale = sale;
		this.area = area;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getSale() {
		return sale;
	}

	public void setSale(double sale) {
		this.sale = sale;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	
	
}
