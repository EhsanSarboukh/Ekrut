package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.ProductController;
import controllers.StockController;
import entity.AreaStock;
import entity.Message;
import entity.MessageType;
import entity.Product;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * @author daniella
 * The messageFromAreaManagerController class is a JavaFX controller class that provides functionality for a user interface that allows
 *  an Area Manager to view stock information for a specific location or device.
 *
 */
public class messageFromAreaManagerController implements Initializable {
	@FXML
	private Button BackBtn;
	@FXML 
	private TextField txtID;
	@FXML
	private Button ViewStockBtn;
	
	@FXML 
	private ComboBox <String>Area;
	
	@FXML
	private TableView<AreaStock> tblArea;
	
	@FXML
	private TableColumn<AreaStock, Integer> col_deviceId;
	
	@FXML
	private TableColumn<AreaStock, Integer> col_bamba;
	
	@FXML
	private TableColumn<AreaStock, Integer> col_klik;
	
	@FXML
	private TableColumn<AreaStock, Integer> col_cocaCola;
	
	@FXML
	private TableColumn<AreaStock, Integer> col_sevenUp;
	
	@FXML
	private TableColumn<AreaStock, Integer> col_katkatat;
	@FXML
	private TableColumn<AreaStock, String> col_location;
	
	@FXML
	private TableColumn<AreaStock, String> col_updateStock;
	
	@FXML
	private Label lblIdProductMessage;
	
	private Stage stage;
	private Scene scene;
	private Parent root;
	public static String deviceId;
	public static String location;
	
	/**
	 * @param event
	 * @throws IOException
	 * ChooseArea() method which is called when the user selects a location from the combo box
	 */
	@FXML
	void ChooseArea(ActionEvent event) throws IOException{
		location=Area.getValue();
		init_column();
		fillTable();
	}
	/**
	 * Loads the locations available for viewing the stock.
	 */
	public  void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
		 ObservableList<String> Location = FXCollections.observableArrayList("north","south","UAE");
    	 Area.setItems(Location);
	}
	/**
	 * @param event
	 * @throws IOException
	 * Returns the user to the operation worker menu.
	 */
	@FXML
	public void clickBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/operationWorker.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Operation Worker Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) BackBtn.getScene().getWindow();
	}
	/**
	 * @param event
	 * @throws IOException
	 * Allows the user to view the stock in a specific location or a specific device, based on the input provided.
	 */
	@FXML
	public void clickViewStock(ActionEvent event) throws IOException {
		
		boolean flag=false;
		for(AreaStock as:StockController.subArr) {
			if(as.getDeviceId()== Integer.parseInt(txtID.getText())) {
				if(as.getUpdateStock().equals("Update Stock"))
					flag=true;
			}
			
		}
		if(txtID.getText().isEmpty()==true ) {
			lblIdProductMessage.setText("Please Insert DeviceId");
		}
		else{
			deviceId=txtID.getText();
			Message message=new Message(MessageType.CheckIdDevice,deviceId);
			ClientUI.chat.accept(message);
			if(StockController.flag==true && flag==true) {
				tblArea.getItems().clear();
				Parent root = FXMLLoader.load(getClass().getResource("/gui/viewStock.fxml"));
				stage = (Stage)((Node) event.getSource()).getScene().getWindow();
				scene = new Scene(root);
				stage.setTitle("View Stock Form");
				stage.setScene(scene);
				stage.show();
				Stage stage = (Stage) ViewStockBtn.getScene().getWindow();
			}else {
				if(flag==false) {
					lblIdProductMessage.setText("No Need to Update Stock!");
				}else
					lblIdProductMessage.setText("No such device!");
			}
		}
	}
	/**
	 * @param arg0
	 * @param arg1
	 * Initializes the GUI elements and sets their properties.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		LoadComboBoxesValues();

	}
	/**
	 * Fills the table with the stock data retrieved from the server.
	 */
	private void fillTable() {
		// TODO Auto-generated method stub
		tblArea.getItems().clear();
		//updates the observable list of subscriber in SubscriberController
		StockController.getStockByLocation(location);
		tblArea.setItems(StockController.list);
		
	}
	/**
	 * Initializes the table columns and sets their cell value factories.
      The class contains the following FXML elements:
	 */
	private void init_column() {
		col_deviceId.setCellValueFactory(new PropertyValueFactory<AreaStock,Integer>("deviceId"));
		col_bamba.setCellValueFactory(new PropertyValueFactory<AreaStock,Integer>("bamba"));
		col_klik.setCellValueFactory(new PropertyValueFactory<AreaStock,Integer>("klik"));
		col_cocaCola.setCellValueFactory(new PropertyValueFactory<AreaStock,Integer>("cocaCola"));
		col_sevenUp.setCellValueFactory(new PropertyValueFactory<AreaStock,Integer>("sevenUp"));
		col_katkatat.setCellValueFactory(new PropertyValueFactory<AreaStock,Integer>("katkatat"));
		col_location.setCellValueFactory(new PropertyValueFactory<AreaStock,String>("location"));
		col_updateStock.setCellValueFactory(new PropertyValueFactory<AreaStock,String>("updateStock"));
	}
}
