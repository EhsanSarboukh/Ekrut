package gui;

import java.io.IOException;

import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author adham + daniella
 *
 */
public class DeliveryManController {
	@FXML
	private Button btnLogout;

	@FXML
	private Button btnCreatOrder;

	@FXML
	private Button btnUpdateStatus;
	
	private Stage stage;
	private Scene scene;
	private Parent root;



	/**
	 * @author adham + daniella
	 * @param event happened by clicking on "Create Order" button
	 * @throws IOException
	 */
	@FXML
	public void ClickCreateOrder(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Order Main Page");
		primaryStage.setScene(scene);		
		primaryStage.show();
		

	}
	
	/**
	 * @author adham + daniella
	 * @param event happened by clicking on "Logout" button
	 * @throws IOException
	 */
	@FXML
	void ClickLogout(ActionEvent event) throws IOException {
		Message message=new Message(MessageType.LogOut,LoginController.user);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login form");
		stage.setScene(scene);
		stage.show();
		
	}

	/**
	 * @author adham + daniella
	 * @param event happened by clicking on "Update" button
	 * @throws IOException
	 */
	@FXML
	public void ClickUpdate(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/UpdateOrderStatus.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Update Status");
		primaryStage.setScene(scene);		
		primaryStage.show();

	}
	/**
	 * @author adham + daniella
	 * @throws IOException
	 */
	public void start() throws IOException {
		 
	}

}
