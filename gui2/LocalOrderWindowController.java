package gui;

import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh and Mhemad Mdah
 * Class purpose to switch to other fxml pages once clicking on any button 
 * 
 *
 */
public class LocalOrderWindowController implements Initializable {
	
	@FXML
	private ComboBox<Integer> pickDevice;
	
	
	@FXML
	private Button backBtn;
	
	@FXML
	private Label locallbl;
	Stage stage;
	Scene scene;
	public static int deviceCode; 
	
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality: The customer(Client) must Choose device from the combo box in addition
	 * to proceed the create local order process once the client chooses the device id 
	 * it will proceed to the next fxml page witch called "CreateOrder.fxml" and he can start the 
	 * order creating process 
	 * 
	 */
	@FXML
	private void ChooseDevice(ActionEvent event) throws IOException{
		deviceCode=pickDevice.getValue();
		MainPageFormController.deviceCode=deviceCode;
		Message message=new Message(MessageType.getLocation,deviceCode);
		ClientUI.chat.accept(message);
		if(MainPageFormController.results[1].equals("1")) {
			locallbl.setText("The Device is in Use please wait.");
			pickDevice.getItems().clear();
			pickDevice.setPromptText("Pick Device");
			LoadComboBoxesValues();
		}else {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/CreateOrder.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Create Order");
			stage.setScene(scene);
			stage.show();
		}
	}
	
	/**
	 * Loads values into the combo box
	 */
	public  void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
		 ObservableList<Integer> ID = FXCollections.observableArrayList(11,12,13,21,22,23,31,32,33);
		 pickDevice.setItems(ID);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality: On back button click it will return to the Main order page where he can 
	 * start over the create order process
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


	/**
	 *Initialize the local order page .
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LoadComboBoxesValues();
		//RunClockTimer();
	}

}
