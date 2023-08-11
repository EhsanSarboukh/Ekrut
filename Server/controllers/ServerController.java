package controllers;

import java.io.IOException;
import java.util.ArrayList;
import DBControl.*;
import entity.*;
import gui.ServerFormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.*;

/**
 * @author Ehsan Sarboukh , Mhemad Mdah , Sara Asaad , Sabeel Hamood , Daniella Kdmany , Adham Asaad
 *
 */
public class ServerController extends AbstractServer {
	
	public String clientIp;
	public String hostName;
	public String clientConnected = "not connected";
	public static DBConnector dbConnector;
	public static ObservableList<ClientConnection> list = FXCollections.observableArrayList();
	public static ArrayList<ClientConnection> connections=new ArrayList<ClientConnection>();
	public ClientConnection clientConnection=new ClientConnection(null,null,null);
	private String command;
	
	
	/**
	 * @param port
	 *  opens connection to DB
	 */
	public ServerController(int port) {
		super(port);
		dbConnector = new DBConnector();
	}

	/**
	 *  handles message from client and performs based on case
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		Message message = (Message)msg;
		Message msgFromServer = null;
		ArrayList<Subscriber> arr = null;
		ArrayList<Sale> sales = null;
		ArrayList<Product> product=null;
		ArrayList<ActiveSale> activeSale=null;
		ArrayList<AreaStock> areaStock=null;
		ArrayList<DeviceStock> deviceStock=null;
		ArrayList<User>arrUser=null;
		ArrayList<StockProduct> updateStock = null;
		ArrayList<OrderStatus> arrorder = null;
		
		String[] productName=new String[2];
		String user[]=new String[2];
		String[] updateOrder=new String[2];
		int orderId=0;
		String userID;
		switch(message.getMessageType()) {
		case CheckLogin:
			user=(String[]) message.getMessageData();
			boolean flag=LoginDBController.CheckLoginBit(user);
			msgFromServer=new Message(MessageType.CheckLogin,flag);
			break;
		case Login:
			user=(String[]) message.getMessageData();
			String role=LoginDBController.CheckUserValidation(user);
			if(role==null) {role="null";}
			msgFromServer=new Message(MessageType.Role,role);
			break;
		case LogOut:
			user=(String[]) message.getMessageData();
			LoginDBController.LogOutToLoginForm(user);
			msgFromServer=new Message(MessageType.LogOut,null);
			break;
		case Disconnect:
			clientDisconnected(client);
			msgFromServer=new Message(MessageType.Disconnected, null);
			break;
		case getProducts:
			String[] temp = new String[3];
			temp = (String[])message.getMessageData();
			ProductDBController.getAmount(Integer.parseInt(temp[0]));
			product=ProductDBController.getProducts(temp);
			msgFromServer=new Message(MessageType.getProducts,product);
			break;
		case getProductName:
			productName=ProductDBController.getProductName((String)message.getMessageData());
			msgFromServer=new Message(MessageType.getProductName,productName);
			break;
		case addRowInActiveSale:
			ProductDBController.addRowInActiveSale((ActiveSale)message.getMessageData());
			msgFromServer=new Message(MessageType.SuccessUpdate,null);
			break;
		case updateDeviceStock:
			ProductDBController.updateDeviceStock((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.SuccessUpdate,null);
			break;
		case GetOrderId:
			orderId=ProductDBController.getOrderId();
			msgFromServer= new Message(MessageType.GetOrderId,orderId);
			break;
		case ViewActiveSale:
			activeSale=ProductDBController.viewActiveSale((Integer)message.getMessageData());
			msgFromServer=new Message(MessageType.ViewActiveSale,activeSale);
			break;
		case CancelOrder:
			ProductDBController.updateDeviceStock((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.CancelOrder,null);
			break;
		case getLocation:
			String[] location=ProductDBController.getLocation((Integer)message.getMessageData());
			msgFromServer=new Message(MessageType.getLocation,location);
			break;
		case GetTotalPrice:
			orderId = (Integer)message.getMessageData();
			double totalPrice=ProductDBController.getTotalPrice(orderId);
			msgFromServer=new Message(MessageType.GetTotalPrice,totalPrice);
			break;
		case AddToOrders:
			ProductDBController.addToOrders((Order)message.getMessageData());
			msgFromServer=new Message(MessageType.AddToOrders,null);
			break;
		case CheckProduct:
			int amount=ProductDBController.checkProduct((ActiveSale)message.getMessageData());
			msgFromServer=new Message(MessageType.CheckProduct,amount);
			break;
		case addAmountToExistingProduct:
			ProductDBController.addAmountToExistingProduct((ActiveSale)message.getMessageData());
			msgFromServer=new Message(MessageType.addAmountToExistingProduct,null);
			break;
		case UpdateActiveOrder:
			ProductDBController.UpdateActiveOrder((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.UpdateActiveOrder,null);
			break;
		case GetStockByLocation:
			areaStock=AreaStockDBController.getStockByLocation((String)message.getMessageData());
			msgFromServer=new Message(MessageType.GetStockByLocation,areaStock);
			break;
		case CheckIdDevice:
			boolean flag1=AreaStockDBController.checkIdDevice((String)message.getMessageData());
			msgFromServer=new Message(MessageType.CheckIdDevice,flag1);
			break;
		case GetStockByDeviceId:
			deviceStock=AreaStockDBController.getStockByDeviceId((String)message.getMessageData());
			msgFromServer=new Message(MessageType.GetStockByDeviceId,deviceStock);
			break;
		case UpdateStockByDeviceId:
			AreaStockDBController.updateStockByDeviceId((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.UpdateStockByDeviceId,null);
			break;
		case GetAllCustomers:
			LoginDBController.getAllCustomers();
			msgFromServer=new Message(MessageType.GetAllCustomers,null);
			break;
		case getSubscriberDetails://add
			user=(String[]) message.getMessageData();
			arrUser= ClientDBController.getSubscriber(user);	
			msgFromServer = new Message(MessageType.ShowSubscriberDetails,arrUser);
			break;
		case customerWantToSubscribe:
			user=(String[]) message.getMessageData();
			boolean flag2=ClientDBController.requestToSubscribe(user);
			msgFromServer=new Message(MessageType.customerWantToSubscribe,flag2);
			break;
		case checkRequests:
			user=(String[]) message.getMessageData();
			boolean flag3=ClientDBController.checkRequests(user);
			msgFromServer=new Message(MessageType.checkRequests,flag3);
			break;		
		case customerCanSubscribe:
			user=(String[]) message.getMessageData();
			ClientDBController.waitingForCEOToChangeStatus(user);
			msgFromServer=new Message(MessageType.customerCanSubscribe,null);
			break;	
		case changeStatus:
			user=(String[]) message.getMessageData();
			ClientDBController.ceoChangeSubscribeStatus(user);
			msgFromServer=new Message(MessageType.changeStatus,null);
			break;
		case RecieveOrder:
			String CheckOrder=ProductDBController.recieveOrder((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.RecieveOrder,CheckOrder);
			break;
		case getSales:
			sales = SaleDBController.getSale();
			msgFromServer = new Message(MessageType.getSales,sales);
			break;
		case UpdateSale:
			String[] sale = (String[])message.getMessageData();
			SaleDBController.UpdateSale(sale);
			msgFromServer = new Message(MessageType.UpdateSale,null);
		case getSalesToActivate:
			sales = SaleDBController.getSalesToActivate();
			msgFromServer = new Message(MessageType.getSalesToActivate,sales);
			break;
		case UpdateStatusSale:
			SaleDBController.UpdateSaleStatus();
			msgFromServer = new Message (MessageType.UpdateStatusSale,null);
			break;
		case GetSubscriberStatus:
			userID=(String) message.getMessageData();
			boolean flagSubscriberStatus = LoginDBController.getSubscribeStatus(userID);
			msgFromServer=new Message(MessageType.SaveSubscribeStatus,flagSubscriberStatus);
			break;
		case GetUsernameAndPassword:
			userID=(String) message.getMessageData();
			user[0]=LoginDBController.getUsername(userID);
			user[1]=LoginDBController.getPassword(userID);
			msgFromServer=new Message(MessageType.SaveUsernameAndPassword,user);
			break;
		case StockEqualsToThresholdLevel://****
			ThresholdLevelDBController.LetOperationWorkerUpdate((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.StockEqualsToThresholdLevel,null);
			break;
		case CheckThresholdLevel://***
			int[] user1= new int[2];
			user1=(int[]) message.getMessageData();
			String ThresholdLevel =ThresholdLevelDBController.CheckThresholdLevelDataValidation(user1);
			msgFromServer=new Message(MessageType.CheckThresholdLevel,ThresholdLevel);
			break;
		case RemoveFromCart:
			int amount1=ProductDBController.removeFromCart((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.RemoveFromCart,amount1);
			break;
		case GetAllOrderDetails:
			String OrderLocation=(String)message.getMessageData();
			arrorder= UpdateOrderStatusDBController.GetOrderStatus(OrderLocation);
			msgFromServer= new Message(MessageType.ShowOrderDetails, arrorder);
			break;
		case CheckUpdateOrder:
			updateOrder=(String[])message.getMessageData();
			boolean flagOrder=UpdateOrderStatusDBController.CheckUpdateOrder(updateOrder);
			msgFromServer=new Message(MessageType.UpdateOrderFlag,flagOrder);
			break;
		case UpdateStatus:
			Integer status=(Integer)message.getMessageData();
			String deliveryDate=UpdateOrderStatusDBController.updateStatus(status);
			msgFromServer = new Message(MessageType.SuccessOrderUpdate, deliveryDate);
			break;
		case GetAreaManagerLocation:
			String arealoc=reportsDBController.getAreaManagerLocation((String)message.getMessageData());
			msgFromServer=new Message(MessageType.GetAreaManagerLocation,arealoc);
			break;
		case FillOrderStatus:
			int [] dataInt=reportsDBController.getOrderStatus((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.FillOrderStatus,dataInt);
		    break;
		case FillProfit:
			double profit=reportsDBController.getOrderProfit((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.FillProfit,profit);
		    break;
		case FillOrderReport:
			String orderDetails=reportsDBController.fillOrderReport((String[])message.getMessageData());
			msgFromServer=new Message(MessageType.FillOrderReport,orderDetails);
			break;
		case GetStockReportDetails:
			Integer[] products = new Integer[5]; 
			Integer[] stockreportdetails=(Integer[])message.getMessageData();
			products=reportsDBController.getStockReportDetails(stockreportdetails);
			msgFromServer = new Message(MessageType.SavemMonthlyStockReport, products);
			break;
		case CheckReportExist:
			boolean flagReport; 
			String[] reportDetails=(String[])message.getMessageData();
			flagReport=reportsDBController.checkReportExist(reportDetails);
			msgFromServer = new Message(MessageType.MonthlyReportIsExisted,flagReport);
			break;
		case CheckIDValidatin:
			String checkid=(String) message.getMessageData();
			boolean flagIdValid=LoginDBController.CheckIdValidation(checkid);
			msgFromServer=new Message(MessageType.UpdateIdValidation,flagIdValid);
			break;
		case getCustomerReport:
			String[] temp1 = (String[]) message.getMessageData();
			ArrayList<CustomerReport> report = reportsDBController.getCustomerReport(temp1);
			msgFromServer = new Message(MessageType.getCustomerReport,report);
			break;
		case getCustomerReportLabels:
			String[] label = reportsDBController.getCustomerReportLabel();
			msgFromServer = new Message(MessageType.getCustomerReportLabels,label);
			break;
		case DeleteActiveOrder:
			ProductDBController.DeleteActiveOrder((Integer) message.getMessageData());
			msgFromServer = new Message(MessageType.DeleteActiveOrder,null);
			break;
		case checkUser:
			user=(String[]) message.getMessageData();
			boolean flag4=ClientDBController.checkUser(user);
			msgFromServer=new Message(MessageType.checkUser,flag4);
			break;
		case AddCustomer:
			ClientDBController.AddCustomer((Customer)message.getMessageData());
			msgFromServer=new Message(MessageType.AddCustomer,null);
			break;
		case AddSale:
			SaleDBController.addSale((Sale)message.getMessageData());
			msgFromServer = new Message(MessageType.AddSale,null);
			break;
		case stockThresholdStatus:
			boolean sendMsg = ThresholdLevelDBController.checkThresholdStatus((Integer)message.getMessageData());
			msgFromServer = new Message(MessageType.stockThresholdStatus,sendMsg);
			break;
		case DeActivateSale:
			SaleDBController.deActivateSale((String[])message.getMessageData());
			msgFromServer = new Message(MessageType.DeActivateSale,null);
			break;
		case UpdateIsInUse:
			ProductDBController.updateIsInUse((Integer) message.getMessageData());
			msgFromServer = new Message(MessageType.UpdateIsInUse,null);
			break;
		case GetIsInUse:
			int isInUse=ProductDBController.getIsInUse((Integer) message.getMessageData());
			msgFromServer = new Message(MessageType.GetIsInUse,isInUse);
			break;
			default:
				msgFromServer = new Message(MessageType.Error, null);
		}
		
		try {
			client.sendToClient(msgFromServer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 *  gets connection of the client
	 */
	@Override
	protected void clientConnected(ConnectionToClient client) {
		super.clientConnected(client);
		clientConnection.setIp(client.getInetAddress().getHostAddress());
		clientConnection.setHost(client.getInetAddress().getHostName());
		clientConnection.setStatus("connected");
		connections.add(clientConnection);
		System.out.println(client + " connected !");

	}
	
	/**
	 *  closes connection with client
	 */
	@Override
	  synchronized protected void clientDisconnected(ConnectionToClient client) {
		clientConnection.setIp(client.getInetAddress().getHostAddress());
		clientConnection.setHost(client.getInetAddress().getHostName());
		clientConnection.setStatus("connected");
		connections.remove(clientConnection);
		super.clientDisconnected(client);
		
		
	}
	
	/**
	 *  all connection with server
	 */
	public static void getAllConnection() {
		for (ClientConnection cc :list )
			list.remove(cc);
		for (ClientConnection cc : connections) 
			list.add(cc);
	}

	/**
	 *  opens server and calls method for reports
	 */
	@Override
	protected void serverStarted() {
        AutoGenerateMonthlyReports monthlyOrders = new AutoGenerateMonthlyReports(); //create daemon thread to generate monthly reports every first day of month
        monthlyOrders.run();
		super.serverStarted();
		System.out.println("server started and listening on port " + getPort());

	}
	
	/**
	 *  closes connection of server
	 */
	@Override
	protected void serverStopped() {
		super.serverStopped();
		System.out.println("server stopped! :)");		
	}
	

}
