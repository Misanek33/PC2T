package Project.DB.Operations;

import Project.DB.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Delete {
    public void deleteEntry(int id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement;
        String teacher = "DELETE FROM teachers WHERE id=(?)";
        String studlist = "DELETE FROM teacher_students WHERE teacher_id=(?)";
        String student = "DELETE FROM students WHERE id=(?)";
        String gradelist = "DELETE FROM grades WHERE student_id=(?)";

        try
        {

            preparedStatement = connection.prepareStatement(teacher);
            preparedStatement.setString(1,String.valueOf(id));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(studlist);
            preparedStatement.setString(1,String.valueOf(id));
            preparedStatement.executeUpdate();

            preparedStatement = connection.prepareStatement(student);
            preparedStatement.setString(1,String.valueOf(id));
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(gradelist);
            preparedStatement.setString(1,String.valueOf(id));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}