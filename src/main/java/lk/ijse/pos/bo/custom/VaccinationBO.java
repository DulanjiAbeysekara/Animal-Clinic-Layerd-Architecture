package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.VaccinationDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface VaccinationBO {

    ArrayList<VaccinationDTO> getAllVaccination() throws SQLException, ClassNotFoundException;

    boolean saveVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deleteVaccination(String id) throws SQLException, ClassNotFoundException;
}
