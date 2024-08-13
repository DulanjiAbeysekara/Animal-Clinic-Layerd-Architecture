package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.PetItemDTO;
import lk.ijse.pos.entity.PetItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO {

    ArrayList<PetItemDTO> getAllItem() throws SQLException, ClassNotFoundException;

    boolean saveItem(PetItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateItem(PetItemDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException;

    List<PetItem> getAll()throws SQLException, ClassNotFoundException;
}
