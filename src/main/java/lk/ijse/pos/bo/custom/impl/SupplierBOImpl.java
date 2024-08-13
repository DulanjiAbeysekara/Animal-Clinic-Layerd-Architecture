package lk.ijse.pos.bo.custom.impl;

import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.dao.DAOFactory;
import lk.ijse.pos.dao.custom.SupplierDAO;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO =  DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> all = supplierDAO.getAll();
        ArrayList<SupplierDTO> arrayList = new ArrayList<>();

        for (Supplier c : all) {
            arrayList.add(new SupplierDTO(c.getId(), c.getName(), c.getSupplierMobileNum(), c.getNameOfTheSupplierCompany()));
        }

        return arrayList;
    }


    @Override
    public boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.save(new Supplier(dto.getId(), dto.getName(), dto.getSupplierMobileNum(), dto.getNameOfTheSupplierCompany()));
    }

    @Override
    public boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName(), dto.getSupplierMobileNum(), dto.getNameOfTheSupplierCompany() ));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.delete(id);

    }

    @Override
    public boolean existByID(String id) throws SQLException, ClassNotFoundException {
        return supplierDAO.exist(id);
    }
}
