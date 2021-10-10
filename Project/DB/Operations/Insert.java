package Project.DB.Operations;

import java.sql.*;
import Project.*;
import Project.DB.*;

public class Insert
{
    public void insertTeacher(Teacher t)
    {
        Connection connection = DBConnection.getConnection();
        String teacher = "INSERT INTO teachers (ID,name,surname,birthdate,salary) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(teacher);

            preparedStatement.setString(1, String.valueOf(t.getID()));
            preparedStatement.setString(2, t.getName());
            preparedStatement.setString(3, t.getSurname());
            preparedStatement.setString(4, String.valueOf(t.getBirthDate()));
            preparedStatement.setString(5, String.valueOf(t.getSalary()));
            preparedStatement.executeUpdate();
            if (t.getListOFStudents().size() != 0)
            {
                for (int i = 0; t.getListOFStudents().size() > i; i++)
                {
                    String teacher_students = "INSERT INTO teacher_students (teacher_id,student_id) VALUES(?, ?)";
                    preparedStatement = connection.prepareStatement(teacher_students);
                    preparedStatement.setString(1, String.valueOf(t.getID()));
                    preparedStatement.setString(2, String.valueOf(t.getListOFStudents().get(i)));
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void insertStudent(Student s)
    {
        Connection connection = DBConnection.getConnection();
        String student ="INSERT INTO students (ID,name,surname,birthdate,scholarship) VALUES(?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(student);
            preparedStatement.setString(1, String.valueOf(s.getID()));
            preparedStatement.setString(2, s.getName());
            preparedStatement.setString(3, s.getSurname());
            preparedStatement.setString(4, String.valueOf(s.getBirthDate()));
            preparedStatement.setString(5, String.valueOf(s.getScholarship()));
            preparedStatement.executeUpdate();
            if (s.getGrades().size() != 0)
            {
                for (int i = 0; i < s.getGrades().size(); i++)
                {
                    String grades = "INSERT INTO grades (student_id,grade) VALUES(?, ?)";
                    preparedStatement = connection.prepareStatement(grades);
                    preparedStatement.setString(1, String.valueOf(s.getID()));
                    preparedStatement.setString(2, String.valueOf(s.getGrades().get(i)));
                    preparedStatement.executeUpdate();
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
