package Project.DB;

import java.sql.*;

public class DBConnection
{
    private static Connection connection = null;
    private static boolean isAvailable = true;
    private static boolean unlocked = false;
    private static final String pathToDB = "src/Project/DB/DB.db";

    public static void setAvailability(boolean availability)
    {
        isAvailable = availability;
    }
    public static boolean isAvailable()
    {
        return isAvailable;
    }

    
    public static void unlockDB(String credintials) {
    	
    	unlocked = true;
    }

    public static Connection getConnection()
    {
        if (unlocked)
        {
            if (isAvailable)
            {
                if (connection == null)
                {
                    try
                    {
                        Class.forName("org.sqlite.JDBC");
                        connection = DriverManager.getConnection("jdbc:sqlite:" + pathToDB);
                        createNewDB();
                    } catch (SQLException | ClassNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                return connection;
            }
            return null;
        }
        return null;
    }
    public static void disconnect()
    {
        try
        {
            connection.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    public static void createNewDB()
    {
        try
        {
            getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS teachers (" +
                    "ID int primary key not null," +
                    "name varchar(30) not null," +
                    "surname varchar(30) not null," +
                    "birthdate int not null," +
                    "salary double default 0)").execute();
            getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS students (" +
                    "ID int primary key not null," +
                    "name varchar(30) not null," +
                    "surname varchar(30) not null," +
                    "birthdate int not null," +
                    "scholarship double default 0," +
                    "gradeavg double default 0)").execute();
            getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS teacher_students (" +
                    "teacher_id int not null," +
                    "student_id int not null)").execute();
            getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS grades (" +
                    "student_id int not null," +
                    "grade double not null)").execute();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            System.out.println("Není pøipojena DB");
        }

    }
    public static void clearDB()
    {
        try
        {
            getConnection().prepareStatement("DELETE FROM teachers").execute();
            getConnection().prepareStatement("DELETE FROM students").execute();
            getConnection().prepareStatement("DELETE FROM teacher_students").execute();
            getConnection().prepareStatement("DELETE FROM grades").execute();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
            System.out.println("Není pøipojena DB");
        }
    }
}
