package lk.ijse.NiharaShoe.dao.custom;

import lk.ijse.NiharaShoe.dao.CrudDAO;
import lk.ijse.NiharaShoe.entity.OrderDetails;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface OrderDetailsDAO extends CrudDAO<OrderDetails,String> {
    ResultSet getOrderIncomeItems() throws SQLException, ClassNotFoundException;
    ResultSet getOrderItems(String id) throws SQLException, ClassNotFoundException;
}
