package controllers;


import javax.swing.JOptionPane;
import gui.ConnectToServerController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat;
	public static String machineCode;
	
	public static void main(String[] args) {
	    launch(args);
	  }

	@Override
	public void start(Stage primaryStage) throws Exception {
		ConnectToServerController aFrame = new ConnectToServerController(); // create Connect To server
		aFrame.start(primaryStage);
		

	}

	public static void display(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, msg);
	}
	
}
