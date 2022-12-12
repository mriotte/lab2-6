package serialization;
import lab1.classes.Employee;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SerializerTXT implements Serializer{
    private String toString(Employee m) {
        return
                "fullName~" + m.getFullName() + "~" +
                        "position~" + m.getPosition() + "~" +
                        "gender~" + m.getGender() + "~" + "date~" + m.getBirthDate()
                ;
    }
    private String toString(List<Employee> employee) {
        StringBuilder res = new StringBuilder();
        for (int i=0; i<employee.size(); i++) {
            res.append(toString(employee.get(i)));
            if (i+1 < employee.size()) {
                res.append(";");
            }
        }
        return res.toString();
    }
    private Employee fromString(String s){
        String [] obj = s.split("~");
        Employee res = new Employee();
        res.setFullName(obj[1]);
        res.setPosition(obj[3]);
        res.setBirthDate(LocalDate.parse(obj[7]));
        res.setGender(Employee.Gender.valueOf(obj[5]));
        return res;
    }

    private  List<Employee> fromStringList(String s){
        String [] obj = s.split(";");
        List<Employee> res = new ArrayList<>();
        for(String item:obj){
            res.add(fromString(item));
        }
        return res;
    }

    @Override
    public List<Employee> listFromFile(String fileName) {
        try (
                FileReader fw = new FileReader(new File(fileName));
                BufferedReader bw = new BufferedReader(fw)) {
            return fromStringList(bw.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Employee fromFile(String fileName) {
        try (
                FileReader fw = new FileReader(new File(fileName));
                BufferedReader bw = new BufferedReader(fw)) {
            return fromString(bw.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void toFile(Employee employee, String fileName) {
        try (
                FileWriter fw = new FileWriter(new File(fileName));
                BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(toString(employee));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void listToFile(List<Employee> employees, String fileName) {
        try(
                FileWriter fw = new FileWriter(fileName);
                BufferedWriter bw = new BufferedWriter(fw)){
            bw.write(toString(employees));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String args[]) {
        Employee employee = new Employee();
        employee.setFullName("Mariia Venhryniuk");
        employee.setPosition("administrator");
        employee.setGender(Employee.Gender.female);
        employee.setBirthDate(LocalDate.of(2003, 10, 14));
        Serializer serializer = new SerializerTXT();
        serializer.toFile(employee, "employeeTXT");


        List<Employee> employees = new ArrayList<>();
        employees.add(employee);
        employee = new Employee();
        employee.setFullName("Mariia");
        employee.setPosition("Director");
        employee.setGender(Employee.Gender.female);
        employee.setBirthDate(LocalDate.of(2003, 10, 14));
        employees.add(employee);
        serializer.listToFile(employees, "employeesTXT");

        employee = serializer.fromFile("employeeTXT");
        System.out.println(employee);
        employees = serializer.listFromFile("employeesTXT");
        System.out.println(employees);
    }
}