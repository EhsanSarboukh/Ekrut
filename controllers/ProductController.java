package controllers;

import java.util.ArrayList;
import entity.ActiveSale;
import entity.Message;
import entity.MessageType;
import entity.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Ehsan Sarboukh , Mhemad Mdah
 * class handles all things relevant to data of products in data base
 */
public class ProductController {
	public static ObservableList<Product> list = FXCollections.observableArrayList();
	public static ArrayList<Product> subArr = new ArrayList<>();
	public static ObservableList<ActiveSale> listActive = FXCollections.observableArrayList();
	public static ArrayList<ActiveSale> activeArr = new ArrayList<>();
	public static Product s = new Product(null, null, 0, 0, 0,0.0);
	public static String[] name=new String[2];
	public static int orderId;
	public static double totalPrice;
	public static int amount;
	public static int amo=0;
	public static String PickUpOrderLabel;
	
	/**
	 * @param device
	 * all details of the products of the given device
	 */
	public static void getAllProducts(String[] device) {
		Message msg = new Message(MessageType.getProducts, device);
		ClientUI.chat.accept(msg);
		
		for (Product p : list)
			list.remove(p);
		for (Product p : subArr) {
			list.add(p);
		}
	}

	/**
	 * @param orderId
	 *  all details of the order made now by the current customer
	 */
	public static void getAllActiveSales(int orderId) {
		// TODO Auto-generated method stub
		Message msg = new Message(MessageType.ViewActiveSale, orderId);
		ClientUI.chat.accept(msg);
		
		for (ActiveSale p : listActive)
			listActive.remove(p);
		for (ActiveSale p : activeArr) {
			listActive.add(p);
		}
	}

	/**
	 * @param orderId
	 * if the order id entered by the customer is valid
	 */
	public static boolean checkValidOrderId(String orderId) {
		char[] orderNum=orderId.toCharArray();
		for(char c :orderNum) {
			if(!((c>= '0') && (c<= '9'))) {
				return false;
			}
		}
		return true;
	}


}
