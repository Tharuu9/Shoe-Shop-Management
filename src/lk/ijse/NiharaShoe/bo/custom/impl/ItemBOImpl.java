package lk.ijse.NiharaShoe.bo.custom.impl;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lk.ijse.NiharaShoe.bo.custom.ItemBO;
import lk.ijse.NiharaShoe.dao.DAOFactory;
import lk.ijse.NiharaShoe.dao.custom.ItemDAO;
import lk.ijse.NiharaShoe.dto.ItemDTO;
import lk.ijse.NiharaShoe.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    private final ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOType.ITEM);

    @Override
    public boolean addNewItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.add(new Item(dto.getCode(), dto.getQtyOnHand(), dto.getDescription(), dto.getUnitPrice(), dto.getSupplierID()));
    }

    @Override
    public boolean deleteItem(String s) throws SQLException, ClassNotFoundException {
        return itemDAO.delete(s);
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException {
        return itemDAO.update(new Item(dto.getCode(), dto.getQtyOnHand(), dto.getDescription(), dto.getUnitPrice(), dto.getSupplierID()));
    }

    @Override
    public Item searchItem(String s) throws SQLException, ClassNotFoundException {
        return itemDAO.search(s);
    }

    @Override
    public String getNewItemID() throws SQLException, ClassNotFoundException {
        return itemDAO.getNewItem();
    }

    @Override
    public ObservableList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<Item> itemTMS = itemDAO.getAll();
        ObservableList<Item> obList = FXCollections.observableArrayList();

        itemTMS.forEach(e -> obList.addAll(new Item(e.getCode(), e.getQtyOnHand(), e.getDescription(), e.getUnitPrice(),
                e.getSupplierID())));
        return obList;
    }
}

