package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.VaccinationDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Vaccination;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VaccinationDAOImpl implements VaccinationDAO {
    @Override
    public ArrayList<Vaccination> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Vaccination> allVaccination = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Vaccination");
        while (rst.next()) {
            allVaccination.add(new Vaccination(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7)));
        }
        return allVaccination;
    }
    @Override
    public boolean save(Vaccination entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Vaccination (VaccinationId,VaccinatioName,Price ,Brand,ExpiryDate,DateOfIssue,DoctorId) VALUES (?,?,?,?,?,?,?)", entity.getId(), entity.getName(), entity.getPrice(),entity.getBrand(),entity.getExpiryDate(),entity.getDateOfIssue(),entity.getDoctorId());
    }


    @Override
    public boolean update(Vaccination entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Vaccination SET VaccinatioName=?, Price=?,Brand=?,ExpiryDate=?,DateOfIssue=?,DoctorId=? WHERE VaccinationId=?",entity.getId(), entity.getName(), entity.getPrice(),entity.getBrand(),entity.getExpiryDate(),entity.getDateOfIssue(),entity.getDoctorId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT VaccinationId FROM Vaccination WHERE VaccinationId=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Vaccination WHERE VaccinationId=?", id);
    }
}


