package lk.ijse.pos.dto;

import java.io.Serializable;

public class DoctorDTO implements Serializable {
    private String id;
    private String name;
    private String schedule;
    private int  mobileNum;

    public DoctorDTO(){

    }

    public DoctorDTO(String id, String name, String schedule, int mobileNum) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
        this.mobileNum = mobileNum;
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

    public int getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(int mobileNum) {
        this.mobileNum = mobileNum;
    }

    @Override
    public String toString() {
        return "DoctorDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", schedule='" + schedule + '\'' +
                ", mobileNum=" + mobileNum +
                '}';
    }
}

