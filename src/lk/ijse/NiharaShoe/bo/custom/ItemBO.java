package lk.ijse.NiharaShoe.bo.custom;

import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.SuperBO;
import lk.ijse.NiharaShoe.dto.ItemDTO;

import lk.ijse.NiharaShoe.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    boolean addNewItem(ItemDTO dto) throws SQLException,ClassNotFoundException;

    boolean deleteItem(String s) throws SQLException,ClassNotFoundException;

    boolean updateItem(ItemDTO dto) throws SQLException,ClassNotFoundException;

    Item searchItem(String s)throws SQLException,ClassNotFoundException;

    String getNewItemID() throws SQLException, ClassNotFoundException;

    ObservableList<Item> getAllItem() throws SQLException, ClassNotFoundException;

}
