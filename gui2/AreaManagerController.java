package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * @author Sara Asaad
 *
 */
public class AreaManagerController implements Initializable {
	public static Stage AreaManagerStage;

	@FXML
	private Button btnCreateOrder;

	@FXML
	private Button btnViewMonthlyReport;

	@FXML
	private Button btnViewStockUpdates;

	@FXML
	private Button btnDeterminingThresholdLevel;

	@FXML
	private Button btnManageRegisteringRequests;
	
	@FXML
	private Button btnSendingMessageToOperationWorker;

	@FXML
	private Button btnLogout;
	
	@FXML
	private ImageView femaleAreaManager;
	
	
	@FXML
	private ImageView maleAreaManager;
	@FXML
	private Text txtSarah;
	@FXML
	private Text txtDaniella;
	@FXML
	private Text txtAdham;

	
	public static String arealoc="null";
	private Stage stage;
	private Scene scene;
	private Parent root;

	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * Open the previous Login window by click on back button.
	 */
	@FXML
	void ClickLogout(ActionEvent event) throws IOException {
		Message message=new Message(MessageType.LogOut,LoginController.user);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login form");
		stage.setScene(scene);
		stage.show();
		//Stage stage = (Stage) btnLogout.getScene().getWindow();
	}

	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * Open the next MainPage window by click on Create Order.
	 */
	@FXML
	public void ClickCreateOrder(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Order Main Page");
		primaryStage.setScene(scene);		
		primaryStage.show();

	}
	
	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * Open the next DeterminingThresoldLevel window by click on Set threshold.
	 */
	@FXML
	public void ClickDeterminigThersholdLevel(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/DeterminingThresoldLevel.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Determining a threshold level");
		primaryStage.setScene(scene);		
		primaryStage.show();

	}
	
	

	
	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * Open the next ViewMonthlyReport window by click on Monthly Report.
	 */
	@FXML
	public void ClickViewMonthlyReport(ActionEvent event) throws IOException {
		Message message=new Message(MessageType.GetAreaManagerLocation,LoginController.user[0]);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ViewMonthlyReport.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("View Monthly Report");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnViewMonthlyReport.getScene().getWindow();

	}
	
	/**
	 * @author Sara Asaad
	 * @param event
	 * @throws IOException
	 * Send msg to operation worker to Update stock.
	 */
	@FXML
	public void ClickSendingAMessageToOperationWorker(ActionEvent event) throws IOException {
		Message message= new Message(MessageType.StockEqualsToThresholdLevel,LoginController.user);
		ClientUI.chat.accept(message);
	}
	
	/**
	 * @author Sara Asaad
	 * method to start the window
	 */
	public void start()  {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("/gui/AreaManagerPrimary.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		scene = new Scene(root);
		stage.setTitle("Area Manager");
		stage.setScene(scene);
		stage.show();
	}

	/**
	 * @author Sara Asaad
	 * @param arg0, arg1
	 * method to initialize the window
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(LoginController.user[0].equals("smnorth")) {
			maleAreaManager.visibleProperty().setValue(false);
			txtSarah.visibleProperty().setValue(false);
			txtAdham.visibleProperty().setValue(false);
		}else if(LoginController.user[0].equals("smsouth")) {
			maleAreaManager.visibleProperty().setValue(false);
			txtAdham.visibleProperty().setValue(false);
			txtDaniella.visibleProperty().setValue(false);
		}else {
			femaleAreaManager.visibleProperty().setValue(false);
			txtDaniella.visibleProperty().setValue(false);
			txtSarah.visibleProperty().setValue(false);
		}
	}

}
