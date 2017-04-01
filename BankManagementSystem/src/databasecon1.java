import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class databasecon1 {
	Integer empid;
	String password;
	
	public String getbal(String statement){
		String a = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root",""); 
			Statement stmt = con.createStatement();
			ResultSet rs= stmt.executeQuery(statement);
			while(rs.next()){
				a= rs.getString("Balance");
				}
			return a;
		}
		
			catch(Exception e){ System.out.println(e);}
		return statement;  
		
	}
	public void withdrawal(String statement){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root",""); 
			System.out.println(statement);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(statement);
			System.out.println("Executed Success");
			
			}
			catch(Exception e){ System.out.println(e);}  
	}
	
	public void transfer (String statement4){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root",""); 
			System.out.println(statement4);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(statement4);
			System.out.println("Executed Success");
			
			}
			catch(Exception e){ System.out.println(e);}  
	}
	
	public void insertacc(String statement){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root",""); 
			System.out.println(statement);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(statement);
			System.out.println("Executed Success");
			
			}
			catch(Exception e){ System.out.println(e);}  
	}
	
	public void deleteacc(String statement){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root",""); 
			System.out.println(statement);
			Statement stmt = con.createStatement();
			stmt.executeUpdate(statement);
			System.out.println("Executed Success");
			
			}
			catch(Exception e){ System.out.println(e);}  
	}
	public void logincheck(String statement){
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root",""); 
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(statement);
			while(rs.next()){
				empid = rs.getInt("empid");
				String name = rs.getString("Name");
				password = rs.getString("Password");
			}
			}
			catch(Exception e){ System.out.println(e);}  
			}
	public String password(){
		return password;
	}
	public int empid(){
		return empid;
	}
	public void databasecon1()  {
		try{
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/bankingsystem","root","");    
			}
			catch(Exception e){ System.out.println(e.getMessage());}  
			}
			
}
