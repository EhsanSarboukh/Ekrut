package entity;

import java.io.Serializable;
import java.sql.Time;

public class Order implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int orderId;
	private double price;
	private String location;
	private int deviceId;
	private String startDate;
	private String status;
	private String deliveryDate;
	private String username;
	private String paymentMethod="creditCard";

	
	public Order(int orderId, double price, String location, int deviceId, String startDate, String status,
			String deliveryDate, String username, String paymentMethod) {
		this.orderId = orderId;
		this.price = price;
		this.location = location;
		this.deviceId = deviceId;
		this.startDate = startDate;
		this.status = status;
		this.deliveryDate = deliveryDate;
		this.username = username;
		this.paymentMethod = paymentMethod;

	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(int deviceId) {
		this.deviceId = deviceId;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDeliveryDate() {
		return deliveryDate;
	}
	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPaymentMethod() {
		return paymentMethod;
	}
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


}
