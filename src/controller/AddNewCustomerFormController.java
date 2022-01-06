package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.Item;
import view.tm.CartTm;
import view.tm.CustomerTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class AddNewCustomerFormController extends CustomerController {

    public AnchorPane AddNewCustomerContext;
    public TextField txtAddNewCustomerId;
    public TextField txtAddNewCustomerTitle;
    public TextField txtAddNewCustomerName;
    public TextField txtAddNewCustomerAddress;
    public TextField txtAddNewCustomerCity;
    public TextField txtAddNewCustomerProvince;
    public TextField txtAddNewCustomerPostalCode;

    public Button btnAdd;

    public TableView<Customer> tblAddNewCustomer;

    public TableColumn colAdNewCustomerId;
    public TableColumn colAdNewCustomerTitle;
    public TableColumn colAdNewCustomerName;
    public TableColumn colAdNewCustomerAddress;
    public TableColumn colAdNewCustomerCity;
    public TableColumn colAdNewCustomerProvince;
    public TableColumn colAdNewCustomerPostalCode;



    public void initialize() throws SQLException, ClassNotFoundException {


        colAdNewCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colAdNewCustomerTitle.setCellValueFactory(new PropertyValueFactory<>("customerTitle"));
        colAdNewCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        colAdNewCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        colAdNewCustomerCity.setCellValueFactory(new PropertyValueFactory<>("city"));
        colAdNewCustomerProvince.setCellValueFactory(new PropertyValueFactory<>("province"));
        colAdNewCustomerPostalCode.setCellValueFactory(new PropertyValueFactory<>("postalCode"));

        setCustomersToTable(new CustomerController().getAllCustomers());

    }
    private void setCustomersToTable(ArrayList<Customer> customers) {
        ObservableList<Customer> obList = FXCollections.observableArrayList();
        customers.forEach(e -> {
            obList.add(
                    new Customer(e.getCustomerId(), e.getCustomerTitle(), e.getCustomerName(), e.getCustomerAddress(),
                            e.getCity(),e.getProvince(),e.getPostalCode()));
        });
        tblAddNewCustomer.setItems(obList);
    }

    public void addCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Customer c = new Customer(
                    txtAddNewCustomerId.getText(), txtAddNewCustomerTitle.getText(), txtAddNewCustomerName.getText(),
                    txtAddNewCustomerAddress.getText(), txtAddNewCustomerCity.getText(), txtAddNewCustomerProvince.getText(),
                    txtAddNewCustomerPostalCode.getText()
            );

            if (new CustomerController().saveCustomer(c)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();


//                Stage stage = (Stage) btnAdd.getScene().getWindow();
//                stage.close();

                new CashierFormController().loadCustomerIds();

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();
            }

            setCustomersToTable(new CustomerController().getAllCustomers());

        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            e.printStackTrace();
        }


    }
    public void selectItemOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String  id= txtAddNewCustomerId.getText();

        Customer i1 = new CustomerController().getCustomer(id);

        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }

    }

    private void setData(Customer i1) {
        txtAddNewCustomerId.setText(i1.getCustomerId());
        txtAddNewCustomerTitle.setText(i1.getCustomerTitle());
        txtAddNewCustomerName.setText(i1.getCustomerName());
        txtAddNewCustomerAddress.setText(String.valueOf(i1.getCustomerAddress()));
        txtAddNewCustomerCity.setText(String.valueOf(i1.getCity()));
        txtAddNewCustomerProvince.setText(String.valueOf(i1.getProvince()));
        txtAddNewCustomerPostalCode.setText(String.valueOf(i1.getPostalCode()));

    }
    public void modifyCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Customer c1 = new Customer(
                    txtAddNewCustomerId.getText(),
                    txtAddNewCustomerTitle.getText(),
                    txtAddNewCustomerName.getText(),
                    txtAddNewCustomerAddress.getText(),
                    txtAddNewCustomerCity.getText(),
                    txtAddNewCustomerProvince.getText(),
                    txtAddNewCustomerPostalCode.getText()

            );
            if (new CustomerController().updateCustomer(c1))
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();
            else
                new Alert(Alert.AlertType.WARNING, "Try Again").show();

            setCustomersToTable(new CustomerController().getAllCustomers());

        }catch (Exception e){
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.WARNING,e.getMessage());
        }
    }


    public void removeCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new CustomerController().deleteCustomer(txtAddNewCustomerId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setCustomersToTable(new CustomerController().getAllCustomers());
    }

    public void moveCustomerTitleOnAction(ActionEvent actionEvent) {
        txtAddNewCustomerTitle.requestFocus();
    }

    public void moveCustomerNameOnAction(ActionEvent actionEvent) {
        txtAddNewCustomerName.requestFocus();
    }

    public void moveCustomerAddressOnAction(ActionEvent actionEvent) {
        txtAddNewCustomerAddress.requestFocus();
    }

    public void moveCustomerCityOnAction(ActionEvent actionEvent) {
        txtAddNewCustomerCity.requestFocus();
    }

    public void moveCustomerProvinceOnAction(ActionEvent actionEvent) {
        txtAddNewCustomerProvince.requestFocus();
    }

    public void moveCustomerPostalCodeOnAction(ActionEvent actionEvent) {
        txtAddNewCustomerPostalCode.requestFocus();
    }


}
