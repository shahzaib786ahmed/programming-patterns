package org.example;

import org.example.controller.CompanySystemController;
import org.example.controller.DatabaseController;
import org.example.controller.MainController;
import org.example.model.Client;
import org.example.model.Employee;
import org.example.model.Manager;
import org.example.view.MainView;

public class Main {
    //TODO: IF WE RUN DIRECTLY CSM IT DOESNT FIND THE TABLES
    public static void main(String[] args) {
     MainView mainView = new MainView();
        MainController mainController = new MainController(mainView);
      mainView.show();
    }
}
