import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class databasecon {
	String userName;
	String password;
	Float Balance;
	Integer accno;
	int pin;
		public void databasecon(){
			try{
				Class.forName("com.mysql.jdbc.Driver");  
				Connection con=DriverManager.getConnection(  
				"jdbc:mysql://localhost:3306/bankingsystem","root","");    
				}
				catch(Exception e){ System.out.println(e);}  
				}
		public void updatestate(String statement){
			Connection con;
			try {
				con = DriverManager.getConnection(  
						"jdbc:mysql://localhost:3306/bankingsystem","root","");
				Statement stmt=con.createStatement();  
				ResultSet rs = stmt.executeQuery(statement); 
				while (rs.next()) {
					  userName = rs.getString("Username");
					  password = rs.getString("Password");
					}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}    
		}
		public String sendusername(){
			return this.userName;
		}
		public String senduserpass(){
			return this.password;
		}
		public void ministatement(String statement){
				Connection con;
				try {
					con = DriverManager.getConnection(  
							"jdbc:mysql://localhost:3306/bankingsystem","root","");
					Statement stmt=con.createStatement();  
					ResultSet rs = stmt.executeQuery(statement); 
					while (rs.next()) {
						  Balance = rs.getFloat("Balance");
						  accno = rs.getInt("accountno");
						}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}    
			}
		public Float getBalance(){
			return Balance;
		}
		public Integer accountno(){
			return accno;
		}
		}
		

