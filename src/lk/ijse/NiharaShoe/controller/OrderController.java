package lk.ijse.NiharaShoe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.NiharaShoe.bo.custom.OrderBO;
import lk.ijse.NiharaShoe.bo.custom.impl.OrderBOImpl;
import lk.ijse.NiharaShoe.entity.Order;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderController {

    public JFXTextField txtId;
    public JFXTextField txtDate;
    public JFXTextField txtCustId;
    public TableView<Order> tblOrder;
    public TableColumn colOrderId;
    public TableColumn colOrderDate;
    public TableColumn colCustId;
    private OrderBO orderBO;


    public void initialize() {
        orderBO = new OrderBOImpl();
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colCustId.setCellValueFactory(new PropertyValueFactory<>("custID"));
        try {
            loadAllOrder();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblOrder.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            setValues(newValue);
        });
    }

    private void setValues(Order order) {
        txtId.setText(order.getId());
        txtDate.setText(order.getDate());
        txtCustId.setText(order.getCustID());
    }
    private void clear() {
        txtId.clear();
        txtDate.clear();
        txtCustId.clear();
    }

    private void loadAllOrder() throws SQLException, ClassNotFoundException {
        //ResultSet resultSet = CrudUtil.executeQuery("SELECT * FROM Orders");
        ObservableList<Order> obList = FXCollections.observableArrayList(orderBO.getAll());

        tblOrder.setItems(obList);
    }


    public void txtSearchOnAction(ActionEvent actionEvent) {
        search();
    }

    public void searchOnAction(ActionEvent actionEvent) {
        search();
    }
    private void search() {
        try {
            Order search = orderBO.search(txtId.getText());
            txtDate.setText(search.getDate());
            txtCustId.setText(search.getCustID());
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING, "Empty Results...!").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        /*try {
            ResultSet result = CrudUtil.executeQuery( "SELECT * FROM Orders WHERE orderID=?",txtId.getText());
            if (result.next()) {
                txtDate.setText(result.getString(2));
                txtCustId.setText(result.getString(3));
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Results...!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }*/
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = orderBO.delete(txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllOrder();
    }


    public void ClearOnAction(ActionEvent actionEvent) {
        clear();
    }

}
