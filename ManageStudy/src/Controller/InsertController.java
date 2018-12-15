package Controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import DAO.StudentDAO;
import Model.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InsertController{
	private Stage InsertStage;
	
	private MenuController menu;

	private StudentDAO newsDAO = new StudentDAO();
	
	private String WorkingStatus;
	
	@FXML
	private TextField txtID;
	
	@FXML
	private TextField txtFirstName;
	
	@FXML
	private TextField txtLastName;
	
	@FXML
	private TextField txtClassName;
	
	@FXML
	private TextField txtAddress;
	
	@FXML
	private TextField txtNumberPhone;
	
	@FXML
	private ToggleGroup gender;
	
	@FXML
	private RadioButton rdbtnMale;
	
	@FXML
	private RadioButton rdbtnFemale;

	@FXML
	private DatePicker datepickerBirthday;
	
	@FXML
	private Button btnInsert;
	
	@FXML
	private Button btnCancel;
	
	public void setInsertStage(Stage InsertStage) {
		this.InsertStage = InsertStage;
	}
	
	public void setMenuController(MenuController menu) {
		this.menu = menu;
	}	
	
	public void setWorkingStatus(String workingstatus) {
		this.WorkingStatus = workingstatus;
	}
	
	//Get information
	public Student getStudent() {
		Student student = new Student();
		student.setID(txtID.getText());
		student.setFirstName(txtFirstName.getText());
		student.setLastName(txtLastName.getText());
		student.setClassName(txtClassName.getText());
		student.setAddress(txtAddress.getText());
		student.setNumberPhone(txtNumberPhone.getText());
		
		LocalDate birthday = datepickerBirthday.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		student.setBirthday(birthday.format(formatter));
		
		RadioButton rdbtn = (RadioButton) gender.getSelectedToggle();
		student.setGender(rdbtn.getText());
		return student;
	}
	
	//Insert a new student
	public void InsertStudent(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(menu.getMenuStage());
		alert.initModality(Modality.WINDOW_MODAL);
		alert.setTitle("Notification!");
		
		if(WorkingStatus.equals("Database")) {
			InsertStage.close();
			if(newsDAO.InsertStudent(getStudent()) == 1) {
				menu.getListStudentFromDatabase().add(getStudent());
				alert.setHeaderText("Succeeded!");
				alert.setContentText("You have inserted a new student!");
			} else {
				alert.setHeaderText("Failed!");
				alert.setContentText("Can not insert a new student!");
			}
			alert.show();
		}
		else {
			menu.getListStudentFromXML().add(getStudent());
			InsertStage.close();
			alert.setHeaderText("Succeeded!");
			alert.setContentText("You have inserted a new student!");
			alert.show();
		}
	}
	
	//Close insert stage
	public void Cancel(ActionEvent event) {
		InsertStage.close();
	}
}
