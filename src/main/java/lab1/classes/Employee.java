package lab1.classes;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * class "Employee" with fields: fullName, birthDate, gender, position
 * @author User
 * @version 1.0
 */

public class Employee implements Comparable<Employee>{

    @Pattern(regexp = "^([A-Z][a-z]*((\\s)))+[A-Z][a-z]*$", message = "Full name must consists only letters and first of them must started with UpperCase")
    protected String fullName;
    @Pattern(regexp = "[A-Z][a-z]{1,32}", message = "Position must consists only from letter and first letter must be UpperCase and word must consist only 32 letters")
    protected String position;
    @PastOrPresent(message = "Must be a date in the past or in the present")
    protected  LocalDate birthDate;
    public enum Gender{male, female}

    private Gender gender;

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    private int employeeId;



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public int compareTo(Employee t) {
        return fullName.compareTo(t.fullName);
    }
    /**
     *  Overridden function of obtaining a string representation of
     *  an instance of a class "Employee"
     *  @return returns the string representation
     */
    @Override
    public String toString() {
        return "Employee{" +
                "fullName='" + fullName + '\'' +
                ", birthDate=" + birthDate +
                ", gender=" + gender +
                ", position='" + position + '\'' +
                '}';
    }
    /**
     * Overridden function of comparison an instance of
     * the class "Employee" and an instance of the class "Object"
     * @return returns the boolean value of the comparison
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return fullName.equals(employee.fullName) && birthDate.equals(employee.birthDate) && gender == employee.gender && position.equals(employee.position);
    }
    /**
     * Overridden function of obtaining the hash code
     * @return returns the numeric value of the hash code
     */
    @Override
    public int hashCode() {
        return Objects.hash(fullName, birthDate, gender, position);
    }

    /**
     * Class "Builder" with fields: employee
     * @author User
     * @version 1.0
     */
    public static class Builder{
        private Employee employee;
        public Builder(){
            employee = new Employee();
        }
        /**
         * Setter name designation
         * @param fullName - employee full name
         * @return returns current object
         */
        public Builder setFullName(String fullName){
            employee.fullName = fullName;
            return this;
        }
        /**
         * Setter name designation
         * @param birthDate - date of birth
         * @return returns current object
         */
        public Builder setBirthDate(LocalDate birthDate){
            employee.birthDate = birthDate;
            return this;
        }
        public Builder setEmployeeId(int employeeId){
            employee.employeeId = employeeId;
            return this;
        }
        /**
         * Setter name designation
         * @param gender - employee gender
         * @return returns current object
         */
        public Builder setGender(Gender gender){
            employee.gender = gender;
            return this;
        }


        /**
         * Setter name designation
         * @param position - employee position
         * @return returns current object
         */
        public Builder setPosition(String position){
            employee.position = position;
            return this;
        }
        /**
         * Function of creating an object of class "Employee"
         * @return returns new object of class "Employee"
         */
        public Employee build(){
            validate(employee);
            return employee;
        }
    }
    private static void validate(Employee employee) throws IllegalArgumentException {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();


        Set<ConstraintViolation<Employee>> check = validator.validate(employee);

        StringBuilder sb = new StringBuilder();

        for(ConstraintViolation<Employee> element : check){
            sb.append("Error value "+element.getInvalidValue() + " because " + element.getMessage());
            sb.append("\n");
        }

        if(sb.length() > 0){
            throw new IllegalArgumentException(sb.toString());
        }
    }

    public static void main(String args[]){
        try{
            Employee e = new Employee.Builder()
                    .setFullName("mariia")
                    .setPosition("s3n4j4k")
                    .setBirthDate(LocalDate.of(2022, 12, 29))
                    .build();
        } catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }
    }
}
