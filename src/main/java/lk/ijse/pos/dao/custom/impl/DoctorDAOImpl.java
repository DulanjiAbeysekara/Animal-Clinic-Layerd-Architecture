package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.DoctorDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorDAOImpl implements DoctorDAO {
    @Override
    public ArrayList<Doctor> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> allDoctors = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Doctor");
        while (rst.next()) {
            allDoctors.add(new Doctor(rst.getString(1), rst.getString(2), rst.getString(3),rst.getInt(4)));
        }
        return allDoctors;
    }

    @Override
    public boolean save(Doctor entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Doctor (DoctorId,DoctorName,Schedule ,MobileNum) VALUES (?,?,?,?)", entity.getId(), entity.getName(), entity.getSchedule());
    }

    @Override
    public boolean update(Doctor entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Doctor SET DoctorName=?, Schedule=?,MobileNum=? WHERE DoctorId=?", entity.getName(), entity.getSchedule(), entity.getMobileNum(),entity.getId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT id FROM Doctor WHERE DoctorId=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Doctor WHERE DoctorId=?", id);
    }


}
