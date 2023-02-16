package lk.ijse.NiharaShoe.dao.custom.impl;

import lk.ijse.NiharaShoe.dao.custom.ItemDAO;
import lk.ijse.NiharaShoe.entity.Customer;
import lk.ijse.NiharaShoe.entity.Item;
import lk.ijse.NiharaShoe.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    public boolean add(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("INSERT INTO Item VALUES (?,?,?,?,?)", item.getCode(), item.getQtyOnHand(), item.getDescription(), item.getUnitPrice(), item.getSupplierID());
    }
    public boolean delete(String s) throws SQLException, ClassNotFoundException{
        return CrudUtil.executeUpdate("DELETE FROM Item WHERE  itemID=?", s);
    }

    @Override
    public boolean update(Item item) throws SQLException, ClassNotFoundException {
        return CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand =?, itemName =?,supplierID =?,itemPrice =? WHERE  itemID =?", item.getQtyOnHand(), item.getDescription(), item.getSupplierID(), item.getUnitPrice(), item.getCode());
    }

    @Override
    public Item search(String s) throws SQLException, ClassNotFoundException {
        ResultSet rst= CrudUtil.executeQuery("SELECT * FROM Item WHERE itemID=?", s);
        rst.next();
        return new Item(rst.getString(1),rst.getInt(2),rst.getString(3),rst.getDouble(4),rst.getString(5));
    }

    @Override
    public ArrayList<Item> getAll() throws SQLException, ClassNotFoundException {
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Item");
        ArrayList<Item> allItems=new ArrayList<>();
        while (rst.next()){
            allItems.add(new Item( rst.getString(1), rst.getInt(2),
                    rst.getString(3),
                    rst.getDouble(4),
                    rst.getString(5)
            ));
        }
        return allItems;
    }

    @Override
    public String getNewItem() throws SQLException, ClassNotFoundException {
        String lastItemId=getLastId();
        if(lastItemId==null){
            return "I-0001";
        }else {
            String[] split=getLastId().split("[I][-]");
            int lastDigits = Integer.parseInt(split[1]);
            lastDigits++;
            String newItemId=String.format("I-%04d",lastDigits);
            return newItemId;
        }
    }

    public String getLastId() throws SQLException, ClassNotFoundException {
        ResultSet rs = CrudUtil.executeQuery("SELECT itemID from Item order by itemID DESC limit 1");
        if(rs.next()){
            return rs.getString(1);
        }
        return null;
    }

    @Override
    public List<String> getItemIds() throws SQLException, ClassNotFoundException {
        List <String> ids=new ArrayList<>();
        ResultSet rst=CrudUtil.executeQuery("SELECT * FROM Item");
        while (rst.next()){
            ids.add(rst.getString(1));

        }
        return ids;
    }

    @Override
    public void updateQty(String id, int qty) throws SQLException, ClassNotFoundException {
        CrudUtil.executeUpdate("UPDATE Item SET QtyOnHand=(qtyOnHand-"+qty+ ") WHERE itemID=?");

    }
}
