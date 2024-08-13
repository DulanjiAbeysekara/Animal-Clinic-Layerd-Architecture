package lk.ijse.pos.dao;

import lk.ijse.pos.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoObejectCreator;
    private DAOFactory(){

    }
    public static DAOFactory getDAOFactory(){
        if(daoObejectCreator==null){
        daoObejectCreator=new DAOFactory();
        }
        return daoObejectCreator;
    }

    public enum DAOTypes{
        CUSTOMER,DOCTOR,PET,SURGERY,VACCINATION,ITEM,SUPPLIER,QUERY_DAO
    }

    public <T extends SuperDAO> T getDAO(DAOTypes res){
        switch (res){

            case CUSTOMER:
                return (T) new CustomerDAOImpl();

            case DOCTOR:
                return (T) new  DoctorDAOImpl();

            case PET:
                return (T) new PetDAOImpl();

            case SURGERY:
                return (T) new SurgeryDAOImpl();

            case VACCINATION:
                return (T) new VaccinationDAOImpl();

            case ITEM:
                return (T) new PetItemDAOImpl();

            case SUPPLIER:
                return (T) new SupplierDAOImpl();
            default:
                return null;
        }

    }
}
