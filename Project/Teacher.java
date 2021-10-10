package Project;

import java.util.ArrayList;
import java.util.List;

// Trida definujici ucitele
public class Teacher extends Person {

	private List<Integer> listOFStudents;
	private double salary;
	private int salaryPerStudent = 200;
	private int studCount;
		
	public Teacher (String name, String surname, String birthDate) {
		
		super(name,surname,birthDate);
		listOFStudents = new ArrayList<>();
	}

	public void addStudentToList(int ID) {
		listOFStudents.add(ID);
		this.studCount++;
		this.salary = this.salary + this.salaryPerStudent; // Zaroven setter pro salary a salaryPS
	}
	
	// Ziskava list studentu ucitele 
	public List<Integer> getListOFStudents() {
		return listOFStudents; 
	}

	// Ziskava plat ucitele
	public double getSalary() {
		return salary;
	}
		 
	public int getStudentCount() {
		this.studCount = listOFStudents.size();
		return this.studCount;
	}
	
	 public void addTeacherBonus (int count) {
		 this.salary = salary + (200*count);
		 
	 }
	 
	 public String toString() {	 
		 return "\nID: " + getID() + " - Uèitel"  + " \nJméno: " + getName() + " \nPøíjmení: " + getSurname() + " \nDatum narozeni: " + getBirthDate() + "\n";
	    }
	
	 
}



