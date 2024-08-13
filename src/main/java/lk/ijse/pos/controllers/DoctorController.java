package lk.ijse.pos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.DoctorBO;
import lk.ijse.pos.dto.DoctorDTO;
import lk.ijse.pos.view.tdm.DoctorTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//import lk.ijse.pos.dto.tm.Doctor;
//import lk.ijse.pos.model.DoctorModel;

public class DoctorController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colDoctorId;

    @FXML
    private TableColumn<?, ?> colDoctorMobileNum;

    @FXML
    private TableColumn<?, ?> colDoctorName;

    @FXML
    private TableColumn<?, ?> colSchedule;

    @FXML
    private Button doctorBackBtn;

    @FXML
    private AnchorPane doctorPane;

    @FXML
    private TableView<DoctorTM> tblDoctor;

    @FXML
    private TextField txtDoctorId;

    @FXML
    private TextField txtDoctorMobileNum;

    @FXML
    private TextField txtDoctorName;

    @FXML
    private TextField txtSchedule;


    DoctorBO doctorBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.DOCTOR_BO);

    public void initialize() {
        tblDoctor.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblDoctor.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblDoctor.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("schedule"));
        tblDoctor.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobileNum"));

        initUI();

        tblDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);


            if (newValue != null) {
                txtDoctorId.setText(newValue.getId());
                txtDoctorName.setText(newValue.getName());
                txtSchedule.setText(newValue.getSchedule());
                txtDoctorMobileNum.setText(newValue.getSchedule());

                txtDoctorId.setDisable(false);
                txtDoctorName.setDisable(false);
                txtSchedule.setDisable(false);
                txtDoctorMobileNum.setDisable(false);
            }
        });

        txtDoctorName.setOnAction(event -> btnAdd.fire());
        loadAllDoctors();
    }

    private void loadAllDoctors() {
        tblDoctor.getItems().clear();

        try {
            ArrayList<DoctorDTO> allDoctors = doctorBO.getAllDoctors();
            for (DoctorDTO c : allDoctors) {
                tblDoctor.getItems().add(new DoctorTM(c.getId(), c.getName(), c.getSchedule(), c.getMobileNum()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void initUI() {
        txtDoctorId.clear();
        txtDoctorName.clear();
        txtSchedule.clear();
        txtDoctorMobileNum.clear();

        txtDoctorId.setDisable(true);
        txtDoctorName.setDisable(true);
        txtSchedule.setDisable(true);
        txtDoctorMobileNum.setDisable(true);

        txtDoctorId.setEditable(false);

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
    }


    @FXML
    void doctorBackBtnOnAction(ActionEvent event) throws IOException {

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) doctorPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();

    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        String id = txtDoctorId.getText();
        String name = txtDoctorName.getText();
        String schedule = txtSchedule.getText();
        int mobileNum = 0;

        if (!txtDoctorMobileNum.getText().matches("^(070|072|074|075|076|077|078)[0-9]{7}$")) {
            txtDoctorMobileNum.setStyle("-fx-border-color: red;");
            return;

        } else {
            mobileNum = Integer.parseInt(txtDoctorMobileNum.getText());
            txtDoctorMobileNum.setText("");
        }

        txtDoctorId.setText("");
        txtDoctorName.setText("");
        txtSchedule.setText("");

        if (btnAdd.getText().equalsIgnoreCase("save")) {

            try {
                if (existDoctor(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                doctorBO.saveDoctor(new DoctorDTO(id, name, schedule, mobileNum));

                tblDoctor.getItems().add(new DoctorTM(id, name, schedule, mobileNum));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }
    }

        public void btnUpdateOnAction (ActionEvent actionEvent){
            String id = txtDoctorId.getText();
            String name = txtDoctorName.getText();
            String schedule = txtSchedule.getText();
            int mobileNum = Integer.parseInt(txtDoctorMobileNum.getText());


            try {
                if (!existDoctor(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
                }
                doctorBO.updateDoctor(new DoctorDTO(id, name, schedule, mobileNum));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            DoctorTM selectedDoctor = tblDoctor.getSelectionModel().getSelectedItem();
            selectedDoctor.setName(name);
            selectedDoctor.setSchedule(schedule);
            selectedDoctor.setMobileNum(mobileNum);

            tblDoctor.refresh();


            // btnAddNewCustomer.fire();

        }


        public void btnDeleteOnAction (ActionEvent actionEvent){
            String id = tblDoctor.getSelectionModel().getSelectedItem().getId();
            try {
                if (!existDoctor(id)) {
                    new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + id).show();
                }
                doctorBO.deleteDoctor(id);
                tblDoctor.getItems().remove(tblDoctor.getSelectionModel().getSelectedItem());
                tblDoctor.getSelectionModel().clearSelection();
                initUI();
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the item " + id).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

    boolean existDoctor(String id) throws SQLException, ClassNotFoundException {
        return doctorBO.existByID(id);
    }

}