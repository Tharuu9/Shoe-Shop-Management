package lk.ijse.NiharaShoe.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.SuperBO;
import lk.ijse.NiharaShoe.dto.CustomerDTO;
import lk.ijse.NiharaShoe.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    boolean addNewCustomer(CustomerDTO dto) throws SQLException,ClassNotFoundException;

    boolean deleteCustomer(String s) throws SQLException,ClassNotFoundException;

    boolean updateCustomer(CustomerDTO dto) throws SQLException,ClassNotFoundException;

    Customer searchCustomer(String s)throws SQLException,ClassNotFoundException;

    ObservableList<Customer> getAllCustomer() throws SQLException, ClassNotFoundException;

    String getNewCustomerID() throws SQLException, ClassNotFoundException;
}
