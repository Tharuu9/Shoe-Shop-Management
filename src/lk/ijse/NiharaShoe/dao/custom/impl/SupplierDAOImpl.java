package lk.ijse.NiharaShoe.dao.custom.impl;

import lk.ijse.NiharaShoe.dao.custom.SupplierDAO;
import lk.ijse.NiharaShoe.entity.Supplier;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public boolean add(Supplier supplier) throws SQLException, ClassNotFoundException {
        return (CrudUtil.executeUpdate("INSERT INTO Supplier VALUES (?,?,?,?,?)", supplier.getSupplierID(), supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getSupplierEmail(),supplier.getSupplierPhoneNo()));
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Supplier WHERE  supplierID=?", s);
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Supplier SET  supplierName =?,supplierAddress =?,supplierEmail =? ,supplierPhoneNo =? WHERE  supplierID =?", supplier.getSupplierName(), supplier.getSupplierAddress(), supplier.getSupplierPhoneNo(),supplier.getSupplierEmail(), supplier.getSupplierID());
    }

    @Override
    public Supplier search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.executeQuery( "SELECT * FROM Supplier WHERE supplierID=?",s);
        rst.next();
        return new Supplier(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5));
    }

    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Supplier");
        ArrayList<Supplier> allSuppliers=new ArrayList<>();
        while (rst.next()){
            allSuppliers.add(new Supplier( rst.getString(1), rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return allSuppliers;
    }

    @Override
    public String getNewSupplierID() throws SQLException, ClassNotFoundException {
        String lastSupplierId=getLastSupplierId();
        if(lastSupplierId==null){
            return "S-0001";
        }else {
            String[] split=getLastSupplierId().split("[S][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newSupplierId=String.format("S-%04d",lastDigits);
            return newSupplierId;
        }
    }

    private String getLastSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.executeQuery("SELECT supplierID from Supplier order by supplierID DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
