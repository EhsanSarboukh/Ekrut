package gui;
import java.io.IOException;

import controllers.ChatClient;
import controllers.ClientUI;
import entity.Customer;
import entity.Message;
import entity.MessageType;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
public class CustomerManagementController {

    @FXML
    private Button addCustomerBtn;

    @FXML
    private TextField credittxt;

    @FXML
    private TextField emailtxt;

    @FXML
    private TextField firstNametxt;

    @FXML
    private TextField idtxt;

    @FXML
    private TextField lastNametxt;
    @FXML
    private Label lblCustomerMn;

    @FXML
    private Button logOutBtn;

    @FXML
    private TextField passwordtxt;

    @FXML
    private TextField phoneNumbertxt;

    @FXML
    private TextField userNametxt;
    
    private  Stage stage;
    private Scene scene;

    @FXML
    void clickAddCustomer(ActionEvent event) throws IOException{
    	String [] newUser= new String[2];
    	newUser[0]=userNametxt.getText();
    	newUser[1]=idtxt.getText();
    	Message message=new Message(MessageType.checkUser,newUser);
    	ClientUI.chat.accept(message);
    	if(userNametxt.getText().isEmpty() ||  passwordtxt.getText().isEmpty()|| firstNametxt.getText().isEmpty()
    			|| lastNametxt.getText().isEmpty() || emailtxt.getText().isEmpty() || phoneNumbertxt.getText().isEmpty() || idtxt.getText().isEmpty() || credittxt.getText().isEmpty()) {
    		lblCustomerMn.setText("Please Fill All Fields");
    	}
    	
    	else if(ChatClient.flag4==false) {
    		lblCustomerMn.setText("username or id is already used");
    		userNametxt.clear();
    		idtxt.clear();
    	}
    	else {
	    	Customer customer =new Customer(userNametxt.getText(), passwordtxt.getText(), firstNametxt.getText(),
	    			lastNametxt.getText(), emailtxt.getText(), phoneNumbertxt.getText(), idtxt.getText()
	    			, credittxt.getText(), "customer");
	    	
	    	
	    	
	    	
	    	
	        message=new Message(MessageType.AddCustomer,customer);
	    	ClientUI.chat.accept(message);
	    	userNametxt.clear();
	    	passwordtxt.clear();
	    	firstNametxt.clear();
	    	lastNametxt.clear();
	    	emailtxt.clear();
	    	phoneNumbertxt.clear();
	    	idtxt.clear();
	    	credittxt.clear();
	    	lblCustomerMn.setText("");
    	}
    }

    @FXML
    void clickLogOut(ActionEvent event) throws IOException{
		Message message=new Message(MessageType.LogOut,LoginController.user);
		ClientUI.chat.accept(message);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/Login.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Login form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) logOutBtn.getScene().getWindow();
    }

}

