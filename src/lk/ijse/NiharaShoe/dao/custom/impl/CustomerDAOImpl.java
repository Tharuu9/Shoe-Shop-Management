package lk.ijse.NiharaShoe.dao.custom.impl;

import lk.ijse.NiharaShoe.dao.custom.CustomerDAO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public boolean add(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Customer VALUES (?,?,?,?,?)", customer.getCustID(), customer.getCustName(), customer.getCustPhoneNo(), customer.getCustAddress(), customer.getCustNic());
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Customer WHERE  custID=?", s);
    }

    @Override
    public boolean update(Customer customer) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Customer SET custName =?, custPhoneNo =?,custAddress =?,custNic =? WHERE  custID =?",  customer.getCustName(), customer.getCustPhoneNo(), customer.getCustAddress(),customer.getCustNic() ,customer.getCustID());
    }

    @Override
    public Customer search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.executeQuery( "SELECT * FROM Customer WHERE custID=?", s);
        rst.next();
        return  new Customer( rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5));
    }

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst = CrudUtil.executeQuery("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();
        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5)
            ));
        }
        return allCustomers;
    }

    @Override
    public List<String> getCustomerId() throws SQLException, ClassNotFoundException {
        List <String> ids=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Customer");
        while (rst.next()){
            ids.add(rst.getString(1));

        }
        return ids;
    }

    @Override
    public String getNewCustomer() throws SQLException, ClassNotFoundException {
        String lastCustomerId=getLastId();
        if(lastCustomerId==null){
            return "C-0001";
        }else {
            String[] split=lastCustomerId.split("[C][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newCustomerId=String.format("C-%04d",lastDigits);
            return newCustomerId;
        }
    }

    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.executeQuery("SELECT custID from Customer order by custID DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
