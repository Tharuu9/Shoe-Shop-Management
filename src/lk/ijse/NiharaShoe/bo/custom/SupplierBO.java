package lk.ijse.NiharaShoe.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.SuperBO;
import lk.ijse.NiharaShoe.dto.SupplierDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface SupplierBO extends SuperBO {
    boolean addNewSupplier(SupplierDTO dto) throws SQLException,ClassNotFoundException;

    boolean deleteSupplier(String s) throws SQLException,ClassNotFoundException;

    boolean updateSupplier(SupplierDTO dto) throws SQLException,ClassNotFoundException;

    Supplier searchSupplier(String s)throws SQLException,ClassNotFoundException;

    ObservableList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException;

    String getNewSupplierID() throws SQLException,ClassNotFoundException;
}
