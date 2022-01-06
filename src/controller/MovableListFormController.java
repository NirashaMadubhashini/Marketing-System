package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;


public class MovableListFormController {
    public AnchorPane movableListContext;
    public StackedBarChart mostMovableItem;
    public StackedBarChart movableItems1;



    public void backOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) movableListContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
    }
}
