package org.example.controller;

import org.example.view.MainView;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainView mainView;
    private CompanySystemController companySystemController;

    public MainController(MainView mainView) {
        this.mainView = mainView;

        mainView.getAddClientButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //TODO CHANGE IT TO THE WAY YI DID IN CLASS YOU CAN CHANGE ANYTHING IF NEEDED
            //    companySystemController.addClient();
            }
        });
    }
}
