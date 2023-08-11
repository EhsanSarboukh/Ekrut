package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ChatClient;
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
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class AddSubscriberController implements Initializable{
	@FXML
	private Button btnApproval ;
	@FXML
	private Button btnBack ;
	
	@FXML
	private Label LabelApproval;
	private Stage stage;
	private Scene scene;
	private Parent root;
	
	/**
	 * @author Sabeel
	 * @param event
	 * @throws IOException
	 * A function activated when the back button is pressed, changes the displayed screen to the previous screen.
	 * The method can throw an exception in some cases
	 */
	@FXML
	public void BackBtnOnAction(ActionEvent event) throws IOException {
		
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ceoMainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Ceo Main Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnBack.getScene().getWindow();
	}
	
	/**
	 * @author Sabeel
	 * @param event
	 * @throws IOException
	 * The method is activated when the "Approve all requests" button is pressed by the CEO.
	 *  a message is sent to the server to check if there are requests to register as a subscriber
	 *  if the server returns a message that there are no requests in the database flag2=false displayed label "There are no subscription requests"
	 *  otherwise a new message is sent to the server to confirm the requests,The statuses are updated in the database
	 * The method can throw an exception in some cases
	 * 
	 */
	@FXML
	public void ClickApproveOnAction(ActionEvent event) throws IOException{
    Message messageForSubscriber = new Message(MessageType.checkRequests,LoginController.user);//!!!!!!!!!!!
	ClientUI.chat.accept(messageForSubscriber);
		
    if(ChatClient.flag2==true) {
    	 Message message=new Message(MessageType.changeStatus, LoginController.user);
			
			
			ClientUI.chat.accept(message);
			/*Parent root = FXMLLoader.load(getClass().getResource("/client/gui/ceoMainPage.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Ceo Main Page");
			stage.setScene(scene);
			stage.show();
			Stage stage = (Stage) btnBack.getScene().getWindow();*/
	}
	else {
		LabelApproval.setText("There are no subscription requests");
		 }
		
	}
	/**
	 * @author Sabeel
	 *The method is activated when the page  addSubscribers.fxml is loads and initializes the field on this page
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LabelApproval.setText(" ");
	}
	
	
}
