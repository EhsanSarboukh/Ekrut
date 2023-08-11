package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ChatClient;
import controllers.ClientUI;
import controllers.OrderStatusController;
import entity.Message;
import entity.MessageType;
import entity.OrderStatus;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * @author adham + daniella
 *
 */
public class UpdateOrderStatusController {
	ObservableList<String> AreaList= FXCollections.observableArrayList("north","south","UAE");
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnUpdate;

	@FXML
	private ComboBox<String> Area;
	
	@FXML
	private TextField txtNum;
	
	@FXML
	private Label errorLabel;
	
	@FXML
	private TableView<OrderStatus> tblOrderStatus;
	@FXML
	private TableColumn<OrderStatus,Integer>col_OrderNumber;
	
	@FXML
	private TableColumn<OrderStatus,String>col_Status;
	
	public static String comboval;
	public static String txtNumOrder;
	
	
	
	
	/**
	 * @author adham + daniella
	 * @param event hapenning when back button clicked
	 * @throws IOException
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException {
		
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("DeliveryMan.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Delivery Man");
	    primaryStage.setScene(scene);
		primaryStage.show();

	}
	/**
	 * @author adham + daniella
	 * @throws Exception
	 */
	@FXML
	public void AreaChoice() throws Exception {
		if(Area.getValue().equals("north")) {
			Area.setValue("north");
			Area.setItems(AreaList);
			comboval = "north";
		}

		else if(Area.getValue().equals("south"))
		{
			Area.setValue("south");
			Area.setItems(AreaList);
			comboval = "south";
		}
	    else
		{
			Area.setValue("UAE");
			Area.setItems(AreaList);
			comboval = "UAE";
		}
		
		errorLabel.setText("");
	    Message msg = new Message(MessageType.GetAllOrderDetails,comboval);
	    ClientUI.chat.accept(msg);
	    fillTable();
	}

	/**
	 * @author adham + daniella
	 */
	private void fillTable() {
		tblOrderStatus.getItems().clear();
		OrderStatusController.getAllDetails();
		tblOrderStatus.setItems(OrderStatusController.listorder);
		
	}
	
	
    /**
     * @author adham + daniella
     * @throws Exception
     */
    @FXML
	private void  ClikUpdateStatus() throws Exception { 
    	errorLabel.setText("");
    	if(txtNum.getText().isEmpty() || Area.getValue().equals("Area")) {
    		errorLabel.setText("You must enter the number of \nthe order or choose the area");	
    	}
    	else {
    		String[] orderupdate= new String[2];
    		txtNumOrder=txtNum.getText();
    		try {
    		Integer numorder= Integer.parseInt(txtNumOrder);
    		orderupdate[0]= comboval;
    		orderupdate[1]=txtNumOrder;
    		Message message = new Message(MessageType.CheckUpdateOrder,orderupdate);
			ClientUI.chat.accept(message);
			if(ChatClient.ordernumber==true) {
				message = new Message(MessageType.UpdateStatus, numorder);
				ClientUI.chat.accept(message);
				message = new Message(MessageType.GetAllOrderDetails,comboval);
			    ClientUI.chat.accept(message); 
			    fillTable();
			    txtNum.setText("");
			}
			else 
					errorLabel.setText("Order ID not existed in the delivery table for this area");
    		} catch (NumberFormatException e) {
    		    System.out.println("Error: the string is not a valid integer");
    		    errorLabel.setText("Error: the string is not a valid integer");
    		}
    	}
    	
    
    }
	/**
	 * @author adham + daniella
	 */
	private void initColumns() {
		col_OrderNumber.setCellValueFactory(new PropertyValueFactory<OrderStatus, Integer>("ordernumber"));	
		col_Status.setCellValueFactory(new PropertyValueFactory<OrderStatus, String>("Status"));
		
	}

	/**
	 * @author adham + daniella
	 */
	public void initialize() 
	{
		initColumns();
		txtNum.setText(""); 
		Area.setValue("Area");
		Area.setItems(AreaList);
		
	}
	/**
	 * @author adham + daniella
	 * @throws IOException
	 */
	public void start() throws IOException {
		
		
	}
	
}
