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

public class ceoMainPageController {
   @FXML
	private Button AddSubscribersBtn;
   @FXML
  	private Button  btnMakeOrder;
   @FXML
 	private Button  btnLogOut;
   @FXML
   private Button ViewMonthlyReport;
	private Stage stage;
	private Scene scene;
	private Parent root;
	/**
	 * @author Sabeel
	 * @param event
	 * @throws IOException
	 * The method is activated when the "Log out" button is pressed
	 * The  method returns the previous window (login page)
                                          
	 */
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
		Stage stage = (Stage) btnLogOut.getScene().getWindow();
	}
	
	/**
	 * @author Sabeel
	 * @param event
	 * @throws IOException
	 * The method is activated when the "Create Order" button is pressed.
	 * The method opens for  the customer a new window where he can start placing the order
	 */
	@FXML
	public void CreateOrderBtnOnAction(ActionEvent event) throws IOException {
	
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Order Main Page");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnMakeOrder.getScene().getWindow();
	}
	
	/**
     * @author Sabeel
     * @param event
     * @throws IOException
     * The method is activated when the "Add subscribers" button is pressed
     * opens for the CEO a new window where he can approve customer requests
     */
    @FXML	
	public void AddSubscriberrBtnOnAction(ActionEvent event) throws IOException {
	
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/addSubscribers.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Add Subscriber");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) AddSubscribersBtn.getScene().getWindow();
	}
    
	/**
     * @author Sabeel
     * @param event
     * @throws IOException
     * The method is activated when the "View Monthly Report" button is pressed
     * opens for the CEO a new window where he view the monthly report.
     */
    @FXML
  	public void clickViewMonthlyReports(ActionEvent event) throws IOException {	
  		Parent root = FXMLLoader.load(getClass().getResource("/gui/ViewMonthlyReport.fxml"));
  		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
  		scene = new Scene(root);
  		stage.setTitle("View Monthly Report");
  		stage.setScene(scene);
  		stage.show();
  		//Stage stage = (Stage) ViewMonthlyReport.getScene().getWindow();
  	}
} 
