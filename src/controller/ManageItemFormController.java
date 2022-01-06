package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Customer;
import model.Item;
import model.Order;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class ManageItemFormController extends ItemController {
    public AnchorPane ManageItemFormContext;

    public TableView<Item> tblManageItem;

    public TableColumn colManageItemCode;
    public TableColumn colManageItemDescription;
    public TableColumn colManageItemPackSize;
    public TableColumn colManageItemQtyOnHand;
    public TableColumn colManageItemUnitPrice;

    public TextField txtAddItemCode;
    public TextField txtAddItemDescription;
    public TextField txtAddItemPackSize;
    public TextField txtAddItemQtyOnHand;
    public TextField txtAddItemUnitPrice;


    public void initialize() throws SQLException, ClassNotFoundException {


        colManageItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colManageItemDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colManageItemPackSize.setCellValueFactory(new PropertyValueFactory<>("packSize"));
        colManageItemQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colManageItemUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));

        setItemsToTable(new ItemController().getAllItem());

    }


    private void setItemsToTable(ArrayList<Item> items) {
//        System.out.println(items+"in setItem Method===============================================>");
        ObservableList<Item> obList = FXCollections.observableArrayList();
        items.forEach(e -> {
            obList.add(
                    new Item(e.getItemCode(), e.getDescription(), e.getPackSize(), e.getQtyOnHand(), e.getUnitPrice()));
        });
        tblManageItem.setItems(obList);
    }


    public void backManageItemFormOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) ManageItemFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/AdministratorForm.fxml"))));
    }

    public void AddItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            Item i3 = new Item(
                    txtAddItemCode.getText(), txtAddItemDescription.getText(),
                    txtAddItemPackSize.getText(), parseInt(txtAddItemQtyOnHand.getText()),
                    Double.parseDouble(txtAddItemUnitPrice.getText())
            );

            if (new ItemController().saveItem(i3))
                new Alert(Alert.AlertType.CONFIRMATION, "Saved..").show();
            else
                new Alert(Alert.AlertType.WARNING, "Try Again..").show();


            setItemsToTable(new ItemController().getAllItem());


        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            e.printStackTrace();
        }

    }

    public void SearchItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String itemCode = txtAddItemCode.getText();

        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }
    }


    public void selectItemOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String itemCode = txtAddItemCode.getText();

        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(i1);
        }

    }

    private void setData(Item i1) {
        txtAddItemCode.setText(i1.getItemCode());
        txtAddItemDescription.setText(i1.getDescription());
        txtAddItemPackSize.setText(i1.getPackSize());
        txtAddItemQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
        txtAddItemUnitPrice.setText(String.valueOf(i1.getUnitPrice()));

    }

    public void ModifyItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        try {
            Item i1 = new Item(txtAddItemCode.getText(), txtAddItemDescription.getText(),
                    txtAddItemPackSize.getText(), Integer.parseInt(txtAddItemQtyOnHand.getText()),
                    Double.parseDouble(txtAddItemUnitPrice.getText()));


            if (new ItemController().updateItem(i1)) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..").show();

                setItemsToTable(new ItemController().getAllItem());

            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again").show();


            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            new Alert(Alert.AlertType.WARNING,e.getMessage());
        }


    }

    public void DeleteItemOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        if (new ItemController().deleteItem(txtAddItemCode.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setItemsToTable(new ItemController().getAllItem());

    }


    public void moveDescription(ActionEvent actionEvent) {
        txtAddItemDescription.requestFocus();
    }

    public void movePackSize(ActionEvent actionEvent) {
        txtAddItemPackSize.requestFocus();
    }

    public void moveQtyOnHand(ActionEvent actionEvent) {
        txtAddItemQtyOnHand.requestFocus();
    }

    public void moveUnitPrice(ActionEvent actionEvent) {
        txtAddItemUnitPrice.requestFocus();
    }


}
