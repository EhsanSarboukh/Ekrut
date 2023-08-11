package gui;

import java.io.IOException;

import controllers.ClientUI;
import controllers.ReportController;
import entity.Message;
import entity.MessageType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh, Mhemad Mdah, Sabeel Hamood, Adham Asaad, Sara Asaad ,Daniella Kdamany
 *
 */
public class ViewMonthlyReportController {
	
	String msg;
	
	@FXML
	private Button btnBack;

	@FXML
	private Button btnShowReport;

	@FXML
	private ComboBox<String> Type;
	
	@FXML
	private ComboBox<String> Year;
	
	@FXML
	private ComboBox<String> Month;
	
	@FXML
	private ComboBox<String> deviceId;
	
	@FXML
	private ComboBox<String> loca;
	
	//@FXML
	//private Label lblLocation;
	
	@FXML
	private Label lblError;
	
	
	public static String deviceCode;
	public static String month;
	public static String year;
	public static  String location="null";
	public static boolean flagReportExist;
	
	
	@FXML
	private void initialize() {
		if(!(LoginController.user[0].equals("ceo"))) {
			loca.visibleProperty().setValue(false);
		}else {
			Type.visibleProperty().setValue(false);
		}
			
		LoadComboBoxesValues();
		
	}
	
	@FXML
	void ChooseType(ActionEvent event) throws IOException{
		if(LoginController.user[0].equals("ceo") && location!=null) {
			LoadComboBoxCeo();
		}else {
			LoadComboBox();
		}
		
	}


	@FXML
	void ChooseDeviceId(ActionEvent event) throws IOException{
		deviceCode=deviceId.getValue();
	}
	@FXML
	void ChooseMonth(ActionEvent event) throws IOException{
		month=Month.getValue();
	}
	@FXML
	void ChooseYear(ActionEvent event) throws IOException{
		year=Year.getValue();
	}
	@FXML
	void ChooseLocation(ActionEvent event) throws IOException{
		location=loca.getValue();
		Type.visibleProperty().setValue(true);
		
	}
	
	public  void LoadComboBoxesValues() {
		// TODO Auto-generated method stub
		ObservableList<String> TypeList= FXCollections.observableArrayList("Order Report","Stock Report","Customer Report");
		Type.setItems(TypeList);
		ObservableList<String> YearList= FXCollections.observableArrayList("2018","2019","2020","2021","2022","2023");
		Year.setItems(YearList);
		ObservableList<String> MonthList= FXCollections.observableArrayList("1","2","3","4","5","6","7","8","9","10","11","12");
		Month.setItems(MonthList);
		ObservableList<String> location= FXCollections.observableArrayList("north", "south","UAE");
		loca.setItems(location);
	}
	
	public  void LoadComboBox() {
		// TODO Auto-generated method stub
		if(AreaManagerController.arealoc.equals("north") && (Type.getValue().equals("Order Report") || Type.getValue().equals("Stock Report"))) {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("11","12","13");
	    	 deviceId.setItems(deviceCode);
		}else if(AreaManagerController.arealoc.equals("south")&& (Type.getValue().equals("Order Report") || Type.getValue().equals("Stock Report"))) {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("21","22","23");
	    	 deviceId.setItems(deviceCode);
		}else if(AreaManagerController.arealoc.equals("UAE") && (Type.getValue().equals("Order Report") || Type.getValue().equals("Stock Report"))){
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("31","32","33");
	    	 deviceId.setItems(deviceCode);
		}else {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList();
	    	 deviceId.setItems(deviceCode);
		}
	}
	
	
	private void LoadComboBoxCeo() {
		// TODO Auto-generated method stub
		if(loca.getValue().equals("north") && (Type.getValue().equals("Order Report") || Type.getValue().equals("Stock Report"))) {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("11","12","13");
	    	 deviceId.setItems(deviceCode);
		}else if(loca.getValue().equals("south")&& (Type.getValue().equals("Order Report") || Type.getValue().equals("Stock Report"))) {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("21","22","23");
	    	 deviceId.setItems(deviceCode);
		}else if(loca.getValue().equals("UAE") && (Type.getValue().equals("Order Report") || Type.getValue().equals("Stock Report"))){
			 ObservableList<String> deviceCode = FXCollections.observableArrayList("31","32","33");
	    	 deviceId.setItems(deviceCode);
		}else {
			 ObservableList<String> deviceCode = FXCollections.observableArrayList();
	    	 deviceId.setItems(deviceCode);
		}
	}
	
	
	@FXML
	public void ClickShowReport(ActionEvent event) throws IOException {
		if(Year.getValue()==null || Month.getValue()==null || Type.getValue()==null) {
			lblError.setText("PLease Choose from the Combo Box!");
		}else{
			String[] stockReport = new String[4];
			stockReport[0]=deviceCode;
			stockReport[1]=month;
			stockReport[2]=year;
			stockReport[3]=Type.getValue();
			if(Type.getValue().equals("Customer Report")) {
				Message message = new Message(MessageType.getCustomerReport,stockReport);
				ClientUI.chat.accept(message);
				message = new Message(MessageType.getCustomerReportLabels,null);
				ClientUI.chat.accept(message);
				if(ReportController.report.size() < 1) {
					lblError.setText("The Report not existed!");
				}
				else {
					((Node)event.getSource()).getScene().getWindow().hide();
					Parent root = FXMLLoader.load(getClass().getResource("monthlyCustomerReport.fxml"));
					Stage primaryStage = new Stage();
					Scene scene = new Scene(root);
					primaryStage.setTitle("Show Monthly Customer Report");
					primaryStage.setScene(scene);		
					primaryStage.show();
				}
			}else {
				Message message=new Message(MessageType.CheckReportExist,stockReport);
				ClientUI.chat.accept(message);
				if(flagReportExist==false)
					lblError.setText("The Report not existed!");
				else
				{
					if (Type.getValue().equals("Order Report"))
					{
						((Node)event.getSource()).getScene().getWindow().hide();
						Parent root = FXMLLoader.load(getClass().getResource("ShowOrderMonthlyReport.fxml"));
						Stage primaryStage = new Stage();
						Scene scene = new Scene(root);
						primaryStage.setTitle("Show Monthly Order Report");
						primaryStage.setScene(scene);		
						primaryStage.show();
					}
					else if (Type.getValue().equals("Stock Report"))
					{
						((Node)event.getSource()).getScene().getWindow().hide();
						Parent root = FXMLLoader.load(getClass().getResource("ShowStockReport.fxml"));
						Stage primaryStage = new Stage();
						Scene scene = new Scene(root);
						primaryStage.setTitle("Show Monthly Stock Report");
						primaryStage.setScene(scene);
						primaryStage.show();
					}
				}
				
			}
		}
		
		

	}
	
	@FXML
	public void ClickBack(ActionEvent event) throws IOException {
		if(LoginController.user[0].equals("ceo")) {
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("ceoMainPage.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setTitle("Ceo Main Form");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}else {
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("AreaManagerPrimary.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root);
			primaryStage.setTitle("AreaManager");
			primaryStage.setScene(scene);		
			primaryStage.show();
		}


	}
	public void start() throws IOException {
		 
	}


}
