package controllers;

import java.util.ArrayList;
import entity.AreaStock;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author daniella
 * This class is a utility class that provides functions for managing a list of AreaStock objects.
 *
 */
public class ClintKstockMessageController {
	 //class variables******
		/**
		 * An ObservableList of AreaStock objects.
		 */
	public static ObservableList<AreaStock> liststock = FXCollections.observableArrayList();
	/**
	 * An ArrayList of AreaStock objects.
	 */
	public static ArrayList<AreaStock> stockArr = new ArrayList<>();
	//Instance variables*****
		/**
		 *  An instance of AreaStock.
		 */
	public static AreaStock st = new AreaStock(0,0, 0, 0,0, 0, null,null);
	
	/**
	 * @author daniella 
	 * Retrieves all details and updates the list of AreaStock objects.
     * All AreaStock objects in the liststock ObservableList are removed, 
     * and all AreaStock objects in the stockArr ArrayList are added to the liststock ObservableList.
	 */
	public static void getAllDetails() {
		for (AreaStock st : liststock)
			liststock.remove(st);
		for (AreaStock st : stockArr) {
			liststock.add(st);
		
	}
	}
}