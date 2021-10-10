package Project;

import java.util.Scanner;
import Project.DB.DBConnection;
import Project.DB.Operations.Select;

public class RunApp {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner sc = new Scanner(System.in);

		int id;
		int i;
		boolean run = true;
        Database database = new Database();
        Select select = new Select();
		
		while(run) {
			
			System.out.println("\n\n\t---- [Menu] ----");
            System.out.println("[1] Pøidat novou osobu"); 
            System.out.println("[2] Zadat studentovi známku");
            System.out.println("[3] Propuštìní osoby z univerzity");
            System.out.println("[4] Výpis všech uèitelù studenta");
            System.out.println("[5] Pøiøazení nebo odebrání studenta uèiteli");
            System.out.println("[6] Vypsání info o osobì");
            System.out.println("[7] Výpis uèitelù podle poètu studentù");
            System.out.println("[8] Výpis studijního prùmìru dle uèitelù ");
            System.out.println("[9] Výpis osob dle pøíjmìní");
            System.out.println("[10] Celkové finanèní prostøedky");
            System.out.println("[11] Pøipojení databáze");
            System.out.println("[12] Naètení všech údajù z SQL databáze");
            System.out.println("[13] Uložení všech údajù do SQL databáze");
            System.out.println("[14] Vymazání vybrané osoby z SQL databáze");
            System.out.println("[15] Naètení vybrané osoby SQL databáze");
            System.out.println("[16] Ukonèení programu");
            
            int option = RunApp.integers(sc);
			switch(option) {
            
            case 1: // Pridani osoby (A) - funguje
            	System.out.println("Zadejte roli uživatele: \n [1] Student \n [2] Uèitel");
            	boolean isStudent = false;
            	boolean testing = true;
            	int t;
            	while (testing) {
            		t = RunApp.integers(sc);
            		if (t == 1) {
            			isStudent = true;
            			testing = false;
            		}
            		else testing = t != 2;
            	}
            	
            	sc.nextLine();
            	System.out.print("Zadejte jméno osoby: ");
            	String name = sc.nextLine();
            	System.out.print("Zadejte pøíjmení osoby: ");
            	String surname = sc.nextLine();
            	System.out.print("Zadejte datum narození osoby (dd.mm.yy): ");
            	String birthDate = sc.nextLine();
            	
            	database.addPersonToDatabase(name, surname, birthDate, isStudent);
            break;
            
            case 2: // Zadani znamky (B) - funguje
            	database.printStudents();
            	System.out.println("Zadejte studenta, kterému chcete pøidìlit známku: ");
            	sc.nextLine();
            	id = RunApp.integers(sc);
            	database.setGradeToStudent(id, sc);
            	database.updateTeachersSalary();
            break;
            
            case 3: // Odebrani studenta (C) - funguje
            	
            	System.out.print("Zadejte ID studenta, kterého chcete odebrat: ");
            	sc.nextLine();
            	id = RunApp.integers(sc);
            	database.removePerson(id);
            	database.updateTeachersSalary();
            break;
            
            case 4: // Vypsani ucitelu (D) - funguje
            	System.out.print("Zadejte ID studenta, pro vypsání jeho uèitelù: ");
            	sc.nextLine();
            	id = RunApp.integers(sc);
            	database.printTeachersOfStudent(id);
            break;
            
            case 5: // Pridani a odebrani studenta (E) - funguje
            	System.out.println("Chcete pøidat/odebrat studenta vyuèujícímu? \n [1] Pøidat \n [2] Odebrat");
            	i = RunApp.integers(sc);
            	switch(i) {
            		case 1: // Pridani studenta
            			System.out.print("Zadejte ID studenta: ");
            			sc.nextLine();
            			id = RunApp.integers(sc);
            			database.printTeachers();
            			System.out.print("Zadejte ID uèitele:");
            			int tid = RunApp.integers(sc);
            		break;
            		
            		case 2: // Odebrani studenta
            			database.printStudents();
            			System.out.print("Zadejte ID studenta: ");
            			sc.nextLine();
            			id = RunApp.integers(sc);
            			database.removeStudentFromTeacher(id, sc);
            		break;
            	}	
            break;
            	
            case 6: // Hledani osob podle ID (F) 
            	System.out.print("Zadejte ID hledane osoby: ");
            	sc.nextLine();
            	id = integers(sc);
            	database.printByID(id);
            break;
            
            case 7: // Vypis ucitelu podle poctu studentu (G)
            	database.printByNumberOfStudents();
            break;
            
            case 8: // Vypis studentu dle stud prumeru (H)
            	database.printTeachers();
            	System.out.print("Zadejte ID uèitele, pro vypsání studentù dle prùmìru: ");
            	sc.nextLine();
            	int tid = RunApp.integers(sc);
            	database.printByAvg(tid);
            break;
            
            case 9: // Abecedne razeny vypis dle prijmeni (I)
            	database.printAlfabeticallyBySurname();
            break;
            	
            case 10: // Naklady univerzity (J)
            	database.printUniversityExpences();
            break;
            
            case 11: // Pripojeni DB (K)
            	database.unlockDB();
                DBConnection.getConnection();
            break;
            
            case 12: // Nacteni z DB (L)
            	select.loadAll(database);
            break;
            
            case 13: // Ulozeni do DB (M)
            	database.writeIntoSQLDB();
            break;
            	
            case 14: // Vymazani osoby z DB (N)
            	database.deleteFromDB(sc);
            break;
            
            case 15: // Nacteni osoby z DB (O)
            	System.out.print("Zadejte ID osoby: ");
            	id = RunApp.integers(sc);
            	select.loadSelect(database, id);
            break;
            	
            case 16: // Konec
            	run = false;
            	System.out.print("Program ukonèen");
            break;
            
            }
		}		
	}
	
	
	// Osetreni zadani celeho cisla
	public static int integers(Scanner sc) {
	
		int i;	
		try {
			i = sc.nextInt();
		}
		catch (Exception e){
			System.out.println("Chyba, zadejte prosím celé èíslo: ");
			sc.nextLine();
			i = integers(sc);
		}
		return i;
	}
	
	// Osetreni zadani jineho znaku nez cisla
	public static double numbers(Scanner sc) {
		
		double i;
		try {
			i = sc.nextDouble();
		}
		catch(Exception e) {
			System.out.println("Chyba, zadejte prosím èíslo: ");
			sc.nextLine();
			i = numbers(sc);
		}
		return i;
	}
	
	// Osetreni zadani cisel mensi nez 0
	public static int positiveNumbers(Scanner sc) {
		
		int i = 0;
		
		boolean isPositive = false;
		while (!isPositive) {
			i = integers(sc);
			if (i > 0) {
				isPositive = true;
			}
			else {
				System.out.println("Chyba, zadejte prosím èíslo vìtší než 0: ");
			}
		}
		return i;
	}
}
