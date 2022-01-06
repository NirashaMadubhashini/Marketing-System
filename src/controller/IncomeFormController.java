package controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import java.io.IOException;


public class IncomeFormController {
    public AnchorPane incomeFormContext;

    public ComboBox cmbMonths;
    public Label lblItemTotal;
    public Label lblWeeklyItemCount;
    public Label lblDailyIncome;
    public Label lblMonthlyIncome;
    public Label lblAnnualIncome;
    public Label lblCustomerWiseIncome;
    public Label lblItemFormDate;
    public Label lblItemFormTime;








    public void FindTotalOnAction(ActionEvent actionEvent) {
    }

    public void SellItemOnAction(ActionEvent actionEvent) {
    }

    public void CustomerWiseIncomeOnAction(ActionEvent actionEvent) {
    }

    public void AnnualIncomeOnAction(ActionEvent actionEvent) {
    }

    public void MonthlyIncomeOnAction(ActionEvent actionEvent) {
    }

    public void DailyIncomeOnAction(ActionEvent actionEvent) {
    }

    public void backToHomeOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) incomeFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
    }
}
