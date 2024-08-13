package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SurgeryBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.SurgeryDAO;
import lk.ijse.pos.dto.SurgeryDTO;
import lk.ijse.pos.entity.Surgery;

import java.sql.SQLException;
import java.util.ArrayList;

public class SurgeryBOImpl implements SurgeryBO {

    SurgeryDAO surgeryDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SURGERY);
    @Override
    public ArrayList<SurgeryDTO> getAllSurgery() throws SQLException, ClassNotFoundException {
        ArrayList<Surgery> all = surgeryDAO.getAll();
        ArrayList<SurgeryDTO> arrayList= new ArrayList<>();
        for (Surgery c : all) {
            arrayList.add(new SurgeryDTO(c.getId(),c.getName(),c.getSchedule(),c.getReason(),c.getPrice(),c.getDoctorId()));
        }
        return arrayList;
    }

    @Override
    public boolean saveSurgery(SurgeryDTO dto) throws SQLException, ClassNotFoundException {
        return surgeryDAO.save( new Surgery(dto.getId(), dto.getName(),dto.getSchedule(),dto.getReason(),dto.getPrice(),dto.getDoctorId()));
    }

    @Override
    public boolean updateSurgery(SurgeryDTO dto) throws SQLException, ClassNotFoundException {
        return surgeryDAO.update( new Surgery(dto.getId(), dto.getName(),dto.getSchedule(),dto.getReason(),dto.getPrice(),dto.getDoctorId()));
    }

    @Override
    public boolean existByID(String id) throws SQLException, ClassNotFoundException {
        return surgeryDAO.exist(id);
    }

    @Override
    public boolean deleteSurgery(String id) throws SQLException, ClassNotFoundException {
        return surgeryDAO.delete(id);
    }



}
