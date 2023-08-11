package DBControl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Product;
import entity.Sale;

/**
 * @author Sara Asaad , Mhemad Mdah
 *
 */
public class SaleDBController {

	/**
	 *  all sales in the system with all their details
	 */
	public static ArrayList<Sale> getSale() {
		String sqlQuery = "SELECT * FROM sales;";
		ArrayList<Sale> sales = new ArrayList<Sale>();
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					String productName = rs.getString(1);
					String status = rs.getString(2);
					double sale = rs.getDouble(3);
					String area = rs.getString(4);
					
					sales.add(new Sale(productName,status,sale,area));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	/**
	 * @param sale
	 *  changes status of sale from not active to activate
	 */
	public static void UpdateSale(String[] sale) {
		String sqlQuery = "UPDATE sales SET status = 'activate' where product = ? AND area = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,sale[0]);
				ps.setString(2,sale[1]);
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 *  all sales that need to be activated
	 */
	public static ArrayList<Sale> getSalesToActivate() {
		String sqlQuery = "SELECT * FROM sales WHERE status = 'activate';";
		ArrayList<Sale> sales = new ArrayList<Sale>();
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ResultSet rs= ps.executeQuery();
				while(rs.next()) {
					String productName = rs.getString(1);
					String status = rs.getString(2);
					double sale = rs.getDouble(3);
					String area = rs.getString(4);
					
					sales.add(new Sale(productName,status,sale,area));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sales;
	}
	
	/**
	 *  changes all sales that their status is activate to activated
	 */
	public static void UpdateSaleStatus() {
		String sqlQuery = "UPDATE sales SET status = 'activated' WHERE status = 'activate';";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * @param sale
	 *  adds sale to system
	 */
	public static void addSale(Sale sale) {
		// TODO Auto-generated method stub
		String sqlQuery = "INSERT INTO sales Values (?,?,?,?);";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1, sale.getProductName());
				ps.setString(2,sale.getStatus());
				ps.setDouble(3,sale.getSale());
				ps.setString(4,sale.getArea());
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param sale
	 *  changes status of sale from activated to not active 
	 */
	public static void deActivateSale(String[] sale) {
		// TODO Auto-generated method stub
		String sqlQuery = "UPDATE sales SET status = 'not Active' WHERE product = ? AND area = ?;";
		try {
			if (DBConnector.conn != null) {
				PreparedStatement ps = DBConnector.conn.prepareStatement(sqlQuery);
				ps.setString(1,sale[0]);
				ps.setString(2,sale[1]);
				ps.executeUpdate();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}

