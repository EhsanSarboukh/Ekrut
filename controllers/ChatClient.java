package controllers;

import java.io.IOException;
import java.util.ArrayList;

import client.AbstractClient;
import common.*;
import entity.*;
import gui.AreaManagerController;
import gui.MainPageFormController;
import gui.ShowStockReportController;
import gui.ViewMonthlyReportController;
import gui.loginBySpmatphoneController;

/**
 * @author  Ehsan Sarboukh , Mhemad Mdah , Sabeel Hamood ,Sara Asaad , Adham Asaad , Daniella Kadmany
 *
 */
public class ChatClient extends AbstractClient {
	public static String username;
	public static String password;
	public static String role;
	public static boolean flag;
	public static boolean awaitResponse = false;
	public static boolean flag1;
	public static boolean flag2;
	public static boolean flag4;
	public static boolean ordernumber;
	public static boolean subscribeStatus;
	public static String[] user= new String[2];
	public ChatIF clientUI;
	
	/**
	 * @param host
	 * @param port
	 * @param clientUI
	 * @throws IOException
	 *  opens connections for chat
	 */
	public ChatClient(String host, int port,ChatIF clientUI) throws IOException {
		super(host, port);
	    this.clientUI = clientUI;
	    openConnection();
		//closeConnection();
	}

	/**
	 * handles message from server and performs the required case
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		awaitResponse = false;
		Message message = (Message)msg;
		switch(message.getMessageType()) {
		case ShowDetails :
			SubscriberController.subArr = (ArrayList<Subscriber>)message.getMessageData();
			break;
		case ShowOrderDetails :
			OrderStatusController.orderkArr= (ArrayList<OrderStatus>)message.getMessageData();
			break;
		case SuccessOrderUpdate:
			clientUI.display("The delivery has been sent, estimated time : "+ (String)message.getMessageData());
			break;
		case SuccessUpdate:
			break;
		case Disconnected:
			System.exit(0);
			break;
		case  Role:
			role=(String)message.getMessageData();
			break;
		case CheckLogin:
			flag=(boolean)message.getMessageData();
			break;
		case checkUser:
			flag4=(boolean)message.getMessageData();
			break;
		case LogOut:
			break;
		case StockEqualsToThresholdLevel: //***
			clientUI.display("The message has been sent to the operation worker successfully");
			break;
		case getProducts:
			ProductController.subArr=(ArrayList<Product>)message.getMessageData();
			break;
		case getProductName:
			ProductController.name=(String[])message.getMessageData();
			break;
		case GetOrderId:
			ProductController.orderId=((Integer)message.getMessageData());
			break;
		case ViewActiveSale:
			ProductController.activeArr=((ArrayList<ActiveSale>)message.getMessageData());
			break;
		case CancelOrder:
			break;
		case getLocation:
			MainPageFormController.results=((String[])message.getMessageData());
			break;
		case GetTotalPrice:
			ProductController.totalPrice=((Double)message.getMessageData());
			break;
		case AddToOrders:
			break;
		case CheckProduct:
			ProductController.amount=((Integer)message.getMessageData());
			break;
		case addAmountToExistingProduct:
			break;
		case UpdateActiveOrder:
			break;
		case GetStockByLocation:
			StockController.subArr=((ArrayList<AreaStock>)message.getMessageData());
			break;
		case CheckIdDevice:
			StockController.flag=((boolean)message.getMessageData());
			break;
		case GetStockByDeviceId:
			StockController.deviceArr=((ArrayList<DeviceStock>)message.getMessageData());
			break;
		case UpdateStockByDeviceId:
			break;
		case GetAllCustomers:
			break;
		case ShowSubscriberDetails://add
			SubscriberController.subArrUser = ((ArrayList<User>)message.getMessageData());//add
			break;
		case customerWantToSubscribe:
			flag1=(boolean)message.getMessageData();
			break;
		case checkRequests:
			flag2=(boolean)message.getMessageData();
			break;
		case changeStatus:
			clientUI.display("The requests have been successfully approved");
			break;
		case customerCanSubscribe:
			clientUI.display("we will add you soon to  subscribers");
			break;
		case RecieveOrder:
			ProductController.PickUpOrderLabel=((String)message.getMessageData());
			break;
		case getSales:
			SaleController.saleArr = (ArrayList<Sale>) message.getMessageData();
			break;
		case UpdateSale:
			clientUI.display("Status Changed");
			break;
		case getSalesToActivate:
			SaleController.saleActivateArr = (ArrayList<Sale>) message.getMessageData();
			break;
		case UpdateStatusSale:
			clientUI.display("Status Changed");
			break;
		case  SaveUsernameAndPassword:
			user=(String[])message.getMessageData();
			username=user[0];
			password=user[1];
			break;
		case  SaveSubscribeStatus:
			subscribeStatus=(boolean)message.getMessageData();
			break;
		case CheckThresholdLevel: //***
			clientUI.display("Determining the threshold level was performed successfully");
			break;
		case RemoveFromCart:
			ProductController.amo=((Integer)message.getMessageData());
			break;
		case UpdateOrderFlag:
			ordernumber=(boolean)message.getMessageData();
			break;
		case GetAreaManagerLocation:
			AreaManagerController.arealoc=((String)message.getMessageData());
			break;
		case FillOrderStatus:
			ReportController.orderStatus=((int [])message.getMessageData());
			break;
		case FillProfit:
			ReportController.profit=((double)message.getMessageData());
			break;
		case FillOrderReport:
			ReportController.orderDetails=((String)message.getMessageData());
			break;
		case SavemMonthlyStockReport:
			ShowStockReportController.products=((Integer[])message.getMessageData());
			break;	
		case MonthlyReportIsExisted:
			ViewMonthlyReportController.flagReportExist=((boolean)message.getMessageData());
			break;	
		case UpdateIdValidation:
			loginBySpmatphoneController.flagIdExist=((boolean)message.getMessageData());
			break;
		case getCustomerReport:
			ReportController.report = (ArrayList<CustomerReport>) message.getMessageData();
			break;
		case getCustomerReportLabels:
			ReportController.label = (String[]) message.getMessageData();
			break;
		case DeleteActiveOrder:
			break;
		case AddCustomer:
			ClientUI.display("Customer has been added Successfully");
			break;
		case AddSale:
			ClientUI.display("Sale Added Successfully");
			break;
		case stockThresholdStatus:
			if((boolean)message.getMessageData() == true) {
				ClientUI.display("A Message Has Been Sent To Area Manager");
			}
			break;
		case DeActivateSale:
			break;
		case UpdateIsInUse:
			break;
		case GetIsInUse:
			MainPageFormController.results[1]=String.valueOf(message.getMessageData());
			break;
			default:
				clientUI.display("cant read message from server");
		}
		
	}
	
	/**
	 * @param msg
	 *  opens connection with server and sends it to server
	 */
	public void handleMessageFromClientUI(Object msg) {
		try
	    {
	    	openConnection();//in order to send more than one message
	       	awaitResponse = true;
	    	sendToServer(msg);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
	    }
	    catch(IOException e)
	    {
	    	e.printStackTrace();
	      clientUI.display("Could not send message to server");
	      
	    }
	}
	
	/**
	 *  closes connection for messages
	 */
	  public void quit()
	  {
	    try
	    {
	      closeConnection();
	    }
	    catch(IOException e) {}
	    System.exit(0);
	  }
}
