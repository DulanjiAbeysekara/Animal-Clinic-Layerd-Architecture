package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.DoctorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorBO {

    ArrayList<DoctorDTO> getAllDoctors() throws SQLException, ClassNotFoundException;

    boolean saveDoctor(DoctorDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateDoctor(DoctorDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException;

}
