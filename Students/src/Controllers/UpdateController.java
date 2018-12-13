package Controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import DAOs.ContactDAO;
import Models.ContactStudents;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class UpdateController implements Initializable {
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
	private TextField txtInputID;

	@FXML
	private TextField txtID;

	@FXML
	private TextField txtName;
	
	@FXML
	private TextField txtAge;
	
	@FXML
	private TextField txtAddress;
	
	@FXML
	private Button btnTim;
	
	@FXML
	private Button btnUpdate;

	ContactDAO contact = new ContactDAO();

	public ObservableList<ContactStudents> getStudents() {
		List<ContactStudents> list = contact.getContacts();
		ObservableList<ContactStudents> students = FXCollections.observableArrayList(list);
		return students;
	}

	public void initialize(URL location, ResourceBundle resource) {
		IDColumn.setCellValueFactory(new PropertyValueFactory<>("ID"));
		NameColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
		AgeColumn.setCellValueFactory(new PropertyValueFactory<>("Age"));
		AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
		ListTable.getItems().setAll(getStudents());
	}
	
	int ID = Integer.parseInt(txtInputID.getText());
	ContactStudents student = contact.Search(ID);
	public void SearchStudent() {
		txtID.setText(String.valueOf(student.getID()));
		txtName.setText(student.getName());
		txtAge.setText(String.valueOf(student.getAge()));
		txtAddress.setText(student.getAddress());
	}
	int newID = Integer.parseInt(txtInputID.getText());
	String newName = txtName.getText();
	int newAge = Integer.parseInt(txtAge.getText());
	String newAddress = txtAddress.getText();
	
	public void UpdateStudent() {
		if(student.getID() != newID){
		
		}
	}
}
