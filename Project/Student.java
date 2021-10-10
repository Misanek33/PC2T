package Project;

import java.util.ArrayList;
import java.util.List;

public class Student extends Person {
	
	private List<Double> grades;
	private double gradeAvg;
	private double scholarship;
	
	public Student(String name, String surname, String birthDate) {
		
		super(name,surname, birthDate);
		grades = new ArrayList<>();
	}
	
	public List<Double> getGrades(){
		return grades;
	}
		
	public double getGradeAvg() {
		return this.gradeAvg;
	}
	
	public double getScholarship() {
		return this.scholarship;
	}
		
	public void addGrade(double grade)
    {
        grades.add(grade);

        double sum = 0;
        for (Double aDouble : grades) {
            sum = sum + aDouble;
        }
        this.gradeAvg = sum/grades.size();

        if (gradeAvg == 1 || gradeAvg < 1.3)
        {
            this.scholarship = 500;
        }
        else
            this.scholarship = 0;
    	}
	
	 public String toString() {
	        return "\nID: " + getID() + " - Student"  + " \nJméno: " + getName() + " \nPøíjmení: " + getSurname() + " \nDatum narozeni: " + getBirthDate() + "\n";
	    }
}
