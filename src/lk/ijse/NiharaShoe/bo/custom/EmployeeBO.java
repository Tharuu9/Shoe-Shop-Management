package lk.ijse.NiharaShoe.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.SuperBO;
import lk.ijse.NiharaShoe.dto.EmployeeDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface EmployeeBO extends SuperBO {
    boolean addNewEmployee(EmployeeDTO dto) throws SQLException,ClassNotFoundException;

    boolean deleteEmployee(String s) throws SQLException,ClassNotFoundException;

    boolean updateEmployee(EmployeeDTO dto) throws SQLException,ClassNotFoundException;

    Employee searchEmployee(String s)throws SQLException,ClassNotFoundException;

    ObservableList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;

    String getNewEmployeeID() throws SQLException,ClassNotFoundException;
}
