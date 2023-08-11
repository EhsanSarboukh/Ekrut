package gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ChatClient;
import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh
 * Class purpose defines a controller to the client main page 
 *
 */
public class ClientMainFormController implements Initializable{
	@FXML
	private Button btnLogOut;
	
	@FXML
	private Button btnSubscribe;
	@FXML
	private Button btnMakeOrder;
	@FXML
	private ImageView imgOL;
	@FXML
	private ImageView imgEK;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	/**
	 * @param event
	 * @throws IOException
	 * Functionality : Change the isLoggedIn bit from 1 to 0 .
	 * 
	 */
	@FXML
	private void ClickLogOut(ActionEvent event)throws IOException{
		Message message=new Message(MessageType.LogOut,LoginController.user);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnLogOut.getScene().getWindow();
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality: On click action event once the client clicked the create order button
	 * he will proceed to the page MainPage.fxml
	 */
	@FXML
	private void ClickMakeOrder(ActionEvent event)throws IOException{
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Order Main Page");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnMakeOrder.getScene().getWindow();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality : On click action event it will send the client to a form where
	 * he can send a request to become a subscriber
	 */
	@FXML
	private void ClickSubscribe(ActionEvent event)throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Subscribe.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Subscriber Main Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnMakeOrder.getScene().getWindow();
	}


	/**
	 *Initialize the page 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		if(!(ClientUI.machineCode.equals("1"))) {
			if(!(ChatClient.role.equals("customer"))) {
				btnSubscribe.visibleProperty().setValue(false);
			}
			imgOL.visibleProperty().setValue(false);
		}
		else {
			imgEK.visibleProperty().setValue(false);
		}
	}

}
