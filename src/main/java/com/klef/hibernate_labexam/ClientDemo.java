package com.klef.hibernate_labexam;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class ClientDemo {

    public static void main(String[] args) {
        // Step 1: Create session factory
        SessionFactory factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Department.class).buildSessionFactory();

        // Step 2: Create session
        Session session = factory.getCurrentSession();
        
        // Get input from user to insert a new department
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user for Department details and insert a new department record
        insertDepartment(session, scanner);

        // Start a new session for deletion
        session = factory.getCurrentSession();

        // Prompt the user for the ID of the department to delete
        deleteDepartment(session, scanner);

        // Step 5: Close the session and factory
        factory.close();
    }

    // Method to insert a new Department record based on user input
    private static void insertDepartment(Session session, Scanner scanner) {
        // Prompt for department details
        System.out.println("Enter Department Name:");
        String name = scanner.nextLine();

        System.out.println("Enter Department Location:");
        String location = scanner.nextLine();

        System.out.println("Enter Head of Department (HoD) Name:");
        String hodName = scanner.nextLine();

        // Create a new Department object with user input
        Department department = new Department(name, location, hodName);

        // Start a transaction
        Transaction transaction = session.beginTransaction();

        // Save the department object to the database
        session.save(department);

        // Commit the transaction
        transaction.commit();

        // Output the saved department details
        System.out.println("Department inserted: " + department);
    }

    // Method to delete a Department record based on Department ID
    private static void deleteDepartment(Session session, Scanner scanner) {
        // Prompt for the ID of the department to delete
        System.out.println("Enter the Department ID to delete:");
        int deptId = scanner.nextInt();

        // Start a new transaction for the delete operation
        Transaction transaction = session.beginTransaction();

        // HQL query to delete the department based on the Department ID (using positional parameter)
        Query query = session.createQuery("delete from Department where deptId = ?0");
        query.setParameter(0, deptId);

        // Execute the delete operation and get the number of records deleted
        int result = query.executeUpdate();

        // Commit the transaction
        transaction.commit();

        // Output the result
        if (result > 0) {
            System.out.println("Department with ID " + deptId + " deleted successfully.");
        } else {
            System.out.println("No Department found with ID " + deptId + " to delete.");
        }
    }
}
