package lk.ijse.NiharaShoe.dao.custom;

import lk.ijse.NiharaShoe.dao.CrudDAO;
import lk.ijse.NiharaShoe.entity.Employee;

import java.sql.SQLException;

public interface EmployeeDAO extends CrudDAO<Employee,String> {
    String getNewEmployeeId() throws SQLException,ClassNotFoundException;
}
