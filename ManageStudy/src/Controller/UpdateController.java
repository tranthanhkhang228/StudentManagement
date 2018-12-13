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

public class UpdateController {
	private Stage UpdateStage;
	
	private MenuController menu;

	private StudentDAO newsDAO = new StudentDAO();
	
	private String tempID;
	
	private Student tempStudent;
	
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
	private Button btnUpdate;
	
	@FXML
	private Button btnCancel;
	
	public void setUpdateStage(Stage UpdateStage) {
		this.UpdateStage = UpdateStage;
	}
	
	public void setMenuController(MenuController menu) {
		this.menu = menu;
	}
	
	//Set student for form from Student Table
	public void setStudent(Student student) {
		this.tempStudent = student;
		tempID = tempStudent.getID();
		
		
		txtID.setText(tempStudent.getID());
		txtFirstName.setText(tempStudent.getFirstName());
		txtLastName.setText(tempStudent.getLastName());
		txtClassName.setText(tempStudent.getClassName());
		
		String pattern = "dd/MM/yyyy";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		datepickerBirthday.setValue(LocalDate.parse(tempStudent.getBirthday(), formatter));
		
		txtAddress.setText(tempStudent.getAddress());
		
		if(student.getGender().equals("Male")) {
			rdbtnMale.setSelected(true);
		}
		else {
			rdbtnFemale.setSelected(true);
		}
		
		txtNumberPhone.setText(tempStudent.getNumberPhone());
	}
	
	//Get student from form
	public Student getStudent() {
		tempStudent.setID(txtID.getText());
		tempStudent.setFirstName(txtFirstName.getText());
		tempStudent.setLastName(txtLastName.getText());
		tempStudent.setClassName(txtClassName.getText());
		tempStudent.setAddress(txtAddress.getText());
		tempStudent.setNumberPhone(txtNumberPhone.getText());
		
		LocalDate birthday = datepickerBirthday.getValue();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		tempStudent.setBirthday(birthday.format(formatter));
		
		RadioButton rdbtn = (RadioButton) gender.getSelectedToggle();
		tempStudent.setGender(rdbtn.getText());
		return tempStudent;
	}
	
	//Update student
	public void UpdateStudent(ActionEvent event) {
		UpdateStage.close();
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.initOwner(menu.getMenuStage());
		alert.initModality(Modality.WINDOW_MODAL);
		alert.setTitle("Notification!");
		
		if(newsDAO.UpdateStudent(getStudent(),tempID) == 1) {
			alert.setHeaderText("Succeeded!");
			alert.setContentText("You have updated student!");
		} else {
			alert.setHeaderText("Failed!");
			alert.setContentText("Can not update student!");
		}
		alert.show();
	}
	
	//Close update stage
	public void Cancel(ActionEvent event) {
		UpdateStage.close();
	}
}
