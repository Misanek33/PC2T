package Project.DB.Operations;

import Project.DB.DBConnection;
import Project.Database;

import java.sql.*;

public class Select
{
    public void loadAll(Database load)
    {
        Connection connection = DBConnection.getConnection();
        String teacher = "SELECT * FROM teachers";
        String studlist = "SELECT * FROM teacher_students";
        String student = "SELECT * FROM students";
        String gradelist = "SELECT *  FROM grades";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(teacher);
            int id;
            String name;
            String surname;
            String birthDate;
            int teacherID;
            int studentID;
            double grade;

            while(resultSet.next())
            {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                birthDate = resultSet.getString("birthdate");
                load.addTeacherFromDB(id,name,surname,birthDate);
            }
            resultSet = statement.executeQuery(student);
            while(resultSet.next())
            {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                birthDate = resultSet.getString("birthdate");
                load.addStudentFromDB(id,name,surname,birthDate);
            }
            resultSet = statement.executeQuery(studlist);
            while(resultSet.next())
            {
                teacherID = resultSet.getInt("teacher_id");
                studentID = resultSet.getInt("student_id");
                load.addStudentListFromDB(teacherID,studentID);
            }
            resultSet = statement.executeQuery(gradelist);
            while(resultSet.next())
            {
                studentID = resultSet.getInt("student_id");
                grade = resultSet.getDouble("grade");
                load.addGradesFromDB(studentID,grade);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public void loadSelect(Database load, int id)
    {
        Connection connection = DBConnection.getConnection();
        String teacher = "SELECT * FROM teachers WHERE id=(" + id + ")";
        String studlist = "SELECT * FROM teacher_students WHERE teacher_id=(" + id + ")";
        String student = "SELECT * FROM students WHERE id=(" + id + ")";
        String gradelist = "SELECT *  FROM grades WHERE student_id=(" + id + ")";
        try
        {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(teacher);

            String name;
            String surname;
            String birthDate;
            int teacherID;
            int studentID;
            double grade;

            while(resultSet.next())
            {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                birthDate = resultSet.getString("birthdate");
                load.addTeacherFromDB(id,name,surname,birthDate);
            }
            resultSet = statement.executeQuery(student);
            while(resultSet.next())
            {
                id = resultSet.getInt("id");
                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                birthDate = resultSet.getString("birthdate");
                load.addStudentFromDB(id,name,surname,birthDate);
            }
            resultSet = statement.executeQuery(studlist);
            while(resultSet.next())
            {
                teacherID = resultSet.getInt("teacher_id");
                studentID = resultSet.getInt("student_id");
                load.addStudentListFromDB(teacherID,studentID);
            }
            resultSet = statement.executeQuery(gradelist);
            while(resultSet.next())
            {
                studentID = resultSet.getInt("student_id");
                grade = resultSet.getDouble("grade");
                load.addGradesFromDB(studentID,grade);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}