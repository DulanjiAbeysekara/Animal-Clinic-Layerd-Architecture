package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.custom.PetDAO;
import lk.ijse.pos.dao.custom.impl.util.SQLUtil;
import lk.ijse.pos.entity.Pet;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PetDAOImpl implements PetDAO {


    @Override
    public ArrayList<Pet> getAll() throws SQLException, ClassNotFoundException {
        ArrayList<Pet> allPets = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT * FROM Pet");
        while (rst.next()) {
            allPets.add(new Pet(rst.getString(1), rst.getString(2), rst.getString(3),rst.getString(4),rst.getString(5),rst.getString(6),rst.getString(7)));

        }
        return allPets;
    }


    @Override
    public boolean save(Pet entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("INSERT INTO Pet (PetId,PetName,Age ,Breed,DateOfBrith,Gender,CustomerId) VALUES (?,?,?,?,?,?,?,?)", entity.getId(), entity.getName(), entity.getAge(),entity.getBreed(),entity.getDateOfBirth(),entity.getGender(),entity.getCustomerId());
    }


    @Override
    public boolean update(Pet entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE Pet SET PetName=?, Age=?,Breed=?,DateOfBrith=?,Gender=?,CustomerId=? WHERE petId=?", entity.getName(), entity.getAge(), entity.getId());

    }

    @Override
    public boolean exist(String id) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT PetId FROM Pet WHERE PetId=?", id);
        return rst.next();
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("DELETE FROM Pet WHERE PetId=?", id);
    }
}
