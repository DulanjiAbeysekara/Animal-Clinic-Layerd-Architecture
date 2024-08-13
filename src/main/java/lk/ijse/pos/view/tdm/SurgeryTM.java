package lk.ijse.pos.view.tdm;

public class SurgeryTM  implements Comparable<SurgeryTM>{
    private String id;
    private String name;
    private String schedule;
    private String reason;
    private String price;
    private String doctorId;

    public SurgeryTM() {
    }

    public SurgeryTM(String id, String name, String schedule, String reason, String price, String doctorId) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
        this.reason = reason;
        this.price = price;
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

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "SurgeryTM{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", schedule='" + schedule + '\'' +
                ", reason='" + reason + '\'' +
                ", price='" + price + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }

    @Override
    public int compareTo(SurgeryTM o) {
        return id.compareTo(o.getId());
    }
}
