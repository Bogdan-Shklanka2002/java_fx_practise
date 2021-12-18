package com.example.demo;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class HelloController {
    @FXML
    private VBox mainPane;

    @FXML
    private Stage stage;

    @FXML
    private TableView<Person> tableAddressBook;

    @FXML
    private Label labelCount;

    private CollectionAddressBook addressBook = new CollectionAddressBook();

    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    public void initialize(){
        columnPIP.setCellValueFactory(new PropertyValueFactory<Person, String>("PIP"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("Phone"));
        addressBook.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                updateCountLabel();
            }
        });
        addressBook.fillTestData();
        tableAddressBook.setItems(addressBook.getPersonList());
    }

    private void updateCountLabel(){
        labelCount.setText("Кількість записів: " + addressBook.getPersonList().size());
    }

    @FXML
    private void logout(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Вихід з програми");
        alert.setContentText("Ви дійсно бажаєте вийти?");

        if(alert.showAndWait().get() == ButtonType.OK){
            stage = (Stage) mainPane.getScene().getWindow();
            System.out.println("Success");
            stage.close();
        }

    }

    @FXML
    void delete_Alert(ActionEvent event){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Видалення");

        alert.setHeaderText("Results: ");
        alert.setContentText("Ви успішно видалили запис!");

        alert.showAndWait();
    }
}