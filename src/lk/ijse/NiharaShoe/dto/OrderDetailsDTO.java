package lk.ijse.NiharaShoe.dto;

public class OrderDetailsDTO {
    public String getItemCodes;
    private String id;
    private String code;
    private int qtyOnHand;
    private double unitPrice;

    public OrderDetailsDTO(String id, String code, int qtyOnHand, double unitPrice) {
        this.id = id;
        this.code = code;
        this.qtyOnHand = qtyOnHand;
        this.unitPrice = unitPrice;
    }

    public OrderDetailsDTO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "id='" + id + '\'' +
                ", code='" + code + '\'' +
                ", qtyOnHand=" + qtyOnHand +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
