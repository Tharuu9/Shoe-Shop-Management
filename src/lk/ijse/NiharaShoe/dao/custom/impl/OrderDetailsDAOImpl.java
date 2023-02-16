package lk.ijse.NiharaShoe.dao.custom.impl;

import lk.ijse.NiharaShoe.dao.custom.OrderDetailsDAO;
import lk.ijse.NiharaShoe.entity.Order;
import lk.ijse.NiharaShoe.entity.OrderDetails;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public boolean add(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO orderDetail VALUES(?,?,?,?)",orderDetails.getId(),
                orderDetails.getCode(),orderDetails.getQtyOnHand(),orderDetails.getUnitPrice());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM orderDetail WHERE orderID=?",s);
    }

    @Override
    public boolean update(OrderDetails orderDetails) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE orderDetail SET  orderID=?, itemID=?,Qty=? WHERE OrderID=?",
                orderDetails.getId(),orderDetails.getCode(),orderDetails.getQtyOnHand(),orderDetails.getUnitPrice());
    }

    @Override
    public OrderDetails search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM orderDetail WHERE orderID=?",s);
        rst.next();
        return  new OrderDetails(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4));
    }

    @Override
    public ArrayList<OrderDetails> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM orderDetail");
        ArrayList<OrderDetails>getAllOrderDetails=new ArrayList<>();
        while (rst.next()){
            getAllOrderDetails.add(new OrderDetails(rst.getString(1),rst.getString(2),rst.getInt(3),rst.getDouble(4)));
        }
        return getAllOrderDetails;
    }

    @Override
    public ResultSet getOrderIncomeItems() throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT * FROM orderDetail");
    }

    @Override
    public ResultSet getOrderItems(String id) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeQuery("SELECT  * FROM orderDetail WHERE ItemCode=?",id);
    }
}
