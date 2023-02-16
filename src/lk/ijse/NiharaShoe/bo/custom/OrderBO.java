package lk.ijse.NiharaShoe.bo.custom;

import lk.ijse.NiharaShoe.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrderBO {
    ArrayList<Order> getAll() throws SQLException, ClassNotFoundException;
    Order search(String s) throws SQLException, ClassNotFoundException;
    boolean delete(String s) throws SQLException, ClassNotFoundException;
}
