package lab1.classes;
/*
* java docs
*
*
 */

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
/**
 * class "Order" with fields: code, createdAt, tableNumber, type, employee, dish
 * @author User
 * @version 1.0
 */
public class Order {
    public enum Type{Online, Offline}
    
    private int code;
    private LocalDateTime createdAt;
    private int tableNumber;
    private Type type;
    private Employee employee;
    public Employee getEmployee(){ return employee;}
    private List<Employee> employees;
    public List<Employee> getEmployees() {
        return employees;
    }
    private Dish dish;
    private int employeeId;
    private int dishId;

    public int getDishId() {
        return dishId;
    }

    public void setDishId(int dishId) {
        this.dishId = dishId;
    }

    public void setEmployee(Employee employee){this.employee = employee;}

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getCode() {
        return code;
    }
    public void setCode(int code) {
        this.code = code;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public int getTableNumber() {
        return tableNumber;
    }
    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    //public void setEmployee(Employee employee) {
     //   this.employee = employee;
  //  }

    public Dish getDish() {
        return dish;
    }
    public void setDish(Dish dish) {
        this.dish = dish;
    }
    /**
     *  Overridden function of obtaining a string representation of
     *  an instance of a class "Order"
     *  @return returns the string representation
     */
    @Override
    public String toString() {
        return "Order{" +
                "code=" + code +
                ", createdAt=" + createdAt +
                ", tableNumber=" + tableNumber +
                ", type=" + type +
                ", employee=" + employees +
                ", dish=" + dish +
                '}';
    }
    /**
     * Overridden function of comparison an instance of
     * the class "Order" and an instance of the class "Object"
     * @return returns the boolean value of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return code == order.code && createdAt.equals(order.createdAt) && type == order.type  && dish.equals(order.dish);
    }
    /**
     * Overridden function of obtaining the hash code
     * @return returns the numeric value of the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(code, createdAt, tableNumber, type, dish);
    }

    /**
     * Class "Builder" with fields: order
     * @author User
     * @version 1.0
     */
    public static class OrderBuilder{
        private Order order;
        public OrderBuilder(){ order = new Order(); }
        /**
         * Setter type designation
         * @param code - region name
         * @return returns current object
         */
        public OrderBuilder setCode(int code){
            order.code = code;
            return this;
        }

        /**
         * Setter type designation
         * @param createdAt - order time
         * @return returns current object
         */
        public OrderBuilder setCreatedAt(LocalDateTime createdAt){
            order.createdAt = createdAt;
            return this;
        }

        /**
         * Setter type designation
         * @param tableNumber - table number
         * @return returns current object
         */
        public OrderBuilder setTableNumber(int tableNumber){
            order.tableNumber = tableNumber;
            return this;
        }
        public OrderBuilder setDishId(int dishId){
            order.dishId = dishId;
            return this;
        }
        public OrderBuilder setEmployeeId(int employeeId){
            order.employeeId = employeeId;
            return this;
        }
        /**
         * Setter type designation
         * @param type - order type
         * @return returns current object
         */
        public OrderBuilder setType(Type type){
            order.type = type;
            return this;
        }

        /**
         * Setter type designation
         * @param employees - waiters
         * @return returns current object
         */
        public OrderBuilder setEmployeesList(List<Employee> employees) {
            order.employees = employees;
            return this;
        }
        /**
         * Setter type designation
         * @param dish - ordered dish
         * @return returns current object
         */
        public OrderBuilder setDish(Dish dish){
            order.dish = dish;
            return this;
        }


        /**
         * Function of creating an object of class "Order"
         * @return returns new object of class "Order"
         */
        public Order build(){
            return order;
        }
    }

    public static void main(String args[]) {
    }
}
