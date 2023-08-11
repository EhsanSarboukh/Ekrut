package gui;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;

import controllers.ChatClient;
import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.*;


/**
 * @author Ehsan Sarboukh and Mhemad Mdah
 *Controller Class in order to control the Login form page 
 *initializable method is implemented into the Controller in order 
 *to fill data into the page .
 */
public class LoginController implements Initializable{
	public static String[] user;
	@FXML
	private Button cancelButton;
	@FXML 
	private TextField txtusername;
	@FXML 
	private PasswordField passwordField;
	@FXML
	public static Button loginButton;
	@FXML
	private Label LoginMessageLabel;
	
	@FXML
	private Label olpTitle;
	
	@FXML
	private Label ekTitle;
	@FXML
	private ImageView ekImage;
	
	@FXML
	private ImageView olImage;
	
	@FXML 
	private Button LoginBySmartphone;
	public static Stage stage;
	private Scene scene;
	private String fxmlLoc=null;
	private String title=null;
	

	public void SetLabel() {
		LoginMessageLabel.setText("Error Username Or Password!");
	}
	/**
	 * @param event
	 * @throws IOException
	 * Functionality : A button action event (onClick) in addition to proceed to the second page
	 * the fields must contain a valid text that is in the DB and the column isLoggedIn must be '0'
	 * once it succeeded to proceed the isLoggedIn column in the right place turn to '1' 
	 * the selection is done by the username as primary key .
	 * Each username has a different role and for each role there is a different page 
	 * to open and proceed to and its done by match ,moreover if we need to connect to EK 
	 * system it will proceed automatically to customer service. 
	 * 
	 */
	@FXML
	public void clickLogin(ActionEvent event) throws IOException {
		user=new String[2];
		//user=new String[3];
		user[0]=txtusername.getText();
		user[1]=passwordField.getText();
		if(txtusername.getText().isEmpty() || passwordField.getText().isEmpty()) {
			LoginMessageLabel.setText("Error Username Or Password!");
		}
		else {
			Message message = new Message(MessageType.CheckLogin,user);
			ClientUI.chat.accept(message);
			if(ChatClient.flag==true) {
				message=new Message(MessageType.Login, user);
				ClientUI.chat.accept(message);
				if(!(ClientUI.machineCode.equals("1"))) {
					fxmlLoc="/gui/ClientMainForm.fxml";
					title="Customer Main Page";
				}else {
				
					switch(ChatClient.role) {
						case "ceo":
							fxmlLoc="/gui/ceoMainPage.fxml";
							title="Ceo Main Form";
							break;
						case "customer":
							fxmlLoc="/gui/ClientMainForm.fxml";
							title="Customer Main Page";
							break;
						case "delivery_man":
							fxmlLoc="/gui/DeliveryMan.fxml";
							title="Delivery Man";
							break;
						case "marketing_manager":
							fxmlLoc="/gui/MarketingManagerForm.fxml";
							title="Marketing Manager";
							//Stage stage2 = (Stage) loginButton.getScene().getWindow();
							break;
						case "marketing_employee":
							fxmlLoc="/gui/MarketingEmployee.fxml";
							title="Marketing Employee";
							//Stage stage3 = (Stage) loginButton.getScene().getWindow();
							break;
						case "stock_employee":
							fxmlLoc="/gui/operationWorker.fxml";
							title="Stock Employee";
							//Stage stage4 = (Stage) loginButton.getScene().getWindow();
							break;
						case "area_manager":
							fxmlLoc="/gui/AreaManagerPrimary.fxml";
							title="Area Manager";
							//Stage stage1 = (Stage) loginButton.getScene().getWindow();
							break;
						case "customer_manager":
							fxmlLoc="/gui/CustomerMangment.fxml";
							title="Customer Management";
							break;
						case "null":
							LoginMessageLabel.setText("Error Username Or Password!");
							default:
								SetLabel() ;
					}
				}
				
				if(fxmlLoc!=null && title!=null) {
					Parent root = FXMLLoader.load(getClass().getResource(fxmlLoc));
					stage = (Stage)((Node) event.getSource()).getScene().getWindow();
					scene = new Scene(root);
					stage.setTitle(title);
					stage.setScene(scene);
					stage.show();
				}
			
		}
		else {
			LoginMessageLabel.setText("User is already logged in!");
		}
			
			
	}		
	}
	
	
	@FXML	
	public void LoginBySmartphoneOnAction(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/client/gui/LoginBySmartphone.fxml"));//we have to add this file
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Log in via SmartPhone :)");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) LoginBySmartphone.getScene().getWindow();
	}

	@FXML
	public void ClickCancel(ActionEvent event) throws IOException{

		Message message=new Message(MessageType.Disconnect,null);
		ClientUI.chat.accept(message);

	}
	

	/**
	 * @param primaryStage
	 * @throws IOException
	 * Functionality: A method that start the the fxml page of the Login
	 */
	public void start(Stage primaryStage) throws IOException {
		//Thread.currentThread().getThreadGroup().destroy();
		CreateOrderFormController.flag = false;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		Scene scene = new Scene(root);
		primaryStage.setTitle("Login Form");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	/**
	 *Initialize the fxml Login Page it has two cases for instance 
	 *if we are in the OL and will hide the images and the fields 
	 *that connect to EK 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(ClientUI.machineCode.equals("1")) {
			LoginBySmartphone.visibleProperty().setValue(false);
			ekTitle.visibleProperty().setValue(false);
			ekImage.visibleProperty().setValue(false);
		}else {
			olpTitle.visibleProperty().setValue(false);
			olImage.visibleProperty().setValue(false);
		}
	}
	

}
