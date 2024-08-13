package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.SurgeryDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SurgeryBO {

    ArrayList<SurgeryDTO> getAllSurgery() throws SQLException, ClassNotFoundException;

    boolean saveSurgery(SurgeryDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSurgery(SurgeryDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deleteSurgery(String id) throws SQLException, ClassNotFoundException;
}
