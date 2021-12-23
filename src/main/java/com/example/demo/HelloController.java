package com.example.demo;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class HelloController {
    @FXML
    private Button btnSearch;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnEdit;

    @FXML
    private VBox mainPane;

    @FXML
    private Stage stage;

    @FXML
    private TableView<Person> tableAddressBook;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button logoutButton;

    @FXML
    private Label labelCount;

    private CollectionAddressBook addressBook = new CollectionAddressBook();

    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;

    @FXML
    private VBox scenePane;

    private Stage newStage;
    private Stage editDialogStage;
    private Parent root;
    private Scene modalScene;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditModalController editModalController;

    public void setNewStage(Stage newStage) {

        this.newStage = newStage;
    }

    @FXML
    public void initialize(){
        try {
            fxmlLoader.setLocation(HelloController.class.getResource("/com/example/demo/EditModal.fxml"));
            root = fxmlLoader.load();
            editModalController = fxmlLoader.getController();
            modalScene = new Scene(root);
        } catch (IOException e) {
            e.printStackTrace();
        }

        columnPIP.setCellValueFactory(new PropertyValueFactory<Person, String>("PIP"));
        columnPIP.setText("Прізвище Ім\'я По-батькові");
        columnPhone.setCellValueFactory(new PropertyValueFactory<Person, String>("Phone"));
        columnPhone.setText("Телефон");

        addressBook.getPersonList().addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> change) {
                labelCount.setText("Кількість записів: " + addressBook.getPersonList().size());
            }
        });
        addressBook.fillTestData();
        tableAddressBook.setItems(addressBook.getPersonList());
    }

    private void updateCountLabel(){
        labelCount.setText("Кількість записів: " + tableAddressBook.getItems().size());
    }

    @FXML
    void openbtnAdd(ActionEvent event) throws IOException {

        Button clickedButton = (Button) event.getSource();

        switch (clickedButton.getId()) {
            case "btnAdd":
                editModalController.setPerson(new Person());
                showDialog();
                addressBook.add(editModalController.getPerson());
                break;
            case "btnEdit":
                var item = tableAddressBook.getSelectionModel().getSelectedItem();
                if(item == null)
                    break;
                editModalController.setPerson(item);
                showDialog();
                break;
            case "btnDelete":
                addressBook.delete((Person) tableAddressBook.getSelectionModel().getSelectedItem());
                break;
        }
    }

    @FXML
    private void search(){
        var search = txtSearch.textProperty().get();
        if(search.isEmpty())
            tableAddressBook.setItems(addressBook.getPersonList());

        tableAddressBook.setItems(addressBook.search(search));
        updateCountLabel();
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

    public void showDialog(){
            editDialogStage = new Stage();
            editDialogStage.setScene(modalScene);
            modalScene.getStylesheets().clear();
            File file = new File("C:\\demo\\src\\main\\java\\com\\example\\demo\\style.css");
            String file_s = file.toURI().toString();
            modalScene.getStylesheets().add(file_s);

            //stage.setScene(new Scene(root));
            editDialogStage.setTitle("Редагування");
            editDialogStage.setMinHeight(170);
            editDialogStage.setMinWidth(600);
            editDialogStage.setResizable(false);
            editDialogStage.initOwner(newStage);
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.showAndWait();
    }
}