package lk.ijse.pos.view.tdm;

public class PetItemTM implements Comparable<PetItemTM>{
    private String id;
    private String name;
    private String brand;
    private String datOfIssue;
    private String expiryDate;
    private String sellingPrice;
    private String purchasePrice;
    private String qty;

    public PetItemTM() {
    }

    public PetItemTM(String id, String name, String brand, String datOfIssue, String expiryDate, String sellingPrice, String purchasePrice, String qty) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.datOfIssue = datOfIssue;
        this.expiryDate = expiryDate;
        this.sellingPrice = sellingPrice;
        this.purchasePrice = purchasePrice;
        this.qty = qty;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDatOfIssue() {
        return datOfIssue;
    }

    public void setDatOfIssue(String datOfIssue) {
        this.datOfIssue = datOfIssue;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }




    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    @Override
    public String toString() {
        return "PetItemTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", datOfIssue='" + datOfIssue + '\'' +
                ", expiryDate='" + expiryDate + '\'' +
                ", sellingPrice='" + sellingPrice + '\'' +
                ", purchasePrice='" + purchasePrice + '\'' +
                ", qty='" + qty + '\'' +
                '}';
    }

    @Override
    public int compareTo(PetItemTM o) {
        return id.compareTo(o.getId());
    }
}
