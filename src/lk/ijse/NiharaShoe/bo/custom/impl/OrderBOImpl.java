package lk.ijse.NiharaShoe.bo.custom.impl;

import lk.ijse.NiharaShoe.bo.custom.OrderBO;
import lk.ijse.NiharaShoe.dao.custom.OrderDAO;
import lk.ijse.NiharaShoe.dao.custom.impl.OrderDAOImpl;
import lk.ijse.NiharaShoe.entity.Order;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderBOImpl implements OrderBO {
    OrderDAO orderDAO = new OrderDAOImpl();
    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        return orderDAO.getAll();
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        return orderDAO.search(s);
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return orderDAO.delete(s);
    }
}
