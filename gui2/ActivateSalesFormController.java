package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.SaleController;
import entity.Message;
import entity.MessageType;
import entity.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * @author Sara Asaad , Mhemad Mdah
 *
 */
public class ActivateSalesFormController implements Initializable{
	Stage stage;
	Scene scene;
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnActivate;
	
	
	@FXML
	private TableView<Sale> tblSale;
	
	@FXML
	private TableColumn<Sale, String> col_ProductName;
	
	@FXML
	private TableColumn<Sale, String> col_Status;

	@FXML
	private TableColumn<Sale, Double> col_Sale;
	
	@FXML
	private TableColumn<Sale, String> col_Area;
	
	/**
	 * @param event
	 * @throws IOException
	 * goes back to the page that was open before this page and clears content of the current table shown
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		tblSale.getItems().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MarketingEmployee.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Marketing Employee Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnBack.getScene().getWindow();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * activates the sale based on request of the marketing manager
	 */
	@FXML
	public void ClickActivate(ActionEvent event) throws IOException{
		Message message = new Message(MessageType.UpdateStatusSale ,null);
		ClientUI.chat.accept(message);
		fillTable();
	}
	
	/**
	 *  initializes the columns of the table 
	 */
	private void initColumns(){
		// TODO Auto-generated method stub
		col_ProductName.setCellValueFactory(new PropertyValueFactory<Sale,String>("productName"));
		col_Status.setCellValueFactory(new PropertyValueFactory<Sale,String>("status"));
		col_Sale.setCellValueFactory(new PropertyValueFactory<Sale,Double>("sale"));
		col_Area.setCellValueFactory(new PropertyValueFactory<Sale,String>("area"));
	}
	/**
	 *  method initializes the form 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initColumns();
		fillTable();
	}
	
	/**
	 *  fills the table with the requested details
	 */
	private void fillTable() {
		// TODO Auto-generated method stub
		tblSale.getItems().clear();
		SaleController.getSalesToActivate();
		tblSale.setItems(SaleController.listActivateSale);
	}
}
