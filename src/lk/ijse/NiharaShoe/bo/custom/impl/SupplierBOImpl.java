package lk.ijse.NiharaShoe.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.custom.SupplierBO;
import lk.ijse.NiharaShoe.dao.DAOFactory;
import lk.ijse.NiharaShoe.dao.custom.SupplierDAO;
import lk.ijse.NiharaShoe.dto.SupplierDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    private final SupplierDAO supplierDAO=(SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.SUPPLIER);
    @Override
    public boolean addNewSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.add(new Supplier(dto.getSupplierID(), dto.getSupplierName(), dto.getSupplierAddress(), dto.getSupplierPhoneNo(), dto.getSupplierEmail()));
    }

    @Override
    public boolean deleteSupplier(String s) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(s);
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getSupplierID(), dto.getSupplierName(), dto.getSupplierAddress(), dto.getSupplierPhoneNo(), dto.getSupplierEmail()));
    }

    @Override
    public Supplier searchSupplier(String s) throws SQLException, ClassNotFoundException {
        return supplierDAO.search(s);
    }

    @Override
    public ObservableList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> supplierTMS=supplierDAO.getAll();
        ObservableList<Supplier> obList = FXCollections.observableArrayList();

        supplierTMS.forEach(e->obList.addAll(new Supplier(e.getSupplierID(),e.getSupplierName(),e.getSupplierAddress(),e.getSupplierPhoneNo(),
                e.getSupplierEmail())));
        return obList;
    }

    @Override
    public String getNewSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.getNewSupplierID();
    }
}
