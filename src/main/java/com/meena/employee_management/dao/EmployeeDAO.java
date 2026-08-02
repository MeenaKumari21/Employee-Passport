package com.meena.employee_management.dao;

import com.meena.employee_management.entity.Employee;
import com.meena.employee_management.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class EmployeeDAO {

    // Save Employee
    public void saveEmployee(Employee employee) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            session.persist(employee);

            transaction.commit();

            System.out.println("Employee Saved Successfully");

        } catch (Exception e) {

            if (transaction != null)
                transaction.rollback();

            System.out.println(e.getMessage());
        }
    }

    // Get Employee By ID
    public Employee getEmployeeById(Long id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.get(Employee.class, id);

        }
    }

    // Delete Employee
    public void deleteEmployee(Long id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Employee employee = session.get(Employee.class, id);

            if (employee != null) {

                session.remove(employee);

                System.out.println("Employee Deleted Successfully");

            } else {

                System.out.println("Employee Not Found");
            }

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null)
                transaction.rollback();

            System.out.println(e.getMessage());
        }
    }

    // Get All Employees
    public List<Employee> getAllEmployees() {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            return session.createQuery("from Employee", Employee.class).list();

        }
    }
}