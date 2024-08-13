package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> allSupplier = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM supplier");

        while (rst.next()) {
            allSupplier.add(new Supplier(rst.getString(1), rst.getString(2), rst.getInt(3), rst.getString(4)));
        }
        return allSupplier;
    }

    @Override
    public boolean save(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO supplier(SupplierId,SupplierName,SupplierMobileNum,NameOfSupplierCompany) VALUES (?,?,?,?)", entity.getId(), entity.getName(), entity.getSupplierMobileNum(), entity.getNameOfTheSupplierCompany());

    }

    @Override
    public boolean update(Supplier entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE supplier SET SupplierName = ?, SupplierMobileNum = ?, NameOfSupplierCompany = ? WHERE SupplierId = ?", entity.getName(), entity.getSupplierMobileNum(), entity.getNameOfTheSupplierCompany(), entity.getId());
    }




    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM supplier WHERE SupplierId=?", id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SupplierId FROM supplier WHERE SupplierId=?", id);
        return rst.next();
    }
}
