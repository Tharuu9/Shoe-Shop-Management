package lk.ijse.NiharaShoe.entity;

public class Item {
    private String code;
    private int qtyOnHand;
    private String description;
    private double unitPrice;
    private String supplierID;

    public Item(String code, int qtyOnHand, String description, double unitPrice, String supplierID) {
        this.code = code;
        this.qtyOnHand = qtyOnHand;
        this.description = description;
        this.unitPrice = unitPrice;
        this.supplierID = supplierID;
    }

    public Item() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getQtyOnHand() {
        return qtyOnHand;
    }

    public void setQtyOnHand(int qtyOnHand) {
        this.qtyOnHand = qtyOnHand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    @Override
    public String toString() {
        return "Item{" +
                "code='" + code + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", supplierID='" + supplierID + '\'' +
                '}';
    }
}
