package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import controllers.ClientUI;
import controllers.ProductController;
import entity.ActiveSale;
import entity.Message;
import entity.MessageType;
import entity.Product;
import javafx.application.Platform;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * @author Ehsan Sarboukh , Mhemad Mdah
 *
 */
public class CreateOrderFormController implements Initializable {
	public static Stage CreateOrderStage;	
	@FXML
	private TableView<Product> tblProducts;
	
	@FXML
	private TableColumn<Product, String> col_ID;
	
	@FXML
	private TableColumn<Product, String> col_Name;
	
	@FXML
	private TableColumn<Product, Integer> col_Price;
	
	@FXML
	private TableColumn<Product, Integer> col_Amount;
	
	@FXML
	private TableColumn<Product, Double> col_Sale;
	
	@FXML
	private TableColumn<Product, Double> col_PriceAfterSale;
	
	@FXML
	private Button btnAddToCart;
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Button btnViewOrder;
	
	@FXML
	private Label availablelbl;
	
	@FXML
	private Button BambaPBtn;
	
	@FXML
	private Button BambaNBtn;
	
	@FXML
	private Button KlikPBtn;
	
	@FXML
	private Button KlikNBtn;
	
	
	@FXML
	private Button CocaColaPBtn;
	
	@FXML
	private Button CocaColaNBtn;
	
	@FXML
	private Button SevenUpPBtn;
	
	@FXML
	private Button SevenUpNBtn;
	
	@FXML
	private Button KatkatatPBtn;
	
	@FXML
	private Button KatkatatNBtn;
	@FXML
	private ImageView imgOL;
	@FXML
	private ImageView imgEK;
	
	private String[] product=new String[2];
	Stage stage;
	Scene scene;
    private Timer timer;
	public static boolean flag=true;
	
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  goes back to the page that was open before this page and clears content of the current table shown
	 * and cancels the order of the customer
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException{
		flag=false;
		Message message =new Message(MessageType.UpdateIsInUse,MainPageFormController.deviceCode);
		ClientUI.chat.accept(message);
		tblProducts.getItems().clear();
		message = new Message(MessageType.ViewActiveSale,ProductController.orderId);
		ClientUI.chat.accept(message);
		String[] stock=new String[3];
		double amount=0;	
		stock[0]=String.valueOf(MainPageFormController.deviceCode);
		for(ActiveSale as:ProductController.activeArr) {
			for(Product p:ProductController.subArr) {
				if(as.getProductName().equals(p.getName())) {
					System.out.println(as.getPrice()+" "+p.getAmount());
					amount=as.getAmount()+p.getAmount();
					stock[1]=as.getProductName();
					stock[2]=String.valueOf(amount);
					message=new Message(MessageType.CancelOrder,stock);
					ClientUI.chat.accept(message);
					amount=0;
				}
			}
		}
		message = new Message(MessageType.DeleteActiveOrder,ProductController.orderId);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/MainPage.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Order Main Page");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnBack.getScene().getWindow();
		
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  goes to the page of view order and loads details of the customers order
	 */
	@FXML
	public void ClickViewOrder(ActionEvent event) throws IOException{
		tblProducts.getItems().clear();
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ViewOrder.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("View Order");
		stage.setScene(scene);
		stage.show();
		
	}
	

	/**
	 *  clears content of the table
	 */
	public  void ClearTable() {
		tblProducts.getItems().clear();
	}
	/**
	 *  initializes the columns of the table
	 */
	private void initColumns(){
		col_Name.setCellValueFactory(new PropertyValueFactory<Product,String>("name"));
		col_Price.setCellValueFactory(new PropertyValueFactory<Product,Integer>("price"));
		col_Sale.setCellValueFactory(new PropertyValueFactory<Product,Double>("sale"));
		col_Amount.setCellValueFactory(new PropertyValueFactory<Product,Integer>("amount"));
		col_PriceAfterSale.setCellValueFactory(new PropertyValueFactory<Product,Double>("priceAfterSale"));
		
	}
	/**
	 * method starts with page , sets timer for the order
	 *calls for method initcolumns
	 *calls for method fillTable
	 *and shows logo of the current machine
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		setTimer();
		initColumns();
		fillTable();
		if(ClientUI.machineCode.equals("1")) {
			imgEK.visibleProperty().setValue(false);
		}
		else{
			imgOL.visibleProperty().setValue(false);
		}
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls addToCart
	 */
	@FXML
	private void ClickBambaPBtn(ActionEvent event)throws IOException{
		product[0]="1";
		product[1]="Bamba";
		addToCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls removeFromCart
	 */
	@FXML
	private void ClickBambaNBtn(ActionEvent event)throws IOException{
		product[0]="1";
		product[1]="Bamba";
		removeFromCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls addToCart
	 */
	@FXML
	private void ClickKlikPBtn(ActionEvent event)throws IOException{
		product[0]="2";
		product[1]="Klik";
		addToCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls removeFromCart
	 */
	@FXML
	private void ClickKlikNBtn(ActionEvent event)throws IOException{
		product[0]="2";
		product[1]="Klik";
		removeFromCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls addToCart
	 */
	@FXML
	private void ClickCocaColaPBtn(ActionEvent event)throws IOException{
		product[0]="3";
		product[1]="CocaCola";
		addToCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls removeFromCart
	 */
	@FXML
	private void ClickCocaColaNBtn(ActionEvent event)throws IOException{
		product[0]="3";
		product[1]="CocaCola";
		removeFromCart(product);
	}
	
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls addToCart
	 */
	@FXML
	private void ClickSevenUpPBtn(ActionEvent event)throws IOException{
		product[0]="4";
		product[1]="SevenUp";
		addToCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls removeFromCart
	 */
	@FXML
	private void ClickSevenUpNBtn(ActionEvent event)throws IOException{
		product[0]="4";
		product[1]="SevenUp";
		removeFromCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls addToCart
	 */
	@FXML
	private void ClickKatkatatPBtn(ActionEvent event)throws IOException{
		product[0]="5";
		product[1]="Katkatat";
		addToCart(product);
	}
	
	/**
	 * @param event
	 * @throws IOException
	 *  saves product name and amount 1 in product array and calls removeFromCart
	 */
	@FXML
	private void ClickKatkatatNBtn(ActionEvent event)throws IOException{
		product[0]="5";
		product[1]="Katkatat";
		removeFromCart(product);
	}

	/**
	 * @param product
	 *  adds 1 to the current amount of the product in the order
	 * saves the detail in the activeorder table in DB
	 * updates stock in the required device
	 */
	private void addToCart(String[] product) {
		double price=0;
		int amount = 0;
		ActiveSale activeSale;
		String[] stock=new String[3];
		for(Product p : ProductController.subArr) {
			if(p.getId().equals(product[0])) {
				price = p.getPriceAfterSale();
				amount=p.getAmount();
			}
			
		}
		if(amount >0) {
			availablelbl.setText("");
			activeSale=new ActiveSale(ProductController.orderId,Integer.parseInt(product[0])
					,product[1]
					,price
					,1);
			Message message=new Message(MessageType.CheckProduct,activeSale);
			ClientUI.chat.accept(message);
			if(ProductController.amount==0) {
				message=new Message(MessageType.addRowInActiveSale,activeSale);
				ClientUI.chat.accept(message);
			}else {
				activeSale.setAmount(activeSale.getAmount()+ProductController.amount);
				message=new Message(MessageType.addAmountToExistingProduct,activeSale);
				ClientUI.chat.accept(message);
			}
			stock[0]=String.valueOf(MainPageFormController.deviceCode);
			stock[1]=product[1];
			amount = 0;
			for(Product p:ProductController.subArr) {
				if(p.getId().equals(product[0])) {
					amount=p.getAmount()- 1;
				}
				stock[2]=String.valueOf(amount);
				message=new Message(MessageType.updateDeviceStock,stock);
				ClientUI.chat.accept(message);
				
				fillTable();
			}
		
		}else {
			availablelbl.setText(product[1]+" is not available ATM!");
		}
			
		//Integer.parseInt(txtAmount.getText());
		
	}
	
	
	/**
	 * @param product
	 *  removes 1 to the current amount of the product in the order
	 * saves the detail in the activeorder table in DB
	 * updates stock in the required device
	 */
	private void removeFromCart(String[] product) {
		// TODO Auto-generated method stub
		//Update totalPrice amount if amount =0 delete row 
		//deviceManagement update stock product name deviceCode amount = amount + canceled amount4
		String[] id=new String [2];
		id[0]=String.valueOf(ProductController.orderId);
		id[1]=product[1];
		Message message=new Message(MessageType.RemoveFromCart,id);
		ClientUI.chat.accept(message);
		if(ProductController.amo+1>0) {
			String[] stock=new String[4];
			stock[0]=String.valueOf(MainPageFormController.deviceCode);
			stock[1]=product[1];
			int amount = 0;
			//ProductController.getAllActiveSales(ProductController.orderId);
			for(Product p:ProductController.subArr) {
				if(p.getId().equals(product[0])) {
					amount=p.getAmount()+ 1;
				}
			}
			
			stock[2]=String.valueOf(amount);
			message=new Message(MessageType.updateDeviceStock,stock);
			ClientUI.chat.accept(message);
			fillTable();

		}
	}


	/**
	 *  fills the table with required details
	 */
	private void fillTable() {
		// TODO Auto-generated method stub
		tblProducts.getItems().clear();
		String[] device = new String[3];
		device[0] = String.valueOf(MainPageFormController.deviceCode);
		device[1] = MainPageFormController.results[0];
		device[2] = LoginController.user[0];
		ProductController.getAllProducts(device);
		tblProducts.setItems(ProductController.list);
		
	}
	
	/**
	 *  sets timer of 15 minutes for the current order
	 * if time is up , system logs the user out and cancels its order
	 */
	private void setTimer() {
        this.timer = new Timer();
        Long startTime = System.currentTimeMillis();
        flag=true;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                    	
                    	 Long endTime=System.currentTimeMillis();
                    	 if (((endTime-startTime)/(1000*60*15) == 1 ) && (flag == true)) 
                        {
                        	
                    		ClearTable();
                            timer.cancel();
                            ObservableList<Window> open = Stage.getWindows();
    						final Stage primaryStage = new Stage();
    						LoginController loginController = new LoginController();
    						Message message = new Message(MessageType.ViewActiveSale,ProductController.orderId);
    						ClientUI.chat.accept(message);
    						String[] stock=new String[3];
    						double amount=0;	
    						stock[0]=String.valueOf(MainPageFormController.deviceCode);
    						for(ActiveSale as:ProductController.activeArr) {
    							for(Product p:ProductController.subArr) {
    								if(as.getProductName().equals(p.getName())) {
    									System.out.println(as.getPrice()+" "+p.getAmount());
    									amount=as.getAmount()+p.getAmount();
    									stock[1]=as.getProductName();
    									stock[2]=String.valueOf(amount);
    									message=new Message(MessageType.CancelOrder,stock);
    									ClientUI.chat.accept(message);
    									amount=0;
    								}
    							}
    						}
    						message = new Message(MessageType.UpdateIsInUse,MainPageFormController.deviceCode);
    						ClientUI.chat.accept(message);
    						message = new Message(MessageType.DeleteActiveOrder,ProductController.orderId);
    						ClientUI.chat.accept(message);
							message = new Message(MessageType.LogOut,LoginController.user);
							ClientUI.chat.accept(message);
							try {
								loginController.start(primaryStage);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						
							for(Window w : open) {
								w.hide();
							}  
                        }else if(flag==false){
                        	timer.cancel();
                        }
                    }
                });
            }
        };

        timer.schedule(timerTask, 0, 1000);
    }
}
