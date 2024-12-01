package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.DatabaseController;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CompanySystem {
    private List<Employee> employees;
    private List<Manager> managers;
    private List<Client> clients;

    private static CompanySystem companySystem;

    /**
     * Private constructor for initializing the CompanySystem.
     * This constructor initializes the lists of employees, managers, and clients by querying the database.
     * It ensures that only one instance of the CompanySystem is created.
     */
    private CompanySystem() {
        this.employees = new ArrayList<>();
        this.managers = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    /**
     * Returns the singleton instance of the CompanySystem.
     * If the instance does not already exist, it creates a new one.
     * @return the singleton instance of the CompanySystem.
     */
    public static CompanySystem getInstance() {
        if (companySystem == null) {
            synchronized (CompanySystem.class) {
                if (companySystem == null) {
                    companySystem = new CompanySystem();
                }
            }
        }
        return companySystem;
    }
}
