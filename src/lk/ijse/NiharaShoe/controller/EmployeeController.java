package lk.ijse.NiharaShoe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Paint;
import lk.ijse.NiharaShoe.bo.BOFactory;
import lk.ijse.NiharaShoe.bo.custom.CustomerBO;
import lk.ijse.NiharaShoe.bo.custom.EmployeeBO;
import lk.ijse.NiharaShoe.dto.EmployeeDTO;
import lk.ijse.NiharaShoe.entity.Employee;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtContact;
    public JFXTextField txtAddress;
    public JFXTextField txtNic;
    public JFXTextField txtSalary;
    public TableColumn colEmpId;
    public TableColumn colEmpName;
    public TableColumn colContact;
    public TableColumn colEmpAddress;
    public TableColumn colEmpNic;
    public TableColumn colEmpSalary;
    public TableView<Employee> tblEmployee;

    private final EmployeeBO employeeBO=(EmployeeBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.EMPLOYEE_CONTROLLER);


    public void initialize() {
        colEmpId.setCellValueFactory(new PropertyValueFactory<>("empID"));
        colEmpName.setCellValueFactory(new PropertyValueFactory<>("empName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("empContact"));
        colEmpAddress.setCellValueFactory(new PropertyValueFactory<>("empAddress"));
        colEmpNic.setCellValueFactory(new PropertyValueFactory<>("empNic"));
        colEmpSalary.setCellValueFactory(new PropertyValueFactory<>("empSalary"));
        try {
            loadAllEmployee();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblEmployee.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setValues(newValue);
        });
        setNewEmployeeId();
    }

    private void setNewEmployeeId() {
        try{
            txtId.setText(getNewEmployee());
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }

    }
    private String getNewEmployee() throws SQLException, ClassNotFoundException {
        return employeeBO.getNewEmployeeID();

    }

    private void setValues(Employee em){
        txtId.setText(em.getEmpID());
        txtName.setText(em.getEmpName());
        txtContact.setText(em.getEmpContact());
        txtAddress.setText(em.getEmpAddress());
        txtNic.setText(em.getEmpNic());
        txtSalary.setText(String.valueOf(em.getEmpSalary()));
    }
    private void clear() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtAddress.clear();
        txtNic.clear();
        txtSalary.clear();
        tblEmployee.refresh();
    }

    private void loadAllEmployee() throws SQLException, ClassNotFoundException {
        tblEmployee.setItems(employeeBO.getAllEmployee());
    }

    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!validation()){
            new Alert(Alert.AlertType.ERROR,"Wrong Data...Check It...!").show();
            return;
        }
        EmployeeDTO employee = new EmployeeDTO(txtId.getText(), txtName.getText(), txtContact.getText(), txtAddress.getText(), txtNic.getText(),
                Double.parseDouble(txtSalary.getText())
        );
        try {
            if (employeeBO.addNewEmployee(employee));
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed").show();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clear();
        loadAllEmployee();
    }

    public void SearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void search() throws ClassNotFoundException, SQLException {
        try {
            Employee e =  employeeBO.searchEmployee(txtId.getText());
            if (e!=null) {
                txtName.setText(e.getEmpName());
                txtContact.setText(e.getEmpContact());
                txtAddress.setText(e.getEmpAddress());
                txtNic.setText(e.getEmpNic());
                txtSalary.setText(String.valueOf(e.getEmpSalary()));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Results").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        EmployeeDTO employee = new EmployeeDTO(
                txtId.getText(), txtName.getText(),txtContact.getText(),txtAddress.getText(), txtNic.getText(),Double.parseDouble(txtSalary.getText()));
        try {
            boolean isUpdated = employeeBO.updateEmployee(employee);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllEmployee();
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = employeeBO.deleteEmployee(txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllEmployee();
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        clear();
    }

    public void txtUserNameAction(KeyEvent keyEvent) {
        System.out.println(txtName.getUnFocusColor().toString());
        Pattern p1=Pattern.compile("^([ \\u00c0-\\u01ffa-zA-Z'\\-])+$");
        Matcher m1=p1.matcher(txtName.getText());
        boolean b=m1.find();
        if (b){
            txtName.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtName.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }

    public void txtContactOnAction(KeyEvent keyEvent) {
        System.out.println(txtContact.getUnFocusColor().toString());
        Pattern p1=Pattern.compile("^(0)([0-9+]{9,})$");
        Matcher m1=p1.matcher(txtContact.getText());
        boolean b=m1.find();
        if (b){
            txtContact.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtContact.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
    public void txtNicOnAction(KeyEvent keyEvent) {
        System.out.println(txtNic.getUnFocusColor().toString());
        Pattern p1=Pattern.compile("^([0-9]{9}[x|X|v|V]|[0-9]{12})$");
        Matcher m1=p1.matcher(txtNic.getText());
        boolean b=m1.find();
        if (b){
            txtNic.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtNic.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
    public boolean validation(){
        if(!txtName.getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        if(!txtContact.getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        if(!txtNic.
                getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        return true;
    }
}
