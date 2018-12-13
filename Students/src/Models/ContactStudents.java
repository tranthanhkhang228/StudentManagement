package Models;

public class ContactStudents {
	private int ID;
	private String Name;
	private int Age;
	private String Address;

	public String getAddress() {
		return Address;
	}

	public void setAddress(String address) {
		Address = address;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getAge() {
		return Age;
	}

	public ContactStudents(int iD, String name, int age, String address) {
		ID = iD;
		Name = name;
		Age = age;
		Address = address;
	}

	public void setAge(int age) {
		Age = age;
	}

	public ContactStudents() {
	}

	@Override
	public String toString() {
		return "ContactStudents [ID=" + ID + ", Name=" + Name + ", Age=" + Age + ", Address=" + Address + "]";
	}


}
