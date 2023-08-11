package gui;

import java.io.IOException;

import controllers.ClientUI;
import entity.Message;
import entity.MessageType;
import entity.Sale;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * @author Sara Asaad , Mhemad Mdah
 *
 */
public class AddSaleFormController {

	@FXML
	private Button btnBack;

	@FXML
	private Button btnAdd;

	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtArea;
	
	@FXML
	private TextField txtDiscount;
	
	/**
	 * @param event
	 * @throws IOException
	 *  goes back to the page that was open before this page and clears content of the current table shown
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("MarketingManagerForm.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Marketing Manager");
		primaryStage.setScene(scene);		
		primaryStage.show();

	}
	
	/**
	 * @param event
	 * @throws IOException
	 * adds the sale to the system and saves it in the DB
	 */
	@FXML
	public void ClickAdd(ActionEvent event) throws IOException {
		Sale sale = new Sale(null, "Not Active", 0, null);
		sale.setArea(txtArea.getText());
		sale.setProductName(txtName.getText());
		sale.setSale(Double.parseDouble(txtDiscount.getText()));
		Message message = new Message(MessageType.AddSale,sale);
		ClientUI.chat.accept(message);
	}
	
	/**
	 * @throws IOException
	 *  method starts with opening of the page , initializes the text fields
	 */
	public void start() throws IOException {
		txtDiscount.setText(""); 
		txtName.setText("");
		txtArea.setText("");
	}
}
