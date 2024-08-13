package lk.ijse.pos.bo.custom;

import lk.ijse.pos.dto.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO {

    ArrayList<SupplierDTO> getAllSupplier() throws SQLException, ClassNotFoundException;

    boolean saveSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean updateSupplier(SupplierDTO dto) throws SQLException, ClassNotFoundException;

    boolean existByID(String id) throws SQLException, ClassNotFoundException;

    boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;
}

