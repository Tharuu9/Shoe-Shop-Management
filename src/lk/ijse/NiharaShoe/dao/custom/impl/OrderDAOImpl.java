package lk.ijse.NiharaShoe.dao.custom.impl;

import lk.ijse.NiharaShoe.dao.custom.OrderDAO;
import lk.ijse.NiharaShoe.entity.Order;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public boolean add(Order order) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Orders VALUES (?,?,?)",order.getId(),order.getDate(),
                order.getCustID());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Orders WHERE orderID=?",s);
    }

    @Override
    public boolean update(Order order) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public Order search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Orders WHERE orderID=?",s);
        rst.next();
        return new Order(rst.getString(1),rst.getString(2),rst.getString(3));
    }

    @Override
    public ArrayList<Order> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Orders");
        ArrayList<Order> getAllOrder=new ArrayList<>();

        while (rst.next()){
            getAllOrder.add(new Order(rst.getString(1),rst.getString(2),rst.getString(3)));
        }

        return getAllOrder;
    }

    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT orderID FROM Orders ORDER BY orderID DESC LIMIT 1;");
        if(!rst.next())return "O-0001";
        String[] orderIDS = rst.getString("orderID").split("[O][-]");
        int i = Integer.parseInt(orderIDS[1]);
        return String.format("O-%04d", (i + 1)) ;
    }
}
