package lk.ijse.pos.dto;

public class SupplierDTO {
    private String id;
    private String name;
    private int supplierMobileNum;
    private String  nameOfTheSupplierCompany;

    public SupplierDTO() {
    }

    public SupplierDTO(String id, String name, int supplierMobileNum, String nameOfTheSupplierCompany) {
        this.id = id;
        this.name = name;
        this.supplierMobileNum = supplierMobileNum;
        this.nameOfTheSupplierCompany = nameOfTheSupplierCompany;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplierMobileNum() {
        return supplierMobileNum;
    }

    public void setSupplierMobileNum(int supplierMobileNum) {
        this.supplierMobileNum = supplierMobileNum;
    }

    public String getNameOfTheSupplierCompany() {
        return nameOfTheSupplierCompany;
    }

    public void setNameOfTheSupplierCompany(String nameOfTheSupplierCompany) {
        this.nameOfTheSupplierCompany = nameOfTheSupplierCompany;
    }

    @Override
    public String toString() {
        return "SupplierDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", supplierMobileNum=" + supplierMobileNum +
                ", nameOfTheSupplierCompany='" + nameOfTheSupplierCompany + '\'' +
                '}';
    }
}
