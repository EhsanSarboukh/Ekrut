package DBControl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import gui.ServerFormController;

/**
 * @author Ehsan Sarboukh 
 *Class purpose to connect to the DB schema 
 */
public class DBConnector {
	
	private String url = ServerFormController.conn[0];
	private String username = ServerFormController.conn[1];
	private String password = ServerFormController.conn[2];
	
	public static Connection conn;
	
	public DBConnector() {
		createConnection();
	}
	
	
	/**
	 * Creates a new Connection to the DB
	 */
	private void createConnection() {
	    try {
	      Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
	      System.out.println("Driver definition succeed");
	    } catch (Exception ex) {
	      System.out.println("Driver definition failed");
	    } 
	    try {
	    	conn = (Connection)DriverManager.getConnection(url, username, password);
	      System.out.println("SQL connection succeed");
	    } catch (SQLException ex) {
	      System.out.println("SQLException: " + ex.getMessage());
	      System.out.println("SQLState: " + ex.getSQLState());
	      System.out.println("VendorError: " + ex.getErrorCode());
	    } 
	  }
	
	/**
	 * CLose the Connection to the DB
	 */
	public void closeConnection() {
	    try {
	      if (!conn.isClosed()) {
	    	  conn.close();
	    	  conn=null;
	      }
	    	  
	    } catch (SQLException e) {
	      e.printStackTrace();
	    } 
	  }
}
