package lk.ijse.NiharaShoe.entity;

public class Order {
    private String id;
    private String date;
    private String custID;

    public Order(String id, String date, String custID) {
        this.id = id;
        this.date = date;
        this.custID = custID;
    }

    public Order() {
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustID() {
        return custID;
    }

    public void setCustID(String custID) {
        this.custID = custID;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", custID='" + custID + '\'' +
                '}';
    }
}
