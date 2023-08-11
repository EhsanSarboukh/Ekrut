package gui;

import java.io.IOException;

import controllers.ChatClient;
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

/**
 * @author adham
 * login by smartphone (insert id)
 */
public class loginBySpmatphoneController {
	
	public static String[] user;
	public static String userID;
	public static boolean flagIdExist=false;

	@FXML 
	private TextField txtID;

	@FXML
	private Button LoginBySmartphoneButton;
	
	@FXML
	private Label LoginMessageLabel;
	
	@FXML 
	private Button BackBtn;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	private String fxmlLoc=null;
	private String title=null;
	
	
	public void start(Stage primaryStage) throws IOException {
		// TODO Auto-generated method stub
		txtID.setText("");
		
	}
	
	/**
	 * @author adham
	 * @param event
	 * @throws IOException
	 * When click on back button the application will open the window of login  
	 */
	@FXML
	public void BackBtnOnAction(ActionEvent event) throws IOException {
		
		root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login Form");
		stage.setScene(scene);
		stage.show();
		stage = (Stage) BackBtn.getScene().getWindow();
	}
	
	/**
	 * @author adham
	 * @param event
	 * @throws IOException
	 * When click on login button the application will open the window of the user if the id is correct and if the user is a subscriber and if not logged in. 
	 */
	@FXML
	public void clickLogin(ActionEvent event) throws IOException {
		user= new String[2];
		userID=txtID.getText();
		if(txtID.getText().isEmpty()) {
			LoginMessageLabel.setText("Empty ID!");
		}
		else {
			Message message = new Message(MessageType.CheckIDValidatin,userID);
			ClientUI.chat.accept(message);
			if(flagIdExist==false)
				LoginMessageLabel.setText("Wrong ID!");
			else
			{
			message = new Message(MessageType.GetSubscriberStatus,userID);
			ClientUI.chat.accept(message);
			if(ChatClient.subscribeStatus)
			{
				message = new Message(MessageType.GetUsernameAndPassword,userID);
				ClientUI.chat.accept(message);
				user[0]=ChatClient.username;
				user[1]=ChatClient.password;
				message = new Message(MessageType.CheckLogin,user);
				ClientUI.chat.accept(message);
				if(ChatClient.flag==true) {
					message=new Message(MessageType.Login, user);
					ClientUI.chat.accept(message);
					LoginController.user=user;
					switch(ChatClient.role) {
						case "ceo":
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
						case "null":
							LoginMessageLabel.setText("(NULL) Wrong ID!");
						default:
							LoginMessageLabel.setText("Wrong ID!");
					}
					if(fxmlLoc!=null && title!=null) {
						root = FXMLLoader.load(getClass().getResource(fxmlLoc));
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
			else 
				LoginMessageLabel.setText("User is not a subscriber");
		}
		}		
	}

}
