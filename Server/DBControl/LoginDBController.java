package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Customer;


/**
 * @author Ehsan Sarboukh and Mhemad Mdah
 *
 */
public class LoginDBController {
	/**
	 * @param user
	 * @return String "Role"
	 * Functionality: The method checks in the DB if the user is valid 
	 * once its in the DB it will return its role 
	 */
	public static String CheckUserValidation(String[] user) {
		String userRole = null;
		String sqlQuery = "SELECT role FROM users WHERE username = ? AND password= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, String.valueOf(user[0]));
				ps.setString(2, String.valueOf(user[1]));
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					userRole = rs.getString(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlQuery = "UPDATE users SET isLoggedIn= ?  WHERE username = ? AND password= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, 1);
				ps.setString(2, String.valueOf(user[0]));
				ps.setString(3, String.valueOf(user[1]));
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userRole;
	}

	/**
	 * @param user
	 * @return true if the flag is 0 else false
	 * Functionality: Check the bit of the loggedIn in the database
	 * so we don't have the same users connected twice or more
	 */
	public static boolean CheckLoginBit(String[] user) {
		int flag = 0;
		String sqlQuery = "SELECT isLoggedIn FROM users WHERE username = ? AND password= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, String.valueOf(user[0]));
				ps.setString(2, String.valueOf(user[1]));
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					flag = rs.getInt(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==0) {return true;}
		else {
			return false;
		}
		
	}

	/**
	 * @param user
	 * Functionality : turns the bit isLoggedIn from 1 to 0 
	 */
	public static void LogOutToLoginForm(String[] user) {
		// TODO Auto-generated method stub
		String sqlQuery = "UPDATE users SET isLoggedIn= ?  WHERE username = ? AND password= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, 0);
				ps.setString(2, String.valueOf(user[0]));
				ps.setString(3, String.valueOf(user[1]));
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	/**
	 * @author Ehsan Sarboukh and Sabeel Hamood 
	 * Functionality : Gets all the customers from the customer table and add it to the users 
	 * so the customer can create order and use the system 
	 * once the customer added to the users table the customer table will be empty of customers
	 * and they will turn into users
	 */
	public static void getAllCustomers() {
		ArrayList<Customer> customer=new ArrayList<Customer>();
		
		String sqlQuery = "SELECT* FROM customer;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					String username = rs.getString(1);
					String password = rs.getString(2);
					String firstName = rs.getString(3);
					String lastName = rs.getString(4);
					String email = rs.getString(5);
					String phoneNumber = rs.getString(6);
					String id = rs.getString(7);
					String creditCardNumber = rs.getString(8);
					String role=rs.getString(9);
					
					customer.add(new Customer(username, password, firstName, lastName, email, phoneNumber, id, creditCardNumber,role));			
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(Customer c:customer) {
			sqlQuery = "INSERT INTO users(username,password,firstName,lastName,role,email,phoneNumber,isLoggedIn,id,status,storeName,amount,creditCardNumber,subscribeStatus) "
					+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setString(1,c.getUsername());
					ps.setString(2,c.getPassword());
					ps.setString(3,c.getFirstName());
					ps.setString(4,c.getLastName());
					ps.setString(5,c.getRole());
					ps.setString(6,c.getEmail());
					ps.setString(7,c.getPhoneNumber());
					ps.setInt(8,0);
					ps.setString(9,c.getId());
					ps.setInt(10,0);
					ps.setString(11,"null");
					ps.setInt(12,0);
					ps.setString(13,c.getCreditCard());
					ps.setString(14,"null");
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		sqlQuery = "DELETE FROM customer WHERE role = 'customer';";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.executeUpdate();
			}
			
		
		} catch (SQLException e) {
		e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @author adham
	 * @param userID
	 * @return username
	 * this method get the username of the user with this id
	 */
	public static String getUsername(String userID) {
		String username = null;
		String sqlQuery = "SELECT username FROM users WHERE id = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, String.valueOf(userID));
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					username = rs.getString(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
		
	}
	
	/**
	 * @author adham
	 * @param userID
	 * @return password
	 * this method get the password of the user with this id
	 */
	public static String getPassword(String userID) {
		String password = null;
		String sqlQuery = "SELECT password FROM users WHERE id = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, String.valueOf(userID));
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					password = rs.getString(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * @author adham
	 * @param userID
	 * @return flag if user is a subscriber
	 * this method check if the user with the relevant id is a subscriber.
	 */
	public static boolean getSubscribeStatus(String userID) {
		String checkSubscribe = null;
		boolean flagSubscribe=false;
		String sqlQuery = "SELECT subscribeStatus FROM users WHERE id = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, String.valueOf(userID));
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					checkSubscribe =rs.getString(1);
					flagSubscribe = checkSubscribe.equals("Subscriber Customer");
							
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flagSubscribe;
	}

	/**
	 * @author adham
	 * @param checkid
	 * @return flag
	 * this method check if the ID exist in the table.
	 */
	public static boolean CheckIdValidation(String checkid) {
		// TODO Auto-generated method stub
		boolean flag=false;
		String sqlQuery = "SELECT id FROM users WHERE id = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,checkid);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					flag = checkid.equals(rs.getString(1));
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
}
