package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.StockController;
import entity.DeviceStock;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
/**
 * @author Daniella
 * class that displays the current stock of a device in a table view.
  It also allows the user to update the stock of a specific product for that device.
 */
public class viewStockController implements Initializable{
	@FXML
	private Button BackBtn;
	@FXML 
	private TextField txtProductName;
	
	@FXML
	private Button updateBtn;
	@FXML 
	private TextField txtAmount;
	
	@FXML
	private TableView<DeviceStock> tblStockProduct;
	
	@FXML
	private TableColumn<DeviceStock, Integer> col_device;
	
	@FXML
	private TableColumn<DeviceStock, Integer> col_bamba;
	
	@FXML
	private TableColumn<DeviceStock, Integer> col_klik;
	
	@FXML
	private TableColumn<DeviceStock, Integer> col_cocaCola;
	
	@FXML
	private TableColumn<DeviceStock, Integer> col_sevenUp;
	
	@FXML
	private TableColumn<DeviceStock, Integer> col_katkatat;
	
	@FXML
	private Label lblProductNotFound;
	private Stage stage;
	private Scene scene;
	private Parent root;
	/**
	 * @param event
	 * @throws IOException
	 * is a method that is called when the update button is clicked.
       It updates the stock of a specific product for the device whose stock is being displayed.
	 */
	@FXML
	public void ClickUpdateBtn(ActionEvent event)throws IOException{
		String[] update=new String[3];
		update[0]=messageFromAreaManagerController.deviceId;
		System.out.println(update[0]);
		String product=txtProductName.getText();
		if((txtAmount.getText().isEmpty()==true) || (txtProductName.getText().isEmpty()==true) ) {
			lblProductNotFound.setText("Please Insert amount & name");
		}else if(product.equals("Bamba") || txtProductName.getText().equals("Klik") || 
				txtProductName.getText().equals("CocaCola") || txtProductName.getText().equals("SevenUp") ||
				txtProductName.getText().equals("Katkatat")){
			update[1]=product;
			update[2]=txtAmount.getText();
			System.out.println(update[1]+" "+update[2]);
			Message message=new Message(MessageType.UpdateStockByDeviceId,update);
			ClientUI.chat.accept(message);
			fillTable();
		}
	}
	/**
	 * @param event
	 * @throws IOException
	 * this method  is called when the back button is clicked.
	 * Returns the user to the messageFromAreaManager.

	 */
	@FXML	
	public void ClickBtn(ActionEvent event) throws IOException{
			tblStockProduct.getItems().clear();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/messageFromAreaManager.fxml"));//we have to add this file
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Message From Area Manager");
			stage.setScene(scene);
			stage.show();
			Stage stage = (Stage) BackBtn.getScene().getWindow();
		}
	/**
	 * @param arg0 the location used to resolve relative paths for the root object,
	 * @param arg1    the resources used to localize the root object, or null if the
	 * This method is automatically called
       after the fxml file has been loaded.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		init_column();
		fillTable();
	}
	/**
	 * Fills the table with the stock data retrieved from the server.
	 */
	private void fillTable() {
		// TODO Auto-generated method stub
		tblStockProduct.getItems().clear();
		StockController.getStockByDeviceId(messageFromAreaManagerController.deviceId);
		tblStockProduct.setItems(StockController.deviceList);
	}
	/**
	 * Initializes the table columns and sets their cell value factories.
      The class contains the following FXML elements:
	 */
	private void init_column() {
		col_bamba.setCellValueFactory(new PropertyValueFactory<DeviceStock,Integer>("bamba"));
		col_klik.setCellValueFactory(new PropertyValueFactory<DeviceStock,Integer>("klik"));
		col_cocaCola.setCellValueFactory(new PropertyValueFactory<DeviceStock,Integer>("cocaCola"));
		col_sevenUp.setCellValueFactory(new PropertyValueFactory<DeviceStock,Integer>("sevenUp"));
		col_katkatat.setCellValueFactory(new PropertyValueFactory<DeviceStock,Integer>("katkatat"));
	}
}
