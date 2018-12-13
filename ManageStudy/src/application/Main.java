package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

import Controller.LoginController;

public class Main extends Application {
	AnchorPane LoginLayout;

	@Override
	public void start(Stage primaryStage) {
		initLoginLayout();
	}

	/* Initialize Login Stage */
	public void initLoginLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getClassLoader().getResource("View/LoginView.fxml"));

			LoginLayout = (AnchorPane) loader.load();

			Scene loginscene = new Scene(LoginLayout);
			
			Stage LoginStage = new Stage();
			LoginStage.setScene(loginscene);
			LoginStage.setTitle("Log In");
			LoginStage.show();
			
			LoginController controller = loader.getController();
			controller.setLoginStage(LoginStage);

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
