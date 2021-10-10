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
            System.out.println("[1] P�idat novou osobu"); 
            System.out.println("[2] Zadat studentovi zn�mku");
            System.out.println("[3] Propu�t�n� osoby z univerzity");
            System.out.println("[4] V�pis v�ech u�itel� studenta");
            System.out.println("[5] P�i�azen� nebo odebr�n� studenta u�iteli");
            System.out.println("[6] Vyps�n� info o osob�");
            System.out.println("[7] V�pis u�itel� podle po�tu student�");
            System.out.println("[8] V�pis studijn�ho pr�m�ru dle u�itel� ");
            System.out.println("[9] V�pis osob dle p��jm�n�");
            System.out.println("[10] Celkov� finan�n� prost�edky");
            System.out.println("[11] P�ipojen� datab�ze");
            System.out.println("[12] Na�ten� v�ech �daj� z SQL datab�ze");
            System.out.println("[13] Ulo�en� v�ech �daj� do SQL datab�ze");
            System.out.println("[14] Vymaz�n� vybran� osoby z SQL datab�ze");
            System.out.println("[15] Na�ten� vybran� osoby SQL datab�ze");
            System.out.println("[16] Ukon�en� programu");
            
            int option = RunApp.integers(sc);
			switch(option) {
            
            case 1: // Pridani osoby (A) - funguje
            	System.out.println("Zadejte roli u�ivatele: \n [1] Student \n [2] U�itel");
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
            	System.out.print("Zadejte jm�no osoby: ");
            	String name = sc.nextLine();
            	System.out.print("Zadejte p��jmen� osoby: ");
            	String surname = sc.nextLine();
            	System.out.print("Zadejte datum narozen� osoby (dd.mm.yy): ");
            	String birthDate = sc.nextLine();
            	
            	database.addPersonToDatabase(name, surname, birthDate, isStudent);
            break;
            
            case 2: // Zadani znamky (B) - funguje
            	database.printStudents();
            	System.out.println("Zadejte studenta, kter�mu chcete p�id�lit zn�mku: ");
            	sc.nextLine();
            	id = RunApp.integers(sc);
            	database.setGradeToStudent(id, sc);
            	database.updateTeachersSalary();
            break;
            
            case 3: // Odebrani studenta (C) - funguje
            	
            	System.out.print("Zadejte ID studenta, kter�ho chcete odebrat: ");
            	sc.nextLine();
            	id = RunApp.integers(sc);
            	database.removePerson(id);
            	database.updateTeachersSalary();
            break;
            
            case 4: // Vypsani ucitelu (D) - funguje
            	System.out.print("Zadejte ID studenta, pro vyps�n� jeho u�itel�: ");
            	sc.nextLine();
            	id = RunApp.integers(sc);
            	database.printTeachersOfStudent(id);
            break;
            
            case 5: // Pridani a odebrani studenta (E) - funguje
            	System.out.println("Chcete p�idat/odebrat studenta vyu�uj�c�mu? \n [1] P�idat \n [2] Odebrat");
            	i = RunApp.integers(sc);
            	switch(i) {
            		case 1: // Pridani studenta
            			System.out.print("Zadejte ID studenta: ");
            			sc.nextLine();
            			id = RunApp.integers(sc);
            			database.printTeachers();
            			System.out.print("Zadejte ID u�itele:");
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
            	System.out.print("Zadejte ID u�itele, pro vyps�n� student� dle pr�m�ru: ");
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
            	System.out.print("Program ukon�en");
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
			System.out.println("Chyba, zadejte pros�m cel� ��slo: ");
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
			System.out.println("Chyba, zadejte pros�m ��slo: ");
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
				System.out.println("Chyba, zadejte pros�m ��slo v�t�� ne� 0: ");
			}
		}
		return i;
	}
}
