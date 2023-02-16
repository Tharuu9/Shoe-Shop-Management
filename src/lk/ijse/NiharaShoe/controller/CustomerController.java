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
import lk.ijse.NiharaShoe.dao.DAOFactory;
import lk.ijse.NiharaShoe.dao.custom.CustomerDAO;
import lk.ijse.NiharaShoe.db.DBConnection;
import lk.ijse.NiharaShoe.dto.CustomerDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.util.CrudUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerController {
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtNic;
    public JFXTextField txtTel;
    public JFXTextField txtAddress;
    public TableColumn colCustId;
    public TableColumn colCustName;
    public TableColumn colCustAddress;
    public TableColumn colCustNic;
    public TableColumn colPhoneNo;
    public TableView<Customer> tblCustomer;

    private final CustomerBO customerBO=(CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.CUSTOMER_CONTROLLER);

    public void initialize() {
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        colCustName.setCellValueFactory(new PropertyValueFactory<>("custName"));
        colPhoneNo.setCellValueFactory(new PropertyValueFactory<>("custPhoneNo"));
        colCustAddress.setCellValueFactory(new PropertyValueFactory<>("custAddress"));
        colCustNic.setCellValueFactory(new PropertyValueFactory<>("custNic"));
        try {
            loadAllCustomer();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                setValues(newValue);
            }
        });
        setNewCustomerId();
    }
    private void setNewCustomerId() {
        try{
            txtId.setText(getNewCustomer());
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private String getNewCustomer() throws SQLException, ClassNotFoundException {
        return customerBO.getNewCustomerID();
    }
    private void setValues(Customer c) {
        txtId.setText(c.getCustID());
        txtName.setText(c.getCustName());
        txtTel.setText(c.getCustPhoneNo());
        txtAddress.setText(c.getCustAddress());
        txtNic.setText(c.getCustNic());
    }

    private void clear() {
        txtId.clear();
        txtName.clear();
        txtTel.clear();
        txtAddress.clear();
        txtNic.clear();
        tblCustomer.refresh();
    }

    private void loadAllCustomer() throws SQLException, ClassNotFoundException {
        tblCustomer.setItems(customerBO.getAllCustomer());
//        ObservableList<Customer> obList = FXCollections.observableArrayList();
//        while (resultSet.next()) {
//            obList.add(
//                    new Customer(
//                            resultSet.getString(1),
//                            resultSet.getString(2),
//                            resultSet.getString(3),
//                            resultSet.getString(4),
//                            resultSet.getString(5)
//                    ));
//        }
//        tblCustomer.setItems(obList);
    }

    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!validation()){
            new Alert(Alert.AlertType.ERROR,"Wrong Data...Check It...!").show();
            return;
        }
        CustomerDTO customer = new CustomerDTO(txtId.getText(), txtName.getText(), txtTel.getText(), txtAddress.getText(),
                txtNic.getText()
        );
        try {
            if (customerBO.addNewCustomer(customer)) ;

            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed...!").show();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clear();
        loadAllCustomer();
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        CustomerDTO customer = new CustomerDTO(
                txtId.getText(), txtName.getText(),txtTel.getText(), txtAddress.getText(),txtNic.getText()
        );
        try {
            boolean isUpdated = customerBO.updateCustomer(customer);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllCustomer();
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = customerBO.deleteCustomer(txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //autoId();
        clear();
        loadAllCustomer();
    }

    public void txtSearchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void searchOnAction(ActionEvent actionEvent) {
        try {
            search();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    private void search() throws ClassNotFoundException, SQLException {
        try {
            Customer c = customerBO.searchCustomer(txtId.getText());
            if (c!=null) {
                txtName.setText(c.getCustName());
                txtNic.setText(c.getCustNic());
                txtTel.setText(c.getCustPhoneNo());
                txtAddress.setText(c.getCustAddress());
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Results...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
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
        System.out.println(txtTel.getUnFocusColor().toString());
        Pattern p1=Pattern.compile("^(0)([0-9+]{9,})$");
        Matcher m1=p1.matcher(txtTel.getText());
        boolean b=m1.find();
        if (b){
            txtTel.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtTel.setUnFocusColor(Paint.valueOf("#e74c3c"));
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
        if(!txtTel.getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        if(!txtNic.
        getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        return true;
    }
}
