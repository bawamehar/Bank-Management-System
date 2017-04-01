import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class atmlogin extends Application {
	private Float Balance;
	private int accno;
	private TextField username;
	private PasswordField password;
	private Button login;
	private Label captcha;
	private int random = 0;
	private int captchaentry;
	private TextField captchaenter;
	private void captcha(){
		Random rand = new Random();
		random = rand.nextInt(100) + 5;
	}
	databasecon d = new databasecon();
	public static void main(String[] args) {
			launch(args);
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void start(Stage arg0) throws Exception {
		captcha();
		arg0.setTitle("LOGIN SCREEN");
		captcha = new Label("What is "+random+" +1");
		captchaenter = new TextField();
		Label userlabel = new Label("Username: ");
		Label passlabel = new Label("Password: ");
		username = new TextField();
		password = new PasswordField();
		login = new Button("Login");
		username.setPromptText("Username");
		password.setPromptText("Password");
		captchaenter.setPromptText("Enter the Captcha");
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(10,10,10,10));
		grid.setConstraints(username, 5, 1);
		grid.setConstraints(password, 5, 2);
		grid.setConstraints(userlabel, 4, 1);
		grid.setConstraints(passlabel, 4, 2);
		grid.setConstraints(captcha, 4, 3);
		grid.setConstraints(captchaenter, 5,3);
		grid.setConstraints(login, 5, 5);
		grid.setMaxHeight(300);
		grid.setMaxWidth(300);
		grid.getChildren().addAll(username,password,userlabel,passlabel,captcha,captchaenter,login);
		Scene scene = new Scene(grid,300,300);
		arg0.setResizable(false);
		userlabel.requestFocus();
		arg0.setScene(scene);
		arg0.show();
		grid.addEventFilter(KeyEvent.KEY_PRESSED,e->{
			if(e.getCode()==KeyCode.ENTER){
				mainpage(arg0);
			}
		});
		login.setOnAction(e->{mainpage(arg0);});
	}
	
	private void login(Scene scene,Stage arg0){
		captchaentry = Integer.parseInt(captchaenter.getText());
		String user = username.getText();
		String pass = password.getText();
		d.databasecon();
		String statement = "SELECT * FROM custpass WHERE Username='"+user+"'";
		d.updatestate(statement);
		String checkuser = d.sendusername();
		String checkpass = d.senduserpass();
		if(captchaentry==(random+1)){
			if((checkuser.equals(user))&&(checkpass.equals(pass))){
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Login Success");
				alert.setHeaderText("Congrats! You have logged in successfully");
				alert.setContentText("");
				alert.showAndWait();
				arg0.setScene(scene);
				
			}}
			else{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Login Unsuccessful");
				alert.setHeaderText("Please check your login info");
				alert.setContentText("");
				alert.showAndWait();
			}
	}
	private void mainpage(Stage arg0){
		Button pinchange = new Button("PIN Change");
		Button benquiry = new Button("Balance Enquiry");
		Button mstatement = new Button("Mini Statement");
		Button bwithdrawal = new Button("Balance Withdrawal");
		Button transfer = new Button("Transfer");
		GridPane mainp = new GridPane();
		mainp.setVgap(10);
		mainp.setHgap(10);
		mainp.setConstraints(pinchange, 2, 2);
		mainp.setConstraints(bwithdrawal, 8,2);
		mainp.setConstraints(mstatement, 8, 3);
		mainp.setConstraints(benquiry, 2, 5);
		mainp.setConstraints(transfer, 8, 5);
		mainp.getChildren().addAll(pinchange,bwithdrawal,benquiry,transfer,mstatement);
		Scene scene = new Scene(mainp,350,300);
		arg0.requestFocus();
		arg0.setScene(scene);
		mstatement.setOnAction(e->{ministatement(arg0);});
		pinchange.setOnAction(e->{
			pinchange(arg0);
		});
	}
	private void pinchange(Stage arg0){
		TextField oldpin = new TextField();
		TextField newpin = new TextField();
		TextField confnewpin = new TextField();
		Button set = new Button();
		GridPane pinpage = new GridPane();
	}
	private void ministatement(Stage arg0){
		GridPane MiniStatement = new GridPane();
		MiniStatement.setPadding(new Insets(10,10,10,10));
		MiniStatement.setHgap(10);
		MiniStatement.setVgap(10);
		Button getministatement = new Button("Get Mini Statement");
		TextArea statement = new TextArea();
		Button back = new Button("Back");
		statement.setEditable(false);
		MiniStatement.setConstraints(getministatement, 5, 1);
		MiniStatement.setConstraints(statement, 5, 5);
		MiniStatement.getChildren().addAll(getministatement,statement);
		Scene scene2 = new Scene(MiniStatement,500,500);
		arg0.setScene(scene2);
		getministatement.setOnAction(e->{getMinistatement(statement);});
	}
	private void getMinistatement(TextArea ta){
		String uname = username.getText();
		String statement = "SELECT * FROM accounts WHERE Username='"+uname+"'";
		d.ministatement(statement);
		Balance = d.getBalance();
		accno =  d.accountno();
		ta.setText("Your Balance for Account no. :"+accno+"\n is "+Balance);
	}
}
