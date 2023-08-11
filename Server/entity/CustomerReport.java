package entity;

import java.io.Serializable;

/**
 * @author sara asaad , mhemad mdah
 *
 */
public class CustomerReport implements Serializable{
	

	private static final long serialVersionUID = 1L;
	private String customerName;
	private int numOfOrders;
	
	
	/**
	 * @param customerName
	 * @param numOfOrders
	 * @return constructor for class
	 */
	public CustomerReport(String customerName, int numOfOrders) {
		super();
		this.customerName = customerName;
		this.numOfOrders = numOfOrders;
	}
	
	
	/**
	 * @return customerName
	 */
	public String getCustomerName() {
		return customerName;
	}
	
	
	/**
	 * @param customerName
	 * @return set customerName
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	
	/**
	 * @return numOfOrders
	 */
	public int getNumOfOrders() {
		return numOfOrders;
	}
	
	
	/**
	 * @param numOfOrders
	 * @return set numOfOrders
	 */
	public void setNumOfOrders(int numOfOrders) {
		this.numOfOrders = numOfOrders;
	}
	
	
}
