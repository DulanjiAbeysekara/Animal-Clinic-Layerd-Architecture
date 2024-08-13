package lk.ijse.pos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.custom.VaccinationBO;
import lk.ijse.pos.bo.custom.impl.VaccinationBOImpl;
import lk.ijse.pos.dto.VaccinationDTO;

import java.io.IOException;
import java.sql.SQLException;

public class VaccinationController {


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnView;

    @FXML
    private TextField txtBrand;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDateOfIssue;

    @FXML
    private TextField txtDoctorId;

    @FXML
    private TextField txtExpiryDate;

    @FXML
    private TextField txtPaymentCode;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtVaccinationId;

    @FXML
    private TextField txtVaccineName;

    @FXML
    private TextField txtPetId;

    @FXML
    private AnchorPane vaccineDetailsPane;


    VaccinationBO vaccinationBO=new VaccinationBOImpl() {
    };

    public void btnAddOnAction(ActionEvent actionEvent) {

        String id = txtVaccinationId.getText();
        String name = txtVaccineName.getText();
        String price = txtPrice.getText();
        String brand = txtBrand.getText();
        String expiryDate = txtExpiryDate.getText();
        String dateOfIssue = txtDateOfIssue.getText();
        String doctorId = txtDoctorId.getText();
      //  String paymentId = txtPaymentCode.getText();
       // String customerId = txtCustomerId.getText();
       // String petId=txtPetId.getText();


       // Vaccination vaccination=new Vaccination(id,name,price,brand,expiryDate,dateOfIssue,doctorId);
        //VaccinationDetail vaccinationDetail=new VaccinationDetail(id,petId);

        if (btnAdd.getText().equalsIgnoreCase("save")) {
            try {
                if (existVaccination(id)) {
                    new Alert(Alert.AlertType.ERROR, id + "already exists").show();
                }
                vaccinationBO.saveVaccination(new VaccinationDTO(id, name, price,brand, expiryDate,  dateOfIssue,doctorId));
//                tblVaccination.getItems().add(new VaccinationTM(id, name, price,brand, expiryDate,  dateOfIssue,doctorId));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

        public void btnUpdateOnAction (ActionEvent actionEvent){

            String id = txtVaccinationId.getText();
            String name = txtVaccineName.getText();
            String price = txtPrice.getText();
            String brand = txtBrand.getText();
            String expiryDate= txtExpiryDate.getText();
            String dateOfIssue=txtDateOfIssue.getText();
            String doctorId=txtDoctorId.getText();


            try {
                if (existVaccination(id)){
                    new Alert(Alert.AlertType.ERROR,"There is no such customer associated with the id"+id).show();
                }
                vaccinationBO.updateVaccination(new VaccinationDTO(id, name, price,brand, expiryDate,  dateOfIssue,doctorId));

            }catch (SQLException e){
                new Alert(Alert.AlertType.ERROR,"Failed to update the customer"+id+e.getMessage()).show();
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            }

//            VaccinationTM selectedVaccination=tblVaccination.getSelectionModel().getSelectedItem();
//            selectedVaccination.setName(name);
//            selectedVaccination.setPrice(price);
//            selectedVaccination.setBrand(brand);
//            selectedVaccination.setExpiryDate(expiryDate);
//            selectedVaccination.setDateOfIssue(dateOfIssue);
//            selectedVaccination.setDoctorId(doctorId);
//
//            tblVaccination.refresh();


        }

        public void btnDeleteOnAction (ActionEvent actionEvent) {
            String id = txtVaccinationId.getText();

            try {
                if (!existVaccination(id)) {
                    new Alert(Alert.AlertType.ERROR, "The is no such customer associated with the id" + id).show();
                }
                vaccinationBO.deleteVaccination(id);
//                tblVaccination.getItems().remove(tblVaccination.getSelectionModel().getSelectedItem());
//                tblVaccination.getSelectionModel().clearSelection();
//                initUI();

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to delete the customer" + id).show();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        public void btnViewOnAction (ActionEvent actionEvent) throws IOException {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/health.fxml"));

            Scene scene = new Scene(anchorPane);

            Stage stage = (Stage) vaccineDetailsPane.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Vaccination Details");
            stage.centerOnScreen();

        }

    boolean existVaccination(String id) throws SQLException, ClassNotFoundException {
        return vaccinationBO.existByID(id);
    }



    }

