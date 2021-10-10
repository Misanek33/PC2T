package Project;


// Trida, ktera definuje cloveka
public abstract class Person {

	 // Definovani zakladnich parametru
	 private int ID;
	 private String name;
	 private String surname;
	 private String birthDate;
	 
	 public Person(String name, String surname, String birthDate) {
		 this.name = name;
		 this.surname = surname;
		 this.birthDate = birthDate;		 
	 }
	 
	 public int getID() {
		 return ID;
	 }
	 
	 public void setID (int ID) {
		 this.ID = ID;
	 }
	 	 
	 public String getName() {
		 return name;
	 } 
	 
	 
	 public String getSurname() {
		 return surname;
	 }
	 
	 /*
	 public String getFullName() {
		 return name + " " + surname;
	 }
	 */
	 
	 public String getBirthDate() {
		 return birthDate;
	 }
}
