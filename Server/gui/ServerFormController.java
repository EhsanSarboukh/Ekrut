package gui;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ServerController;
import controllers.ServerUI;
import entity.ClientConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ServerFormController implements Initializable {

    @FXML
    private TextField txtIp;
    
    @FXML
    private TextField txtPort;
    
    @FXML
    private TextField txtDbName;
    
    @FXML
    private TextField txtDbUser;
    
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
	private TableView<ClientConnection> tblClientsConnected;
    
    @FXML
	private TableColumn<ClientConnection, String> col_ip;
    
    @FXML
	private TableColumn<ClientConnection, String> col_host;
    
    @FXML
	private TableColumn<ClientConnection, String> col_status;

    
    @FXML
    private Button btnConnect;
    
    @FXML
    private Button btnDisConnect;
    
    @FXML
    private Button btnImportData;
    
    public static String[] conn = new String[3];

    
    @FXML
    void ClickExit(ActionEvent event) {
    	ServerUI.exit();
    }
    
    public void start(Stage primaryStage) throws Exception {
    	
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Server.fxml"));
		Parent root=loader.load();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Connections");
		primaryStage.setScene(scene);
		primaryStage.show();


	}
    
    @FXML
    private void ClickConnect(ActionEvent event) throws IOException{
    	conn[0] = txtDbName.getText();
    	conn[1] = txtDbUser.getText();
    	conn[2] = passwordField.getText();
    	ServerUI.runServer(5555);
    }
    
    @FXML
    private void ClickDisconnect(ActionEvent event) throws IOException{
    	ServerUI.serverControl.stopListening();
    	ServerUI.serverControl.close();
    }
    @FXML
    private void ClickImport(ActionEvent event) throws IOException {
    	fillTable();
    	
    }
	private void fillTable() {
		// TODO Auto-generated method stub
		tblClientsConnected.getItems().clear();
		//updates the observable list of subscriber in SubscriberController
		ServerController.getAllConnection();
		tblClientsConnected.setItems(ServerController.list);
		
	}

	private void initColumns(){
// TODO Auto-generated method stub
		col_ip.setCellValueFactory(new PropertyValueFactory<ClientConnection,String>("Ip"));
		col_host.setCellValueFactory(new PropertyValueFactory<ClientConnection,String>("Host"));
		col_status.setCellValueFactory(new PropertyValueFactory<ClientConnection,String>("Status"));
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
    	txtIp.setText("localhost");
		txtPort.setText("5555");
		txtDbName.setText("jdbc:mysql://localhost/ekrut?serverTimezone=IST");
		txtDbUser.setText("root");
		passwordField.setText("Aa123456");
		initColumns();
	}
	

}
