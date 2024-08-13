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
import lk.ijse.pos.bo.custom.PetBO;
import lk.ijse.pos.dto.PetDTO;
import lk.ijse.pos.view.tdm.PetTM;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class PetController  {

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAge;

    @FXML
    private TableColumn<?, ?> colBreed;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDateOfBirth;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colPetId;

    @FXML
    private TableColumn<?, ?> colPetName;

    @FXML
    private ComboBox<String> comboGender;

    @FXML
    private AnchorPane petPane;

    @FXML
    private TableView<PetTM> tblPet;

    @FXML
    private TextField txtAge;

    @FXML
    private TextField txtBreed;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDateOfBirth;

    @FXML
    private TextField txtPetId;

    @FXML
    private TextField txtPetName;


    PetBO petBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.PET_BO);
    //@Override
    public void initialize() {

        tblPet.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblPet.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblPet.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("age"));
        tblPet.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("breed"));
        tblPet.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tblPet.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("gender"));
        tblPet.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("customerId"));

        initUI();

        tblPet.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);


            if (newValue != null) {
                txtPetId.setText(newValue.getId());
                txtPetName.setText(newValue.getName());
                txtAge.setText(newValue.getAge());
                txtBreed.setText(newValue.getBreed());
                txtDateOfBirth.setText(newValue.getDateOfBirth());
                comboGender.setValue(newValue.getGender());
             //  txtCustomerId.setText(newValue.customerId());


                txtPetId.setDisable(false);
                txtPetName.setDisable(false);
                txtAge.setDisable(false);
                txtBreed.setDisable(false);
                txtDateOfBirth.setDisable(false);
                comboGender.setDisable(false);


            }
        });

        txtPetName.setOnAction(event -> btnAdd.fire());
        loadAllPets();

    }
    private void loadAllPets() {
        tblPet.getItems().clear();

        try {
            ArrayList<PetDTO> allPets = petBO.getAllPets();
            for (PetDTO c : allPets) {
                tblPet.getItems().add(new PetTM(c.getId(), c.getName(), c.getAge(), c.getBreed(),c.getDateOfBirth(),c.getGender(),c.getCustomerId()));
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


    }

    private void initUI() {
        txtPetId.clear();
        txtPetName.clear();
        txtAge.clear();
        txtBreed.clear();
        txtDateOfBirth.clear();
        comboGender.cancelEdit();
        txtCustomerId.clear();

        txtPetId.setDisable(true);
        txtPetName.setDisable(true);
        txtAge.setDisable(true);
        txtBreed.setDisable(true);
        txtDateOfBirth.setDisable(true);
        comboGender.setDisable(true);

        txtPetId.setEditable(false);
        btnAdd.setDisable(true);
        btnDelete.setDisable(true);
    }


    public void btnAddOnAction(ActionEvent actionEvent) {

        String id=txtPetId.getText();
        String name=txtPetName.getText();
        String  age=txtAge.getText();
        String breed=txtBreed.getText();
        String dateOfBirth=txtDateOfBirth.getText();
        String  gender=comboGender.getValue();
        String customerId=txtCustomerId.getText();

        if (btnAdd.getText().equalsIgnoreCase("save")) {

            try {
                if (existPet(id)) {
                    new Alert(Alert.AlertType.ERROR, id + " already exists").show();
                }

                petBO.savePet(new PetDTO(id, name, age, breed,dateOfBirth,gender,customerId));

                tblPet.getItems().add(new PetTM(id, name, age, breed,dateOfBirth,gender,customerId));
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the pet " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = txtPetId.getText();

        try {
            if (!existPet(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such item associated with the id " + id).show();
            }
            petBO.deletePet(id);
            tblPet.getItems().remove(tblPet.getSelectionModel().getSelectedItem());
            tblPet.getSelectionModel().clearSelection();
            initUI();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the pet " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtPetName.getText();
        String age = txtAge.getText();
        String breed=txtBreed.getText();
        String dateOfBirth=txtDateOfBirth.getText();
        String  gender=comboGender.getValue();
        String customerId=txtCustomerId.getText();
        String id = txtPetId.getText();


        try {
            if (!existPet(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such customer associated with the id " + id).show();
            }
            petBO.updatePet(new PetDTO( name, age, breed,dateOfBirth,gender,customerId,id));
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the pet " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        PetTM selectedPet = tblPet.getSelectionModel().getSelectedItem();
        selectedPet.setName(name);
        selectedPet.setAge(age);

        tblPet.refresh();

    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)petPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();

    }

    public void comboGenderOnAction(ActionEvent actionEvent) {
    }



    boolean existPet(String id) throws SQLException, ClassNotFoundException {
        return petBO.existByID(id);
    }

}



