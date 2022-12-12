package serialization;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lab1.classes.Employee;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SerializerXML implements Serializer{
    private ObjectMapper objectMapper;
    public SerializerXML(){
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }
    @Override
    public List<Employee> listFromFile(String fileName) {
        try {
            return objectMapper.readValue(new File(fileName), new TypeReference<>() {
            });
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public Employee fromFile(String fileName){
        try {
            return objectMapper.readValue(new File(fileName), Employee.class);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void toFile(Employee employee, String fileName){
        try {
            objectMapper.writeValue(new File(fileName), employee);
        }
        catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }

    @Override
    public void listToFile(List<Employee> employees, String fileName) {
        try {
            objectMapper.writeValue(new File(fileName), employees);
        } catch (IOException exc) {
            throw new RuntimeException(exc);
        }
    }


    public static void main(String args[]) {

        Employee employee = new Employee();
        employee.setFullName("Mariia Venhryniuk");
        employee.setPosition("administrator");
        employee.setGender(Employee.Gender.female);
        employee.setBirthDate(LocalDate.of(2003, 10, 14));
        Serializer serializer = new SerializerXML();
        serializer.toFile(employee, "employeeXML");

        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employee = new Employee();
        employee.setFullName("Mariia");
        employee.setPosition("Director");
        employees.add(employee);
        serializer.listToFile(employees, "employeesXML");

        employee = serializer.fromFile("employeeXML");
        System.out.println(employee);
        employees = serializer.listFromFile("employeesXML");
        System.out.println(employees);
    }

}
