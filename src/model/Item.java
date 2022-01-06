package model;

import java.util.Objects;

public class Item  {
     private String itemCode;
     private String description;
     private String packSize;
     private int qtyOnHand;
     private double unitPrice;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(itemCode, item.itemCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemCode);
    }

    public Item(String itemCode, String description, String packSize,int qtyOnHand, double unitPrice) {
        this.setItemCode(itemCode);
        this.setDescription(description);
        this.setPackSize(packSize);
        this.setQtyOnHand(qtyOnHand);
        this.setUnitPrice(unitPrice);

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

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }



    public String toString() {
        return "Item{" +
                "itemCode='" + itemCode + '\'' +
                ", description='" + description + '\'' +
                ", packSize='" + packSize + '\'' +
                ", qtyOnHand='" + qtyOnHand + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }

}
