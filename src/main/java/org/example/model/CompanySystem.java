package org.example.model;

import lombok.Getter;
import lombok.Setter;
import org.example.controller.DatabaseController;

import java.util.List;
@Getter
@Setter
public class CompanySystem {
    private List<Employee> employees;
    private List<Manager> managers;
    private List<Client> clients;

    private static CompanySystem companySystem;

    private CompanySystem() {
        this.employees = DatabaseController.queryAllEmployees();
        this.managers = DatabaseController.queryAllManagers();
        this.clients = DatabaseController.queryAllClients();

    }

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
