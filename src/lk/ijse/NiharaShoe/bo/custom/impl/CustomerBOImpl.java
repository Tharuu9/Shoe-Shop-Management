package lk.ijse.NiharaShoe.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.custom.CustomerBO;
import lk.ijse.NiharaShoe.dao.DAOFactory;
import lk.ijse.NiharaShoe.dao.custom.CustomerDAO;
import lk.ijse.NiharaShoe.dto.CustomerDTO;
import lk.ijse.NiharaShoe.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    private final CustomerDAO customerDAO=(CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.CUSTOMER);
    @Override
    public boolean addNewCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.add(new Customer(dto.getCustID(), dto.getCustName(), dto.getCustPhoneNo(), dto.getCustAddress(), dto.getCustNic()));
    }

    @Override
    public boolean deleteCustomer(String s) throws SQLException, ClassNotFoundException {
        return customerDAO.delete(s);
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException {
        return customerDAO.update(new Customer(dto.getCustID(), dto.getCustName(), dto.getCustPhoneNo(), dto.getCustAddress(), dto.getCustNic()));
    }

    @Override
    public Customer searchCustomer(String s) throws SQLException, ClassNotFoundException {
        return customerDAO.search(s);
    }
    @Override
    public ObservableList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> customerTMS=customerDAO.getAll();
        ObservableList<Customer> obList = FXCollections.observableArrayList();

        customerTMS.forEach(e->obList.addAll(new Customer(e.getCustID(),e.getCustName(),e.getCustPhoneNo(),e.getCustAddress(),e.getCustNic())));
        return obList;
    }

    @Override
    public String getNewCustomerID() throws SQLException, ClassNotFoundException {
        return customerDAO.getNewCustomer();
    }
}
