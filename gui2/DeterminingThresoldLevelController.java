package gui;

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DeterminingThresoldLevelController implements Initializable{
	public static int[] user;

	ObservableList<String> DeviceIDList= FXCollections.observableArrayList("1","2","3");
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnUpdate;

	@FXML
	private ComboBox<Integer> DeviceID;
	
	@FXML
	private TextField txtThresoldLevel;
	
	@FXML
	private Label ThresholdLevelDataLabel;
	
	private int deviceCode;
	
	/**
	 * @author Sara Asaad
	 * @param location , resources
	 * initialize calls LoadComboBoxesValues methods to loads the comboboxes values.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		LoadComboBoxesValues();		
	}
	
	/**
	 * @author Sara Asaad
	 * a label if the customer does not fill all the fields. 
	 */
	public void SetLabel() {
		ThresholdLevelDataLabel.setText("You have to fill all the fields!");
	}
	
	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * this method is for updating the Threshold Level.
	 */
	@FXML
	public void ClickUpdate(ActionEvent event) throws IOException {
		user=new int[2];
		if(DeviceID.getValue() == null || txtThresoldLevel.getText().isEmpty()) {
			ThresholdLevelDataLabel.setText("You have to fill all the fields!");
		}
		else {
			user[0]=deviceCode;
			user[1]=Integer.parseInt(txtThresoldLevel.getText());
			Message message = new Message(MessageType.CheckThresholdLevel,user);
			ClientUI.chat.accept(message);
		}
	}
	
	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * Open the previous window by click on back button.
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AreaManagerPrimary.fxml"));
		if(AreaManagerController.AreaManagerStage==null){
			AreaManagerController.AreaManagerStage = new Stage();
		}
		Scene scene = new Scene(root);
		AreaManagerController.AreaManagerStage.setTitle("Area Manager");
		AreaManagerController.AreaManagerStage.setScene(scene);		
		AreaManagerController.AreaManagerStage.show();
		
	}
	/**
	 * @author Sara Asaad
	 * @return the label in this window.
	 */
	public Label getThresoldLevelDataLabel() {
		return ThresholdLevelDataLabel;
	}
	
	/**
	 * @author Sara Asaad
	 * @param thresoldLevelDataLabel
	 * set the label in this window.
	 */
	public void setThresoldLevelDataLabel(Label thresoldLevelDataLabel) {
		ThresholdLevelDataLabel = thresoldLevelDataLabel;
	}
	
	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * to save the value of the device id.
	 */
	@FXML
	void ChooseDeviceId(ActionEvent event) throws IOException{
		deviceCode=DeviceID.getValue();
	}
	
	/**
	 * @author Sara Asaad
	 * loading the values of the comboboxes.
	 */
	public  void LoadComboBoxesValues() {
		if(LoginController.user[0].equals("smnorth")) {
			ObservableList<Integer> ID = FXCollections.observableArrayList(11,12,13);
			 DeviceID.setItems(ID);
		}else if(LoginController.user[0].equals("smsouth")) {
			 ObservableList<Integer> ID = FXCollections.observableArrayList(21,22,23);
			 DeviceID.setItems(ID);
		}else {
			ObservableList<Integer> ID = FXCollections.observableArrayList(31,32,33);
			 DeviceID.setItems(ID);
		}
		 
	}
	
	
	/**
	 * @author Sara Asaad
	 * @throws IOException
	 * method to start the window
	 */
	public void start() throws IOException {
		
	}
	
}
