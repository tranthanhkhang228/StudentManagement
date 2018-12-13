package Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAOs.ContactDAO;
import Models.ContactStudents;
import javafx.fxml.*;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

public class SearchController implements Initializable{
	@FXML
	private Button btnTimKiem;
	
	@FXML
	private TextField txtInputID;
	
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
	private TableView<ContactStudents> ResultTable;
	
	@FXML
	private TableColumn<ContactStudents, String> IDResult;
	
	@FXML
	private TableColumn<ContactStudents, String> NameResult;
	
	@FXML
	private TableColumn<ContactStudents, String> AgeResult;
	
	@FXML
	private TableColumn<ContactStudents, String> AddressResult;
	
	ContactDAO contact = new ContactDAO();
	public ObservableList<ContactStudents> getStudents(){
		List<ContactStudents> list = contact.getContacts();
		ObservableList<ContactStudents> students = FXCollections.observableArrayList(list);
		return students;
	}
	
	public void TimKiem(ActionEvent event) {
		int ID = Integer.parseInt(txtInputID.getText());
			ContactStudents student = contact.Search(ID);
			ObservableList<ContactStudents> list2 = FXCollections.observableArrayList(student);
			IDResult.setCellValueFactory(new PropertyValueFactory<>("ID"));
			NameResult.setCellValueFactory(new PropertyValueFactory<>("Name"));
			AgeResult.setCellValueFactory(new PropertyValueFactory<>("Age"));
			AddressResult.setCellValueFactory(new PropertyValueFactory<>("Address"));
			ResultTable.getItems().setAll(list2);
	}
	
	public void initialize(URL location, ResourceBundle resource) {
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		AgeColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
		AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
		ListTable.getItems().setAll(getStudents());
	}
	

}
