package gui;


import java.io.IOException;

import controllers.ClientUI;
import controllers.ProductController;
import entity.Message;
import entity.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh and Mhemad Mdah
 * Class purpose In order to pick up an order that has been created 
 * in remote order with pick up option this page will be responsible for the 
 * pick up process
 *
 */
public class PickupOrderFormController {
	public static Stage pickupStage;

	@FXML
	private TextField txtID;
	
	@FXML
	private Label lblWrongID;
	
	@FXML
	private Button btnGetYourOrder;
	
	@FXML
	private Button backBtn;
	Stage stage;
	Scene scene;
	String[] messageData=new String[2];
	
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality : On click action event button once the txt fields are valid and 
	 * answers all the cases and confirmed the user or the client can receive its order.
	 */
	@FXML
	public void ClickGetYourOrder(ActionEvent event) throws IOException{
		if(txtID.getText().isEmpty() || ProductController.checkValidOrderId(txtID.getText())==false) {
			lblWrongID.setText("Please insert the Order Id !");
		}else {
			messageData[0]=txtID.getText();
			messageData[1]=LoginController.user[0];
			Message message=new Message(MessageType.RecieveOrder,messageData);
			ClientUI.chat.accept(message);
			System.out.println(ProductController.PickUpOrderLabel);
			if(ProductController.PickUpOrderLabel.equals("Waiting for PickUp")) {
				lblWrongID.setText("Thanks for purchasing,here is your order:)");
			}else {
				lblWrongID.setText("You Don't have any order waiting for you to pick up!");
			}
		}
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality: On Click Action event button once its clicked it will return to the previous page 
	 *in this case the MainPage.fxml
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Order Main Page");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) backBtn.getScene().getWindow();
		
	}
}
