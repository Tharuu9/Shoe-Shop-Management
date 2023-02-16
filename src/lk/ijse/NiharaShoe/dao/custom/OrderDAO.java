package lk.ijse.NiharaShoe.dao.custom;

import lk.ijse.NiharaShoe.dao.CrudDAO;
import lk.ijse.NiharaShoe.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDAO extends CrudDAO<Order,String> {
    String generateNewOrderId() throws SQLException, ClassNotFoundException;
}
