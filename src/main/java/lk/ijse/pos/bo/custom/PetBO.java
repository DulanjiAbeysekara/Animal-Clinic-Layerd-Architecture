package lk.ijse.pos.bo.custom;

import lk.ijse.pos.bo.SuperBO;
import lk.ijse.pos.dto.PetDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PetBO extends SuperBO {

    ArrayList<PetDTO> getAllPets() throws SQLException, ClassNotFoundException;

    boolean savePet(PetDTO dto) throws SQLException, ClassNotFoundException;

    boolean updatePet(PetDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deletePet(String id) throws SQLException, ClassNotFoundException;


}
