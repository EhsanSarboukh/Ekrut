package DBControl;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.CustomerReport;

//import server.controllers.dobule;

public class reportsDBController {

	/**
	 * @author Ehsan Sarboukh and Sabeel Hamood
	 * @param username
	 * @return Area Manager Location 
	 * Functionality: Method that checks by the username and returns the area manager location
	 */
	public static String getAreaManagerLocation(String username) {
		String arealoc=null;
		String sqlQuery="SELECT storeName FROM users Where username=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,username );
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					arealoc=rs.getString(1);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return arealoc;
	}

	/**
	 * @author Ehsan Sarboukh and Sabeel Hamood
	 * @param data
	 * @return String orderDetails
	 * Functionality: Gets from monthlyordersreports table the orderdatails and return it to the client
	 */
	public static String fillOrderReport(String[] data) {
		// TODO Auto-generated method stub
		String orderDetials="null";
		String sqlQuery="SELECT orderdetails FROM monthlyorderreports WHERE deviceId=? AND month=? AND year=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,data[0]);
				ps.setString(2,data[1]);
				ps.setString(3,data[2]);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					orderDetials=rs.getString(1);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetials;
	}

	/**
	 * @author Ehsan Sarboukh and Sabeel Hamood
	 * @param data
	 * @return integer array that contains the approved and canceled amount of orders
	 * Functionality: Gets the the approved amount of orders for monthlyorderreports table and save it 
	 * in the first cell in the array and the same for the canceled order but it save it in the second cell
	 */
	public static int[] getOrderStatus(String[] data) {
		// TODO Auto-generated method stub
		 int [] dataOrdersStatus=new int[3];
		String sqlQuery="SELECT approved,canceled,waitingForDelivery FROM monthlyorderreports WHERE deviceId=? AND month=? AND year=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,data[0]);
				ps.setString(2,data[1]);
				ps.setString(3,data[2]);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					dataOrdersStatus[0]=rs.getInt(1);
					dataOrdersStatus[1]=rs.getInt(2);
					dataOrdersStatus[2]=rs.getInt(3);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dataOrdersStatus;
		
	}

	/**
	 * @author Ehsan Sarboukh and Sabeel Hamood
	 * @param data
	 * @return profits
	 * Functionality:Gets the profits from monthlyorderreports table and return the value .
	 */
	public static double getOrderProfit(String [] data) {
		// TODO Auto-generated method stub
		 double profits = 0;
			String sqlQuery="SELECT profit FROM monthlyorderreports WHERE deviceId=? AND month=? AND year=?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setString(1,data[0]);
					ps.setString(2,data[1]);
					ps.setString(3,data[2]);
					
					ResultSet rs= ps.executeQuery();
					while(rs.next()) {
						profits=rs.getDouble(1);
					
						
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return profits;
	}
	
	/**
	 * @author adham
	 * @param getdetails
	 * @return array of integers product (size 5 - number the products)
	 * this method return how much of every product updated in the relevant device,month and year.
	 */
	public static Integer[] getStockReportDetails(Integer[] getdetails) {
		// TODO Auto-generated method stub
		Integer [] products = new Integer[5];
		String sqlQuery="SELECT Bamba,Klik,CocaCola,SevenUp,Katkatat FROM monthlystockreport WHERE deviceId=? AND month=? AND year=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,getdetails[0]);
				ps.setInt(2,getdetails[1]);
				ps.setInt(3,getdetails[2]);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					products[0]=rs.getInt("Bamba");
					products[1]=rs.getInt("Klik");
					products[2]=rs.getInt("CocaCola");
					products[3]=rs.getInt("SevenUp");
					products[4]=rs.getInt("Katkatat");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return products;
	}

	/**
	 * @author adham
	 * @param reportDetails
	 * @return flag
	 * the method check if the report existed and return true if yes and false if not.
	 */
	public static boolean checkReportExist(String[] reportDetails) {
		// TODO Auto-generated method stub
		String reportTable=null;
		if(reportDetails[3].equals("Order Report"))
			reportTable="monthlyorderreports";
		else if(reportDetails[3].equals("Stock Report"))
			reportTable="monthlystockreport";
		boolean flag=false;
		String sqlQuery="SELECT count(*) FROM " + reportTable + " WHERE deviceId=? AND month=? AND year=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,reportDetails[0]);
				ps.setString(2,reportDetails[1]);
				ps.setString(3,reportDetails[2]);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					if(rs.getInt(1)!=0)
						flag=true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return flag;
	}
	
	
	/**
	 * @author Sara Asaad , Mhemad Mdah
	 * @param report
	 * @return the customer report of the month and year requested
	 */
	public static ArrayList<CustomerReport> getCustomerReport(String[] report){
		ArrayList<CustomerReport> reportDetails = new ArrayList<>();
		String sqlQuery="SELECT customerUserName,numberOfOrders FROM monthlycustomerreport WHERE month = ? AND year = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,report[1]);
				ps.setString(2,report[2]);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					String name = rs.getString(1);
					int numOfOrders = rs.getInt(2);
					
					reportDetails.add(new CustomerReport(name , numOfOrders));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return reportDetails;
	}

	/**
	 * @author Sara Asaad , Mhemad Mdah
	 * @return details for the labels in the report
	 */
	public static String[] getCustomerReportLabel() {
		String[] label = new String[2];
		String sqlQuery="SELECT COUNT(role) FROM users WHERE role = 'customer';";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					String number = rs.getString(1);
					label[0] = number;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sqlQuery="SELECT COUNT(role) FROM users WHERE role = 'customer' AND subscribeStatus = 'Subscriber Customer'";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					String percentage = rs.getString(1);
					label[1] = percentage;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return label;
	}


}
