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
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class operationWorker {
	@FXML
	private Button LogOutBtn;


	@FXML
	private Button CreateOrderBtn;

	@FXML 
	private Button ViewMessage;
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
		Stage stage = (Stage) CreateOrderBtn.getScene().getWindow();
	}	
	@FXML
	public void ViewMessageOnAction(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/messageFromAreaManager.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Message From Area Manager");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) ViewMessage.getScene().getWindow();
	}
	@FXML
	public void LogOutBtnOnAction(ActionEvent event) throws IOException {
		Message message=new Message(MessageType.LogOut,LoginController.user);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) LogOutBtn.getScene().getWindow();
	}
	
	public void start() {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/operationWorker.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage.setTitle("Operation Worker");
		stage.setScene(scene);
		stage.show();
	}
}
