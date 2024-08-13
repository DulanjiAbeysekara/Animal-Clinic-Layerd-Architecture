package lk.ijse.pos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.SurgeryBO;
import lk.ijse.pos.dto.SurgeryDTO;
import lk.ijse.pos.view.tdm.SurgeryTM;
import lk.ijse.pos.view.tdm.VaccinationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class HealthController  {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnViewSurgery;

    @FXML
    private Button btnViewVaccination;

    @FXML
    private TableColumn<?, ?> colBrand;

    @FXML
    private TableColumn<?, ?> colDateOfIssue;

    @FXML
    private TableColumn<?, ?> colDoctorId;

    @FXML
    private TableColumn<?, ?> colExpiryDate;

    @FXML
    private TableColumn<?, ?> colPrice;

    @FXML
    private TableColumn<?, ?> colReason;

    @FXML
    private TableColumn<?, ?> colSurgeryId;

    @FXML
    private TableColumn<?, ?> colSurgeryName;

    @FXML
    private TableColumn<?, ?> colSurgerySchedule;

    @FXML
    private TableColumn<?, ?> colVaccinationId;

    @FXML
    private TableColumn<?, ?> colVaccinationName;

    @FXML
    private TableColumn<?, ?> colVaccinationNum;

    @FXML
    private TableColumn<?, ?> colVaccinationStore;

    @FXML
    private AnchorPane healthPane;

    @FXML
    private TableView<SurgeryTM> tblSurgery;

    @FXML
    private TableView<VaccinationTM> tblVaccination;

    private
    @FXML
    //private TableView<?> tblVaccination;


    SurgeryBO surgeryBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SURGERY_BO);



    //@Override
    public void initialize() {
        tblSurgery.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSurgery.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSurgery.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("schedule"));
        tblSurgery.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("reason"));
        tblSurgery.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("price"));
        tblSurgery.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("doctorId"));

//        initUI();

       /* tblSurgery.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);

            if (newValue != null) {
                txtSurgeryId.setText(newValue.getId());
                txtSurgeryName.setText(newValue.getName());
                txtSurgerySchedule.setText(newValue.getSchedule());
                txtReason.setText(newValue.getReason());
                txtPrice.setText(newValue.getPrice());
                txtDoctorId.setText(newValue.getDoctorId());

                txtSurgeryId.setDisable(false);
                txtSurgeryName.setDisable(false);
                txtSurgerySchedule.setDisable(false);
                txtReason.setDisable(false);
                txtPrice.setDisable(false);
                txtDoctorId.setDisable(false);
            }
        });*/

       /* txtSurgeryName.setOnAction(event -> btnAdd.fire());
        loadAllSurgery();
*/
    }

    private void loadAllSurgery() {
        tblSurgery.getItems().clear();

        try {
            ArrayList<SurgeryDTO> allSurgery = surgeryBO.getAllSurgery();
            for (SurgeryDTO c : allSurgery) {
                tblSurgery.getItems().add(new SurgeryTM(c.getId(), c.getName(), c.getSchedule(),c.getReason(),c.getPrice(),c.getDoctorId()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    /*private void initUI() {
        txtSurgeryId.clear();
        txtSurgeryName.clear();
        txtSurgerySchedule.clear();
        txtReason.clear();
        txtPrice.clear();
        txtDoctorId.clear();

        txtSurgeryId.setDisable(true);
        txtSurgeryName.setDisable(true);
        txtSurgerySchedule.setDisable(true);
        txtReason.setDisable(true);
        txtPrice.setDisable(true);
        txtDoctorId.setDisable(true);

        txtSurgeryId.setEditable(false);

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
    }*/

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)healthPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();

    }

  /*  private void getAll() {
        try {
            ObservableList<SurgeryTM> obList = FXCollections.observableArrayList();
            List<Sugery> surgeryList = SurgeryModel.getAll();

            for (Sugery sugery : surgeryList) {
                obList.add(new SurgeryTM(
                        sugery.getId(),
                        sugery.getName(),
                        sugery.getSchedule(),
                        sugery.getReason(),
                        sugery.getPrice(),
                        sugery.getDoctorId()
                ));
            }
            tblSurgery.setItems(obList);

        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

        //////////////////////////////////////////////////////////////////////
        try {
            ObservableList<VaccinationTM> obList = FXCollections.observableArrayList();
            List<Vaccination> vaccinationList = VaccinationModel.getAll();

            for (Vaccination vaccination : vaccinationList) {
                obList.add(new VaccinationTM(
                        vaccination.getId(),
                        vaccination.getName(),
                        vaccination.getPrice(),
                        vaccination.getBrand(),
                        vaccination.getExpiryDate(),
                        vaccination.getDateOfIssue(),
                        vaccination.getDoctorId()
                ));
            }
            tblVaccination.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }*/




    public void btnViewSurgeryOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/surgeryDetails.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)healthPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Health Management");
        stage.centerOnScreen();
    }

    public void btnViewVaccinationOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/vaccinationDetails.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)healthPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Health Management");
        stage.centerOnScreen();
    }
}
