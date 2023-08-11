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
 * @author Mhemad Mdah,Sara Asaad
 *
 */
public class MarketingEmployeeController {
	@FXML
	private Button logOutbtn;
	
	@FXML
	private Button createOrderbtn;

	@FXML
	private Button operateDiscountbtn;
	private Stage stage;
	private Scene scene;
	private Parent root;

	@FXML
	public void CreateOrderBtnOnAction(ActionEvent event) throws IOException {
	
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Order Main Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) createOrderbtn.getScene().getWindow();

	}
	
	

	@FXML
	public void LogOutBtnOnAction(ActionEvent event) throws IOException {
		Message message=new Message(MessageType.LogOut,LoginController.user);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) logOutbtn.getScene().getWindow();
	}
	@FXML
	public void ClickActivateSales(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/activateSales.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Activate Sale Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) operateDiscountbtn.getScene().getWindow();
	}
	
	public void start() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/MarketingEmployee.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage.setTitle("Marketing Employee");
		stage.setScene(scene);
		stage.show();
	}
	

}





	

