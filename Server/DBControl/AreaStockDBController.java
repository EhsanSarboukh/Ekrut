package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.AreaStock;
import entity.DeviceStock;
/**
 * @author daniella
 *
 */
public class AreaStockDBController {
	/**
	 * @param location
	 * @return  a list of AreaStock objects containing the stock information of the devices in the specified location
	 * This method retrieves the stock information of all devices in a specific location from the database.
	 */
	public static ArrayList<AreaStock> getStockByLocation(String location) {
		ArrayList<AreaStock> listStock = new ArrayList<AreaStock>();
		String sqlQuery = "SELECT * FROM devicemanagement WHERE location=?;";
				
		try {
			if (DBConnector.conn != null){
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, location);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Gather Data
					int deviceId = rs.getInt(1);					
		            int Bamba = rs.getInt(2);		           
		            int  Klik = rs.getInt(3);		           
		            int Coca_cola = rs.getInt(4);
		            int Sevan_up = rs.getInt(5);		            
		            int katkatat = rs.getInt(6);
		            String MessageFromTheAreaManger = rs.getString(7);
		            String Location = rs.getString(8);
		           
		            listStock .add(new AreaStock(deviceId,Bamba,Klik,Coca_cola,Sevan_up, katkatat,MessageFromTheAreaManger,Location));
				}
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStock;
	}
	/**
	 * @param deviceId
	 * @return true if the device id exists, false otherwise.
	 * This method checks if the given device id exists in the database.
	 */
	public static boolean checkIdDevice(String deviceId) {
		String flag = null;
		String sqlQuery = "SELECT deviceId FROM devicemanagement WHERE deviceId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, deviceId);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					flag = rs.getString(1);
				}
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==null) {return false;}
		else {
			return true;
		}
	}
	/**
	 * @author daniella
	 * @param deviceId
	 * @return An ArrayList of DeviceStock objects, containing the stock information for the specified device. 
	 * If no data is found or an error occurs, an empty ArrayList is returned.
	 * This method retrieves the stock information for a particular device from the 'devicemanagement' table in the database.
	 */
	public static ArrayList<DeviceStock> getStockByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		ArrayList<DeviceStock> listStock = new ArrayList<DeviceStock>();
		String sqlQuery = "SELECT * FROM devicemanagement WHERE deviceId=?;";
				
		try {
			if (DBConnector.conn != null){
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, deviceId);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Gather Data
					int deviceId1 = rs.getInt(1);					
		            int Bamba = rs.getInt(2);		           
		            int  Klik = rs.getInt(3);		           
		            int Coca_cola = rs.getInt(4);
		            int Sevan_up = rs.getInt(5);		            
		            int katkatat = rs.getInt(6);
		           
		            listStock .add(new DeviceStock(deviceId1,Bamba,Klik,Coca_cola,Sevan_up, katkatat));
				}
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listStock;
	}

	/**
	 * @author daniella + adham
	 * @param update update[0]: The ID of the device whose stock is to be updated.
	 * update[1]: The name of the item whose stock is to be updated.
	 * update[2]: The new stock level for the item.
	 * This method updates the stock/Amount information for a particular device in the 'devicemanagement' table in the database ,
	 *  check if a product is updated and insert a new row
	 */
	public static void updateStockByDeviceId(String[] update) {
		
		boolean flag=false;
		String sqlQuery = "SELECT "+ update[1] +" FROM devicemanagement WHERE deviceId=?;";
		int amountAdded=0;
		
		try {
			if (DBConnector.conn != null){
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, update[0]);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Gather Data
					int currentAmount = rs.getInt(1);
					amountAdded = Integer.valueOf(update[2]) - currentAmount;
					}
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sqlQuery = "UPDATE devicemanagement SET "+update[1]+"= ? WHERE deviceId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, Integer.valueOf(update[2]));
				ps.setString(2, update[0]);
				ps.executeUpdate();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sqlQuery="UPDATE devicemanagement SET updateStock = '0' WHERE deviceId = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, update[0]);
				ps.executeUpdate();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		sqlQuery = "SELECT count(*),amount FROM stockupdates WHERE deviceId=? AND product=? AND month=? AND year=? ;";
		Integer month;
		Integer year;
		LocalDate nowDate = LocalDate.now();
		
		month = nowDate.getMonthValue();
		year = nowDate.getYear();
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, Integer.valueOf(update[0]));
				ps.setString(2,update[1]);
				ps.setInt(3,month);
				ps.setInt(4,year);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					// Gather Data
					System.out.println(rs.getInt(1) + "hello");
					if(rs.getInt(1)!=0)
					{
						int productAmount = rs.getInt(2);
						amountAdded = amountAdded + productAmount;
						flag=true;
					}
				}
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (flag==false)
			{
			/*
			 * Prepare the INSERT statement with the place holders
			 */
			sqlQuery = "INSERT INTO stockupdates  (deviceId, location, product, amount, month, year) VALUES (?, ?, ?, ?, ?, ?);";
			
			month = nowDate.getMonthValue();
			year = nowDate.getYear();
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1, Integer.valueOf(update[0]));
					ps.setString(2,"north");
					ps.setString(3,update[1]);
					ps.setInt(4,amountAdded);
					ps.setInt(5,month);
					ps.setInt(6,year);
					ps.executeUpdate();
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
				}	
			}
		else 
		{ 
			/*
			 *  Prepare the UPDATE statement with the place holders 
			 */
			sqlQuery = "UPDATE stockupdates SET amount = ? WHERE deviceId=? AND product=? AND month=? AND year=?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1,amountAdded);
					ps.setString(2, update[0]);
					ps.setString(3,update[1]);
					ps.setInt(4,month);
					ps.setInt(5,year);
					ps.executeUpdate();
				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
}
