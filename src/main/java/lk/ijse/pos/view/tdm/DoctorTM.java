package lk.ijse.pos.view.tdm;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Getter

public class DoctorTM implements Comparable<CustomerTM> {
    private String id;
    private String name;
    private String schedule;
    private int  mobileNum;

    @Override
    public int compareTo(CustomerTM o) {
        return id.compareTo(o.getId());
    }
}
