package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.SurgeryDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Surgery;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SurgeryDAOImpl implements SurgeryDAO {

    @Override
    public ArrayList<Surgery> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Surgery> allSurgery = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Surgery");
        while (rst.next()) {
            allSurgery.add(new Surgery(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6)));
        }
        return allSurgery;
    }

    @Override
    public boolean save(Surgery entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Surgery (SurgeryId,SurgeryName,SurgerySchedule,Reason,Price,DoctorId) VALUES (?,?,?)", entity.getId(), entity.getName(), entity.getSchedule(),entity.getReason(),entity.getPrice(),entity.getDoctorId());
    }

    @Override
    public boolean update(Surgery entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Surgery SET SurgeryName=?, SurgerySchedule=?,Reason=?,Price=?,DoctorId=? WHERE SurgeryId=?",  entity.getName(), entity.getSchedule(),entity.getReason(),entity.getPrice(),entity.getDoctorId(),entity.getId());
    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT SurgeryId FROM Surgery WHERE SurgeryId=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Surgery WHERE SurgeryId=?", id);
    }

}
