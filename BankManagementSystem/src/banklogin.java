import java.util.Random;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class banklogin extends Application {
	databasecon1 db = new databasecon1();
	
	int temp1,temp2,temp3,temp4,temp5;
	TextField t1,t3;
	PasswordField t2;
	Label l1,l2,l3;	Button b1;
	String t11,t12,t13,accholdname,t14,choice;

	private int rand;
	private int captchainput;
	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("static-access")
	@Override
	public void start(Stage arg0) throws Exception {
		randomgenerator();
		arg0.setTitle("Punjab National Bank");
		t1 = new TextField();
		t2 = new PasswordField();
		t3 = new TextField();
		l1 = new Label("Employee ID");
		l2 = new Label("Password");
		l3 = new Label("What is "+rand+"+5?");
		b1 = new Button("Login");
		t1.setPromptText("enter ID here");
		t2.setPromptText("enter password here");
		t3.setPromptText("enter captcha here");
		//arg0.addEventHandler(KeyCode, eventHandler);
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(20,20,20,20));
		gp.setConstraints(l1, 0, 0);
		gp.setConstraints(l2, 0, 1);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(t2, 1, 1);
		gp.setConstraints(b1, 1, 3);
		gp.setConstraints(l3, 0, 2);
		gp.setConstraints(t3, 1, 2);
		gp.setVgap(10);
		gp.setHgap(10);
		gp.getChildren().addAll(t1,t2,l1,l2,t3,l3,b1);
		b1.setOnAction(e->{login(t3,arg0);});
		Scene scene = new Scene(gp,400,400);
		arg0.setScene(scene);
		l1.requestFocus();
		arg0.show();
	}

	private void login(TextField node,Stage arg0) {
		captchainput= Integer.parseInt(node.getText());
		int empid = Integer.parseInt(t1.getText());
		String password = t2.getText();
		if(captchainput==(rand+5)){
			String statement = "SELECT * FROM employees WHERE empid='"+empid+"'";
			db.logincheck(statement);
			String passwordget = db.password();
			//System.out.println(passwordget);
			if(passwordget.equals(password)){
				loginscreen(arg0);
			}
			
			
		}
		else
		{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Wrong INFO");
			alert.setContentText("Please check your information again");
			alert.showAndWait();
			
		}
			
	}
	
	@SuppressWarnings("static-access")
	private void loginscreen(Stage arg0){
		Button openacc,closeacc,accsystem,transfer,withdrawal,deposit;
		arg0.setTitle("");
		openacc= new Button("Open account");
		closeacc =new Button("Close acoount");
		accsystem = new Button("Account System");
		transfer = new Button("Transfer");
		withdrawal = new Button("Withdrawal");
		deposit = new Button("Deposit");
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(openacc, 0, 0);
		gp.setConstraints(closeacc, 0, 1);
		gp.setConstraints(accsystem, 1, 0);
		gp.setConstraints(transfer, 1, 1);
		gp.setConstraints(withdrawal,2, 0);
		gp.setConstraints(deposit, 2, 1);
		gp.setHgap(10);
		gp.setVgap(10);
		gp.getChildren().addAll(openacc,closeacc,accsystem,transfer,withdrawal,deposit);
		Scene scene= new Scene(gp,450,300);
		arg0.setScene(scene);
		openacc.setOnAction(e-> {openacc();});
		closeacc.setOnAction(e->{closeacc();});
		accsystem.setOnAction(e->{modify();});
		withdrawal.setOnAction(e->{withdrawal();});
		deposit.setOnAction(e->{deposit();});
		transfer.setOnAction(e->{transfer();});
		arg0.show();
	}
	
	
	
	private void transfer() {
		Stage arg1 = new Stage();
		arg1.setTitle("Transfer money");
		TextField t1= new TextField();
		TextField t2= new TextField();
		TextField t3= new TextField();
		Label toaccno = new Label("Transfer From Account No.");
		Label fromaccno = new Label("Transfer To Account No");
		Label money = new Label("Enter amount");
		Button b1= new Button("Transfer");
		
		GridPane gp = new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(toaccno, 0, 0);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(fromaccno, 0, 1);
		gp.setConstraints(t2, 1, 1);
		gp.setConstraints(b1, 2, 3);
		gp.setConstraints(t3, 1, 2);
		gp.setConstraints(money, 0, 2);
		
		b1.setOnAction(e->{
			t11= t1.getText();
			t12=t2.getText();
			//t13= t3.getText();
			String statement1="SELECT Balance FROM customer WHERE accountno='"+t11+"'";
			temp1= Integer.parseInt(t3.getText());
			String cbal=db.getbal(statement1);
			temp2= Integer.parseInt(cbal);
			temp3=temp2-temp1;
			String statement2="SELECT Balance FROM customer WHERE accountno='"+t12+"'";
			String cbal1=db.getbal(statement2);
			temp4= Integer.parseInt(cbal1);
			temp5=temp4+temp1;
			t13= String.valueOf(temp3);
			t14= String.valueOf(temp5);
			String statement = "UPDATE customer SET Balance='"+t13+"' WHERE accountno ='"+t11+"' ";
			String statement4 = "UPDATE customer SET Balance='"+t14+"' WHERE accountno ='"+t12+"' ";
			db.withdrawal(statement);
			db.transfer(statement4);
		
		});
		
		gp.setHgap(10);
		gp.setVgap(10);
		gp.getChildren().addAll(toaccno,fromaccno,t1,t2,b1,money,t3);
		Scene scene= new Scene(gp,450,300);
		arg1.setScene(scene);
		arg1.show();
		
}
@SuppressWarnings("static-access")
	private void deposit() {
		Stage arg1 = new Stage();
		arg1.setTitle("Deposit money");
		Label accno = new Label("Account No.");
		Label money = new Label("Enter the amount");
		TextField t1= new TextField();
		TextField t2= new TextField();
		Button b1= new Button("Deposit");
		GridPane gp= new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(accno, 0, 0);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(b1, 1, 4);
		gp.setConstraints(money, 0, 1);
		gp.setConstraints(t2, 1, 1);
		b1.setOnAction(e->{
			t11= t1.getText();
			String statement1="SELECT Balance FROM customer WHERE accountno='"+t11+"'";
			temp1= Integer.parseInt(t2.getText());
			String cbal=db.getbal(statement1);
			temp2= Integer.parseInt(cbal);
			temp3=temp2+temp1;
			t12= String.valueOf(temp3);
			String statement = "UPDATE customer SET Balance='"+t12+"' WHERE accountno ='"+t11+"' ";
			db.withdrawal(statement);
		
		});
		gp.getChildren().addAll(accno,t1,money,t2,b1);
		gp.setVgap(10);
		gp.setHgap(10);
		Scene scene= new Scene(gp,450,300);
		arg1.setScene(scene);
		arg1.show();

	}

	@SuppressWarnings({ "static-access" })
	private void withdrawal() {
		Stage arg1 = new Stage();
		arg1.setTitle("Withdraw money");
		Label accno = new Label("Account No.");
		Label money = new Label("Enter the amount");
		TextField t1= new TextField();
		TextField t2= new TextField();
		Button b1= new Button("Withdraw");
		GridPane gp= new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(accno, 0, 0);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(b1, 1, 4);
		gp.setConstraints(money, 0, 1);
		gp.setConstraints(t2, 1, 1);
		b1.setOnAction(e->{
			t11= t1.getText();
			String statement1="SELECT Balance FROM customer WHERE accountno='"+t11+"'";
			temp1= Integer.parseInt(t2.getText());
			String cbal=db.getbal(statement1);
			temp2= Integer.parseInt(cbal);
			if(temp1>temp2){
				Alert alert= new Alert(AlertType.INFORMATION);
				alert.setHeaderText("INSUFFICIENT FUND ");
				alert.setContentText("invalid amount entered");
				alert.showAndWait();
			}
			else
				temp3=temp2-temp1;
			t12= String.valueOf(temp3);
			String statement = "UPDATE customer SET Balance='"+t12+"' WHERE accountno ='"+t11+"' ";
			db.withdrawal(statement);
		});
		gp.getChildren().addAll(accno,t1,money,t2,b1);
		gp.setVgap(10);
		gp.setHgap(10);
		Scene scene= new Scene(gp,450,300);
		arg1.setScene(scene);
		arg1.show();

		
	}

	@SuppressWarnings("static-access")
	private void modify() {
		Stage arg1= new Stage();
		arg1.setTitle("Modify Details");
		Label accno = new Label("Account No.");
		TextField t1= new TextField();
		Button b1= new Button("Edit");
		Label l1= new Label("Name");
		Label l2= new Label("PAN");
		Label l3= new Label("Mobile");
		Button b2= new Button("INSERT");
		TextField t2= new TextField();
		TextField t3= new TextField();
		TextField t4= new TextField();
		
		
		ChoiceBox <String> choicebox = new ChoiceBox();
		choicebox.getItems().addAll("Name","Pan","Mobile");
		choicebox.setValue("Name");
				
		GridPane gp= new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(accno, 0, 0);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(b1, 1, 4);
		gp.setConstraints(choicebox, 2, 0);
		gp.setConstraints(l1, 3, 8);
		gp.setConstraints(l2, 4, 8);
		gp.setConstraints(l3, 5, 8);
		gp.setConstraints(t2, 3, 9);
		gp.setConstraints(t3, 4, 9);
		gp.setConstraints(t4, 5, 9);
		gp.setConstraints(b2, 4, 10);
		l1.setVisible(false);
		l2.setVisible(false);
		l3.setVisible(false);
		t2.setVisible(false);
		t3.setVisible(false);
		t4.setVisible(false);
		b2.setVisible(false);
				
		b1.setOnAction(e->{
			t11= t1.getText();
			if(choicebox.getValue()=="Name"){
				l1.setVisible(true);
				t2.setVisible(true);
				b2.setVisible(true);
				choice="name";
			}
			
			if(choicebox.getValue()=="Pan"){
				l2.setVisible(true);
				t2.setVisible(true);
				b2.setVisible(true);
				choice="pan";
			}
			if(choicebox.getValue()=="Mobile"){
				l3.setVisible(true);
				t2.setVisible(true);
				b2.setVisible(true);
				choice="mobile";
			}
		});
		 b2.setOnAction(e-> {
			 t11=t1.getText();
			 t12=t2.getText();
     		 insert(t11, t12, choice);
     		 l1.setVisible(false);
     		 l2.setVisible(false);
     		 l3.setVisible(false);
     		 t2.setVisible(false);
     		 b2.setVisible(false);
     		 choice="";
		 });
		gp.getChildren().addAll(accno,t1,b1,choicebox,l1,l2,l3,t2,t3,t4,b2);
		gp.setVgap(10);
		gp.setHgap(10);
		Scene scene= new Scene(gp,800,600);
		arg1.setScene(scene);
		arg1.show();

	}

	private void insert(String data, String data1,String choice){
		
		String statement = "UPDATE customer SET "+choice+"='"+data1+"' WHERE accountno ='"+data+"' ";
		db.withdrawal(statement);

	}
	private void closeacc() {
		Stage arg1= new Stage();
		arg1.setTitle("Close Account Detail");
		Label accno = new Label("Account No.");
		TextField t1= new TextField();
		Button b1= new Button("Delete Account");
		GridPane gp= new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(accno, 0, 0);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(b1, 1, 4);
		b1.setOnAction(e->{
			t11= t1.getText();
			String statement = "DELETE FROM customer WHERE accountno ='"+t11+"' ";
			db.deleteacc(statement);
		});
		gp.getChildren().addAll(accno,t1,b1);
		gp.setVgap(10);
		gp.setHgap(10);
		Scene scene= new Scene(gp,450,300);
		arg1.setScene(scene);
		arg1.show();

		
		
	}

	private void openacc() {
		Stage arg1= new Stage();
		arg1.setTitle("New Account Details");
		Label name = new Label("Name");
		Label accno = new Label("Account No.");
		Label pan = new Label("PAN Card No.");
		Label mobile= new Label("Mobile NO.");
		TextField t1= new TextField();
		TextField t2= new TextField();
		TextField t3= new TextField();
		TextField t4= new TextField();
		Button b1= new Button("Create Account");
		GridPane gp= new GridPane();
		gp.setPadding(new Insets(10,10,10,10));
		gp.setConstraints(name, 0, 0);
		gp.setConstraints(accno, 0, 1);
		gp.setConstraints(pan, 0, 2);
		gp.setConstraints(mobile, 0, 3);
		gp.setConstraints(t1, 1, 0);
		gp.setConstraints(t2, 1, 1);
		gp.setConstraints(t3, 1, 2);
		gp.setConstraints(t4, 1, 3);
		gp.setConstraints(b1, 1, 4);
		/*String accholdname = t1.getText();
		System.out.println(accholdname);
		System.out.println(t11);
		String t12 = t3.getText();
		System.out.println(t12);
		String t13 = t4.getText();
		System.out.println(t13);
		int accholder = Integer.parseInt(t2.getText());
		int accholdno = Integer.parseInt(t11);
		int panholdno = Integer.parseInt(t12);
		int mobholdno = Integer.parseInt(t13);*/
		String ib="0";
		b1.setOnAction(e-> {
			accholdname= t1.getText();
			t11 = t2.getText();
			t12= t3.getText();
			t13= t4.getText();
			String statement="INSERT INTO customer VALUES('"+accholdname+"','"+t11+"','"+t12+"','"+t13+"','"+ib+"')";
			db.insertacc(statement);
		});
		gp.getChildren().addAll(name,accno,pan,mobile,t1,t2,t3,t4,b1);
		gp.setVgap(10);
		gp.setHgap(10);
		Scene scene= new Scene(gp,450,300);
		arg1.setScene(scene);
		arg1.show();

	}

	private void randomgenerator(){
		Random r = new Random();
		rand = r.nextInt(100);
	}
	
}
