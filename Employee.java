/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//List<Employee> list = ...
//list.sort(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getName));
package assignment1;

/**
 *
 * @author macbook
 */
public class Employee {
    String name,timeIn,timeOut;
	String id;
	String salary;

    public Employee(String name, String id, String salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }
        

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
        
    
}
