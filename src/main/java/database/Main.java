package database;

import lab1.classes.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Employee employee1 = new Employee.Builder().setEmployeeId(9).setFullName("Mariia Venhryniuk").setBirthDate(LocalDate.of(2003, 10, 14))
                .setGender(Employee.Gender.valueOf("female")).setPosition("Administrator")
                .build();
        Employee employee2 = new Employee.Builder().setEmployeeId(1).setFullName("Alice Depp").setBirthDate(LocalDate.of(2002, 12, 4))
                .setGender(Employee.Gender.valueOf("female")).setPosition("Waitress")
                .build();
        Employee employee3 = new Employee.Builder().setEmployeeId(21).setFullName("Bred Pitt").setBirthDate(LocalDate.of(1997, 5, 15))
                .setGender(Employee.Gender.valueOf("male")).setPosition("Waiter").setEmployeeId(21)
                .build();
        Employee employee4 = new Employee.Builder().setEmployeeId(22).setFullName("Alan Deon").setBirthDate(LocalDate.of(2001, 1, 1))
                .setGender(Employee.Gender.valueOf("male")).setPosition("Waiter")
                .build();
        Employee employee5 = new Employee.Builder().setEmployeeId(26).setFullName("Katherine Stone").setBirthDate(LocalDate.of(2000, 11, 1))
                .setGender(Employee.Gender.valueOf("female")).setPosition("Waitress")
                .build();

        Employee updateEmployee = new Employee.Builder().setEmployeeId(26).setFullName("Katherine Brown").setBirthDate(LocalDate.of(2000, 11, 1))
                .setGender(Employee.Gender.valueOf("female")).setPosition("Director").build();

        Dish dish1 = new Dish.DishBuilder().setDishId(1).setDishName("French soup").setGroup(Dish.Group.valueOf("soup")).setPrice(120).setWeight(300)
                .build();
        Dish dish2 = new Dish.DishBuilder().setDishId(2).setDishName("Coca cola").setGroup(Dish.Group.valueOf("drink")).setPrice(50).setWeight(500)
                .build();
        Dish dish3 = new Dish.DishBuilder().setDishId(3).setDishName("Salmon").setGroup(Dish.Group.valueOf("fish")).setPrice(220).setWeight(250)
                .build();
        Dish dish4 = new Dish.DishBuilder().setDishId(4).setDishName("French Fries").setGroup(Dish.Group.valueOf("garnish")).setPrice(75).setWeight(350)
                .build();
        Dish updateDish = new Dish.DishBuilder().setDishId(16).setDishName("French Fries").setGroup(Dish.Group.valueOf("garnish")).setPrice(80).setWeight(350)
                .build();

        Order order1 = new Order.OrderBuilder().setEmployeesList(Arrays.asList(employee2)).setDish(dish2).setCode(1)
                .setCreatedAt(LocalDateTime.parse("November 21, 2022 12:01 AM",
                        DateTimeFormatter.ofPattern("MMMM d',' yyyy hh':'mm a", Locale.US)))
                .setTableNumber(1).setType(Order.Type.valueOf("Offline")).setEmployeeId(1).setDishId(1).build();
        Order order2 = new Order.OrderBuilder().setEmployeesList(Arrays.asList(employee5)).setCode(2)
                .setCreatedAt(LocalDateTime.parse("November 21, 2022 12:12 AM",
                        DateTimeFormatter.ofPattern("MMMM d',' yyyy hh':'mm a", Locale.US)))
                .setTableNumber(1).setType(Order.Type.valueOf("Offline")).setDishId(13).build();
        order2.setEmployee(employee1);
        Order order3 = new Order.OrderBuilder().setEmployeesList(Arrays.asList(employee3)).setCode(3)
                .setCreatedAt(LocalDateTime.parse("November 21, 2022 12:05 AM",
                        DateTimeFormatter.ofPattern("MMMM d',' yyyy hh':'mm a", Locale.US)))
                .setTableNumber(5).setType(Order.Type.valueOf("Offline")).setEmployeeId(23).setDish(dish1).setDishId(16).build();
        Order updateOrder = new Order.OrderBuilder().setEmployeesList(Arrays.asList(employee3)).setCode(3)
                .setCreatedAt(LocalDateTime.parse("November 21, 2022 12:05 AM",
                        DateTimeFormatter.ofPattern("MMMM d',' yyyy hh':'mm a", Locale.US)))
                .setTableNumber(5).setType(Order.Type.valueOf("Online")).setEmployeeId(23).setDish(dish1).setDishId(16).build();


        OrderWrapper orderWrapper = new OrderWrapper("jdbc:mysql://localhost:3306/dbrestaurant", "marie", "1410");

        /*orderWrapper.AddEmployee(order1.getEmployees().get(0));
        orderWrapper.AddDish(order1.getDish());

        orderWrapper.AddEmployee(employee1);
        orderWrapper.AddEmployee(employee2);
        orderWrapper.AddEmployee(employee3);
        orderWrapper.AddEmployee(employee4);
        orderWrapper.AddEmployee(employee5);

        orderWrapper.AddDish(dish1);
        orderWrapper.AddDish(dish2);
        orderWrapper.AddDish(dish3);
        orderWrapper.AddDish(dish4);

        orderWrapper.AddOrder(order2);
        orderWrapper.AddOrder(order3);
        orderWrapper.AddOrder(order1);*/


        List<Employee> employees = orderWrapper.getEmployees();
        List<Dish> dishes = orderWrapper.getDishes();
        List<Order> orders = orderWrapper.getOrders();

        System.out.println("SELECT ALL EMPLOYEES");
        System.out.println(employees);

        System.out.println("SELECT EMPLOYEES BY FULLNAME - Alice Depp");
        System.out.println(orderWrapper.getEmployeesByFullName("Alice Depp"));

        System.out.println("SELECT EMPLOYEES BY POSITION - Administrator");
        System.out.println(orderWrapper.getEmployeesByPosition("Administrator"));

        System.out.println("SELECT EMPLOYEES BY ID - 1");
        System.out.println(orderWrapper.getEmployeeById(1));

        System.out.println("SELECT EMPLOYEES BY GENDER - female");
        System.out.println(orderWrapper.getEmployeesByGender("female"));

        System.out.println("SELECT ALL DISHES");
        System.out.println(dishes);

        System.out.println("SELECT DISH BY NAME - Salmon");
        System.out.println(orderWrapper.getDishByDishName("Salmon"));

        System.out.println("SELECT DISH BY GROUP - soup");
        System.out.println(orderWrapper.getDishByDishGroup("soup"));

        System.out.println("SELECT DISH BY PRICE - 75");
        System.out.println(orderWrapper.getDishByPrice(75));

        System.out.println("SELECT DISH BY WEIGHT - 250");
        System.out.println(orderWrapper.getDishByWeight(250));

        System.out.println("SELECT DISH BY ID - 1");
        System.out.println(orderWrapper.getDishById(1));

        System.out.println("SELECT ALL ORDERS");
        System.out.println(orders);

        System.out.println("SELECT ORDER BY ID - 1");
        System.out.println(orderWrapper.getOrderById(1));

        System.out.println("SELECT ORDER BY TABLE NUMBER - 1");
        System.out.println(orderWrapper.getOrderByTableNumber(1));

        System.out.println("SELECT ORDER BY EMPLOYEE - 23");
        System.out.println(orderWrapper.getOrderByEmployee(23));

        System.out.println("SELECT ORDER BY DISH - 13");
        System.out.println(orderWrapper.getOrderByDish(13));

        System.out.println("SELECT ORDER BY TYPE - Offline");
        System.out.println(orderWrapper.getOrdersByType("Offline"));

        orderWrapper.updateEmployee(updateEmployee);
        System.out.println("UPDATED EMPLOYEE");
        System.out.println(orderWrapper.getEmployeeById(23));

        orderWrapper.updateDish(updateDish);
        System.out.println("UPDATED Dish");
        System.out.println(orderWrapper.getDishById(16));

        System.out.println("SORT EMPLOYEE BY NAME");
        System.out.println(orderWrapper.sortEmployeesByName());

        System.out.println("SORT EMPLOYEE BY DATE OF BIRTH");
        System.out.println(orderWrapper.sortEmployeesByBirthDate());

        System.out.println("FILTER EMPLOYEE BY DATE OF BIRTH");
        System.out.println(orderWrapper.filterEmployeesByDateOfBirth());

        orderWrapper.eraseEmployee(employee5);



    }


}

