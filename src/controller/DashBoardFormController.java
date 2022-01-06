package controller;

import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardFormController {
    public JFXTextField txtPassWord;
    public JFXTextField txtUserName;
    public AnchorPane dashBoardContext;


    public void logInOnAction(ActionEvent actionEvent) throws IOException {
        if (txtUserName.getText().equalsIgnoreCase("Admin") && txtPassWord.getText().equals("1234")) {
            Stage window = (Stage) dashBoardContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
        } else if (txtUserName.getText().equalsIgnoreCase("User") && txtPassWord.getText().equals("12")){
            Stage window = (Stage) dashBoardContext.getScene().getWindow();
            window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml"))));
        }else {
            new Alert(Alert.AlertType.WARNING, "Incorrect User Name, Password. Try again..", ButtonType.CLOSE).show();
        }

    }

    public void moveToPassword(ActionEvent actionEvent) {
        txtPassWord.requestFocus();
    }
}

