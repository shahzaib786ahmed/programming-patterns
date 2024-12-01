package org.example;

import org.example.controller.BookingFlightController;
import org.example.controller.CompanySystemController;
import org.example.controller.DatabaseController;
import org.example.model.Client;
import org.example.model.Employee;
import org.example.model.Manager;
import org.example.model.Review;

public class Main {
    //TODO: IF WE RUN DIRECTLY CSM IT DOESNT FIND THE TABLES
    public static void main(String[] args) {
//     MainView mainView = new MainView();
//        MainController mainController = new MainController(mainView);
//      mainView.show();
        Client client = new Client("Ahmed","Shahzaib","A4325M678","5533257543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123", 0);
        Client client2 = new Client("Kharchafi","Aya","B6325I226","4423277543","shahzaib@gmail.com",20,"ShahzaibisAStar","Shahzaib123", 0);
        //CompanySystemController companySystemController = new CompanySystemController();
        //companySystemController.addClient(client);
        //companySystemController.addClient(client2);

        Employee employee = new Employee("Kharchafi", "Aya", "123456789", "5143334343", "ayakharchafi@gmail.com", 20, 50);
        //companySystemController.addEmployee(employee);

        Manager manager2 = new Manager("BLS", "Rizz", "C23056789","1234565432","Skibidi@gmail.com",3,80);
        //companySystemController.addManager(manager2);

        Review review = new Review("skibidi@gmail.com", "WOWW GREAT SERVICE", "BEST AGENCY EVER;)");
        //companySystemController.addReview(review);

        BookingFlightController bookingFlightController = new BookingFlightController();
    }
}
