package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.ProductController;
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
 * @author Ehsan Sarboukh , Mhemad Mdah
 *
 */
public class FinishCreatingOrderController implements Initializable {
	
	@FXML
	private Button BackBtn;
	
	@FXML
	private Label msglbl;
	
	@FXML
	private ImageView EKImg;
	
	@FXML
	private ImageView OLImg;
	
	Stage stage;
	Scene scene;

	/**
	 * @return initializes the page with required data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(ClientUI.machineCode.equals("1")) {
			EKImg.visibleProperty().setValue(false);
			if(ViewOrderFormController.flagCancelOrder==true) {
				msglbl.setText("Your Order Has Been Canceled!");
			}else if(ViewOrderFormController.flagDroneOrder==true) {
				msglbl.setText("Delivery waiting for approve, sending to "+RemoteOrderViaDroneFormController.Address);
				RemoteOrderViaDroneFormController.Address=null;
			}else {
				msglbl.setText("The Order has been saved and the ID is: "+String.valueOf(ProductController.orderId));
			}
			
		}else {
			OLImg.visibleProperty().setValue(false);
			if(ViewOrderFormController.flagCancelOrder==true) {
				msglbl.setText("Your Order Has Been Canceled!");
			}else {
				msglbl.setText("Thanks for Purchasing :)");
			}
		}
		
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 * @return goes back to the page that was open before this page
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Order Main Page");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) BackBtn.getScene().getWindow();
		
	}
	
	
	

}
