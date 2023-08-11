package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import controllers.ChatClient;
import controllers.ClientController;
import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
import entity.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh
 *Class purpose to connect to the DB and open a connection for clients to join the 
 *server and the program
 */
public class ConnectToServerController implements Initializable{
	public static ChatClient clientControl;
	

	
	public static Stage firstStage;
	public static Stage secondStage;
	
	public static LoginController loginControl;

	
	@FXML
	private Button ConnectBtn;
	
	@FXML
	private Button olConnectBtn;
	
	@FXML
	private Button ExitBtn;
	
	@FXML
	private TextField txtIp;
	
	
	private String getID() {
		return txtIp.getText();
	}
	
	
	
	
	/**
	 * @param event
	 * @throws Exception
	 * Functionality: Once the client click the connect button and fill the fields with 
	 * the correct values it will send all customer and turn them to users so they can create order and
	 * use the system and opens a connection to the server and the db in addition to receive date .
	 */
	public void ClickConnectEK(ActionEvent event) throws Exception {
		String ip;
		
		ip=getID();
		if(ip.trim().isEmpty()) {
			System.out.println("You must enter a Ip Address");
					
		}
		else
		{
			ClientUI.chat =new ClientController(ip,5555);
			Message message=new Message(MessageType.GetAllCustomers,null);
			ClientUI.chat.accept(message);
			ClientUI.machineCode="2";
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			firstStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
			Parent root = loader.load();
			loginControl = loader.getController();
			Scene scene = new Scene(root);
			firstStage.setTitle("Login Form");
			firstStage.setScene(scene);
			firstStage.show();
			
			loginControl = new LoginController();
			loginControl.start(primaryStage);

		}
	}
	/**
	 * @param event
	 * CLose the system
	 */
	
	@FXML
	public void ClickConnectOL(ActionEvent event) throws Exception {
		String ip;
		
		ip=getID();
		if(ip.trim().isEmpty()) {
			System.out.println("You must enter a Ip Address");
					
		}
		else
		{
			ClientUI.chat =new ClientController(ip,5555);
			Message message=new Message(MessageType.GetAllCustomers,null);
			ClientUI.chat.accept(message);
			ClientUI.machineCode="1";
			((Node)event.getSource()).getScene().getWindow().hide(); //hiding primary window
			Stage primaryStage = new Stage();
			firstStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/Login.fxml"));
			Parent root = loader.load();
			loginControl = loader.getController();
			Scene scene = new Scene(root);
			firstStage.setTitle("Login form");
			firstStage.setScene(scene);
			firstStage.show();
			
			loginControl = new LoginController();
			loginControl.start(primaryStage);

		}
	}
	@FXML
	void ClickExit(ActionEvent event) {
		System.exit(0);
	}

	/**
	 * @param primaryStage
	 * @throws IOException
	 * Start the Connect to Server page.
	 */
	public void start(Stage primaryStage) throws IOException {
		// TODO Auto-generated method stub
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ConnectToServer.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Client");
		primaryStage.setScene(scene);
		primaryStage.show();		
	}

	/**
	 *Initialize the text field and sets the text to localhost as default
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		txtIp.setText("localhost");
	}
}
