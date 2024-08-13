package lk.ijse.pos.controllers;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.SurgeryBO;
import lk.ijse.pos.db.DBConnection;
import lk.ijse.pos.dto.SurgeryDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;

public class SurgeryController {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnView;

    @FXML
    private Label lblDoctorId;

    @FXML
    private Label lblPaymentCode;

    @FXML
    private Label lblPetId;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblSurgeryId;

    @FXML
    private Label lblSurgeryName;

    @FXML
    private Label lblSurgerySchedule;

    @FXML
    private AnchorPane surgeryPane;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDoctorId;

    @FXML
    private TextField txtPaymentCode;

    @FXML
    private TextField txtPetId;

    @FXML
    private TextField txtPrice;

    @FXML
    private TextField txtReason;

    @FXML
    private TextField txtSurgeryId;

    @FXML
    private TextField txtSurgeryName;

    @FXML
    private TextField txtSurgerySchedule;

    SurgeryBO surgeryBO=  BoFactory.getBoFactory().getBO(BoFactory.BOTypes.SURGERY_BO);

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException {

        String name = txtSurgeryName.getText();
        String schedule = txtSurgerySchedule.getText();
        String reason = txtReason.getText();
        String price = txtPrice.getText();
        String doctorId = txtDoctorId.getText();
        String id = txtSurgeryId.getText();

        try {
            if (!existSurgery(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            surgeryBO.updateSurgery(new SurgeryDTO(  name, schedule, reason,price,doctorId,id));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the surgery " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

      /*  SurgeryTM selectedSurgery = tblSurgery.getSelectionModel().getSelectedItem();
        selectedSurgery.setName(name);
        selectedSurgery.setSchedule(schedule);

        tblSurgery.refresh();
*/

    }


    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id=txtSurgeryId.getText();

        try {
            if (!existSurgery(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + id).show();
            }
            surgeryBO.deleteSurgery(id);
            /*tblSurgery.getItems().remove(tblSurgery.getSelectionModel().getSelectedItem());
            tblSurgery.getSelectionModel().clearSelection();
*/
//            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the surgery " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnViewOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/health.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) surgeryPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Health Management");
        stage.centerOnScreen();
    }


    @FXML
    void viewBillOnAction(ActionEvent event) throws SQLException {
        try {
            JasperDesign jasperDesign = JRXmlLoader.load("src/main/resources/Report/Blank_A4.jrxml");
            JRDesignQuery query = new JRDesignQuery();
            query.setText("SELECT * FROM payment WHERE PaymentCode ='" + txtPaymentCode.getText() + "'");
            jasperDesign.setQuery(query);

            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer.viewReport(jasperPrint);

        } catch (JRException e) {
            e.printStackTrace();
        }


    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException {
        String id = txtSurgeryId.getText();
        String name = txtSurgeryName.getText();
        String surgerySchedule = txtSurgerySchedule.getText();
        String reason = txtReason.getText();
        String price = txtPrice.getText();
        String doctorId = txtDoctorId.getText();
        //String paymentId = txtPaymentCode.getText();
        //String customerId = txtCustomerId.getText();

        //SurgeryDetail surgeryDetail=new SurgeryDetail();
        //surgeryDetail.setSurgeryId(txtSurgeryId.getText());
        // surgeryDetail.setPetId(txtPetId.getText());
        if (btnAdd.getText().equalsIgnoreCase("save")) {
            try {
                if (existSurgery(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }
                //Save Item
                surgeryBO.saveSurgery(new SurgeryDTO(id, name, surgerySchedule, reason,price,doctorId));

//                tblSurgery.getItems().add(new SurgeryTM(id, name, surgerySchedule, reason,price,doctorId));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }



        /*boolean isPlaced = false;
        try {
            isPlaced = SurgeryModel.save(surgeryDetail);
            isPlaced = PaymentModel.save(paymentId,customerId,id,price);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }

    }*/


        }
    }



    private boolean existSurgery(String id) throws SQLException, ClassNotFoundException {
        return surgeryBO.existByID(id);
    }
}
