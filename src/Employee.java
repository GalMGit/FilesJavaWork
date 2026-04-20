import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

class Employee {
    private String surname;
    private String name;
    private int age;
    private String position;
    private double salary;

    public Employee(String surname, String name, int age, String position, double salary) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.position = position;
        this.salary = salary;
    }

    public String getSurname() { return surname; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getPosition() { return position; }
    public double getSalary() { return salary; }

    public void setSurname(String surname) { this.surname = surname; }
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setPosition(String position) { this.position = position; }
    public void setSalary(double salary) { this.salary = salary; }

    @Override
    public String toString() {
        return surname + " " + name + ", " + age + " лет, " + position + ", зарплата: " + salary;
    }

    public String toFileString() {
        return surname + "|" + name + "|" + age + "|" + position + "|" + salary;
    }

    public static Employee fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Employee(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], Double.parseDouble(parts[4]));
    }
}

