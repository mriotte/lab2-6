package database;

import lab1.classes.Dish;
import lab1.classes.Employee;

import java.sql.*;

public class DBManager implements AutoCloseable {
    private final String url;
    private final String username;
    private final String password;

    public Connection connection;

    public DBManager(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;

        init();
    }

    public void init() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public boolean executeUpdate(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public boolean executeUpdatePrepareStatement(Employee employee) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Employee1 (fullName, birthDate, gender, position)" +
                    "VALUES ( ?, ?, ?, ?  );") ;
            statement.setString(1, employee.getFullName());
            statement.setDate(2, java.sql.Date.valueOf(employee.getBirthDate()));
            statement.setString(3, employee.getPosition());
            statement.setString(4, employee.getGender().toString());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    /*public boolean executeUpdatePrepareStatement(Dish dish) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Dish (dishName, group, price, weight)" +
                    "VALUES ( ?, ?, ?, ?  );") ;
            statement.setString(1, dish.getDish_name());
            statement.setDate(2, java.sql.Date.valueOf(employee.getBirthDate()));
            statement.setString(3, employee.getPosition());
            statement.setString(4, employee.getGender().toString());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    } */

    public ResultSet getData(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return null;
        }
    }
}
