package lk.ijse.NiharaShoe.bo.custom.impl;
import lk.ijse.NiharaShoe.bo.custom.PlaceOrderBO;
import lk.ijse.NiharaShoe.dao.DAOFactory;
import lk.ijse.NiharaShoe.dao.custom.CustomerDAO;
import lk.ijse.NiharaShoe.dao.custom.ItemDAO;
import lk.ijse.NiharaShoe.dao.custom.OrderDAO;
import lk.ijse.NiharaShoe.dao.custom.OrderDetailsDAO;
import lk.ijse.NiharaShoe.db.DBConnection;
import lk.ijse.NiharaShoe.dto.OrderDTO;
import lk.ijse.NiharaShoe.dto.OrderDetailsDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Item;
import lk.ijse.NiharaShoe.entity.Order;
import lk.ijse.NiharaShoe.entity.OrderDetails;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public class PlaceOrderBOImpl implements PlaceOrderBO {


    private final OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDER);
    private final CustomerDAO customerDAO= (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    private final ItemDAO itemDAO= (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);
    private final OrderDetailsDAO orderDetailsDAO= (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ORDERDETAILS);
    @Override
    public String generateNewOrderId() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNewOrderId();
    }

    @Override
    public boolean placeOrder(OrderDTO dto) {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);
            if (orderDAO.add(new Order(dto.getId(),dto.getCustID(), dto.getDate()))) {
                for (OrderDetailsDTO temp : dto.getItems()) {
                    OrderDetails orderDetails = new OrderDetails(dto.getId(), temp.getCode(), temp.getQtyOnHand(), temp.getUnitPrice());
                    if (!orderDetailsDAO.add(orderDetails)) {
                        con.rollback();
                        return false;
                    }
                }
                return true;
            } else {
                con.rollback();
                return false;
            }
        }catch (SQLException | ClassNotFoundException throwables){
            throwables.printStackTrace();
        }finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<String> loadItemIds() throws SQLException, ClassNotFoundException {
        return itemDAO.getItemIds();
    }

    @Override
    public Item setItemData(String itemId) throws SQLException, ClassNotFoundException {
        return itemDAO.search(itemId);
    }

    @Override
    public void updateItemQTY(String id, int qty) throws SQLException, ClassNotFoundException {
        itemDAO.updateQty(id,qty);
    }
    @Override
    public Customer setCustomerData(String customerId) throws SQLException, ClassNotFoundException {
        return customerDAO.search(customerId);
    }
    @Override
    public List<String> loadCustomerIds() throws SQLException, ClassNotFoundException {
        return customerDAO.getCustomerId();
    }
}
