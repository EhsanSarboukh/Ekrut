package gui;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ClientUI;
import controllers.ProductController;
import entity.ActiveSale;
import entity.Message;
import entity.MessageType;
import entity.Order;
import entity.Product;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * @author Ehsan Sarboukh , Mhemad Mdah
 *
 */
public class ViewOrderFormController implements Initializable{
	public static Stage viewOrderStage;
	
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtAmount;
	
	@FXML
	private TableView<ActiveSale> tblOrder;
	
	@FXML
	private TableColumn<ActiveSale, Integer> col_ID;
	
	@FXML
	private TableColumn<ActiveSale, String> col_Name;
	
	@FXML
	private TableColumn<ActiveSale, Double> col_Price;
	
	@FXML
	private TableColumn<ActiveSale, Integer> col_Amount;
	
	@FXML
	private Button btnCancelOrder;
	
	@FXML
	private Button btnCancelProduct; 
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnPay;
	
	@FXML
	private Label lblTotalPrice;
	
	@FXML
	private Label PayEmptyOrderlbl;
	
	@FXML
	private Label CancelEmptyOrderlbl;
	
	
	@FXML
	private Label warninglbl;
	@FXML
	private ImageView imgOL;
	@FXML
	private ImageView imgEK;
	
	public static boolean flagPickUpOrder=false;
	public static boolean flagDroneOrder=false;
	public static boolean flagCancelOrder=false;
	
	
	Stage stage;
	Scene scene;
	

	
	/**
	 * @param event
	 * @throws IOException
	 *  goes back to the page that was before this page
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		tblOrder.getItems().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/CreateOrder.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Create Order");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnBack.getScene().getWindow();
		
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  cancels the order of the user
	 * removes the order from activeorder table
	 * updates stock in DB
	 * displays message for user
	 */
	@FXML
	public void ClickCancelOrder(ActionEvent event) throws IOException{
		if(ProductController.activeArr.size()<1) {
			CancelEmptyOrderlbl.setText("Your Cart Is Empty");
		}else {
			Message message =new Message(MessageType.UpdateIsInUse,MainPageFormController.deviceCode);
			ClientUI.chat.accept(message);
			CancelEmptyOrderlbl.setText("");
			flagCancelOrder=true;
			CreateOrderFormController.flag = false;
			tblOrder.getItems().clear();
			String[] stock=new String[3];
			double amount=0;	
			stock[0]=String.valueOf(MainPageFormController.deviceCode);
			for(ActiveSale as:ProductController.activeArr) {
				for(Product p:ProductController.subArr) {
					if(as.getProductName().equals(p.getName())) {
						amount=as.getAmount()+p.getAmount();
						stock[1]=as.getProductName();
						stock[2]=String.valueOf(amount);
						message=new Message(MessageType.CancelOrder,stock);
						ClientUI.chat.accept(message);
						amount=0;
					}
				}
			}
			Order order=new Order(ProductController.orderId,
					ProductController.totalPrice,
					MainPageFormController.results[0],
					MainPageFormController.deviceCode,
					MainPageFormController.sDate,
					"Canceled",
					"null",
					LoginController.user[0],
					"creditCard");
			message=new Message(MessageType.AddToOrders,order);
			ClientUI.chat.accept(message);
			tblOrder.getItems().clear();
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("FinishCreatingOrder.fxml"));
			if(MainPageFormController.MainStage==null){
				MainPageFormController.MainStage = new Stage();
			}
			Scene scene = new Scene(root);
			MainPageFormController.MainStage.setTitle("Finish Creating Order");
			MainPageFormController.MainStage.setScene(scene);		
			MainPageFormController.MainStage.show();
		}
		
		
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves order of the user in DB
	 * updates stock based on the order
	 * displays message for user
	 */
	@FXML
	public void ClickPay(ActionEvent event) throws IOException{

		if(ProductController.activeArr.size()<1) {
			PayEmptyOrderlbl.setText("Your Cart Is Empty");
		}else {
			Message message =new Message(MessageType.UpdateIsInUse,MainPageFormController.deviceCode);
			ClientUI.chat.accept(message);
			PayEmptyOrderlbl.setText("");
			CreateOrderFormController.flag = false;
			tblOrder.getItems().clear();
			Order order = null;
			if(RemoteOrderPickupOptionFormController.loc==null && RemoteOrderViaDroneFormController.Address==null) {
				 order=new Order(ProductController.orderId,
					ProductController.totalPrice,
					MainPageFormController.results[0],
					MainPageFormController.deviceCode,
					MainPageFormController.sDate,
					"Approved",
					"null",
					LoginController.user[0],
					"creditCard");
			}else if(RemoteOrderViaDroneFormController.Address==null) {
				order=new Order(ProductController.orderId,
						ProductController.totalPrice,
						MainPageFormController.results[0],
						MainPageFormController.deviceCode,
						MainPageFormController.sDate,
						"Waiting for PickUp",
						"null",
						LoginController.user[0],
						"creditCard");
						flagPickUpOrder=true;
			}else {
				order=new Order(ProductController.orderId,
						ProductController.totalPrice,
						MainPageFormController.results[0],
						MainPageFormController.deviceCode,
						MainPageFormController.sDate,
						"Waiting for delivery",
						"null",
						LoginController.user[0],
						"creditCard");
						flagDroneOrder=true;
			}
			
			message=new Message(MessageType.AddToOrders,order);
			ClientUI.chat.accept(message);
			message = new Message(MessageType.stockThresholdStatus,MainPageFormController.deviceCode);
			ClientUI.chat.accept(message);
			((Node)event.getSource()).getScene().getWindow().hide();
			Parent root = FXMLLoader.load(getClass().getResource("FinishCreatingOrder.fxml"));
			if(MainPageFormController.MainStage==null){
				MainPageFormController.MainStage = new Stage();
			}
			Scene scene = new Scene(root);
			MainPageFormController.MainStage.setTitle("Finish Creating Order");
			MainPageFormController.MainStage.setScene(scene);		
			MainPageFormController.MainStage.show();
		}
		
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  based on data entered by user
	 * cancels single product or required amount in the current order
	 */
	@FXML
	void ClickCancelSingleProduct(ActionEvent event) throws IOException{
		//Update totalPrice amount if amount =0 delete row 
		//deviceManagement update stock product name deviceCode amount = amount + canceled amount
		boolean flag=true;
		flag=checkText();
		if(txtAmount.getText().isEmpty() || txtID.getText().isEmpty() || flag==false) {
			warninglbl.setText("One of the fields is entered Illegally!");
		}else {
			String[] stock=new String[4];
			int amount=0;
			int temp=0;
			Message message=new Message(MessageType.getProductName,txtID.getText());
			ClientUI.chat.accept(message);
			stock[0]=(String.valueOf(MainPageFormController.deviceCode));
			stock[1]=ProductController.name[0];
			stock[2]=txtAmount.getText();
			stock[3]=ProductController.name[1];
			for(ActiveSale as:ProductController.activeArr) {
				if(as.getProductName().equals(stock[1])) {
					stock[3]=String.valueOf(as.getPrice()) ;
					temp=as.getAmount();
				}
			}
			if(!(temp < Integer.parseInt(txtAmount.getText()))) {
				for(Product p:ProductController.subArr) {
					if(p.getName().equals(stock[1])) {
						amount=Integer.parseInt(stock[2])+p.getAmount();
						stock[2]=String.valueOf(amount);
						message=new Message(MessageType.CancelOrder,stock);
						ClientUI.chat.accept(message);
						amount=0;
					}
				}
				stock[0]=String.valueOf(ProductController.orderId);
				stock[2]=txtAmount.getText();
				for(ActiveSale as:ProductController.activeArr) {
					if(as.getProductName().equals(stock[1])) {
						amount=as.getAmount()-Integer.parseInt(stock[2]);
						stock[2]=String.valueOf(amount);
						if(amount<0) {
							warninglbl.setText("The amount value is Illegal!");
						}else {
							warninglbl.setText("");
							message=new Message(MessageType.UpdateActiveOrder,stock);
							ClientUI.chat.accept(message);
							fillTable();
							totalPriceLabel();
						}
						
					}
				}
			}else {
				warninglbl.setText("One of the fields is entered Illegally!");
			}
		}
		
		
		

	}

	/**
	 *  checks if text entered to cancel product are valid
	 */
	private boolean checkText() {
		// TODO Auto-generated method stub
		char[] temp1=txtAmount.getText().toCharArray();
		char[] temp2=txtID.getText().toCharArray();
		for(char c :temp1) {
			if(!((c>= '0') && (c<= '9'))) {
				return false;
			}
		}
		for(char c :temp2) {
			if(!((c>= '0') && (c<= '9'))) {
				return false;
			}
		}
		return true;
	}

	/**
	 *  initializes the columns of the table
	 */
	private void initColumns(){
		// TODO Auto-generated method stub
		col_ID.setCellValueFactory(new PropertyValueFactory<ActiveSale,Integer>("productId"));
		col_Name.setCellValueFactory(new PropertyValueFactory<ActiveSale,String>("productName"));
		col_Price.setCellValueFactory(new PropertyValueFactory<ActiveSale,Double>("price"));
		col_Amount.setCellValueFactory(new PropertyValueFactory<ActiveSale,Integer>("amount"));
	}
	/**
	 * initializes the page with required data
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(ClientUI.machineCode.equals("1")) {
			imgEK.visibleProperty().setValue(false);
		}
		else {
			imgOL.visibleProperty().setValue(false);
		}
		flagPickUpOrder=false;
		flagDroneOrder=false;
		flagCancelOrder=false;
		initColumns();
		fillTable();
		totalPriceLabel();
	}
	

	/**
	 *  updates the label of the total price based on the order
	 */
	private void totalPriceLabel() {
		// TODO Auto-generated method stub
		Message message=new Message(MessageType.GetTotalPrice,ProductController.orderId);
		ClientUI.chat.accept(message);
		lblTotalPrice.setText(String.valueOf(ProductController.totalPrice)+" NIS");
		
	}

	/**
	 *  fills the table with required data
	 */
	private void fillTable() {
		// TODO Auto-generated method stub
		tblOrder.getItems().clear();
		ProductController.getAllActiveSales(ProductController.orderId);;
		tblOrder.setItems(ProductController.listActive);
	}
	
	
	
}
