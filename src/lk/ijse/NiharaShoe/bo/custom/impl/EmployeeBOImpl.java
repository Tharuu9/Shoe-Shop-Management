package lk.ijse.NiharaShoe.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.custom.EmployeeBO;
import lk.ijse.NiharaShoe.dao.DAOFactory;
import lk.ijse.NiharaShoe.dao.custom.EmployeeDAO;
import lk.ijse.NiharaShoe.dto.EmployeeDTO;
import lk.ijse.NiharaShoe.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    private final EmployeeDAO employeeDAO=(EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.EMPLOYEE);
    @Override
    public boolean addNewEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.add(new Employee(dto.getEmpID(), dto.getEmpName(), dto.getEmpContact(), dto.getEmpAddress(), dto.getEmpNic(), dto.getEmpSalary()));
    }

    @Override
    public boolean deleteEmployee(String s) throws SQLException, ClassNotFoundException {
        return employeeDAO.delete(s);
    }

    @Override
    public boolean updateEmployee(EmployeeDTO dto) throws SQLException, ClassNotFoundException {
        return employeeDAO.update(new Employee(dto.getEmpID(), dto.getEmpName(), dto.getEmpContact(), dto.getEmpAddress(), dto.getEmpNic(), dto.getEmpSalary()));
    }

    @Override
    public Employee searchEmployee(String s) throws SQLException, ClassNotFoundException {
        return employeeDAO.search(s);
    }

    @Override
    public ObservableList<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        ArrayList<Employee> employeeTMS=employeeDAO.getAll();
        ObservableList<Employee> obList = FXCollections.observableArrayList();

        employeeTMS.forEach(e->obList.addAll(new Employee(e.getEmpID(),e.getEmpName(),e.getEmpContact(),e.getEmpAddress(),
                e.getEmpNic(),e.getEmpSalary())));
        return obList;
    }

    @Override
    public String getNewEmployeeID() throws SQLException, ClassNotFoundException {
        return employeeDAO.getNewEmployeeId();
    }

}
