package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public ArrayList<Customer> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Customer> allCustomers = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer");

        while (rst.next()) {
            allCustomers.add(new Customer(rst.getString(1), rst.getString(2), rst.getString(3), rst.getInt(4), rst.getString(5), rst.getString(6)));
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO customer(CustomerId,CustomerName,Address,MobileNumber,AppoinmentDate,Email) VALUES (?,?,?,?,?,?)", entity.getId(), entity.getName(), entity.getAddress(), entity.getMobileNum(), entity.getAppointmentDate(), entity.getEmail());

    }

    @Override
    public boolean update(Customer entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Customer SET CustomerName = ?, Address = ?, MobileNumber = ?,AppoinmentDate=?,Email=? WHERE CustomerId = ?", entity.getName(), entity.getAddress(), entity.getMobileNum(), entity.getAppointmentDate(), entity.getEmail(), entity.getId());
    }


    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Customer WHERE CustomerId=?", id);
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT CustomerId FROM Customer WHERE CustomerId=?", id);
        return rst.next();
    }
}
