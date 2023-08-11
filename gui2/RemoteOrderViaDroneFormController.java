package gui;


import java.io.IOException;
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

/**
 * @author Ehsan Sarboukh , Mhemad Mdah
 *
 */
public class RemoteOrderViaDroneFormController implements Initializable{
	public static Stage droneStage;
	
	@FXML
	private Button btnMakeOrder;
	
	@FXML
	private TextField txtAddress;
	
	@FXML
	private Button backBtn;
	
	@FXML
	private ComboBox<String> Area;
	
	@FXML
	private ComboBox<String> deviceId;
	
	@FXML
	private Label lblChoose;
	
	Stage stage;
	Scene scene;
	public static String Address=null;
	private String loc;
	private String deviceCode;
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves area choosed by user
	 */
	@FXML
	void ChooseArea(ActionEvent event) throws IOException{
		loc=Area.getValue();
		MainPageFormController.results[0]=loc;
		LoadComboBox();
	}
	
	/**
	 *  puts values in the combobox
	 */
	public  void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
		 ObservableList<String> Loca = FXCollections.observableArrayList("north","south","UAE");
		 Area.setItems(Loca);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves device choosed by user
	 */
	@FXML
	void ChooseDevice(ActionEvent event) throws IOException{
		deviceCode=deviceId.getValue();
		MainPageFormController.deviceCode=Integer.parseInt(deviceCode);
	}
	
	/**
	 *  loads comboboxes with required data
	 */
	public  void LoadComboBox() {
		// TODO Auto-generated method stub
		if(loc.equals("north")) {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("11","12","13");
	    	 deviceId.setItems(deviceCode);
		}else if(loc.equals("south")) {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("21","22","23");
	    	 deviceId.setItems(deviceCode);
		}else {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("31","32","33");
	    	 deviceId.setItems(deviceCode);
		}
		
		

	}
	/**
	 * @param event
	 * @throws IOException
	 *  sends user to the create order page
	 */
	@FXML
	public void ClickMakeOrder(ActionEvent event) throws IOException{
		Message message=new Message(MessageType.GetIsInUse,MainPageFormController.deviceCode);
		ClientUI.chat.accept(message);
		if(MainPageFormController.results[1].equals("1")) {
			lblChoose.setText("The Device is in Use please wait.");
			deviceId.getItems().clear();
			deviceId.setPromptText("Pick Device");
			LoadComboBoxesValues();
		}else {
			Address=txtAddress.getText();
			if(loc==null || deviceCode==null || (txtAddress.getText().isEmpty()==true)) {
				lblChoose.setText("Please choose Area,device Id and insert your Address");
			}else {
				Parent root = FXMLLoader.load(getClass().getResource("/gui/CreateOrder.fxml"));
				stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setTitle("Create Order");
				stage.setScene(scene);
				stage.show();
				Stage stage = (Stage) backBtn.getScene().getWindow();
			}
			
		}
		
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  sends user to the page that was open before this page
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
	 *  initializes the page with values in comboboxes
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LoadComboBoxesValues();
	}
}
