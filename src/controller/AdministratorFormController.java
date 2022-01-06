package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class AdministratorFormController {
    public AnchorPane administratorContext;

    public void incomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/IncomeForm.fxml"))));
    }

    public void movableOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/MovableListForm.fxml"))));
    }

    public void manageOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/ManageItemForm.fxml"))));
    }

    public void backToDashOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) administratorContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }
}
