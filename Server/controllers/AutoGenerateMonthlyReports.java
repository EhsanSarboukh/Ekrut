package controllers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.TimerTask;

import DBControl.DBConnector;

/**
 *
 *  daemon class, runs on the server and generates monthly reports if needed
 * @author Ehsan,Mhemad,Sabeel,Sara,Adham,Daniella
 * @version  V1.00  2022
 */

public class AutoGenerateMonthlyReports extends TimerTask {

    @Override
    public void run() {//thread start running
        Connection dbConn = DBConnector.conn;
        /*caculates the relevant date of the month so it will generate the relevant reports*/
        LocalDate nowDate = LocalDate.now();
        int year = nowDate.getYear();
        int month = 0;
        int Day=0;
        //check if its the beginning of the month and caculate the relevant month
        if(nowDate.getDayOfMonth()==1){//started new month!
            if(nowDate.getMonthValue()==1){
                month = 12;
            }else {
                month = nowDate.getMonthValue()-1;
            }
            	if(month==2) 
            		Day=28;
            	else if(month==4|| month==6 || month==9 || month==11)
            		Day=30;
            	else
            		Day=31;
            try{
                /*check if reports been generated for that month*/
                String SQLmonth = "SELECT orderDetails FROM monthlyorderreports WHERE  month(month) ="+month+";";
                ResultSet rs4 = dbConn.createStatement().executeQuery(SQLmonth);
                rs4.beforeFirst();
                while(rs4.next()){
                    if(month==rs4.getInt("month"));
                    return;
                }
                String SQL1 = "SELECT deviceId FROM devicemanagement;";
                ResultSet rs1 = dbConn.createStatement().executeQuery(SQL1);
                rs1.beforeFirst();
                while(rs1.next()){
                    String SQL = "SELECT orderDetails FROM orders WHERE orderDate between'"+year+"/"+month+"/1' AND '"+year+"/"+month+"/"+Day+"' AND deviceId ='"+rs1.getString("deviceId")+"';";
 	                ResultSet rs = dbConn.createStatement().executeQuery(SQL);
 	                rs.beforeFirst();
 	                String orderReport = "";
 	                int count = 0;
 	                while(rs.next()){
                       orderReport =orderReport+ "\nOrder number " + rs.getString("orderDetails");
                       count++;
                    }
 	               String SQL2 = "SELECT status FROM orders WHERE orderDate between'"+year+"/"+month+"/1' AND '"+year+"/"+month+"/"+Day+"' AND deviceId ='"+rs1.getString("deviceId")+"';";
	                ResultSet rs2 = dbConn.createStatement().executeQuery(SQL2);
	                rs2.beforeFirst();
 	                int approved = 0;
 	                int canceled = 0;
 	                int WaitingStatus=0;
 	                while(rs2.next()){
 	            	   if(rs2.getString(1).equals("Approved"))
 	            		   approved++;
 	            	   else if(rs2.getString(1).equals("Canceled"))
 	            		   canceled++;
 	            	   else if(rs2.getString(1).equals("Waiting for delivery")){
 	            		  WaitingStatus++;
 	            	   }
 	                }
 	                String SQL3 = "SELECT SUM(price) FROM orders WHERE orderDate between'"+year+"/"+month+"/1' AND '"+year+"/"+month+"/"+Day+"' AND status = 'Approved' AND deviceId ='"+rs1.getString("deviceId")+"';";
	                ResultSet rs3 = dbConn.createStatement().executeQuery(SQL3);
	                rs3.beforeFirst();
	                double profits=0;
	                while(rs3.next()){
	                	profits=rs3.getDouble(1);
	                }
                    //insert orders report to DB
                    String SQLin = "INSERT INTO monthlyorderreports Values ('"+rs1.getString("deviceId")+"','"+orderReport+
                    		"','"+profits+"','"+approved+"','"+canceled+"','"+month+"','"+year+"','"+WaitingStatus+"');";
                    dbConn.createStatement().execute(SQLin);
                 
                    
                }     
                //generate customer report
                String sqlCustomer = "SELECT customerUsername , COUNT(customerUsername) FROM orders WHERE orderDate BETWEEN '"+year+"/"+month+"/1' AND '"+year+"/"+month+"/"+Day+"' GROUP BY customerUsername;";
                ResultSet rsCustomer = dbConn.createStatement().executeQuery(sqlCustomer);
                rsCustomer.beforeFirst();
                while(rsCustomer.next()) {
                	String sqlReport = "INSERT INTO monthlycustomerreport Values ('"+rsCustomer.getString("customerUsername")+"','"+rsCustomer.getInt("COUNT(customerUsername)")+"','"+month+"','"+year+"');";
                	dbConn.createStatement().execute(sqlReport);
                }
                //generate stock report
                String SQL10 = "SELECT deviceId,location FROM devicemanagement;";
                ResultSet rs10 = dbConn.createStatement().executeQuery(SQL10);
                rs10.beforeFirst();
                while(rs10.next()){
                	int Bamba=0;
                    int Klik=0;
                    int CocaCola=0;
                    int SevenUp=0;
                    int Katkatat=0;
                    int amount=0;
                    String SQL5 = "SELECT * FROM stockupdates WHERE month = " + month + " AND year = " + year + " AND deviceId = " + rs10.getInt("deviceId")+ " ;";
                    ResultSet rs5 = dbConn.createStatement().executeQuery(SQL5);
                    rs5.beforeFirst();
	                while(rs5.next()){
		                amount=rs5.getInt("amount");
		                if (rs5.getString("product").equals("Bamba"))
		                	Bamba=amount;
		                else if (rs5.getString("product").equals("Klik"))
		                	Klik=amount;
		                else if (rs5.getString("product").equals("CocaCola"))
		                	CocaCola=amount;
		                else if (rs5.getString("product").equals("SevenUp"))
		                	SevenUp=amount;
		                else 
                    		Katkatat=amount;
	                }
                    String SQLinstock = "INSERT INTO monthlystockreport Values ('" + rs10.getInt("deviceId") + "','" + rs10.getString("location") + "','" + Bamba +"','" + Klik + "','" + CocaCola + "','" + SevenUp +
                    		"','" + Katkatat + "','" + month + "','" + year + "');";
                    dbConn.createStatement().execute(SQLinstock);
                }
            }catch(SQLException e){

            }

        }

    }
}
