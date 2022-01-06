package controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.Item;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

public class CashierFormController extends CustomerController {
    public AnchorPane cashierContext;
    public AnchorPane manageCustomerContext;


    public Label lblNewCustomerFormDate;
    public Label lblNewCustomerFormTime;
    public Label txtDiscount;
    public Label txtPaid;


    public JFXTextField txtNewCustomerId;
    public JFXTextField txtNewCustomerTitle;
    public JFXTextField txtNewCustomerName;
    public JFXTextField txtNewCustomerAddress;
    public JFXTextField txtNewCustomerCity;
    public JFXTextField txtNewCustomerProvince;
    public JFXTextField txtNewCustomerPostalCode;

    public JFXTextField txtManageDescription;
    public JFXTextField txtManagePackSIze;
    public JFXTextField txtManageUnitPrice;
    public JFXTextField txtManageQty;
    public JFXTextField txtManageQtyOnHand;

    public TableView<CartTm> tblManageItems;

    public TableColumn colMangeItemCode;
    public TableColumn colMangeDescription;
    public TableColumn colMangePackSize;
    public TableColumn colMangeUnitPrice;
    public TableColumn colMangeTotal;
    public TableColumn colMangeQty;

    public JFXComboBox<String> cmbManageItemCode;
    public JFXComboBox<String> cmbCustomerId = new JFXComboBox<String>();


    int cartSelectedRowForRemove = -1;

    public void initialize() throws IOException {

        colMangeItemCode.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colMangeDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colMangePackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colMangeQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colMangeUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colMangeTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));


        loadDateAndTime();

        try {

            loadCustomerIds();
            loadItemIds();


        } catch (Exception e) {
            e.printStackTrace();
        }

        cmbCustomerId.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    try {
                        setCustomerData(newValue);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                });

        cmbManageItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                setItemData((String) newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tblManageItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });


    }




    public  void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController()
                .getCustomerIds();
        cmbCustomerId.getItems().addAll(customerIds);
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i1 = new ItemController().getItem(itemCode);
        if (i1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtManageDescription.setText(i1.getDescription());
            txtManagePackSIze.setText(String.valueOf(i1.getPackSize()));
            txtManageQtyOnHand.setText(String.valueOf(i1.getQtyOnHand()));
            txtManageUnitPrice.setText(String.valueOf(i1.getUnitPrice()));

        }
    }

    private void setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        } else {
            txtNewCustomerTitle.setText(c1.getCustomerTitle());
            txtNewCustomerName.setText((c1.getCustomerName()));
            txtNewCustomerAddress.setText(c1.getCustomerAddress());
            txtNewCustomerCity.setText(c1.getCity());
            txtNewCustomerProvince.setText(c1.getProvince());
            txtNewCustomerPostalCode.setText(c1.getPostalCode());
        }
    }

    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getAllItemIds();
        cmbManageItemCode.getItems().addAll(itemIds);
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblNewCustomerFormDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblNewCustomerFormTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void backToDashBoardOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoardForm.fxml"))));
    }

    public void makeCustomerOrderOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml"))));

    }

    public void makeCustomerOrderPaymentOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) cashierContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/OrderForm.fxml"))));

    }


    public void addNewCustomerOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        Parent load = FXMLLoader.load(getClass().getResource("../view/AddNewCustomerForm.fxml"));
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();



    }

    public void selectCustomerOnAction(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
        String customerId = txtNewCustomerId.getText();

        Customer c1 = new CustomerController().getCustomer(customerId);
        if (c1 == null) {
            new Alert(Alert.AlertType.WARNING, "Empty Result Set").show();
        } else {
            setData(c1);
        }

    }

    private void setData(Customer c1) {
        txtNewCustomerId.setText(c1.getCustomerId());
        txtNewCustomerTitle.setText(c1.getCustomerTitle());
        txtNewCustomerName.setText(c1.getCustomerName());
        txtNewCustomerAddress.setText(c1.getCustomerAddress());
        txtNewCustomerCity.setText(c1.getCity());
        txtNewCustomerProvince.setText(c1.getProvince());
        txtNewCustomerPostalCode.setText(c1.getPostalCode());

    }

    private void calculateCost() {
        double ttl = 0;
        for (CartTm tm : obList
        ) {
            ttl += tm.getTotal();
        }
        txtPaid.setText(ttl + " /=");

        double discount=0;

        for (CartTm tm:obList
        ) {
            discount+=tm.getTotal();
            if(ttl >= 1000){
                txtDiscount.setText(String.valueOf(ttl-(ttl/10)));
            } else if(ttl <= 1000){
                ttl=ttl;
            }
        }
    }

    private int isExists(CartTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())) {
                return i;
            }
        }
        return -1;
    }


    ObservableList<CartTm> obList = FXCollections.observableArrayList();

    public void AddToItemCartOnAction(ActionEvent actionEvent) {
        try{
        String description = txtManageDescription.getText();
        String packSize = txtManagePackSIze.getText();
        int qtyOnHand = Integer.parseInt(txtManageQtyOnHand.getText() + "");
        double unitPrice = Double.parseDouble(txtManageUnitPrice.getText());
        int qtyForItem = Integer.parseInt(txtManageQty.getText());
        double total = qtyForItem * unitPrice;

        if (qtyOnHand < qtyForItem) {
            new Alert(Alert.AlertType.WARNING, "Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                cmbManageItemCode.getValue(),
                description,
                packSize,
                qtyForItem,
                unitPrice,
                total
        );
        int rowNumber = isExists(tm);

        if (rowNumber == -1) {
            obList.add(tm);
        } else {
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getPackSize(),
                    temp.getQty() + qtyForItem,
                    unitPrice,
                    total + temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblManageItems.setItems(obList);
        calculateCost();


        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            e.printStackTrace();
        }

    }

    public void CustomerIdCmbOnChange(ActionEvent actionEvent) {
//        try {
//            loadCustomerIds();
//
//        } catch (Exception e) {
//           e.printStackTrace();
//        }
    }


    public void moveNewCustomerAddress(ActionEvent actionEvent) {
        txtNewCustomerAddress.requestFocus();
    }

    public void moveNewCustomerCity(ActionEvent actionEvent) {
        txtNewCustomerCity.requestFocus();
    }

    public void moveNewCustomerProvince(ActionEvent actionEvent) {
        txtNewCustomerProvince.requestFocus();
    }

    public void moveNewCustomerPostalCode(ActionEvent actionEvent) {
        txtNewCustomerPostalCode.requestFocus();
    }


    public void moveManagePackSize(ActionEvent actionEvent) {
        txtManagePackSIze.requestFocus();
    }

    public void moveManageUnitPrice(ActionEvent actionEvent) {
        txtManageUnitPrice.requestFocus();
    }

    public void moveManageQtyOnHand(ActionEvent actionEvent) {
        txtManageQty.requestFocus();
    }

    public void moveNewCustomerTitel(ActionEvent actionEvent) {
        txtNewCustomerTitle.requestFocus();
    }

    public void moveNewCustomerName(ActionEvent actionEvent) {
        txtNewCustomerName.requestFocus();
    }


}
