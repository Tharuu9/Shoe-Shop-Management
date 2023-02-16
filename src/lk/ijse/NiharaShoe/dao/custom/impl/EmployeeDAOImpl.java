package lk.ijse.NiharaShoe.dao.custom.impl;

import lk.ijse.NiharaShoe.dao.custom.EmployeeDAO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Employee;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public boolean add(Employee employee) throws SQLException, ClassNotFoundException {
        return (CrudUtil.executeUpdate("INSERT INTO Employee VALUES (?,?,?,?,?,?)", employee.getEmpID(), employee.getEmpName(), employee.getEmpContact(), employee.getEmpAddress(), employee.getEmpNic(), employee.getEmpSalary())) ;
    }

    @Override
    public boolean delete(String s) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("DELETE FROM Employee WHERE  empID=?", s);
    }

    @Override
    public boolean update(Employee employee) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Employee SET  empName =?,empContact =?,empAddress =?,empNic =?,empSalary=? WHERE  empID =?", employee.getEmpName(), employee.getEmpContact(), employee.getEmpAddress(),employee.getEmpNic(),employee.getEmpSalary(), employee.getEmpID());
    }

    @Override
    public Employee search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.executeQuery( "SELECT * FROM Employee WHERE empID=?",s);
        rst.next();
        return new Employee(rst.getString(1),rst.getString(2),rst.getString(3),rst.getString(4),rst.getString(5), rst.getDouble(6));
    }

    @Override
    public ArrayList<Employee> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Employee");
        ArrayList<Employee> allEmployees=new ArrayList<>();
        while (rst.next()){
            allEmployees.add(new Employee( rst.getString(1), rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getString(5),
                    rst.getDouble(6)
            ));
        }
        return allEmployees;
    }

    @Override
    public String getNewEmployeeId() throws SQLException, ClassNotFoundException {
        String lastEmployeeId=getLastId();
        if(lastEmployeeId==null){
            return "E-0001";
        }else {
            String[] split=lastEmployeeId.split("[E][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newEmployeeId=String.format("E-%04d",lastDigits);
            return newEmployeeId;
        }
    }

    private String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.executeQuery("SELECT empID from Employee order by empID DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }
}
