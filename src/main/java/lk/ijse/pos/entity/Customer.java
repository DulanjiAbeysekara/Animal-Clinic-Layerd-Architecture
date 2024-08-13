package lk.ijse.pos.entity;



public class Customer {

        private String id;
        private String name;
        private String address ;
        private  int mobileNum;
        private String appointmentDate;
        private String email;

        public Customer() {
        }

        public Customer(String id, String name, String address, int mobileNum, String appointmentDate, String email) {
                this.id = id;
                this.name = name;
                this.address = address;
                this.mobileNum = mobileNum;
                this.appointmentDate = appointmentDate;
                this.email = email;
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

        public String getAddress() {
                return address;
        }

        public void setAddress(String address) {
                this.address = address;
        }

        public int getMobileNum() {
                return mobileNum;
        }

        public void setMobileNum(int mobileNum) {
                this.mobileNum = mobileNum;
        }

        public String getAppointmentDate() {
                return appointmentDate;
        }

        public void setAppointmentDate(String appointmentDate) {
                this.appointmentDate = appointmentDate;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        @Override
        public String toString() {
                return "Customer{" +
                        "id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", address='" + address + '\'' +
                        ", mobileNum=" + mobileNum +
                        ", appointmentDate='" + appointmentDate + '\'' +
                        ", email='" + email + '\'' +
                        '}';
        }
}


