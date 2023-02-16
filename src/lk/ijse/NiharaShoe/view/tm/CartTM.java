package lk.ijse.NiharaShoe.view.tm;

import javafx.scene.control.Button;

public class CartTM {
    private String code;
    private String description;
    private double unitPrice;
    private int qty;
    private double TotalCost;
    private Button btn;

    public CartTM(String code, String description, double unitPrice, int qty, double totalCost, Button btn) {
        this.code = code;
        this.description = description;
        this.unitPrice = unitPrice;
        this.qty = qty;
        TotalCost = totalCost;
        this.btn = btn;
    }

    public CartTM() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTotalCost() {
        return TotalCost;
    }

    public void setTotalCost(double totalCost) {
        TotalCost = totalCost;
    }

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "CartTM{" +
                "code='" + code + '\'' +
                ", description='" + description + '\'' +
                ", unitPrice=" + unitPrice +
                ", qty=" + qty +
                ", TotalCost=" + TotalCost +
                ", btn=" + btn +
                '}';
    }
}
