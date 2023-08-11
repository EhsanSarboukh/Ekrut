package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import entity.OrderStatus;

public class UpdateOrderStatusDBController {

	public static ArrayList<OrderStatus> GetOrderStatus(String orderLocation) {
		
		ArrayList<OrderStatus> listOrder = new ArrayList<OrderStatus>();
		String check= "Waiting for delivery";
		String sqlQuery = "SELECT orderId,status FROM orders WHERE location= ? AND status= ? ;";
		
				
		try {
			if (DBConnector.conn != null){
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, orderLocation);
				ps.setString(2, check);
				ResultSet rs = ps.executeQuery();

				
				while (rs.next()) {
					// Gather Data
					Integer orderNumber = rs.getInt("orderId");
					listOrder.add(new OrderStatus(orderNumber,check));
					//String Status = rs.getString("status");
					//listOrder.add(new OrderStatus(orderNumber,Status));
				}
				rs.close();
			} else
				System.out.println("myConn is NULL !");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listOrder;
	}

	public static boolean CheckUpdateOrder( String[] updateOrder) {
			Integer flag = null;
			String Status=null;
			Integer orderNum = Integer.parseInt(updateOrder[1]);
			String sqlQuery = "SELECT orderId,status FROM orders WHERE orderId = ? AND location = ?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1, orderNum);
					ps.setString(2, updateOrder[0]);
					ResultSet rs=ps.executeQuery();
					while(rs.next()) {
						flag = rs.getInt(1);
						Status=rs.getString(2);
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(flag!=null && Status.equals("Waiting for delivery")) {return true;}
			else
				return false;
		
	}
	public static String updateStatus(Integer orderNumber) {
        String Status="Approved";
        String deliveryDate;
        LocalDateTime startDate;
        LocalDateTime finishDate;
        startDate = LocalDateTime.now();
        finishDate = startDate.plusWeeks(2);
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
        deliveryDate = finishDate.format(myFormatObj);
		
	    String sqlQuery = "UPDATE orders SET status= ?,deliveryDate= ?  WHERE orderId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, Status);
				ps.setString(2,deliveryDate);
				ps.setInt(3, orderNumber);
				ps.executeUpdate();
			
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return deliveryDate;
    }
	
}
		
