package model;

public class OrderDetails {
     private String itemCode;
     private String orderId;
     private int qty;
     private double unitPrice;
     private double discount;

    public OrderDetails(String itemCode, double unitPrice, int qty) {
    }

    public OrderDetails(String itemCode, String orderId, int qty, double unitPrice, double discount) {
        this.setItemCode(itemCode);
        this.setOrderId(orderId);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setDiscount(discount);
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "itemCode='" + itemCode + '\'' +
                ", orderId='" + orderId + '\'' +
                ", qty=" + qty +
                ", unitPrice=" + unitPrice +
                ", discount=" + discount +
                '}';
    }
}
