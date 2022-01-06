package view.tm;

public class CartTm {
    private String itemCode;
    private String description;
    private String packSize;
    private int qty;
    private double unitPrice;
    private double total = qty * unitPrice;


    public CartTm(String itemCode, String description, String packSize, int qty, double unitPrice, double total) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPackSize(packSize);
        this.setQty(qty);
        this.setUnitPrice(unitPrice);
        this.setTotal(total);
    }



    @Override
    public String toString() {
        return "CartTm{" +
                "itemCode='" + getItemCode() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", priceSize='" + getPackSize() + '\'' +
                ", unitPrice=" + getUnitPrice() +
                ", qty=" + getQty() +
                ", total=" + getTotal() +
                '}';
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPackSize() {
        return packSize;
    }

    public void setPackSize(String packSize) {
        this.packSize = packSize;
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

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
