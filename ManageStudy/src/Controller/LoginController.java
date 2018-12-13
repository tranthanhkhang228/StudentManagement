package Controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {	
	/* Username and Password for Administration */
	private Stage LoginStage;
	
	private final String adminuser = "TranThanhKhang";
	private final String adminpass = "2281999";

	@FXML
	private TextField txtUsername;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private Button btnLogIn;
	
	public void setLoginStage(Stage loginstage) {
		this.LoginStage = loginstage;
	}
	
	/* Check username and password (empty, correct) */
	public int checkLogin() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		int temp = 10;

		if ((username.isEmpty()) || (password.isEmpty())) {
			temp = 1; /* username or password is empty */
		} else {
			if ((username.equals(adminuser)) && (password.equals(adminpass))) {
				temp = 0; /* username or password is incorrect */
			}
		}

		return temp;
	}

	/* Log in Manage Student System */
	public void LogIn(ActionEvent event) {
		switch (checkLogin()) {
		case 1: {
			Alert erroralert = new Alert(AlertType.ERROR);
			erroralert.setTitle("Error");
			erroralert.setHeaderText("Username or Password is empty!");
			erroralert.setContentText("Please fill out this form!");
			erroralert.show();
			break;
		}
		case 10: {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Warning");
			alert.setHeaderText("Username or Password is incorrect!");
			alert.setContentText("Please try again!");
			alert.show();
			break;
		}
		case 0: {
			LoginStage.close();
			initMenuLayout();
			break;
		}
		}
	}

	/* Initialize Menu Stage */
	public void initMenuLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getClassLoader().getResource("View/MenuView.fxml"));

			AnchorPane MenuLayout = (AnchorPane) loader.load();

			Scene menuscene = new Scene(MenuLayout);
			
			Stage MenuStage = new Stage();
			MenuStage.setScene(menuscene);
			MenuStage.setTitle("Manage Student");
			MenuStage.show();
			
			MenuController controller = loader.getController();
			controller.setMenuStage(MenuStage);
			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	
}
