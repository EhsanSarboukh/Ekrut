package entity;

import java.io.Serializable;

public class Subscriber implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String FName;
	private String LName;
	private String id;
	private String phNum;
	private String email;
	private String creditCard;
	private String subNum;
	
	public Subscriber(String FName, String LName, String id, String phNum, String email, String creditCard,
			String subNum) {
		this.FName = FName;
		this.LName = LName;
		this.id = id;
		this.phNum = phNum;
		this.email = email;
		this.creditCard = creditCard;
		this.subNum = subNum;
	}
	
	public String getFName() {
		return this.FName;
	}

	public void setFName(String FName) {
		this.FName = FName;
	}

	public String getLName() {
		return this.LName;
	}

	public void setLName(String LName) {
		this.LName = LName;
	}

	public String getPhNum() {
		return phNum;
	}

	public void setPhNum(String phNum) {
		this.phNum = phNum;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public String getSubNum() {
		return subNum;
	}

	public void setSubNum(String subNum) {
		this.subNum = subNum;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	
}
