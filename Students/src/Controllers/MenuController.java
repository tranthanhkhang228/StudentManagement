package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.Main;
import javafx.event.ActionEvent;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {
	public static Stage listStage;
	public static Stage searchStage;
	
	@FXML
	private Button btnList;
	
	@FXML
	private Button btnInformation;
	
	@FXML
	private Button btnSearch;
	
	@FXML
	private Button btnExit;
	
	public void ViewList(ActionEvent event) throws IOException {
		
		Parent ListScenefxml = FXMLLoader.load(getClass().getClassLoader().getResource("Views/ListScene.fxml"));
		Scene ListScene = new Scene(ListScenefxml);
		Stage ListStage = new Stage();
		ListStage.setScene(ListScene);
		ListStage.setTitle("Danh sách");
		
		ListStage.initModality(Modality.WINDOW_MODAL);
		ListStage.initOwner(Main.primaryStage);
		
		this.listStage = ListStage;
		
		ListStage.show();
		
	}

	public void SearchStudents(ActionEvent event) throws IOException {
		Parent SearchScenefxml = FXMLLoader.load(getClass().getClassLoader().getResource("Views/SearchScene.fxml"));
		Scene SearchScene = new Scene(SearchScenefxml);
		Stage SearchStage = new Stage();
		SearchStage.setScene(SearchScene);
		SearchStage.setTitle("Tim kiem");
		
		SearchStage.initModality(Modality.WINDOW_MODAL);
		SearchStage.initOwner(Main.primaryStage);
		
		this.searchStage = SearchStage;
		
		SearchStage.show();
	}
	
	public void EditorInfor(ActionEvent event) {
		
	}
	
	public void ExitApp(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resource) {
	}
}
