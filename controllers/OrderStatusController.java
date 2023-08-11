package controllers;

import java.util.ArrayList;
import entity.OrderStatus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * @author adham + daniella
 *
 */
public class OrderStatusController {
	public static ObservableList<OrderStatus> listorder = FXCollections.observableArrayList();
	public static ArrayList<OrderStatus> orderkArr = new ArrayList<>();
	

	/**
	 * @author adham + daniella
	 */
	public static void getAllDetails() {
		listorder.clear();
		for (OrderStatus os : orderkArr) {
			listorder.add(os);
		}

	}
}
