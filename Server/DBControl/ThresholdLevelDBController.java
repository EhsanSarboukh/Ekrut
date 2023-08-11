package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.MessageType;




public class ThresholdLevelDBController {
	/**
	 * @author Sara Asaad
	 * @param user
	 * @return this methos has a query to update the ThresholdLevel in the data base according to the deviceId.
	 */
	public static String CheckThresholdLevelDataValidation(int[] user) {
		String ThresholdLevelData = null;

		String sqlQuery = "UPDATE devicemanagement SET ThresholdLevel= ?  WHERE deviceId = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement Ps = DBConnector.conn.prepareStatement(sqlQuery);
				Ps.setInt(1, user[1]);
				Ps.setInt(2, user[0]);
				Ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ThresholdLevelData;
	}
	
	/**
	 * @author Sara Asaad
	 * this method has a query to update the message from the area manager (to tell the operation worker that he has to update the stock of special product) where is the updateStock is 1.
	 */
	public static void LetOperationWorkerUpdate(String[] user) {
		String location = null;
		if(user[0].equals("smnorth"))
			location = "north";
		else if(user[0].equals("smsouth"))
			location = "south";
		else
			location = "UAE";
		String sqlQuery ="UPDATE devicemanagement SET updateStock= 'Update Stock' WHERE deviceId AND location ='"+location+"' AND updateStock='1';";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	
	
	/**
	 * @author Sara Asaad
	 * @param deviceId
	 * @return boolean flag(true,false)
	 * Functionality: Check the update status column in the DB update it to Update Status in case 
	 * it equals to 1 . 
	 */
	public static boolean checkThresholdStatus(int deviceId) {
		// TODO Auto-generated method stub
		String sqlQuery = "SELECT * FROM devicemanagement WHERE deviceId = ?";
		boolean flag = false;
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, deviceId);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					if(rs.getInt(2) <= rs.getInt(9) || rs.getInt(3) <= rs.getInt(9)||
							rs.getInt(4) <= rs.getInt(9) || rs.getInt(5) <= rs.getInt(9) ||
							rs.getInt(6) <= rs.getInt(9)) {
						flag = true;
					}
				}
			}
			
		}catch(SQLException e) {
			
		}
		if(flag == true) {
			sqlQuery = "UPDATE devicemanagement SET updateStock = '1' WHERE deviceId = ?";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1, deviceId);
					ps.executeUpdate();
				}
				
			}catch(SQLException e) {
				
			}
		}
		return flag;
	}

}
