package lk.ijse.NiharaShoe.dao.custom;

import lk.ijse.NiharaShoe.dao.CrudDAO;
import lk.ijse.NiharaShoe.entity.Item;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface ItemDAO extends CrudDAO<Item,String> {
    String getNewItem() throws SQLException,ClassNotFoundException;
    List<String> getItemIds() throws SQLException, ClassNotFoundException;
    void updateQty(String id,int qty) throws SQLException, ClassNotFoundException;
}
