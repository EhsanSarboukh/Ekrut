package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Customer;
import entity.Subscriber;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ClientDBController {

	/**
	 * @author Sabeel
	 * @param user
	 * @return ArrayList<User> userWantToSubscribe
	 * The query extracts from the database the subscriber data by username then saves the data in ArrayList
	 */
public static ArrayList<User> getSubscriber(String [] user) {
		
		
		ArrayList<User> userWantToSubscribe = new ArrayList<User>();

		
		
		String sqlQuery = "SELECT firstName,lastName,id,phoneNumber,email,creditCardNumber FROM users WHERE username='"+user[0]+"';";//to get the details of the user that want to subscribe
		
	
		
		try {
			if (DBConnector.conn != null) {
				
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				
			
				ResultSet rs = ps.executeQuery(sqlQuery);
			
				while (rs.next()) {
					// Gather Data
					
					
					String firstName = rs.getString(1);
					String lastName= rs.getString(2);
					String id = rs.getString(3);
					String phoneNumber = rs.getString(4);
					String email = rs.getString(5);
					String creditCardNumber=rs.getString(6);
					userWantToSubscribe.add(new User(id,firstName,lastName, email, phoneNumber,creditCardNumber));
				}
				
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userWantToSubscribe;		
	}

/**
 * @author Sabeel
 * @param user
 * @return boolean value
 * A method that retrieves data from the database and returns a boolean value based on the retrieved information

 *The query searches for the customer by username and retrieves their subscription status
 *Checks if the subscription status returned from the query is equal to "null" then the method returns true otherwise it returns FALSE
 */
public static boolean requestToSubscribe(String[] user) {
		
		int flag =0;
		//String returnQuery = null;
		String sqlQuery = "SELECT subscribeStatus FROM users WHERE username='"+user[0]+"';";
	
		try {
			if (DBConnector.conn != null) {
				
				
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
			
				ResultSet rs=ps.executeQuery();
			
				while(rs.next()) {
					
					String returnQuery= rs.getString(1);
				
					if(returnQuery.equals("null")) {
					
						return true;
					}
					else {
						return false;
					}
				
				
					
		       }
				
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==0) {//just if the subscriber status =null the customer can to subscribe 
		    
		     return true;
		}
		else {
			
			return false;
		}
	}

/**
 * @author Sabeel
 * @param user
 * @return boolean value
 * The method checks if there are customer requests waiting for approval
 * The query retrieves the subscribeStatus from  users table in the database
 * then the method checks if there are requests waiting for approval, if there is returns true otherwise returns false
 */
public static boolean checkRequests(String[] user) {
	
	int flag =0;
	//String returnQuery = null;
	String sqlQuery = "SELECT subscribeStatus FROM users;";
	
	try {
		if (DBConnector.conn != null) {
			
			//PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
			PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
			
			ResultSet rs=ps.executeQuery();
		
			while(rs.next()) {
				
				String returnQuery= rs.getString(1);//returnQuery=the subscribe status we do this to check if the customer is already subscribe or not 
				
				if(returnQuery.equals("waiting for approval")) {
				
					flag=0;
					break;
				}
				else {
					flag=1;
				}
			
			
				
	       }
			
			rs.close();
		} else
			System.out.println("myConn is NULL !");
	} catch (SQLException e) {
		e.printStackTrace();
	}
	
	if(flag==0) {//just if the subscriber status =null the customer can to subscribe 
	  
	     return true;
	}
	else {
		
		return false;
	}
}	

/**
 * @author Sabeel
 * @param user
 * A method that accesses the database and updates specific data
 * The query searches for the customer by username and updates their subscription status to "waiting for approval"
 */
	public static void waitingForCEOToChangeStatus	(String[] user) {
		
		String sqlQuery = "UPDATE users SET subscribeStatus=? WHERE username='"+user[0]+"';";
		
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, "waiting for approval");
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @author Sabeel
	 * @param user
	 * A method that accesses the database and updates specific data
	 * The query searches for subscribeStatus that equals to waiting for approval and updates subscriptionStatus to "Subscriber Customer"
	 */
	public static void ceoChangeSubscribeStatus	(String[] user) {
		String sqlQuery = "UPDATE users SET subscribeStatus=? WHERE subscribeStatus='waiting for approval';";
		
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, "Subscriber Customer");
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void AddCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sqlQuery="INSERT INTO customer VALUES(?,?,?,?,?,?,?,?,?);";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,customer.getUsername());
				ps.setString(2,customer.getPassword());
				ps.setString(3,customer.getFirstName());
				ps.setString(4,customer.getLastName());
				ps.setString(5,customer.getEmail());
				ps.setString(6,customer.getPhoneNumber());
				ps.setString(7,customer.getId());
				ps.setString(8,customer.getCreditCard());
				ps.setString(9,customer.getRole());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}

	public static boolean checkUser(String[] user) {
		// TODO Auto-generated method stub
		//String returnQuery = null;
		String sqlQuery = "SELECT username,id FROM users WHERE username='"+user[0]+"' OR id='"+user[1]+"';";
		
		try {
			if (DBConnector.conn != null) {
				
				//PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				
				ResultSet rs=ps.executeQuery();
				String returnQuery1=null;
				String returnQuery2=null;
				while(rs.next()) {
					
				    returnQuery1= rs.getString(1);//returnQuery=the subscribe status we do this to check if the customer is already subscribe or not 
				    returnQuery2= rs.getString(2);
					if(returnQuery1!=null || returnQuery2!=null) {
					    return false;
					}
					
				
					
		       }
				
				
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
}