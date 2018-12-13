package Controllers;

import javafx.collections.*;
import javafx.fxml.*;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import DAOs.ContactDAO;
import Models.ContactStudents;
import application.Main;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

public class ListController implements Initializable{
	public static Stage insertStage;
	public static Stage deleteStage;
	
	@FXML
	private TableView<ContactStudents> ListTable;
	
	@FXML
	private TableColumn<ContactStudents, String> IDColumn;
	
	@FXML
	private TableColumn<ContactStudents, String> NameColumn;
	
	@FXML
	private TableColumn<ContactStudents, String> AgeColumn;
	
	@FXML
	private TableColumn<ContactStudents, String> AddressColumn;
	
	@FXML
	private Button btnInsert;
	
	@FXML
	private Button btnDelete;
	
	ContactDAO contact = new ContactDAO();
	public ObservableList<ContactStudents> getStudents(){
		List<ContactStudents> list = contact.getContacts();
		ObservableList<ContactStudents> students = FXCollections.observableArrayList(list);
		return students;
	}
	
	public void InsertStudent(ActionEvent event) throws IOException {
		MenuController.listStage.close();
		
		Parent InsertScenefxml = FXMLLoader.load(getClass().getClassLoader().getResource("Views/InsertScene.fxml"));
		Scene InsertScene = new Scene(InsertScenefxml);
		Stage InsertStage = new Stage();
		InsertStage.setScene(InsertScene);
		InsertStage.setTitle("Danh sách");
		
		InsertStage.initModality(Modality.WINDOW_MODAL);
		InsertStage.initOwner(MenuController.listStage);
		
		this.insertStage = InsertStage;
		InsertStage.show();
	}
	
	public void DeleteStudent() throws IOException {
		MenuController.listStage.close();
		
		Parent DeleteScenefxml = FXMLLoader.load(getClass().getClassLoader().getResource("Views/DeleteScene.fxml"));
		Scene DeleteScene = new Scene(DeleteScenefxml);
		Stage DeleteStage = new Stage();
		DeleteStage.setScene(DeleteScene);
		DeleteStage.setTitle("Danh sách");
		
		DeleteStage.initModality(Modality.WINDOW_MODAL);
		DeleteStage.initOwner(MenuController.listStage);
		
		this.deleteStage = DeleteStage;
		DeleteStage.show();
	}
	
	public void initialize(URL location, ResourceBundle resource) {
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		AgeColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
		AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
		ListTable.getItems().setAll(getStudents());
	}
	
}
