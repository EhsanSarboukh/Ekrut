package gui;

import java.io.IOException;

import controllers.ClientUI;
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
import javafx.stage.Stage;
import javafx.scene.chart.PieChart;


public class ShowStockReportController  {
	
	@FXML
	private Button btnBack;
	
	@FXML
	private Label Bambalbl;
	
	@FXML
	private Label Kliklbl;
	
	@FXML
	private Label CocaColalbl;
	
	@FXML
	private Label SevenUplbl;
	
	@FXML
	private Label Katkatatlbl;
	
	@FXML
	private Label MostUpdatedlbl;
	
	@FXML
	private PieChart productspiechart;

	public static Integer[] products = new Integer[5];

	
	Integer max=0;
	int i=0;
	int count=1;
	String[] productname= new String[5];
	String result="";

	
	/**
	 * @author adham
	 * @param event
	 * @throws IOException
	 * When click on back button the application will open the window of "View Monthly Report"
	 */
	@FXML
	public void ClickBack(ActionEvent event) throws IOException {
		((Node)event.getSource()).getScene().getWindow().hide();
		Parent root = FXMLLoader.load(getClass().getResource("ViewMonthlyReport.fxml"));
		Stage primaryStage = new Stage();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Monthly Report");
		primaryStage.setScene(scene);		
		primaryStage.show();

	}
	//public ViewMonthlyReportController viewMonthlyReportController;
	public void start() throws IOException {

	}
	
	
	/**
	 * @author adham
	 * get and put in products array the amount of every product in the relevant device,month and year. return string of the most wanted products
	 * in the end it show the data in the window of "Show Report" 
	 */
	public void GetStockReportDetails () {
		Integer[] stockReport = new Integer[3];
		stockReport[0]=Integer.parseInt(ViewMonthlyReportController.deviceCode);
		stockReport[1]=Integer.parseInt(ViewMonthlyReportController.month);
		stockReport[2]=Integer.parseInt(ViewMonthlyReportController.year);
		Message message=new Message(MessageType.GetStockReportDetails,stockReport);
		ClientUI.chat.accept(message);
		productname[0]="Bamba";
		productname[1]="Klik";
		productname[2]="CocaCola";
		productname[3]="SevenUp";
		productname[4]="Katkatat";
		result = productname[0];
		max = products[0];
		for (i=1;i<5;i++)
		{
			if (max.equals(products[i]))
			{
				if(count==3)
				{
					result = result + "\n" + productname[i];
					count++;
				}
				else
				{
					result = result + " , " + productname[i];
					count++;
				}
					
			}
			else if (products[i].compareTo(max) > 0)
			{
				result = productname[i];
				max = products[i];
				count=1;
			}
			
		}
		Bambalbl.setText(products[0].toString());
		Kliklbl.setText(products[1].toString());
		CocaColalbl.setText(products[2].toString());
		SevenUplbl.setText(products[3].toString());
		Katkatatlbl.setText(products[4].toString());
		MostUpdatedlbl.setText(result);
					
	}

	/**
	 * @author adham
	 * its to display the products in pie chart 
	 */
	public void initialize() {
		// TODO Auto-generated method stub
		GetStockReportDetails ();
		if (!products[0].equals(0))
			productspiechart.getData().add(new PieChart.Data("Bamba",products[0] ));
		if (!products[1].equals(0))
			productspiechart.getData().add(new PieChart.Data("Klik", products[1]));
		if (!products[2].equals(0))
			productspiechart.getData().add(new PieChart.Data("CocaCola", products[2]));
		if (!products[3].equals(0))
			productspiechart.getData().add(new PieChart.Data("SevenUp", products[3]));
		if (!products[4].equals(0))
			productspiechart.getData().add(new PieChart.Data("Katkatat", products[4]));
	}

}
