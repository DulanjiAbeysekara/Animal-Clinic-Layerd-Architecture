package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.PetItemDAO;
import lk.ijse.pos.dto.PetItemDTO;
import lk.ijse.pos.entity.PetItem;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {


    PetItemDAO petItemDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public ArrayList<PetItemDTO> getAllItem() throws SQLException, ClassNotFoundException {
        ArrayList<PetItem> all = petItemDAO.getAll();
        ArrayList<PetItemDTO> arrayList = new ArrayList<>();

        for (PetItem c : all) {
            arrayList.add(new PetItemDTO(c.getId(), c.getName(), c.getBrand(), c.getDatOfIssue(), c.getExpiryDate(), c.getSellingPrice(),c.getPurchasePrice(),c.getQty()));
        }

        return arrayList;
    }

    @Override
    public boolean saveItem(PetItemDTO dto) throws SQLException, ClassNotFoundException {
        return petItemDAO.save(new PetItem(dto.getId(), dto.getName(), dto.getBrand(), dto.getDatOfIssue(), dto.getExpiryDate(), dto.getSellingPrice(),dto.getPurchasePrice(), dto.getQty()));
    }


    @Override
    public boolean updateItem(PetItemDTO dto) throws SQLException, ClassNotFoundException {
        return petItemDAO.update(new PetItem(dto.getId(), dto.getName(), dto.getBrand(), dto.getDatOfIssue(), dto.getExpiryDate(), dto.getSellingPrice(),dto.getPurchasePrice(), dto.getQty()));
    }

    @Override
    public boolean existByID(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public List<PetItem> getAll() throws SQLException, ClassNotFoundException {
        return null;
    }


}
