package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import entity.ActiveSale;
import entity.Order;
import entity.Product;




/**
 * @author Ehsan Sarboukh and Mhemad Mdah
 *
 */
public class ProductDBController {
	private static int[] arr=new int[5] ;

	
	/**
	 * @param deviceId
	 * @throws SQLExceptions if occurs problems why receiving data from the DB
	 * Functionality: saves in int array and amount of each product of the device.
	 */
	public static void getAmount(int deviceId) {
		String sqlQuery = "SELECT* FROM devicemanagement WHERE deviceId= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,deviceId );
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					arr[0] = rs.getInt(2);
					arr[1] = rs.getInt(3);
					arr[2] = rs.getInt(4);
					arr[3] = rs.getInt(5);
					arr[4] = rs.getInt(6);
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param message
	 * @return arrayList that contains all the data about the products
	 * Functionality : Gets all the data from the products table.
	 * if the customer is a subscriber the price is with discount 
	 */
	public static ArrayList<Product> getProducts(String[] message) {
		// TODO Auto-generated method stub
		ArrayList<Product> products = new ArrayList<Product>();
		String sqlQuery = "SELECT * FROM products";
		int i=0;
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					String id = rs.getString(1);
					String name = rs.getString(2);
					int price = rs.getInt(3);
					sqlQuery = "SELECT subscribeStatus FROM users WHERE username = ? AND subscribeStatus = 'Subscriber Customer';";
					PreparedStatement ps2 = DBConnector.conn.prepareStatement(sqlQuery);
					ps2.setString(1,message[2]);
					ResultSet rs2 = ps2.executeQuery();
					String status = null;
					while(rs2.next()) {
						status = rs2.getString(1);
					}
					double sale = 0; 
					if(status != null) {
						sqlQuery = "SELECT sale FROM sales WHERE product = ? and area = ? and status = 'activated';";
						PreparedStatement ps1 = DBConnector.conn.prepareStatement(sqlQuery);
						ps1.setString(1,name);
						ps1.setString(2,message[1]);
						ResultSet rs1 = ps1.executeQuery();
						while(rs1.next()) {
							sale = rs1.getDouble(1);
						}
					}
					double priceAfterSale = price - (price*sale);
					products.add(new Product(id,name,price,sale,arr[i],priceAfterSale));
					i++;
					
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}
	
	
	/**
	 * @param id
	 * @return an array that contains the product name and its price
	 * Functionality : The method receives the product id 
	 * and find the name and the price of the product
	 */
	public static String[] getProductName(String id) {
		// TODO Auto-generated method stub
		String[] name=new String[2];
		String sqlQuery = "SELECT Name,Price FROM products WHERE ID= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,String.valueOf(id));
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					name[0]=rs.getString(1);
					name[1]=rs.getString(2);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	/**
	 * @param activeSale
	 * Functionality : Receive an activeSale adds a new row to the table in the DB
	 * in case there is a row with the same products we updates the amount
	 * and the total price
	 */
	public static void addRowInActiveSale(ActiveSale activeSale) {
			
			double totalPrice=activeSale.getAmount()*activeSale.getPrice();
			String sqlQuery = "INSERT INTO activeorder(orderId,productId,productName,amount,price,totalPrice) VALUES(?,?,?,?,?,?);";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1,activeSale.getOrderId());
					ps.setInt(2,activeSale.getProductId());
					ps.setString(3,activeSale.getProductName());
					ps.setDouble(4,activeSale.getAmount());
					ps.setDouble(5,activeSale.getPrice());
					ps.setDouble(6,totalPrice);
					ps.executeUpdate();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
	/**
	 * @param stock
	 * Functionality:Updates the device stock . 
	 * 
	 */
	public static void updateDeviceStock(String[] stock) {
		// TODO Auto-generated method stub
		String sqlQuery = "UPDATE Devicemanagement SET "+stock[1]+ "= ?  WHERE deviceId = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, String.valueOf(stock[2]));
				ps.setString(2, String.valueOf(stock[0]));
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return orderId
	 * Functionality:  returns the orderId and updates(increase) the orderId
	 * so we don't have any orderId duplicated.
	 */
	public static int getOrderId() {
		int orderId=0;
		String sqlQuery = "SELECT* FROM orderid ;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					orderId=rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		int temp=orderId+1;
		sqlQuery = "UPDATE orderid SET orderId = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,temp);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderId;
	}
	/**
	 * @param orderId
	 * @return arrayList type of ActiveSale
	 * Functionality: In order to view the customer order we saved the whole process
	 * in DB in table called activeorder each customer gets a different orderID .
	 */
	public static ArrayList<ActiveSale> viewActiveSale(Integer orderId) {
		// TODO Auto-generated method stub
		ArrayList<ActiveSale> activeSale=new ArrayList<ActiveSale>();
		String sqlQuery = "SELECT* FROM activeorder WHERE orderId= ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,orderId );
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					 int productId=rs.getInt(2);
					 String productName=rs.getString(3);
					 int amount=rs.getInt(4);
					 double price=rs.getDouble(5);
					 activeSale.add(new ActiveSale(orderId,productId,productName,price,amount));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return activeSale;
	}
	/**
	 * @param deviceCode
	 * @return the Location of the device
	 * Functionality: The method receive deviceCode and returns it location
	 */
	public static String[] getLocation(Integer deviceCode) {
		// TODO Auto-generated method stub\
		String[] results=new String[2];
		String sqlQuery = "SELECT location,isInUse FROM devicemanagement WHERE deviceId = ? ;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,deviceCode);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					results[0]=rs.getString(1);
					results[1]=String.valueOf(rs.getInt(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(results[1].equals("0")) {
			sqlQuery ="UPDATE devicemanagement SET isInUse='1' WHERE deviceId=?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1,deviceCode);
					ps.executeUpdate();
				}
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		return results;
	}
	
	/**
	 * @param orderId
	 * @return totalPrice
	 * Functionality: calculate the totalPrice from the active order by the orderID
	 * returns the total price at the end
	 */
	public static double getTotalPrice(int orderId) {
		// TODO Auto-generated method stub
		double totalPrice=0;
		String sqlQuery = "SELECT SUM(totalPrice) FROM activeorder WHERE orderId = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, orderId);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					totalPrice=rs.getDouble(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return totalPrice;
	}
	
	/**
	 * @param order
	 * Functionality: Add An order to the orders table in addition to save the 
	 * data of the order to use in the reports, moreover, delete the orderId for the 
	 * activeorder table
	 */
	public static void addToOrders(Order order) {
		String orderDetails=String.valueOf(order.getOrderId())+ " - totalPrice is " + order.getPrice()+ " :) ";
		String sqlQuery="SELECT productName,amount,totalPrice FROM activeorder WHERE orderId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, order.getOrderId());
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					orderDetails+=rs.getString(1);
					orderDetails += " ";
					orderDetails+=String.valueOf(rs.getInt(2));
					orderDetails += " ";
					orderDetails+=String.valueOf(rs.getDouble(3));
					orderDetails += ",";
				
				}
				orderDetails+="Data";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Timestamp orderDate = null;
		sqlQuery = "INSERT INTO orders(orderId,price,location,deviceId,orderDate,"
				+ "status,deliveryDate,customerUsername,paymentMethod,orderDetails) VALUES(?,?,?,?,?,?,?,?,?,?);";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,order.getOrderId());
				ps.setDouble(2,order.getPrice());
				ps.setString(3,order.getLocation());
				ps.setInt(4,order.getDeviceId());
				ps.setTimestamp(5,orderDate.valueOf(order.getStartDate()));
				ps.setString(6,order.getStatus());
				ps.setString(7,order.getDeliveryDate());
				ps.setString(8,order.getUsername());
				ps.setString(9,order.getPaymentMethod());
				ps.setString(10, orderDetails);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		sqlQuery ="DELETE FROM activeorder WHERE orderId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1, order.getOrderId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sqlQuery = "UPDATE users SET amount = amount + 1 where username=?;";
		try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setString(1,order.getUsername());
					ps.executeUpdate();
				}
			
			}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param activeSale
	 * @return amount of the product in the active order
	 * Functionality: if the product already exists in the active order
	 * and have the orderId add to the amount in the same row 
	 */
	public static int checkProduct(ActiveSale activeSale) {
		int amount=0;
		String sqlQuery = "SELECT amount FROM activeorder WHERE orderId = ? AND productName=? ;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,activeSale.getOrderId());
				ps.setString(2,activeSale.getProductName());
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					amount=rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return amount;
	}
	/**
	 * @param activeSale
	 * Functionality: Adding an amount to existing product
	 * in the activeorder table that have the  same orderId
	 */
	public static void addAmountToExistingProduct(ActiveSale activeSale) {
		double totalPrice=activeSale.getAmount()*activeSale.getPrice();
		String sqlQuery = "UPDATE activeorder SET amount = ?,totalPrice=? WHERE orderId=? AND productName=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setDouble(1,activeSale.getAmount());
				ps.setDouble(2, totalPrice);
				ps.setInt(3, activeSale.getOrderId());
				ps.setString(4, activeSale.getProductName());
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param stock
	 * Functionality: if the amount of an product reaches 0 in the activeorder table
	 * it will be automatically deleted from the table and also it will 
	 * recalculate the total price 
	 */
	public static void UpdateActiveOrder(String[] stock) {
		if(Integer.parseInt(stock[2])==0) {
			String sqlQuery="DELETE FROM activeorder WHERE orderId=? AND productName= ?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setString(1,stock[0]);
					ps.setString(2, stock[1]);
					ps.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			double totalPrice=Integer.parseInt(stock[2])*Double.parseDouble(stock[3]);
			String sqlQuery = "UPDATE activeorder SET amount = ?,totalPrice=? WHERE orderId=? AND productName=?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setString(1,stock[2]);
					ps.setDouble(2, totalPrice);
					ps.setString(3, stock[0]);
					ps.setString(4, stock[1]);
					ps.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}

		
	}
	/**
	 * @param messageData
	 * @return status "Order status"
	 * Functionality : Checks if the orderId that the customer insert
	 * has a status waiting for PickUp if it does the status would be
	 * updated to Approved the customer took his order
	 */
	public static String recieveOrder(String[] messageData) {
		String status="null";
		String sqlQuery="SELECT status FROM orders WHERE orderId=? AND customerUserName=? ;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,Integer.parseInt(messageData[0]));
				ps.setString(2,messageData[1]);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					status=rs.getString(1);
				}
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		sqlQuery = "UPDATE orders SET status = 'Approved' WHERE orderId=? AND customerUserName=? AND status='Waiting for PickUp';";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,Integer.parseInt(messageData[0]));
				ps.setString(2,messageData[1]);
				ps.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	/**
	 * @param id
	 * @return amount of the product
	 * Functionality: decrease by 1 the product from the cart once the amount
	 * reaches 0 it can not decrease any more .
	 */
	public static int removeFromCart(String[] id) {
		// TODO Auto-generated method stub
		int amount = 0;
		double price=0;
		String sqlQuery="SELECT amount,price FROM activeorder WHERE orderId=? AND productName=? ;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,Integer.parseInt(id[0]));
				ps.setString(2,id[1]);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					amount=rs.getInt(1);
					price=rs.getDouble(2);
					
				}
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		amount-=1;
		if(amount<=0) {
			sqlQuery="DELETE FROM activeorder WHERE orderId=? AND productName= ?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setString(1,id[0]);
					ps.setString(2, id[1]);
					ps.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}else {
			sqlQuery = "UPDATE activeorder SET amount = ?,totalPrice=? WHERE orderId=? AND productName=?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1,amount);
					ps.setDouble(2,price*amount);
					ps.setInt(3,Integer.parseInt(id[0]));
					ps.setString(4,id[1]);
					ps.executeUpdate();
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return amount;
	}

	/**
	 * @param orderId
	 * Functionality: The method removes all the data from the active order where the right order id 
	 * 
	 */
	public static void DeleteActiveOrder(int orderId) {
		String sqlQuery="DELETE FROM activeorder WHERE orderId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,orderId);
				ps.executeUpdate();
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public static void updateIsInUse(Integer deviceCode) {
		// TODO Auto-generated method stub
		String sqlQuery ="UPDATE devicemanagement SET isInUse='0' WHERE deviceId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,deviceCode);
				ps.executeUpdate();
			}
			}catch(SQLException e){
				e.printStackTrace();
			}
	}
	public static int getIsInUse(Integer deviceCode) {
		// TODO Auto-generated method stub
		int isInUse=0;
		String sqlQuery="SELECT isInUse FROM devicemanagement WHERE deviceId=?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setInt(1,deviceCode);
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					isInUse=rs.getInt(1);
					
				}
				}
		}catch (SQLException e) {
			e.printStackTrace();
		}if(isInUse==0) {
			sqlQuery ="UPDATE devicemanagement SET isInUse='1' WHERE deviceId=?;";
			try {
				if (DBConnector.conn != null) {
					PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
					ps.setInt(1,deviceCode);
					ps.executeUpdate();
				}
				}catch(SQLException e){
					e.printStackTrace();
				}
		}
		return isInUse;
	}
}
