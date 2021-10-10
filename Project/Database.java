package Project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import Project.DB.DBConnection;
import Project.DB.Operations.Delete;
import Project.DB.Operations.Insert;

public class Database {

	Map<Integer,Person> people = new HashMap<>();
	private int ID;
	
	public int maxID() {
		return Collections.max(people.keySet()) + 1;
	}
	
	// a) Metoda pro pridani cloveka do databaze
	public void addPersonToDatabase (String name, String surname, String birthDate, boolean isStudent) {
		 
		if(isStudent) {
			Scanner sc = new Scanner(System.in);
			people.put(ID, new Student(name, surname, birthDate));
			printTeachers();
			System.out.print("Zadejte ID k jakému uèiteli student patøí: ");
			int id = RunApp.integers(sc); 
			Teacher addStudent = (Teacher) people.get(id);
			addStudent.addStudentToList(ID);
			
			Student setID = (Student) people.get(ID);
			setID.setID(ID);
		}
		else {
			people.put(ID, new Teacher(name, surname, birthDate));
			Teacher setID = (Teacher) people.get(ID);
			setID.setID(ID);
		}
		ID++;
	}
	
	// Metoda pro vypsani ucitelu do menu
	public void printTeachers() {
		int i;
		for(i = 0; i < maxID(); i++) {
			if (people.get(i) instanceof Teacher) {
				System.out.println(people.get(i));
			}
		}
	}
	
	// Metoda pro vypsani studentu do menu
	public void printStudents() {
		int i;
		for(i = 0; i < maxID(); i++) {
			if (people.get(i) instanceof Student) {
				System.out.println(people.get(i));
			}
		}
	}
	
	// Pridani studenta k vyucujicimu 
	public void addStudent(int id, int tid) {
		Teacher list = (Teacher) people.get(tid);
		list.addStudentToList(id);
	}
	
	// Nastaveni znamky studentovi, zaroven kontrola vstupu
	public void setGradeToStudent (int id, Scanner sc) {
		
		Student addGrade = (Student) people.get(id);
		double gradeA;
		System.out.print("Kolik známek budete chtít zadat?: ");
		int i = RunApp.positiveNumbers(sc);
		for (int a = 0; a < i; a++) {
			
			boolean inputCheck = false;
			while(!inputCheck) {
				System.out.print("Zadejte známku: ");
				gradeA = RunApp.numbers(sc);
				if (gradeA >= 1 && gradeA <= 5) {
					addGrade.addGrade(gradeA);
					inputCheck = true;
				}
				else {
					System.out.println("Chyba, nezadali jste èíslo. ");
				}
			}
		}
	}
	
	// Update platu ucitele
	public void updateTeachersSalary() {
		
		int counter = 0;
		for (int i = 0; i < maxID(); i++) {
			
			// Kontrola jestli je student v seznamu (podle ID studenta)
			if(people.get(i) instanceof Teacher) {
				List<Integer> test = ((Teacher) people.get(i)).getListOFStudents();
				
				for (int a = 0; a < maxID(); a++) {
					
					if (people.get(a) instanceof Student && test.contains(a)) {
						
						if(((Student) people.get(a)).getScholarship() != 0) {
							counter ++;
						}
					}
				}
				
				// Pokud student dostane stipendum pricte se bonus uciteli
				if (counter != 0) { 
					((Teacher) people.get(i)).addTeacherBonus(counter);
				}	
			}
		}
	}
	
	// Odebrani osoby z databaze, update platu ucitele
	public void removePerson(int id) {
		
		for(int i = 0; people.get(i) instanceof Teacher; i++) {
			
			if(((Teacher) people.get(i)).getListOFStudents().contains(id)) {
				Teacher list = (Teacher) people.get(i);
				list.getListOFStudents().remove(id);
			}
		}
		people.remove(id);
	}
	
	// Vypsani ucitelu daneho studenta
	public void printTeachersOfStudent(int ID) {
		
		for (int i = 0; i < ID; i++) {
			
			if (people.get(i) instanceof Teacher) {
				Teacher list = (Teacher) people.get(i);
				for (int j = 0; j < list.getListOFStudents().size(); j++) {
					
					if (list.getListOFStudents().get(j) == ID) {
						System.out.println("Výpis uèitelù u vybraného studenta: \n");
						System.out.println(people.get(i));
					}
				}
			}
		}
	}
	
	// Vypsani podle ID
	public void printByID(int ID) {
		
		if(people.containsKey(ID)) {
			System.out.println(people.get(ID));
		}
		else {
			System.out.println("Hledaná osoba nebyla nalezena v databázi.");
		}
	}
	
	// Odebrani studenta od daneho vyucujiciho
	public void removeStudentFromTeacher (int id, Scanner sc) {
		
		for (int i = 0; i < maxID(); i++) {
			
			if (people.get(i) instanceof Teacher && ((Teacher) people.get(i)).getListOFStudents().contains(id)) {
				printByID(i);
			}
		}		
		System.out.println("Zadejte ID uèitele, kterému chcete odebrat studenta: \n");
		int i = RunApp.integers(sc);
		
		if (people.get(i) instanceof Teacher && ((Teacher) people.get(i)).getListOFStudents().contains(id)) {
			Teacher list = (Teacher) people.get(i);
			list.getListOFStudents().remove(Integer.valueOf(id));
		}
		else {
			System.out.println("Zadané ID nemá pøiøazeného studenta. \n");
		}
		
	}
	
	// Vypsani ucitelu podle poctu studentu
	public void printByNumberOfStudents() {
		
		List<Teacher> databaseT = new ArrayList<>();
		for (int i = 0; i < maxID(); i++) {
			
			if (people.get(i) instanceof Teacher) {
				Teacher teacher = (Teacher) people.get(i);
				databaseT.add(teacher);
			}
		}
		
		Comparator<Teacher> compareByStudentCount = Comparator.comparing(Teacher::getStudentCount);
		databaseT.sort(compareByStudentCount);
		Collections.reverse(databaseT);
		
		int count = 0;
		System.out.println("Uèitelé: ");
		for (Teacher t : databaseT) {
			System.out.println(t + " Poèet studentù: " + databaseT.get(count).getStudentCount());
			count ++;
		}
		databaseT.clear();
	}  
	
	// Vvypis na zaklade studijniho prumeru
	public void printByAvg(int tid) {
		Teacher list = (Teacher) people.get(tid);
		List<Student> databaseS = new ArrayList<>();
		for (int i = 0; i < ID; i++) { 
			
			if (list.getListOFStudents().contains(i)) {
				Student gradesA = (Student) people.get(i);
				
				if (gradesA.getGradeAvg() != 0) {
					databaseS.add(gradesA);
				}
			}
		}
		
		Comparator<Student> compareByAvg = Comparator.comparing(Student::getGradeAvg);
		databaseS.sort(compareByAvg);
		
		int count = 0;
		for (Student student : databaseS) {
			System.out.println("\nInfo o studenovi " + student + "Jeho studijní prùmìr je: " + databaseS.get(count).getGradeAvg());
			count ++;
		}
	}
	
	// Vypsani abecedniho seznamu vcetne platu / stipendia
	public void printAlfabeticallyBySurname() {
		List<Teacher> databaseT = new ArrayList<>();
		
		for (int i = 0; i < maxID(); i++) {
			
			if (people.get(i) instanceof Teacher) {
				Teacher teacher = (Teacher) people.get(i);
				databaseT.add(teacher);
			} 
		}
		
		Comparator <Teacher> compareBySurnameT = Comparator.comparing(Person::getSurname);
		databaseT.sort(compareBySurnameT);
		
		int count = 0;
		System.out.println("Seznam uèitelù: ");
		for (Teacher t : databaseT) {
			System.out.println("\n- Info o uèiteli: \n" + t + "Výše platu: " + databaseT.get(count).getSalary() + " Kè");
			count ++;
		}
		databaseT.clear();
		
		List<Student> databaseS = new ArrayList<>();
		
		for (int i = 0; i < maxID(); i++) {
			
			if (people.get(i) instanceof Student) {
				Student student = (Student) people.get(i);
				databaseS.add(student);
			}
		}
		
		Comparator <Student> compareBySurnameS = Comparator.comparing(Person::getSurname);
		databaseS.sort(compareBySurnameS);
		
		count = 0;
		System.out.println("\nSeznam studentù: ");
		for (Student s : databaseS) {
			System.out.print("\n- Info o studentovi: " + s + "Výše stipendia: " + databaseS.get(count).getScholarship()+ " Kè");
			count ++;
		}
		databaseS.clear();		
	}
	
	public void printUniversityExpences() {
		double expences = 0;
		double teachers = 0;
		double students = 0;
		
		for (int i = 0; i < maxID(); i++) {
			
			if (people.get(i) instanceof Teacher) {
				expences = expences + ((Teacher) people.get(i)).getSalary();
				teachers = teachers + ((Teacher) people.get(i)).getSalary();
			}
			
			if (people.get(i) instanceof Student) {
				expences = expences + ((Student) people.get(i)).getScholarship();
				students = students + ((Student) people.get(i)).getScholarship();
						
			}
		}
		
		System.out.println("Celkový výdaj za uèitele: " + teachers + " Kè");
		System.out.println("Celkový výdaj za studenty: " + students + " Kè");
		System.out.println("Celkové výdaje univerzity: " + expences + " Kè");
	}
	
// -------- SQL Shiiit -------
	
	public void unlockDB()
    {
        Path fileName = Path.of("src/Project/DB/Credentials.txt");
        String logincredentials = "";
        try
        {
            logincredentials = Files.readString(fileName);
        }
        catch (IOException e)
        {
           System.out.println("Špatné pøihlašovací údaje");
        }
        DBConnection.unlockDB(logincredentials);
    }

	public void writeIntoSQLDB()
    {
        Insert insert = new Insert();

        for (int i = 0; maxID() > i; i++)
        {
            if (people.get(i) instanceof  Teacher)
            {
                insert.insertTeacher((Teacher) people.get(i));
            }
            else if (people.get(i) instanceof Student)
            {
                insert.insertStudent((Student) people.get(i));
            }
        }
    }

    public void deleteFromDB(Scanner sc)
    {
        Delete delete = new Delete();
        System.out.println("Zadejte ID které chcete odebrat: ");
        sc.nextLine();
        int i = RunApp.integers(sc);
        delete.deleteEntry(i);
    }
    public void addTeacherFromDB(int id, String name,String surname,String birthDate)
    {
        Teacher teacher = new Teacher (name,surname,birthDate);
        people.put(id, teacher);
        people.get(id).setID(id);
        ID++;
    }
    public void addStudentFromDB(int id, String name,String surname,String birthDate)
    {
        Student student =  new Student (name,surname,birthDate);
        people.put(id,student);
        people.get(id).setID(id);
        ID++;
    }
    public void addStudentListFromDB(int teacherID, int StudentID)
    {
        Teacher t = (Teacher) people.get(teacherID);
        t.addStudentToList(StudentID);
    }
    public void addGradesFromDB(int id, double grade)
    {
        Student s = (Student) people.get(id);
        s.addGrade(grade);
    }
	
}
