package entity;

import java.io.Serializable;

public class OrderStatus implements Serializable  {
	
 
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private Integer ordernumber;
 private String Status;
 
public OrderStatus(Integer ordernumber, String status) {
		super();
		this.ordernumber = ordernumber;
		Status = status;
	}

public Integer getOrdernumber() {
	return ordernumber;
}

public void setOrdernumber(Integer ordernumber) {
	this.ordernumber = ordernumber;
}

public String getStatus() {
	return Status;
}

public void setStatus(String status) {
	Status = status;
}
}