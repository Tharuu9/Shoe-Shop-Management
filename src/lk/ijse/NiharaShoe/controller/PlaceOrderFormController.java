package lk.ijse.NiharaShoe.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.NiharaShoe.bo.BOFactory;
import lk.ijse.NiharaShoe.bo.custom.ItemBO;
import lk.ijse.NiharaShoe.bo.custom.PlaceOrderBO;
import lk.ijse.NiharaShoe.dao.custom.impl.OrderDetailsDAOImpl;
import lk.ijse.NiharaShoe.db.DBConnection;
import lk.ijse.NiharaShoe.dto.OrderDTO;
import lk.ijse.NiharaShoe.dto.OrderDetailsDTO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Item;
import lk.ijse.NiharaShoe.entity.Order;
import lk.ijse.NiharaShoe.entity.OrderDetails;
import lk.ijse.NiharaShoe.view.tm.CartTM;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class PlaceOrderFormController {
    public AnchorPane contest;
    public JFXComboBox<String> cmbCustomerId;
    public JFXComboBox<String> cmbItemCode;
    public JFXTextField txtCustomerName;
    public JFXTextField txtDescription;
    public JFXTextField txtQtyOnHand;
    public JFXTextField txtUnitPrice;
    public JFXTextField txtQty;
    public JFXButton btnSave;
    public TableView<CartTM> tblOrderDetails;
    public Label lblTotal;
    public JFXButton btnPlaceOrder;
    public Label lblId;
    public Label lblDate;
    public TableColumn colCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colTotalCost;
    public TableColumn colButton;

    private final PlaceOrderBO placeOrderBO= (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.PLACE_ORDER_CONTROLLER);

    private void CartTM() {
        colCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotalCost.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        colButton.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        setDate();
        setCmbCustomerIds();
        setCmbItemCodes();
        CartTM();
        setOrderId();

        cmbItemCode.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    setItemCodes(newValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                //clear();
            }
        });

        cmbCustomerId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                try {
                    setCustomerIds(newValue);
                } catch (SQLException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void setItemCodes(String ItemCode) throws SQLException, ClassNotFoundException {
        Item i = placeOrderBO.setItemData(cmbItemCode.getValue());
        if (i != null) {
            txtDescription.setText(i.getDescription());
            txtUnitPrice.setText(String.valueOf(i.getUnitPrice()));
            txtQtyOnHand.setText(String.valueOf(i.getQtyOnHand()));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    private void setCustomerIds(String CustomerId) throws SQLException, ClassNotFoundException {
        Customer c = placeOrderBO.setCustomerData(cmbCustomerId.getValue());
        if (c != null) {
            txtCustomerName.setText(c.getCustName());
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty Result").show();
        }
    }

    public void setCmbCustomerIds() throws SQLException, ClassNotFoundException {
        cmbCustomerId.getItems().addAll(placeOrderBO.loadCustomerIds());
    }

    public void setCmbItemCodes() throws SQLException, ClassNotFoundException {
        cmbItemCode.getItems().addAll(placeOrderBO.loadItemIds());
    }

    private void setDate() {
        lblDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
    }
    ObservableList<CartTM> tmList = FXCollections.observableArrayList();
    private CartTM isExists(String itemCodes) {
        for (CartTM tm : tmList
        ) {
            if (tm.getCode().equals(itemCodes)) {
                return tm;
            }
        }
        return null;
    }
    private void calculateTotal() {
        double total = 0;
        for (CartTM tm : tmList
        ) {
            total += tm.getTotalCost();
        }
        lblTotal.setText(String.valueOf(total));
    }
    public void btnPlaceOrder_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ArrayList<OrderDetailsDTO> items=new ArrayList<>();
        double total=0;
        for (CartTM tempTm:obList) {
            total+=tempTm.getTotalCost();
            items.add(new OrderDetailsDTO(tempTm.getCode(), tempTm.getDescription(),tempTm.getQty(), tempTm.getUnitPrice()));
        }
        OrderDTO order=new OrderDTO(lblId.getText(),cmbCustomerId.getValue(),lblDate.getText(),items);
        if (placeOrderBO.placeOrder(order)){
            new Alert(Alert.AlertType.CONFIRMATION, "Success....!").show();
            setOrderId();
            //System.out.println(order);
        }else{
            new Alert(Alert.AlertType.WARNING, "Try Again....!").show();
        }
    }

    private void setOrderId() throws SQLException, ClassNotFoundException {
        lblId.setText(placeOrderBO.generateNewOrderId());

    }

    ObservableList<CartTM> obList= FXCollections.observableArrayList();

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());
        int qty = Integer.parseInt(txtQty.getText());
        double totalCost = unitPrice * qty;
        CartTM isExists = isExists(cmbItemCode.getValue());

        if (isExists != null) {
            for (CartTM temp : tmList
            ) {
                if (temp.equals(isExists)) {
                    temp.setQty((temp.getQty() + qty));
                    temp.setTotalCost(temp.getTotalCost() + totalCost);
                }
            }
        } else {
            Button btn = new Button("Delete");
            CartTM tm = new CartTM(
                    cmbItemCode.getValue(),
                    txtDescription.getText(),
                    unitPrice,
                    qty,
                    totalCost,
                    btn
            );
            btn.setOnAction(e -> {

                tmList.remove(tm);


            });

            tmList.add(tm);
            tblOrderDetails.setItems(tmList);
        }
        tblOrderDetails.refresh();
        calculateTotal();
        QtyChange();

    }

    private void QtyChange() {
        int value = Integer.parseInt(txtQtyOnHand.getText());
        if (!txtQty.getText().equals("") & (value > 0)) {
            int q = Integer.parseInt(txtQty.getText());
            int q2 = Integer.parseInt(txtQtyOnHand.getText());
            int result = q2 - q;

            if (result <= 0) {
                new Alert(Alert.AlertType.WARNING, "Out Of Stock...!").show();
            } else {
                txtQtyOnHand.setText(String.valueOf(result));
            }
        }
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) contest.getScene().getWindow();
        stage.close();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"))));
        stage.show();
    }

    private void clear(){
        txtCustomerName.clear();
        txtDescription.clear();
        txtQtyOnHand.clear();
        txtUnitPrice.clear();
        txtQty.clear();
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
