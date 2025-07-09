package com.example.layeredarchitecture.dao;

import com.example.layeredarchitecture.db.DBConnection;
import com.example.layeredarchitecture.model.ItemDTO;
import com.example.layeredarchitecture.view.tdm.ItemTM;
import javafx.scene.control.TableView;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public  ArrayList<ItemDTO> getLoadAllItems() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        Statement stm = connection.createStatement();
//        ResultSet rst = stm.executeQuery("SELECT * FROM item");

        ResultSet rst = SQLUtil.executeQuery("SELECT * FROM item");
        ArrayList<ItemDTO>items = new ArrayList<>();
        while (rst.next()) {
//           String code =  rst.getString("code");
//           String desc = rst.getString("description");
//           BigDecimal unitPrice =  rst.getBigDecimal("unitPrice");
//           int qtyOnHand = rst.getInt("qtyOnHand");
//           items.add(new ItemDTO(code, desc, unitPrice, qtyOnHand));
            ItemDTO itemDTO = new ItemDTO(rst.getString("code") , rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
            items.add(itemDTO);
        }
        return items;
    }

    @Override
    public boolean saveItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeUpdate("INSERT INTO Item (code,description, unitPrice , qtyOnHand) VALUES (?,?,?,?)"
                ,itemDTO.getCode(),itemDTO.getDescription(),itemDTO.getUnitPrice() , itemDTO.getQtyOnHand());
    }

    @Override
    public boolean updateItem(ItemDTO itemDTO) throws SQLException, ClassNotFoundException {
       return SQLUtil.executeUpdate("UPDATE Item SET description=?, unitPrice=? , qtyOnHand = ? WHERE code=?");
    }

    @Override
    public boolean deleteItem(String code) throws SQLException, ClassNotFoundException {
      return SQLUtil.executeUpdate("DELETE FROM Item WHERE code=?", code);
    }

    @Override
    public boolean getExitItem(String code) throws SQLException, ClassNotFoundException {
        return SQLUtil.executeQuery("SELECT code FROM Item WHERE code=?",code).next();
    }

    @Override
    public String getGenerateItemId() throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        ResultSet rst = connection.createStatement().executeQuery("SELECT code FROM item ORDER BY code DESC LIMIT 1;");
        ResultSet rst = SQLUtil.executeQuery("SELECT code FROM Item ORDER BY code DESC LIMIT 1");
        if (rst.next()) {
            String id = rst.getString("code");
            int newItemId = Integer.parseInt(id.replace("I00-", "")) + 1;
            return String.format("I00-%03d", newItemId);
        } else {
            return "I00-001";
        }
    }

    @Override
    public ItemDTO searchItem(String code) throws SQLException, ClassNotFoundException {
//        Connection connection = DBConnection.getDbConnection().getConnection();
//        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
//        pstm.setString(1, code);
//        ResultSet rst = pstm.executeQuery();

       ResultSet rst = SQLUtil.executeQuery("SELECT * FROM Item WHERE code=?",code);
       rst.next();
       return new ItemDTO(code , rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));

    }

    @Override
    public  String getLastItemId(TableView<ItemTM> tblItems) {
        List<ItemTM> tempItemsList = new ArrayList<>(tblItems.getItems());
        Collections.sort(tempItemsList, (o1, o2) -> o1.getCode().compareTo(o2.getCode()));
        return tempItemsList.get(tempItemsList.size() - 1).getCode();
    }


}
