package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.ReportController;
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
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * @author Ehsan Sarboukh , Sabeel Hamood
 *
 */
public class ShowMonthlyOrderReportController implements Initializable{
	@FXML
	private Button backBtn;
	
	@FXML
    private TextArea ReportTextArea;
	@FXML
	private Label lblCancel;
	@FXML
	private Label lblApproved;
	@FXML
	private Label lblProfit;
	@FXML
	private Text txtSouth;
	@FXML
	private Text txtUae;
	@FXML
	private Text txtnorth;
	@FXML
	private Label lblWaitingForDelivery;
	
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ViewMonthlyReport.fxml"));
		Stage stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("View Monthly Report");
		stage.setScene(scene);
		stage.show();
		stage = (Stage) backBtn.getScene().getWindow();
		
	}

	/**
	 *Initialize the Combo Boxes 
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

		if(AreaManagerController.arealoc.equals("north") || ViewMonthlyReportController.location.equals("north")) {
			txtSouth.visibleProperty().setValue(false);
			txtUae.visibleProperty().setValue(false);

		}else if(AreaManagerController.arealoc.equals("south") || ViewMonthlyReportController.location.equals("south")) {
			txtnorth.visibleProperty().setValue(false);
			txtUae.visibleProperty().setValue(false);
		}
		else {
			txtnorth.visibleProperty().setValue(false);
			txtSouth.visibleProperty().setValue(false);

		}
		ReportTextArea.clear();
		setReport();
		
	}

	/**
	 * Load Data into the text area and prepare the report page screen
	 */
	private void setReport() {
		// TODO Auto-generated method stub
		String[] data=new String[3];
		data[0]=ViewMonthlyReportController.deviceCode;
		data[1]=ViewMonthlyReportController.month;
		data[2]=ViewMonthlyReportController.year;
		Message message=new Message(MessageType.FillOrderReport,data);
		ClientUI.chat.accept(message);
		if(ReportController.orderDetails.equals("")) {
			ReportTextArea.setText("There was no Orders Done in this device!");
		}else {
			String temp = ReportController.orderDetails;
			temp=temp.replace(")", "\n\n");
			temp=temp.replace(",", "\n ");
			temp=temp.replace("Data","______________________________________\n");
			ReportTextArea.appendText(temp);
			
		}
		message=new Message(MessageType.FillOrderStatus,data);
		ClientUI.chat.accept(message);
		lblApproved.setText(String.valueOf(ReportController.orderStatus[0]));
		lblCancel.setText(String.valueOf(ReportController.orderStatus[1]));
		lblWaitingForDelivery.setText(String.valueOf(ReportController.orderStatus[2]));
		message=new Message(MessageType.FillProfit,data);
		ClientUI.chat.accept(message);
		lblProfit.setText(String.valueOf((int)(ReportController.profit)));
		
	}
}
