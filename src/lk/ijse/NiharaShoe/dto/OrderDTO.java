package lk.ijse.NiharaShoe.dto;

import java.util.ArrayList;

public class OrderDTO {
    private String id;
    private String date;
    private String custID;
    private ArrayList<OrderDetailsDTO> items;

    public OrderDTO(String id, String date, String custID) {
        this.id = id;
        this.date = date;
        this.custID = custID;
    }
    public OrderDTO(String orderID, String orderDate, String custID, ArrayList<OrderDetailsDTO> items) {
        this.id = orderID;
        this.date = orderDate;
        this.custID = custID;
        this.items = items;
    }
    public ArrayList<OrderDetailsDTO> getItems() {
        return items;
    }

    public void setItems(ArrayList<OrderDetailsDTO> items) {
        this.items = items;
    }
    public OrderDTO() {
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
        return "OrderDTO{" +
                "id='" + id + '\'' +
                ", date='" + date + '\'' +
                ", custID='" + custID + '\'' +
                ", items=" + items +
                '}';
    }
}
