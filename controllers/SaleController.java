package controllers;

import java.util.ArrayList;
import entity.Message;
import entity.MessageType;
import entity.Sale;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author Sara Asaad , Mhemad Mdah
 *
 */
public class SaleController {
	
	public static ObservableList<Sale> listSale = FXCollections.observableArrayList();
	public static ArrayList<Sale> saleArr = new ArrayList<>();
	public static ObservableList<Sale> listActivateSale = FXCollections.observableArrayList();
	public static ArrayList<Sale> saleActivateArr = new ArrayList<>();
	
	/**
	 *  all details of the sales in the system
	 */
	public static void getAllSales() {
		Message msg = new Message(MessageType.getSales, null);
		ClientUI.chat.accept(msg);
		
		for (Sale s : listSale)
			listSale.remove(s);
		for (Sale s : saleArr) 
			listSale.add(s);
	}
	
	/**
	 * all sales that need to activate based on the request of the marketing manager
	 */
	public static void getSalesToActivate() {
		Message msg = new Message(MessageType.getSalesToActivate, null);
		ClientUI.chat.accept(msg);
		
		for (Sale s : listActivateSale)
			listActivateSale.remove(s);
		for (Sale s : saleActivateArr) 
			listActivateSale.add(s);
	}
}
