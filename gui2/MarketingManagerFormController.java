package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Sara Asaad , Mhemad Mdah
 *
 */
public class MarketingManagerFormController implements Initializable  {
	
	@FXML
	private Button btnCreateOrder;
	
	@FXML
	private Button btnDisplay;


	@FXML
	private Button btnAddSale;

	@FXML
    private Button btnLogout;
	@FXML
	private Text txtMarketingManage;
	
	private Stage stage;
	private Scene scene;
	
	/**
	 * @param event
	 * @throws IOException
	 *  moves the user to page of main page of the order
	 */
	@FXML	
	public void ClickCreateOrder(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Order Main Form");
		primaryStage.setScene(scene);		
		primaryStage.show();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  shows all sales in system to the marketing manager in the page view sales
	 */
	@FXML	
	public void ClickDisplaySales(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/viewSales.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("View Sale Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnDisplay.getScene().getWindow();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  logs the user out of the system
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
		Stage stage = (Stage) btnLogout.getScene().getWindow();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  adds the sale to the system that has been entered by the user and saves it in DB
	 */
	@FXML
	void ClickAddSale(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/AddSaleForm.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Add Sale Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnAddSale.getScene().getWindow();
	}
	

}

