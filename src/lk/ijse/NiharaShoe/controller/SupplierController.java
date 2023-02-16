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
import lk.ijse.NiharaShoe.bo.custom.SupplierBO;
import lk.ijse.NiharaShoe.dto.SupplierDTO;
import lk.ijse.NiharaShoe.entity.Supplier;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierController {

    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtAddress;
    public JFXTextField txtEmail;
    public JFXTextField txtTel;
    public TableColumn colSupId;
    public TableColumn colSupName;
    public TableColumn colSupAddress;
    public TableColumn colContact;
    public TableColumn colEmail;
    public TableView<Supplier> tblSupplier;

    private final SupplierBO supplierBO=(SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.SUPPLIER_CONTROLLER);

    public void initialize() {
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("supplierName"));
        colSupAddress.setCellValueFactory(new PropertyValueFactory<>("supplierAddress"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("supplierPhoneNo"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("supplierEmail"));
        try {
            loadAllSupplier();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setValues(newValue);
        });
        setNewSupplierId();
    }
    private void setNewSupplierId() {
        try{
            txtId.setText(getNewSupplier());
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private String getNewSupplier() throws SQLException, ClassNotFoundException {
        return supplierBO.getNewSupplierID();
    }

    private void setValues(Supplier s){
        txtId.setText(s.getSupplierID());
        txtName.setText(s.getSupplierName());
        txtAddress.setText(s.getSupplierAddress());
        txtTel.setText(s.getSupplierPhoneNo());
        txtEmail.setText(s.getSupplierEmail());
    }

    private void clear() {
        txtId.clear();
        txtName.clear();
        txtAddress.clear();
        txtTel.clear();
        txtEmail.clear();
        tblSupplier.refresh();
    }

    private void loadAllSupplier() throws SQLException, ClassNotFoundException {
        tblSupplier.setItems(supplierBO.getAllSupplier());
    }

    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if(!validation()){
            new Alert(Alert.AlertType.ERROR,"Wrong Data...Check It...!").show();
            return;
        }
        SupplierDTO supplier = new SupplierDTO(txtId.getText(), txtName.getText(), txtAddress.getText(),txtEmail.getText(),txtTel.getText()
        );
        try {
            if(supplierBO.addNewSupplier(supplier)) ;
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed...!").show();
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        clear();
        loadAllSupplier();
    }
    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        SupplierDTO supplier = new SupplierDTO(
                txtId.getText(), txtName.getText(),txtAddress.getText(),txtEmail.getText(), txtTel.getText()
        );
        try {
            boolean isUpdated = supplierBO.updateSupplier(supplier);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllSupplier();
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = supplierBO.deleteSupplier(txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllSupplier();
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
            Supplier s = supplierBO.searchSupplier(txtId.getText());
            if (s!=null) {
                txtName.setText(s.getSupplierName());
                txtAddress.setText(s.getSupplierAddress());
                txtTel.setText(s.getSupplierPhoneNo());
                txtEmail.setText(s.getSupplierEmail());
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

    public void txtEmailOnAction(KeyEvent keyEvent) {
        System.out.println(txtEmail.getUnFocusColor().toString());
        Pattern p1=Pattern.compile("^([a-z|0-9]{3,})[@]([a-z]{2,})\\.(com|lk)$");
        Matcher m1=p1.matcher(txtEmail.getText());
        boolean b=m1.find();
        if (b){
            txtEmail.setUnFocusColor(javafx.scene.paint.Paint.valueOf("#2ecc71"));
        }else{
            txtEmail.setUnFocusColor(Paint.valueOf("#e74c3c"));
        }
    }
    public boolean validation(){
        if(!txtName.getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        if(!txtTel.getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        if(!txtEmail.
                getUnFocusColor().toString().equalsIgnoreCase("0x2ecc71ff")) {
            return false;
        }
        return true;
    }
}
