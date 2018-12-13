package Model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Student {

	private final StringProperty ID;
	private final StringProperty FirstName;
	private final StringProperty LastName;
	private final StringProperty ClassName;
	private final StringProperty Birthday;
	private final StringProperty Address;
	private final StringProperty Gender;
	private final StringProperty NumberPhone;

	public Student() {
		this.ID = new SimpleStringProperty();
		this.FirstName = new SimpleStringProperty();
		this.LastName = new SimpleStringProperty();
		this.ClassName = new SimpleStringProperty();
		this.Birthday = new SimpleStringProperty();
		this.Address = new SimpleStringProperty();
		this.Gender = new SimpleStringProperty();
		this.NumberPhone = new SimpleStringProperty();
	}

	public Student(String iD, String firstName, String lastName, String className, String birthday, String address,
			String gender, String numberPhone) {
		super();
		this.ID = new SimpleStringProperty(iD);
		this.FirstName = new SimpleStringProperty(firstName);
		this.LastName = new SimpleStringProperty(lastName);
		this.ClassName = new SimpleStringProperty(className);
		this.Birthday = new SimpleStringProperty(birthday);
		this.Address = new SimpleStringProperty(address);
		this.Gender = new SimpleStringProperty(gender);
		this.NumberPhone = new SimpleStringProperty(numberPhone);
	}

	public String getID() {
		return this.ID.get();
	}

	public void setID(String id) {
		this.ID.set(id);
	}

	public StringProperty IDProperty() {
		return this.ID;
	}

	public String getFirstName() {
		return this.FirstName.get();
	}

	public void setFirstName(String firstName) {
		this.FirstName.set(firstName);
	}

	public StringProperty FirstNameProperty() {
		return this.FirstName;
	}

	public String getLastName() {
		return this.LastName.get();
	}

	public void setLastName(String lastName) {
		this.LastName.set(lastName);
	}

	public StringProperty LastNameProperty() {
		return this.LastName;
	}

	public String getClassName() {
		return this.ClassName.get();
	}

	public void setClassName(String className) {
		this.ClassName.set(className);
	}

	public StringProperty ClassNameProperty() {
		return this.ClassName;
	}

	public String getBirthday() {
		return this.Birthday.get();
	}

	public void setBirthday(String birthday) {
		this.Birthday.set(birthday);
	}
	
	public StringProperty BirthdayProperty() {
		return this.Birthday;
	}

	public String getAddress() {
		return this.Address.get();
	}

	public void setAddress(String address) {
		this.Address.set(address);
	}
	
	public StringProperty AddressProperty() {
		return this.Address;
	}

	public String getGender() {
		return this.Gender.get();
	}

	public void setGender(String gender) {
		this.Gender.set(gender);
	}
	
	public StringProperty GenderProperty() {
		return this.Gender;
	}

	public String getNumberPhone() {
		return this.NumberPhone.get();
	}

	public void setNumberPhone(String numberPhone) {
		this.NumberPhone.set(numberPhone);
	}
	
	public StringProperty NumberPhoneProperty() {
		return this.NumberPhone;
	}

	@Override
	public String toString() {
		return "Student [ID=" + ID + ", FirstName=" + FirstName + ", LastName=" + LastName + ", ClassName=" + ClassName
				+ ", Birthday=" + Birthday + ", Address=" + Address + ", Gender=" + Gender + ", NumberPhone="
				+ NumberPhone + "]";
	}
	
	
}
