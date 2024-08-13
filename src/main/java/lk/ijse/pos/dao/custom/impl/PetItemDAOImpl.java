package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.PetItemDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.PetItem;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetItemDAOImpl implements PetItemDAO {


    @Override
    public ArrayList<PetItem> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<PetItem> allPetItem = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item");
        while (rst.next()) {
            allPetItem.add(new PetItem(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8)));

        }
        return allPetItem;
    }

    @Override
    public boolean save(PetItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO item (ItemId,ItemName,BrandName ,DateOfIssue,ExpiryDate,PurchasePrice,SellingPrice,Qty) VALUES (?,?,?,?,?,?,?,?)", entity.getId(), entity.getName(), entity.getBrand(), entity.getDatOfIssue(), entity.getExpiryDate(), entity.getPurchasePrice(), entity.getSellingPrice(), entity.getQty());
    }


    @Override
    public boolean update(PetItem entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE item SET ItemName=?, BrandName=?,DateOfIssue=?,ExpiryDate=?,PurchasePrice=?,SellingPrice=?,Qty=? WHERE petId=?", entity.getId(), entity.getName(), entity.getBrand(), entity.getDatOfIssue(), entity.getExpiryDate(), entity.getPurchasePrice(), entity.getSellingPrice(), entity.getQty());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT ItemId FROM item WHERE ItemId=?", id);
        return rst.next();
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM item WHERE ItemId=?", id);
    }

}
