package controller;

import db.DbConnection;
import model.Customer;
import model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemController implements ItemManage {
    public List<String> getAllItemIds() throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item").executeQuery();
        List<String> ids= new ArrayList<>();
        while (rst.next()){
            ids.add(
                    rst.getString(1)
            );
        }
        return ids;
    }


    @Override
    public boolean saveItem(Item i) throws SQLException, ClassNotFoundException {
        Connection con= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Item VALUES(?,?,?,?,?)";
        PreparedStatement stm = con.prepareStatement(query);
        stm.setObject(1,i.getItemCode());
        stm.setObject(2,i.getDescription());
        stm.setObject(3,i.getPackSize());
        stm.setObject(4,i.getQtyOnHand());
        stm.setObject(5,i.getUnitPrice());



        return stm.executeUpdate()>0;
    }

    @Override
    public boolean updateItem(Item i) throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("UPDATE Item SET description=?, packSize=?, qtyOnHand=?,unitPrice=? WHERE itemCode=?");
        stm.setObject(1,i.getDescription());
        stm.setObject(2,i.getPackSize());
        stm.setObject(3,i.getQtyOnHand());
        stm.setObject(4,i.getUnitPrice());
        stm.setObject(5,i.getItemCode());
        return stm.executeUpdate()>0;
    }

    @Override
    public boolean deleteItem(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Item WHERE itemCode='" + id + "'").executeUpdate() > 0){
            return true;
        }else{
            return false;
        }
    }

    public Item getItem(String itemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = DbConnection.getInstance().getConnection().
                prepareStatement("SELECT * FROM Item WHERE itemCode='" + itemCode+ "'").
                executeQuery();
        if (rst.next()){
            return new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)


            );
        }else{
            return null;
        }
    }

    @Override
    public ArrayList<Item> getAllItem() throws SQLException, ClassNotFoundException {
        PreparedStatement stm = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Item");
        ResultSet rst = stm.executeQuery();
        ArrayList<Item> items = new ArrayList<>();
        while (rst.next()) {
            items.add(new Item(
                    rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getInt(4),
                    rst.getDouble(5)

            ));
        }
//        System.out.println(items);
        return items;
    }


}
