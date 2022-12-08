package database;

import lab1.classes.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class OrderWrapper extends DBManager {
    public OrderWrapper(String url, String username, String password) {
        super(url, username, password);

        executeUpdate("CREATE TABLE Dish " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "dishName VARCHAR(60) NOT NULL, " +
                "dishGroup VARCHAR(50) NOT NULL, " +
                "price INTEGER NOT NULL, " +
                "weight INTEGER NOT NULL, " +
                "CONSTRAINT UNIQUE (dishName, dishGroup, price, weight))");

        executeUpdate("CREATE TABLE Employee " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "fullName VARCHAR(100) NOT NULL, " +
                "birthDate DATE NOT NULL, " +
                "gender VARCHAR(10) NOT NULL, " +
                "position VARCHAR(50) NOT NULL," +
                "CONSTRAINT UNIQUE (fullName, birthDate, gender, position))");

        executeUpdate("CREATE TABLE Orders " +
                "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                "createdAt TIMESTAMP NOT NULL, " +
                "tableNumber INTEGER NOT NULL, " +
                "type VARCHAR(10) NOT NULL, " +
                "employee INTEGER NOT NULL, " +
                "dish INTEGER NOT NULL, " +
                "FOREIGN KEY (employee) REFERENCES Employee (id), " +
                "FOREIGN KEY (dish) REFERENCES Dish (id), " +
                "CONSTRAINT UNIQUE (createdAt, tableNumber, type, employee, dish))");
    }


    public boolean addEmployee(Employee employee) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Employee (fullName, birthDate, gender, position)" +
                    "VALUES ( ?, ?, ?, ?  );");
            statement.setString(1, employee.getFullName());
            statement.setDate(2, java.sql.Date.valueOf(employee.getBirthDate()));
            statement.setString(3, employee.getGender().toString());
            statement.setString(4, employee.getPosition());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }


    public boolean addDish(Dish dish) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Dish (dishName, dishGroup, price, weight)" +
                    "VALUES ( ?, ?, ?, ?  );");
            statement.setString(1, dish.getDishName());
            statement.setString(2, dish.getGroup().toString());
            statement.setInt(3, dish.getPrice());
            statement.setInt(4, dish.getWeight());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }


    public boolean addOrder(Order order) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Orders (createdAt, tableNumber, type, employee, dish)" +
                    "VALUES ( ?, ?, ?, ?, ?  );");
            statement.setTimestamp(1, Timestamp.valueOf(order.getCreatedAt()));
            statement.setInt(2, order.getTableNumber());
            statement.setString(3, order.getType().toString());
            statement.setInt(4, order.getEmployee().getEmployeeId());
            statement.setInt(5, order.getDish().getDishId());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }
    ////////////////////////////////
    private Employee getEmployee(ResultSet set) {
        try {
            return new Employee.Builder().setEmployeeId(set.getInt("id"))
                    .setFullName(set.getString("fullName"))
                    .setBirthDate(set.getDate("birthDate").toLocalDate())
                    .setGender(Employee.Gender.valueOf(set.getString("gender")))
                    .setPosition(set.getString("position"))
                    .setEmployeeId(set.getInt("id")).build();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private Dish getDish(ResultSet set) {
        try {
            return new Dish.DishBuilder().setDishId(set.getInt("id"))
                    .setDishName(set.getString("dishName"))
                    .setGroup(Dish.Group.valueOf(set.getString("dishGroup")))
                    .setPrice(set.getInt("price"))
                    .setWeight(set.getInt("weight"))
                    .setDishId(set.getInt("id")).build();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
    }

    private Order getOrder(ResultSet set) {
        try {
            return new Order.OrderBuilder().setCode(set.getInt("id"))
                    .setCreatedAt(set.getTimestamp("createdAt").toLocalDateTime())
                    .setTableNumber(set.getInt("tableNumber"))
                    .setType(Order.Type.valueOf(set.getString("type")))
                    .setEmployeeId(set.getInt("employee"))
                    .setDishId(set.getInt("dish")).build();
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return null;
    }

    ////////////////////////////
    public List<Employee> getEmployeesByQuery(String query){
        List<Employee> result = new ArrayList<>();
        ResultSet set = getData(query);
        try {
            while (set.next()){
                result.add(getEmployee(set));
            }
        }catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }

    public List<Employee> getEmployees()
    {
        return getEmployeesByQuery("SELECT * FROM Employee");
    }

    public List<Employee> getEmployeesByFullName(String fullName) {
        return getEmployeesByQuery("SELECT * FROM Employee WHERE fullName = '" + fullName + "'");
    }

    public List<Employee> getEmployeesByPosition(String position) {
        return getEmployeesByQuery("SELECT * FROM Employee WHERE position = '" + position + "'");
    }

    public Employee getEmployeeById(int id) {
        List<Employee> result = getEmployeesByQuery("SELECT * FROM Employee WHERE id = '" + id + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
    public List<Employee> getEmployeesByGender(String gender) {
        return getEmployeesByQuery("SELECT * FROM Employee WHERE gender = '" + gender + "'");
    }

    ///////////////////////////////////////////////
    public List<Dish> getDishesByQuery(String query) {
        List<Dish> result = new ArrayList<>();
        ResultSet set = getData(query);
        try {
            while (set.next()) {
                result.add(getDish(set));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }
    public List<Dish> getDishes()
    {
        return getDishesByQuery("SELECT * FROM dish");
    }
    public Dish getDishById(int id) {
        List<Dish> result = getDishesByQuery("SELECT * FROM Dish WHERE id = '" + id + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    public List<Dish> getDishByDishName(String dishName) {
        return getDishesByQuery("SELECT * FROM Dish WHERE dishName = '" + dishName + "'");
    }

    public List<Dish> getDishByDishGroup(String dishGroup) {
        return getDishesByQuery("SELECT * FROM Dish WHERE dishGroup = '" + dishGroup + "'");
    }
    public Dish getDishByPrice(int price) {
        List<Dish> result = getDishesByQuery("SELECT * FROM Dish WHERE price = '" + price + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
    public Dish getDishByWeight(int weight) {
        List<Dish> result = getDishesByQuery("SELECT * FROM Dish WHERE weight = '" + weight + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    ////////////////////////////////////////////////////////////

    public List<Order> getOrdersByQuery(String query) {
        List<Order> result = new ArrayList<>();
        ResultSet set = getData(query);
        try {
            while (set.next()) {
                result.add(getOrder(set));
            }
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }

        return result;
    }
    public List<Order> getOrders()
    {
        return getOrdersByQuery("SELECT * FROM Orders");
    }
    public Order getOrderById(int id) {
        List<Order> result = getOrdersByQuery("SELECT * FROM Orders WHERE id = '" + id + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
    public List<Order> getOrdersByType(String type) {
        return getOrdersByQuery("SELECT * FROM Orders WHERE type = '" + type + "'");
    }
    public Order getOrderByTableNumber(int tableNumber) {
        List<Order> result = getOrdersByQuery("SELECT * FROM Orders WHERE tableNumber = '" + tableNumber + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
    public Order getOrderByEmployee(int employee) {
        List<Order> result = getOrdersByQuery("SELECT * FROM Orders WHERE employee = '" + employee + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }
    public Order getOrderByDish(int dish) {
        List<Order> result = getOrdersByQuery("SELECT * FROM Orders WHERE dish = '" + dish + "'");
        if (result.isEmpty())
            return null;
        return result.get(0);
    }

    /////////////////////////////////////////////////
    public boolean updateEmployee(Employee employee){
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE Employee SET fullName = ?, " +
                    "birthDate = ?, " +
                    "gender = ?, " +
                    "position = ? " +
                    "WHERE id = ?");
            statement.setString(1, employee.getFullName());
            statement.setDate(2, java.sql.Date.valueOf(employee.getBirthDate()));
            statement.setString(3, employee.getGender().toString());
            statement.setString(4, employee.getPosition());
            statement.setInt(5, employee.getEmployeeId());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public boolean updateDish(Dish dish){
        try{
        PreparedStatement statement = connection.prepareStatement("UPDATE dish SET dishName = ?," +
                "dishGroup = ?, " +
                "price = ?, " +
                "weight = ? " +
                "WHERE id = ?");
            statement.setString(1, dish.getDishName());
            statement.setString(2, dish.getGroup().toString());
            statement.setInt(3, dish.getPrice());
            statement.setInt(4, dish.getWeight());
            statement.setInt(5, dish.getDishId());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }

    public boolean updateOrder(Order order){
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE Orders SET createdAt = ?," +
                    "tableNumber = ?, " +
                    "type = ?, " +
                    "employee = ?, " +
                    "dish = ? " +
                    "WHERE id = ?");
            statement.setTimestamp(1, Timestamp.valueOf(order.getCreatedAt()));
            statement.setInt(2, order.getTableNumber());
            statement.setString(3, order.getType().toString());
            statement.setInt(4, order.getEmployee().getEmployeeId());
            statement.setInt(5, order.getDish().getDishId());
            statement.setInt(6, order.getCode());
            statement.executeUpdate();
            return true;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
            return false;
        }
    }


    //////////////////////////////////////////////////////

    /// Methods from lab about collections

    public List<Employee> sortEmployeesByName()
    {
        return getEmployeesByQuery("SELECT * FROM Employee ORDER BY fullName;");
    }
    public List<Employee> sortEmployeesByBirthDate()
    {
        return getEmployeesByQuery("SELECT * FROM Employee ORDER BY birthDate;");
    }

    public List<Employee> filterEmployeesByDateOfBirth()
    {
        return getEmployeesByQuery("SELECT * FROM Employee WHERE birthDate > '2001-01-01';");
    }


    ////////////////////////////////////////////

    public void eraseEmployee(Employee employee) {
        executeUpdate("DELETE FROM orders WHERE employee = '" + employee.getEmployeeId() + "'");
        executeUpdate("DELETE FROM Employee  WHERE id = '" + employee.getEmployeeId() + "'");
    }

    public void eraseDish(Dish dish) {
        executeUpdate("DELETE FROM orders WHERE dish = '" + dish.getDishId() + "'");
        executeUpdate("DELETE FROM Dish  WHERE id = '" + dish.getDishId() + "'");
    }
    public void eraseOrder(Order order) {
        executeUpdate("DELETE FROM Orders  WHERE id = '" + order.getCode() + "'");
    }

    public void dropTables(){
        executeUpdate("DROP TABLE employee;");
        executeUpdate("DROP TABLE dish;");
        executeUpdate("DROP TABLE orders;");
    }
}
