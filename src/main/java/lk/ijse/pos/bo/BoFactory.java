package lk.ijse.pos.bo;

import lk.ijse.pos.bo.custom.impl.*;

public class BoFactory {
        private static BoFactory boFactory;

        private BoFactory(){

        }

    public static BoFactory getBoFactory() {
        return (boFactory == null) ? boFactory = new BoFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER_BO,DOCTOR_BO,PET_BO,SURGERY_BO,VACCINATION_BO,ITEM_BO,SUPPLIER_BO
    }
    public<T extends SuperBO>T getBO(BOTypes boTypes){
        switch (boTypes){
            case CUSTOMER_BO:
                return (T) new CustomerBOImpl();

            case DOCTOR_BO:
                return (T) new DoctorBOImpl();

            case PET_BO:
                return (T) new PetBOImpl();

            case SURGERY_BO:
                return (T) new SurgeryBOImpl();

            case VACCINATION_BO:
                return (T) new VaccinationBOImpl();

            case ITEM_BO:
                return (T) new ItemBOImpl();

            case SUPPLIER_BO:
                return (T) new SupplierBOImpl();

            default:
                return null;

        }

    }
}
