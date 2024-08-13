package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.PetBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.PetDAO;
import lk.ijse.pos.dto.PetDTO;
import lk.ijse.pos.entity.Pet;

import java.sql.SQLException;
import java.util.ArrayList;

public class PetBOImpl implements PetBO {


    PetDAO petDAO = DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.PET);

    @Override
    public ArrayList<PetDTO> getAllPets() throws SQLException, ClassNotFoundException {
        ArrayList<Pet> all = petDAO.getAll();
        ArrayList<PetDTO> arrayList = new ArrayList<>();
        for (Pet c : all) {
            arrayList.add(new PetDTO(c.getId(), c.getName(), c.getAge(), c.getBreed(), c.getDateOfBirth(), c.getGender(), c.getCustomerId()));
        }
        return arrayList;

    }

    @Override
    public boolean savePet(PetDTO dto) throws SQLException, ClassNotFoundException {
        return petDAO.save( new Pet(dto.getId(), dto.getName(),dto.getAge(),dto.getBreed(),dto.getDateOfBirth(),dto.getGender(),dto.getCustomerId()));
    }
    @Override
    public boolean updatePet(PetDTO dto) throws SQLException, ClassNotFoundException {
        return petDAO.update( new Pet(dto.getId(), dto.getName(),dto.getAge(),dto.getBreed(),dto.getDateOfBirth(),dto.getGender(),dto.getCustomerId()));
    }

    @Override
    public boolean existByID(String id) throws SQLException, ClassNotFoundException {
        return petDAO.exist(id);
    }

    @Override
    public boolean deletePet(String id) throws SQLException, ClassNotFoundException {
        return petDAO.delete(id);
    }
}