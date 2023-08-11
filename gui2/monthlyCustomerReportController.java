package gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import controllers.ReportController;
import entity.CustomerReport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * @author sara asaad , mhemad mdah
 *
 */
public class monthlyCustomerReportController implements Initializable{
    @FXML
    private Button btnBack;

    @FXML
    private CategoryAxis customerName;

    @FXML
    private BarChart<?, ?> monthlyReport;

    @FXML
    private NumberAxis numOfOrders;
    
    @FXML
    private Label lblCustomers;
    

    @FXML
    private Label lblSubscriber;

    @FXML
    private Label lblPercentage;
    
    Stage stage;
    Scene scene;
    
    
    /**
     * @param event
     * @throws IOException
     *  goes back to the page that was before
     */
    @FXML
    void ClickBack(ActionEvent event) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/ViewMonthlyReport.fxml"));
		stage = (Stage)((Node) event.getSource()).getScene().getWindow();
		scene = new Scene(root);
		stage.setTitle("Monthly Report Form");
		stage.setScene(scene);
		stage.show();
		Stage stage = (Stage) btnBack.getScene().getWindow();
    }
    
    
	/**
	 * initialize the page with required data
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		XYChart.Series set1 = new XYChart.Series<>();
		
		for(CustomerReport report :ReportController.report) {
			set1.getData().add(new XYChart.Data(report.getCustomerName(),report.getNumOfOrders()));
		}
		
		monthlyReport.getData().addAll(set1);
		
		lblCustomers.setText(ReportController.label[0]);
		lblSubscriber.setText(ReportController.label[1]);
		double percentage = (int)Double.parseDouble(ReportController.label[1]) /Integer.parseInt(ReportController.label[0]) * 100; 
		lblPercentage.setText(percentage + "%");
	}
}
