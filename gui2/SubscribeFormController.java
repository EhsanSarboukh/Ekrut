package gui;
import javafx.scene.Parent;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ChatClient;
import controllers.ClientUI;
import controllers.SubscriberController;
import javafx.scene.text.Text;
import entity.Message;
import entity.MessageType;
import entity.Subscriber;
import entity.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class SubscribeFormController implements Initializable{
	public static Stage SubscribeStage;
	private SubscriberController subscriberController=new SubscriberController();

	@FXML
	private TextField txtFirstName;
	
	@FXML
	private TextField txtLastName;
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtPhoneNum;
	
	@FXML
	private TextField txtCreditNum;
	
	@FXML
	private TextField txtEmail;
	@FXML
	private ImageView imgOL;
	@FXML
	private ImageView imgEK;
	
	@FXML
	private Label SubscribeMessageLabel;
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnSubscribe;
	
	

	/**
	 * @author Sabeel
	 * A method that fills the fields according to the user's data
	 */
	public void fillTextField() {

		SubscriberController.getSubscriberDetails(LoginController.user);
		String s=(String) subscriberController.listToUser.get(0).getFirstName();
		txtFirstName.setText(subscriberController.listToUser.get(0).getFirstName());
		txtLastName.setText(subscriberController.listToUser.get(0).getLastName());
		txtID.setText(subscriberController.listToUser.get(0).getId());
		txtPhoneNum.setText(subscriberController.listToUser.get(0).getPhoneNumber());
		txtEmail.setText(subscriberController.listToUser.get(0).getEmail());
		txtCreditNum.setText(subscriberController.listToUser.get(0).getCreditCardNumber());
		
	}
	
	
	
	/**
	 * @author Sabeel
	 * @param event
	 * @throws IOException
	 * A function activated when the back button is pressed, changes the displayed screen to the previous screen.
	 * The method can throw an exception in some cases
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("ClientMainForm.fxml"));
		if(MainPageFormController.MainStage==null){
			MainPageFormController.MainStage = new Stage();
		}
		Scene scene = new Scene(root);
		MainPageFormController.MainStage.setTitle("Client Main Form");
		MainPageFormController.MainStage.setScene(scene);		
		MainPageFormController.MainStage.show();
		
	}
	
	
	/**
	 * @author Sabeel
	 * @param event
	 * @throws IOException
	 * The method is activated when the "Subscribe" button is pressed.
	 * A message is sent to the server that a client wants to subscribe
	 * If the customer can to subscribe, a new message is sent to the server to update his status
	 * otherwise a message is displayed to the user "You already subscriber"
	 */
	@FXML
	public void ClicSubscribe(ActionEvent event) throws IOException{
    Message messageForSubscriber = new Message(MessageType.customerWantToSubscribe,LoginController.user);
	ClientUI.chat.accept(messageForSubscriber);
    if(ChatClient.flag1==true) {
    	 Message message=new Message(MessageType.customerCanSubscribe, LoginController.user);
			
			
			ClientUI.chat.accept(message);
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
			if(MainPageFormController.MainStage==null){
				MainPageFormController.MainStage = new Stage();
			}
			Scene scene = new Scene(root);
			MainPageFormController.MainStage.setTitle("Main Form");
			MainPageFormController.MainStage.setScene(scene);		
			MainPageFormController.MainStage.show();
	}
	else {
				SubscribeMessageLabel.setText("You already subscriber/Waiting for approval");
		 }
		
	}
	
	/**
	 * @author Sabeel
	 *The method is activated when the page Subscribe.fxml is loads and initializes the fields on this page
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(ClientUI.machineCode.equals("1")) {
			imgEK.visibleProperty().setValue(false);
		}
		else {
			imgOL.visibleProperty().setValue(false);
		}
		txtFirstName.setText("");

		txtLastName.setText("");
		txtID.setText("");
		txtPhoneNum.setText("");
		txtCreditNum.setText("");
		txtEmail.setText("");
	
		fillTextField();
		
	}

}
