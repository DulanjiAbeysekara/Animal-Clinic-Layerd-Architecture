package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.VaccinationBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.VaccinationDAO;
import lk.ijse.pos.dto.VaccinationDTO;
import lk.ijse.pos.entity.Vaccination;

import java.sql.SQLException;
import java.util.ArrayList;

public class VaccinationBOImpl implements VaccinationBO {

    VaccinationDAO vaccinationDAO= DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.VACCINATION);

    @Override
    public ArrayList<VaccinationDTO> getAllVaccination() throws SQLException, ClassNotFoundException {
        ArrayList<Vaccination> all = vaccinationDAO.getAll();
        ArrayList<VaccinationDTO> arrayList = new ArrayList<>();

        for (Vaccination c : all) {
            arrayList.add(new VaccinationDTO(c.getId(), c.getName(), c.getPrice(), c.getBrand(), c.getExpiryDate(), c.getDateOfIssue(),c.getDoctorId()));
        }

        return arrayList;
    }

    @Override
    public boolean saveVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.save(new Vaccination(dto.getId(), dto.getName(), dto.getPrice(), dto.getBrand(), dto.getExpiryDate(), dto.getDateOfIssue(),dto.getDoctorId()));
    }

    @Override
    public boolean updateVaccination(VaccinationDTO dto) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.update(new Vaccination(dto.getId(), dto.getName(),dto.getPrice(), dto.getBrand(), dto.getExpiryDate(), dto.getDateOfIssue(),dto.getDoctorId()));
    }

    @Override
    public boolean deleteVaccination(String id) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.delete(id);

    }

    @Override
    public boolean existByID(String id) throws SQLException, ClassNotFoundException {
        return vaccinationDAO.exist(id);
    }
}

