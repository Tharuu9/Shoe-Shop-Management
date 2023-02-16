package lk.ijse.NiharaShoe.dao.custom;

import lk.ijse.NiharaShoe.dao.CrudDAO;
import lk.ijse.NiharaShoe.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier,String> {
    String getNewSupplierID() throws SQLException,ClassNotFoundException;

}
