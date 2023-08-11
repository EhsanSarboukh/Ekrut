package gui;

import java.io.IOException;
import java.net.URL;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import controllers.ChatClient;
import controllers.ClientUI;
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
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh and Mhemad Mdah
 * Class purpose to let the Client choose in witch way he wants to order 
 * Remote Order is only visible for OL users
 * Local order and pick up order is only visible to EK users
 *
 */
public class MainPageFormController implements Initializable {
	public static Stage MainStage;
	
	@FXML
	private Button btnPickUpOrder;
	
	@FXML
	private Button btnLocalOrder;
	
	@FXML
	private Button btnRemoteOrderViaDrone;
	
	@FXML
	private Button btnRemoteOrderPickUpOption;
	
	@FXML
	private Button btnSubscribe;
	
	
	@FXML
	private Button btnBack;
	
	@FXML
	private ImageView imgEK;
	@FXML
	private ImageView imgOL;
	
	
	@FXML
	private Label lblSubscribe;
	public static int deviceCode;
	private LocalDateTime startDate;
	public static String sDate;
	public static String[] results=new String[2];
	private static int count=0;
	private Stage stage;
	private Scene scene;
	private String fxmlLoc,title;


	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality : OnClick open and proceed to PickUpOrder.fxml page
	 */
	@FXML
	void ClickBtnPickUpOrder(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/gui/PickUpOrder.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Pick Your Order");
		stage.setScene(scene);
		stage.show();
	}
	
	/**
	 * @param event
	 * @throws IOException
	 * Functionality : OnClick open and proceed to LocalOrderWindow.fxml page and 
	 * saves the actual click date(Start date of creating a new order) .
	 */
	@FXML
	void ClickBtnLocalOrder(ActionEvent event) throws IOException{
		startDate = LocalDateTime.now();
	    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
	    sDate = startDate.format(myFormatObj);
	    //system.out.println(time.valueOf(sDate));
		getOrderId();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/LocalOrderWindow.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Local Order");
		stage.setScene(scene);
		stage.show();
	
	}
	
	
	/**
	 * Send a message to the server in order to receive the order Id for the current order 
	 */
	private void getOrderId() {
		// TODO Auto-generated method stub
		Message message=new Message(MessageType.GetOrderId,null);
		ClientUI.chat.accept(message);
	}

	/**
	 * @param event
	 * @throws IOException
	 * Functionality : OnClick open and proceed to RemoteOrderViaDrone.fxml page and 
	 * saves the actual click date(Start date of creating a new order) .
	 */
	@FXML
	void ClickRemoteOrderViaDrone(ActionEvent event) throws IOException{
			startDate = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
		    sDate = startDate.format(myFormatObj);
			getOrderId();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/RemoteOrderViaDrone.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Remote Order Via Drone");
			stage.setScene(scene);
			stage.show();
			Stage stage = (Stage) btnRemoteOrderViaDrone.getScene().getWindow();
	
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  Functionality : OnClick open and proceed to RemoteOrderPickUpOption.fxml page and 
	 * saves the actual click date(Start date of creating a new order) .
	 */
	@FXML
	void ClickRemoteOrderPickupOption(ActionEvent event) throws IOException{
			startDate = LocalDateTime.now();
		    DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss.SSS");
		    sDate = startDate.format(myFormatObj);
			getOrderId();
			Parent root = FXMLLoader.load(getClass().getResource("/gui/RemoteOrderPickupOption.fxml"));
			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setTitle("Remote Order Pickup Option");
			stage.setScene(scene);
			stage.show();
			Stage stage = (Stage) btnRemoteOrderPickUpOption.getScene().getWindow();
	
	}

	/**
	 * @param event
	 * @throws IOException
	 * Functionality : OnClick method once the button back is activated if the client is in
	 * EK system and will open the customer services page , but if the client is using the OL 
	 * system it will open the exact role page of the user (client) that are using the program.
	 */
	@FXML
	void ClickBack(ActionEvent event) throws IOException{
		if(!(ClientUI.machineCode.equals("1"))) {
			fxmlLoc="/gui/ClientMainForm.fxml";
			title="Customer Main Form";
		}else {
			switch(ChatClient.role) {
			case "ceo":
				fxmlLoc="/gui/ceoMainPage.fxml";
				title="Ceo Main Form";
				break;
			case "customer":
				fxmlLoc="/gui/ClientMainForm.fxml";
				title="Customer Main Page";
				break;
			case "delivery_man":
				fxmlLoc="/gui/DeliveryMan.fxml";
				title="Delivery Man";
				break;
			case "marketing_manager":
				fxmlLoc="/gui/MarketingManagerForm.fxml";
				title="Marketing Manager";
				//Stage stage2 = (Stage) loginButton.getScene().getWindow();
				break;
			case "marketing_employee":
				fxmlLoc="/gui/MarketingEmployee.fxml";
				title="Marketing Employee";
				//Stage stage3 = (Stage) loginButton.getScene().getWindow();
				break;
			case "stock_employee":
				fxmlLoc="/gui/operationWorker.fxml";
				title="Stock Employee";
				//Stage stage4 = (Stage) loginButton.getScene().getWindow();
				break;
			case "area_manager":
				fxmlLoc="/gui/AreaManagerPrimary.fxml";
				title="Area Manager";
				//Stage stage1 = (Stage) loginButton.getScene().getWindow();
				break;
				default:
					ClientUI.display("bad choice!");
		}
		}
		
	if(fxmlLoc!=null && title!=null) {
		Parent root = FXMLLoader.load(getClass().getResource(fxmlLoc));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle(title);
		stage.setScene(scene);
		stage.show();
	}
			
	}



	/**
	 *Initialize the Main Page with the proper buttons for each system 
	 *once we are in EK the options that are connected to remote ordering wont appear
	 *and the as the OL 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(ClientUI.machineCode.equals("1")) {
			btnLocalOrder.visibleProperty().setValue(false);
			btnPickUpOrder.visibleProperty().setValue(false);
			imgEK.visibleProperty().setValue(false);
		}else {
			btnRemoteOrderPickUpOption.visibleProperty().setValue(false);
			btnRemoteOrderViaDrone.visibleProperty().setValue(false);
			imgOL.visibleProperty().setValue(false);
		}

	}
	
	
	
	
}
