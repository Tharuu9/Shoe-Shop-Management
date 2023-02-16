package lk.ijse.NiharaShoe.bo.custom;

import lk.ijse.NiharaShoe.bo.SuperBO;
import lk.ijse.NiharaShoe.dto.OrderDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Item;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;

    boolean placeOrder(OrderDTO dto);

    List<String> loadItemIds() throws SQLException, ClassNotFoundException;

    Item setItemData(String itemId) throws SQLException, ClassNotFoundException;

    void updateItemQTY(String id,int qrt) throws SQLException, ClassNotFoundException;

    Customer setCustomerData(String customerId) throws SQLException, ClassNotFoundException;

    List<String> loadCustomerIds() throws SQLException, ClassNotFoundException;
}
