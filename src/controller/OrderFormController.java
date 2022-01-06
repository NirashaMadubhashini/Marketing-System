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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Item;
import model.Order;
import view.tm.CartTm;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderFormController extends OrderController{
    public AnchorPane orderDetailFormContext;
    public AnchorPane orderFormContext;

    public Label lblOrderFormDate;
    public Label lblOrderFormTime;
    public Label lblOrderFormId;

    public JFXComboBox <String> cmbCustomerCode;
    public JFXComboBox <String> cmbItemCode;
    public JFXComboBox <String> cmbSearchOrder;

    public JFXTextField txtOrderDescription;
    public JFXTextField txtOrderPackSize;
    public JFXTextField txtOrderQtyOnHand;
    public JFXTextField txtOrderUnitPrice;
    public JFXTextField txtOrderQty;


    public Label txtToPaid1;
    public  Label txtDiscount1;


    public TableView  <CartTm> tblOrderForm;
    public TableColumn colOrderFormId;
    public TableColumn colOrderFormDescription;
    public TableColumn colOrderFormUnitPrice;
    public TableColumn colOrderFormQty;
    public TableColumn colOrderFormTotal;


    public TableColumn colOrderFormPackSize;



    int cartSelectedRowForRemove = -1;

    public void initialize() throws IOException {

        colOrderFormId.setCellValueFactory(new PropertyValueFactory<>("ItemCode"));
        colOrderFormDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colOrderFormPackSize.setCellValueFactory(new PropertyValueFactory<>("PackSize"));
        colOrderFormQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colOrderFormUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
        colOrderFormTotal.setCellValueFactory(new PropertyValueFactory<>("Total"));




        loadDateAndTime();
        setOrderId();

        try {

            loadOrderIds();
            loadCustomerIds();
            loadItemIds();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }



        cmbSearchOrder.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                });

        cmbCustomerCode.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                });

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
//            try {
//                setItemData(newValue);
//            } catch (SQLException throwables) {
//                throwables.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
            try {
                setItemData((String) newValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        tblOrderForm.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            cartSelectedRowForRemove = (int) newValue;
        });


    }


    private void loadOrderIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new OrderController().getOrderIds();
        cmbSearchOrder.getItems().addAll(itemIds);

    }


    private void loadItemIds() throws SQLException, ClassNotFoundException {
        List<String> itemIds = new ItemController().getAllItemIds();
        cmbItemCode.getItems().addAll(itemIds);

    }

    private void loadCustomerIds() throws SQLException, ClassNotFoundException {
        List<String> customerIds = new CustomerController()
                .getCustomerIds();
        cmbCustomerCode.getItems().addAll(customerIds);
    }

    private void setItemData(String itemCode) throws SQLException, ClassNotFoundException {
        Item i2= new ItemController().getItem(itemCode);
        if (i2==null){
            new Alert(Alert.AlertType.WARNING, "Empty Result Set");
        }else{
            txtOrderDescription.setText(i2.getDescription());
            txtOrderPackSize.setText(String.valueOf(i2.getPackSize()));
            txtOrderQtyOnHand.setText(String.valueOf(i2.getQtyOnHand()));
            txtOrderUnitPrice.setText(String.valueOf(i2.getUnitPrice()));
    }
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblOrderFormDate.setText(f.format(date));

        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblOrderFormTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void backPreviousOnAction(ActionEvent actionEvent) throws IOException {
        Stage window = (Stage) orderDetailFormContext.getScene().getWindow();
        window.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/CashierForm.fxml"))));
    }


    public void removeDetailOnAction(ActionEvent actionEvent) {
        if (cartSelectedRowForRemove==-1){
            new Alert(Alert.AlertType.WARNING, "Please Select a row").show();
        }else{
            obList.remove(cartSelectedRowForRemove);
            calculateCost();
            tblOrderForm.refresh();
        }
    }

    private void calculateCost() {
        double ttl = 0;
        for (CartTm tm : obList
        ) {
            ttl += tm.getTotal();
        }
        txtToPaid1.setText(ttl + " /=");

        double discount=0;

        for (CartTm tm:obList
        ) {
            discount+=tm.getTotal();
        if(ttl >= 1000){
            txtDiscount1.setText(String.valueOf(ttl-(ttl/10)));
        } else if(ttl <= 1000){
            ttl=ttl;
        }
        }

}

    public void ConformOrderOnAction(ActionEvent actionEvent) {
        ArrayList<Item> items= new ArrayList<>();
        double total=0;
        for (CartTm tempTm:obList
        ) {
            total+=tempTm.getTotal();
            items.add(new Item(tempTm.getItemCode(),
                    tempTm.getDescription(),
                    tempTm.getPackSize(),
                    tempTm.getQty(),
                    tempTm.getUnitPrice()));
        }

        Order order= new Order(lblOrderFormId.getText(), cmbCustomerCode.getValue(),
                lblOrderFormDate.getText(),
                lblOrderFormTime.getText(),
                total,
                items
        );

        if (new OrderController().placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success").show();
            setOrderId();
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }
    }

    private void setOrderId() {
        try {
            lblOrderFormId.setText(new OrderController().getOrderId());

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    ObservableList<CartTm> obList= FXCollections.observableArrayList();
    public void cancleOrderOnAction(ActionEvent actionEvent) {
        try{
        String description = txtOrderDescription.getText();
        String packSize = txtOrderPackSize.getText();
        int qtyOnHand = Integer.parseInt(txtOrderQtyOnHand.getText()+"");
        double unitPrice = Double.parseDouble(txtOrderUnitPrice.getText());
        int qtyForOrder = Integer.parseInt(txtOrderQty.getText());
        double total = qtyForOrder * unitPrice;


        if (qtyOnHand<qtyForOrder){
            new Alert(Alert.AlertType.WARNING,"Invalid QTY").show();
            return;
        }

        CartTm tm = new CartTm(
                cmbItemCode.getValue(),
                description,
                packSize,
                qtyForOrder,
                unitPrice,
                total
        );
        int rowNumber=isExists(tm);

        if (rowNumber==-1){
            obList.add(tm);
        }else{
            CartTm temp = obList.get(rowNumber);
            CartTm newTm = new CartTm(
                    temp.getItemCode(),
                    temp.getDescription(),
                    temp.getPackSize(),
                    temp.getQty()+qtyForOrder,
                    unitPrice,
                    total+temp.getTotal()
            );

            obList.remove(rowNumber);
            obList.add(newTm);
        }
        tblOrderForm.setItems(obList);
        calculateCost();
        } catch (Exception e) {
            new Alert(Alert.AlertType.WARNING, e.getMessage()).show();
            e.printStackTrace();
        }
    }

    private int isExists(CartTm tm) {
        for (int i = 0; i < obList.size(); i++) {
            if (tm.getItemCode().equals(obList.get(i).getItemCode())){
                return i;
            }
        }
        return -1;
    }



    public void moveNowUnitPrice(ActionEvent actionEvent) {
        txtOrderUnitPrice.requestFocus();
    }


    public void moveNowQty(ActionEvent actionEvent) {
        txtOrderQty.requestFocus();
    }

    public void moveNowPackSize(ActionEvent actionEvent){
        txtOrderPackSize.requestFocus();
    }

    public void moveNowQtyOnHand(ActionEvent actionEvent) {
        txtOrderQtyOnHand.requestFocus();
    }
}
