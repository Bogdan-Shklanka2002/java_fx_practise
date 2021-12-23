package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class EditModalController {

    @FXML
    private TextField txtPhone;

    @FXML
    private  TextField txtPip;

    private Person person;


    @FXML
    void actionSave(ActionEvent event){
        person.setPIP(txtPip.getText());
        person.setPHONE(txtPhone.getText());
        actionCancel(event);

    }

    @FXML
    void actionCancel(ActionEvent event){
        Node sourse = (Node) event.getSource();
        Stage stage = (Stage) sourse.getScene().getWindow();
        stage.hide();

    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        txtPip.setText(person.getPIP());
        txtPhone.setText(person.getPhone());
    }


}
