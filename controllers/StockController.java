package controllers;

import java.io.Serializable;
import java.util.ArrayList;
import entity.AreaStock;
import entity.DeviceStock;
import entity.Message;
import entity.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 * @author daniella
 *The StockController class is a utility class that provides functions
 * for managing lists of AreaStock and DeviceStock objects
 */
public class StockController implements Serializable {
	  // class variables 
		/**
		 * A unique identifier for the class.
		 */
	private static final long serialVersionUID = 1L;
	/**
	 * An ObservableList of AreaStock objects.
	 */
	public static ObservableList<AreaStock> list = FXCollections.observableArrayList();
	/**
	 * An ArrayList of AreaStock objects.
	 */
	public static ArrayList<AreaStock> subArr = new ArrayList<>();
	/**
	 * An ObservableList of DeviceStock objects.
	 */
	public static ObservableList<DeviceStock> deviceList = FXCollections.observableArrayList();
	/**
	 * An ArrayList of DeviceStock objects.
	 */
	public static ArrayList<DeviceStock> deviceArr = new ArrayList<>();
	/**
	 * A flag that used in chat client for a message.
	 */
	public static boolean flag=false;
	
	/**
	 * @param location
	 * Retrieves stock information for the specified location and updates the list of AreaStock objects.
     * All AreaStock objects in the list ObservableList are removed, and all AreaStock
     *  objects in the subArr ArrayList are added to the list ObservableList.
	 */
	public static void getStockByLocation(String location) {
		Message msg = new Message(MessageType.GetStockByLocation, location);
		ClientUI.chat.accept(msg);
		for (AreaStock as : list)
			list.remove(as);
		for (AreaStock as : subArr) {
			list.add(as);
		}
	}
	
	/**
	 * @param deviceId
	 * Retrieves stock information for the specified device ID and updates the list of DeviceStock objects.
     * All DeviceStock objects in the deviceList ObservableList are removed, and all DeviceStock objects in the deviceArr
     *  ArrayList are added to the deviceList ObservableList.
	 */
	public static void getStockByDeviceId(String deviceId) {
		// TODO Auto-generated method stub
		Message msg = new Message(MessageType.GetStockByDeviceId, deviceId);
		ClientUI.chat.accept(msg);
		for (DeviceStock dl : deviceList)
			deviceList.remove(dl);
		for (DeviceStock da : deviceArr) {
			deviceList.add(da);
		}
	}

}
