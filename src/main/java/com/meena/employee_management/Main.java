package com.meena.employee_management;

import com.meena.employee_management.dao.EmployeeDAO;
import com.meena.employee_management.entity.Employee;
import com.meena.employee_management.entity.Passport;
import com.meena.employee_management.util.HibernateUtil;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeDAO dao = new EmployeeDAO();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== EMPLOYEE MANAGEMENT =====");
            System.out.println("1. Create Employee");
            System.out.println("2. Get Employee By ID");
            System.out.println("3. Get All Employees");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    Employee employee = new Employee();

                    System.out.print("Employee Name: ");
                    employee.setName(sc.nextLine());

                    System.out.print("Department: ");
                    employee.setDepartment(sc.nextLine());

                    System.out.print("Salary: ");
                    employee.setSalary(sc.nextDouble());
                    sc.nextLine();

                    System.out.print("Assign Passport? (yes/no): ");
                    String option = sc.nextLine();

                    if (option.equalsIgnoreCase("yes")) {

                        Passport passport = new Passport();

                        System.out.print("Passport Number: ");
                        passport.setPassportNumber(sc.nextLine());

                        System.out.print("Country: ");
                        passport.setCountry(sc.nextLine());

                        System.out.print("Expiry Date: ");
                        passport.setExpiryDate(sc.nextLine());

                        employee.setPassport(passport);
                    }

                    dao.saveEmployee(employee);
                    break;

                case 2:

                    System.out.print("Enter Employee ID: ");
                    Long id = sc.nextLong();

                    Employee emp = dao.getEmployeeById(id);

                    if (emp == null)
                        System.out.println("Employee Not Found");
                    else
                        System.out.println(emp);

                    break;

                case 3:

                    List<Employee> employees = dao.getAllEmployees();

                    if (employees.isEmpty())
                        System.out.println("No Employees Found");
                    else
                        employees.forEach(System.out::println);

                    break;

                case 4:

                    System.out.print("Enter Employee ID: ");
                    Long deleteId = sc.nextLong();

                    dao.deleteEmployee(deleteId);
                    break;

                case 5:

                    HibernateUtil.shutdown();
                    sc.close();

                    System.out.println("Application Closed");
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}