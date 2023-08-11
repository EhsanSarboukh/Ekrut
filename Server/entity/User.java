package entity;

import java.io.Serializable;

public class User implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userName;
	private String password;
	private String id;
	private String firstName;
	private String lastName;
	private Role role;
	private String email;
	private String phoneNumber;
	private int loggedIn;
	private String storeName;
	private String creditCardNumber;
	
	public User(String userName, String password, String id, String firstName, String lastName, Role role,
			String email, String phoneNumber, int loggedIn,String storeName,String creditCardNumber) {
		super();
		this.userName = userName;
		this.password = password;
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.loggedIn = loggedIn;
		this.storeName=storeName;
		this.creditCardNumber=creditCardNumber;
	}
	public User(String id,String firstName, String lastName,String email, String phoneNumber, String creditCardNumber) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;		
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.creditCardNumber=creditCardNumber;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getLoggedIn() {
		return loggedIn;
	}

	public void setLoggedIn(int loggedIn) {
		this.loggedIn = loggedIn;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	
	
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber= creditCardNumber;
	}

}
