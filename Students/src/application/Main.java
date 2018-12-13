package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	public static Stage primaryStage;
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("Views/MenuScene.fxml"));
	        Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/Views/application.css").toExternalForm());
			primaryStage.setTitle("Danh s�ch");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
