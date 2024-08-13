package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.DoctorBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.DoctorDAO;
import lk.ijse.pos.dto.DoctorDTO;
import lk.ijse.pos.entity.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorBOImpl implements DoctorBO {

    DoctorDAO doctorDAO=DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.DOCTOR);

    @Override
    public ArrayList<DoctorDTO> getAllDoctors() throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> all = doctorDAO.getAll();
        ArrayList<DoctorDTO> arrayList= new ArrayList<>();
        for (Doctor c : all) {
            arrayList.add(new DoctorDTO(c.getId(),c.getName(),c.getSchedule(),c.getMobileNum()));
        }
        return arrayList;
    }


    @Override
    public boolean saveDoctor(DoctorDTO dto) throws SQLException, ClassNotFoundException {
        return doctorDAO.save( new Doctor(dto.getId(), dto.getName(),dto.getSchedule(),dto.getMobileNum()));
    }

    @Override
    public boolean updateDoctor(DoctorDTO dto) throws SQLException, ClassNotFoundException {
        return doctorDAO.update( new Doctor(dto.getId(), dto.getName(),dto.getSchedule(),dto.getMobileNum()));
    }

    @Override
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {
        return doctorDAO.delete(id);
    }

    @Override
    public boolean existByID(String id) throws SQLException, ClassNotFoundException {
        return doctorDAO.exist(id);
    }


}
