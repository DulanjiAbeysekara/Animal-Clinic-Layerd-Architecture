package lk.ijse.pos.entity;


public class Vaccination {
    private String id;
    private String name;
    private String price;
    private String brand;
    private String expiryDate;
    private String dateOfIssue;
    private String doctorId;

    public Vaccination() {
    }

    public Vaccination(String id, String name, String price, String brand, String expiryDate, String dateOfIssue, String doctorId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.expiryDate = expiryDate;
        this.dateOfIssue = dateOfIssue;
        this.doctorId = doctorId;
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(String dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "Vaccination{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price='" + price + '\'' +
                ", brand='" + brand + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", dateOfIssue='" + dateOfIssue + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }
}
