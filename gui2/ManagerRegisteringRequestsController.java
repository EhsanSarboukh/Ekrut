package gui;

import java.io.IOException;

import controllers.ClientUI;
import controllers.SubscriberController;
import entity.Customer;
import entity.Subscriber;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class ManagerRegisteringRequestsController {
	
	@FXML
	private TableView<Customer> tblCustomer;
	
	@FXML
	private TableColumn<Customer,String>col_Username;

	@FXML
	private TableColumn<Customer,String>col_ID;
	
	@FXML
	private TableColumn<Customer,String>col_CustomerStatus;
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnUpdate;
	
	@FXML
	private TextField txtCustomerID;
	
	/*
	@FXML
	void ClickUpdateBtn(ActionEvent event) {
			Subscriber s = new Subscriber(null, null, null, null, null, null, null);
			//list = (tblTests.getItems());

			// get chosen test details
			for (Subscriber sub : SubscriberController.list) {
				if (sub.getId().equals(txtCustomerID.getText())) {
					s.setFName(sub.getFName());
					s.setLName(sub.getLName());
					s.setId(sub.getId());
					s.setEmail(sub.getEmail());
					s.setPhNum(sub.getPhNum());
					s.setCreditCard(txtCreditCardNumber.getText());
					s.setSubNum(txtSubscriberNumber.getText());
				}
				// we can add else and print error window

			}
			SubscriberController.update(s);

			fillTable();
		
	}
	*/




	public void initColumns() {
		// System.out.println("is null col?" + col_id.getId() + " " + col_id);
		col_Username.setCellValueFactory(new PropertyValueFactory<Customer, String>("username"));
		col_ID.setCellValueFactory(new PropertyValueFactory<Customer, String>("id"));
		col_CustomerStatus.setCellValueFactory(new PropertyValueFactory<Customer, String>("customer status"));
	}
	
	/*

	public void fillTable() {

		tblCustomer.getItems().clear();
		//updates the observable list of subscriber in SubscriberController
		SubscriberController.getAllDetails();
		tblCustomer.setItems(SubscriberController.list);

	}
	*/

	
	@FXML
	public void ClickBack(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("AreaManagerPrimary.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Area Manager");
		primaryStage.setScene(scene);		
		primaryStage.show();

	}
	
	
	public void start() throws IOException {
		
		initColumns();
		txtCustomerID.setText(""); 
	}

	/*
	@FXML
	public void ClickUpdate(ActionEvent event) throws IOException {

	}
	*/
	
}
