package lk.ijse.pos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.custom.SupplierBO;
import lk.ijse.pos.bo.custom.impl.SupplierBOImpl;
import lk.ijse.pos.dto.SupplierDTO;
import lk.ijse.pos.view.tdm.SupplierTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

//import lk.ijse.pos.dto.tm.Supplier;

public class SupplierController {

    @FXML
    private Button BackBtn;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colNameOfTheSupplierCompany;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> colSupplierMobileNum;

    @FXML
    private TableColumn<?, ?> colSupplierName;

    @FXML
    private AnchorPane supplierPane;

    @FXML
    private TableView<SupplierTM> tblSupplier;

    @FXML
    private TextField txtNameOfTheSupplierCompany;

    @FXML
    private TextField txtSupplierMobileNum;

    @FXML
    private TextField txtSupplierName;

    @FXML
    private TextField txtSupplierId;


    @FXML
    void BackBtnOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)supplierPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();

    }
    SupplierBO supplierBO=new SupplierBOImpl();

    // @Override
    public void initialize() {
        tblSupplier.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblSupplier.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSupplier.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("supplierMobileNum"));
        tblSupplier.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("nameOfTheSupplierCompany"));


        initUI();

        tblSupplier.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);

            if (newValue != null) {
                txtSupplierId.setText(newValue.getId());
                txtSupplierName.setText(newValue.getName());
                txtSupplierMobileNum.setText(newValue.getSupplierMobileNum());
                txtNameOfTheSupplierCompany.setText(newValue.getNameOfTheSupplierCompany());


                txtSupplierId.setDisable(false);
                txtSupplierName.setDisable(false);
                txtSupplierMobileNum.setDisable(false);
                txtNameOfTheSupplierCompany.setDisable(false);

            }

        });

        txtSupplierName.setOnAction(event -> btnAdd.fire());
        loadAllSuppliers();
    }

    private void loadAllSuppliers() {
        tblSupplier.getItems().clear();

        try {
            ArrayList<SupplierDTO> allSupplier = supplierBO.getAllSupplier();
            for (SupplierDTO c : allSupplier) {
                tblSupplier.getItems().add(new SupplierTM(c.getId(), c.getName(), c.getSupplierMobileNum(), c.getNameOfTheSupplierCompany()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }

    private void initUI() {
        txtSupplierId.clear();
        txtSupplierName.clear();
        txtSupplierMobileNum.clear();
        txtNameOfTheSupplierCompany.clear();

        txtSupplierId.setDisable(true);
        txtSupplierName.setDisable(true);
        txtSupplierMobileNum.setDisable(true);
        txtNameOfTheSupplierCompany.setDisable(true);

        txtSupplierId.setEditable(false);

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);

    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        int supplierMobileNum = 0;
        String nameOfTheSupplierCompany = txtNameOfTheSupplierCompany.getText();


        if (!txtSupplierMobileNum.getText().matches("^(070|072|074|075|076|077|078)[0-9]{7}$")) {
            txtSupplierMobileNum.setStyle("-fx-border-color: red;");
            return;

        }else {
            supplierMobileNum = Integer.parseInt(txtSupplierMobileNum.getText());
            txtSupplierMobileNum.setText("");
        }

        if (btnAdd.getText().equalsIgnoreCase("save")) {
            try {
                if (existSupplier(id)) {
                    new Alert(Alert.AlertType.ERROR, id + "already exists").show();
                }
                supplierBO.saveSupplier(new SupplierDTO(id, name, supplierMobileNum,nameOfTheSupplierCompany));
                tblSupplier.getItems().add(new SupplierTM(id, name, supplierMobileNum, nameOfTheSupplierCompany));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the supplier " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

        String id = txtSupplierId.getText();
        String name = txtSupplierName.getText();
        int supplierMobileNum = Integer.parseInt(txtSupplierMobileNum.getText());
        String nameOfTheSupplierCompany = txtNameOfTheSupplierCompany.getText();



        try {
            if (existSupplier(id)){
                new Alert(Alert.AlertType.ERROR,"There is no such supplier associated with the id"+id).show();
            }
            supplierBO.updateSupplier(new SupplierDTO(id,name,supplierMobileNum,nameOfTheSupplierCompany));

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Failed to update the customer"+id+e.getMessage()).show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        SupplierTM selectedSupplier=tblSupplier.getSelectionModel().getSelectedItem();
        selectedSupplier.setName(name);
        selectedSupplier.setSupplierMobileNum(supplierMobileNum);
        selectedSupplier.setNameOfTheSupplierCompany(nameOfTheSupplierCompany);


        tblSupplier.refresh();

    }



    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtSupplierId.getText();
        try {
            if (!existSupplier(id)) {
                new Alert(Alert.AlertType.ERROR, "The is no such supplier associated with the id" + id).show();
            }
            supplierBO.deleteSupplier(id);
            tblSupplier.getItems().remove(tblSupplier.getSelectionModel().getSelectedItem());
            tblSupplier.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the supplier" + id).show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        }

    }

    boolean existSupplier(String id) throws SQLException, ClassNotFoundException {
        return supplierBO.existByID(id);
    }

}
