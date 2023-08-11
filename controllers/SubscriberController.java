package controllers;

import java.util.ArrayList;

import entity.Message;
import entity.MessageType;
import entity.Subscriber;
import entity.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class SubscriberController {
	public static ObservableList<User> listToUser = FXCollections.observableArrayList();
	public static ArrayList<Subscriber> subArr = new ArrayList<>();
	public static Subscriber s = new Subscriber(null, null, null, null, null, null, null);
	public static ArrayList<User> subArrUser = new ArrayList<>();
	public static User u = new User(null, null, null, null, null, null,null,null,0,null,null);

	
	/**
	 * @author Sabeel
	 * @param user
	 * A method that receives an array that contains a username and password
	 * Sends the message "getSubscriberDetails" to the server using the accept method to get the subscriber details then remove all the users from listToUser.
	 * adding the users that in subArrUser (the array that returned from the server and filled with data from the database) to listToUser of type ObservableList
	 */
	public static void getSubscriberDetails(String[] user) {
		Message msg = new Message(MessageType.getSubscriberDetails, user);
		ClientUI.chat.accept(msg);
		for (User u : listToUser)
			listToUser.remove(u);
		for (User u : subArrUser) {
			listToUser.add(u);
	
		}
		
	}
}
