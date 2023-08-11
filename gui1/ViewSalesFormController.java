package gui;

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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.SaleController;
import entity.Message;
import entity.MessageType;
import entity.Sale;

/**
 * @author Sara Asaad , Mhemad Mdah
 *
 */
public class ViewSalesFormController implements Initializable{
		Stage stage;
		Scene scene;
		@FXML
		private Button btnBack;
		
		@FXML
		private Button btnUpdate;
		
		@FXML
		private Button btnDeActivate;
		
		@FXML
		private TextField txtName;
		
		@FXML
		private TextField txtArea;
		
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
		 *  goes back to the page that was before this page
		 */
		@FXML
		public void ClickBack(ActionEvent event) throws IOException{
			tblSale.getItems().clear();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/MarketingManagerForm.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Marketing Manager Form");
			stage.setScene(scene);
			stage.show();
			Stage stage = (Stage) btnBack.getScene().getWindow();
		}
		
		/**
		 * @param event
		 * @throws IOException
		 *  updates status of sale to activate in DB
		 */
		@FXML
		public void ClickUpdate(ActionEvent event) throws IOException{
			boolean flag = true;
			String[] sale = new String[2];
			sale[0] = txtName.getText();
			sale[1] = txtArea.getText();
			for(Sale s : SaleController.saleArr) {
				if((s.getProductName().equals(sale[0])) && (s.getArea().equals(sale[1]))){
					if(s.getStatus().equals("activated")) {
						flag = false;
					}
				}
			}
			if(flag == true) {
				Message message = new Message(MessageType.UpdateSale ,sale);
				ClientUI.chat.accept(message);
				fillTable();
			}
		}
		
		/**
		 * @param event
		 * @throws IOException
		 *  updates status of sale to not active
		 */
		@FXML
		public void ClickDeActivate(ActionEvent event) throws IOException{
			String[] sale = new String[2];
			sale[0] = txtName.getText();
			sale[1] = txtArea.getText();
			Message message = new Message(MessageType.DeActivateSale ,sale);
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
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
			initColumns();
			fillTable();
		}
		
		/**
		 *  fills the table with required data
		 */
		private void fillTable() {
			// TODO Auto-generated method stub
			tblSale.getItems().clear();
			SaleController.getAllSales();
			tblSale.setItems(SaleController.listSale);
		}
		
}
