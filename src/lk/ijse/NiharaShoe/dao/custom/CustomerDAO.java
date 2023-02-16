package lk.ijse.NiharaShoe.dao.custom;

import lk.ijse.NiharaShoe.dao.CrudDAO;
import lk.ijse.NiharaShoe.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO extends CrudDAO<Customer,String> {
      List<String> getCustomerId()throws SQLException,ClassNotFoundException;
      String getNewCustomer() throws SQLException, ClassNotFoundException;


}

