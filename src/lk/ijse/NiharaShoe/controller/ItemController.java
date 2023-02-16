package lk.ijse.NiharaShoe.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.NiharaShoe.bo.BOFactory;
import lk.ijse.NiharaShoe.bo.custom.CustomerBO;
import lk.ijse.NiharaShoe.bo.custom.ItemBO;
import lk.ijse.NiharaShoe.dto.ItemDTO;
import lk.ijse.NiharaShoe.entity.Item;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController {
    public TableColumn colItemCode;
    public TableColumn colDesc;
    public TableColumn colUnitPrice;
    public TableColumn colQty;
    public TableColumn colSupId;
    public JFXTextField txtId;
    public JFXTextField txtName;
    public JFXTextField txtPrice;
    public JFXTextField txtQty;
    public JFXTextField txtSupId;
    public TableView<Item> tblItem;

    private final ItemBO itemBo=(ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOType.ITEM_CONTROLLER);

    public void initialize() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qtyOnHand"));
        colDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colSupId.setCellValueFactory(new PropertyValueFactory<>("supplierID"));
        try {
            loadAllItem();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblItem.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                setValues(newValue);
            }
        });
        setNewItemId();
    }

    private void setNewItemId() {
        try{
            txtId.setText(getNewItem());
        }catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
    }
    private String getNewItem() throws SQLException, ClassNotFoundException {
        return itemBo.getNewItemID();
    }
    private void setValues(Item i) {
        txtId.setText(i.getCode());
        txtQty.setText(String.valueOf(i.getQtyOnHand()));
        txtName.setText(i.getDescription());
        txtPrice.setText(String.valueOf(i.getUnitPrice()));
        txtSupId.setText(i.getSupplierID());
    }

    private void clear() {
        txtId.clear();
        txtQty.clear();
        txtName.clear();
        txtPrice.clear();
        txtSupId.clear();
        tblItem.refresh();
    }

    private void loadAllItem() throws SQLException, ClassNotFoundException {
        tblItem.setItems(itemBo.getAllItem());
    }


    public void AddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        ItemDTO item = new ItemDTO(txtId.getText(),Integer.parseInt(txtQty.getText()), txtName.getText(), Double.parseDouble(txtPrice.getText()), txtSupId.getText());
        try {
            if (itemBo.addNewItem(item))
            new Alert(Alert.AlertType.CONFIRMATION, "Saved Successed").show();


        } catch (ClassNotFoundException | SQLException e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Try Again!").show();
            e.printStackTrace();
        }
        clear();
        loadAllItem();
    }

    public void UpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ItemDTO item = new ItemDTO(
                txtId.getText(), Integer.parseInt(txtQty.getText()), txtName.getText(), Double.parseDouble(txtPrice.getText()), txtSupId.getText());
        try {
            boolean isUpdated = itemBo.updateItem(item);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated..!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again..!").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllItem();
    }

    public void DeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        try {
            boolean isDeleted = itemBo.deleteItem(txtId.getText());
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted !").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Try Again !").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        loadAllItem();
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
            Item i = itemBo.searchItem(txtId.getText());
            if (i!=null) {
                txtQty.setText(String.valueOf(i.getQtyOnHand()));
                txtName.setText(i.getDescription());
                txtPrice.setText(String.valueOf(i.getUnitPrice()));
                txtSupId.setText(i.getSupplierID());
            } else {
                new Alert(Alert.AlertType.WARNING, "Empty Results").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void ClearOnAction(ActionEvent actionEvent) {
        clear();
    }
}
