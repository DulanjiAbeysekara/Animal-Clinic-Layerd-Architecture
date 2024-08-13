package lk.ijse.pos.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.custom.CustomerBO;
import lk.ijse.pos.bo.custom.impl.CustomerBOImpl;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.view.tdm.CustomerTM;

import java.io.IOException;
import java.sql.SQLException;
import java.text.CollationElementIterator;
import java.util.ArrayList;

//import lk.ijse.pos.model.CustomerModel;
//import lk.ijse.pos.model.CustomerDTO;

public class CustomerController {


    @FXML
    private Button btnAdd;

    @FXML
    private Button btnBack;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colMobileNum;

    @FXML
    private TableColumn<?, ?> coltAppointmentDate;

    @FXML
    private AnchorPane customerPane;

    @FXML
    private TableView<CustomerTM> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtCustomerName;

    @FXML
    private TextField txtDate;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtMobileNum;
    private CollationElementIterator Print;

    CustomerBO customerB0=new CustomerBOImpl();

    // @Override
    public void initialize() {
        tblCustomer.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tblCustomer.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblCustomer.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("address"));
        tblCustomer.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("mobile number"));
        tblCustomer.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("appoitnment date"));
        tblCustomer.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("email"));


        initUI();

        tblCustomer.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btnDelete.setDisable(newValue == null);
            btnAdd.setText(newValue != null ? "Update" : "Save");
            btnAdd.setDisable(newValue == null);

            if (newValue != null) {
                txtCustomerId.setText(newValue.getId());
                txtCustomerName.setText(newValue.getName());
                txtAddress.setText(newValue.getAddress());
                txtMobileNum.setText(newValue.getAddress());
                txtDate.setText(newValue.getAppointmentDate());
                txtEmail.setText(newValue.getEmail());

                txtCustomerId.setDisable(false);
                txtCustomerName.setDisable(false);
                txtAddress.setDisable(false);
                txtMobileNum.setDisable(false);
                txtDate.setDisable(false);
                txtEmail.setDisable(false);
            }

        });

        txtAddress.setOnAction(event -> btnAdd.fire());
        loadAllCustomers();
    }


    private void loadAllCustomers() {
        tblCustomer.getItems().clear();

        try {
            ArrayList<CustomerDTO> allCustomer = customerB0.getAllCustomers();
            for (CustomerDTO c : allCustomer) {
                tblCustomer.getItems().add(new CustomerTM(c.getId(), c.getName(), c.getAddress(), c.getMobileNum(), c.getAppointmentDate(), c.getEmail()));

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }


    /*private void setCellValueFactory() {
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colMobileNum.setCellValueFactory(new PropertyValueFactory<>("mobileNum"));
        coltAppointmentDate.setCellValueFactory(new PropertyValueFactory<>("appointmentDate"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    }*/
    private void initUI() {
        txtCustomerId.clear();
        txtCustomerName.clear();
        txtAddress.clear();
        txtMobileNum.clear();
        txtDate.clear();
        txtEmail.clear();

        txtCustomerId.setDisable(true);
        txtCustomerName.setDisable(true);
        txtAddress.setDisable(true);
        txtMobileNum.setDisable(true);
        txtDate.setDisable(true);
        txtEmail.setDisable(true);

        txtCustomerId.setEditable(false);

        btnAdd.setDisable(true);
        btnDelete.setDisable(true);

    }


    @FXML
    void customerIdOnAction(ActionEvent event) {

    }


    public void btnAddOnAction(ActionEvent actionEvent) {

        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtAddress.getText();
        String appointmentDate = txtDate.getText();
        int mobileNum = 0;
        String email;



        if (!txtEmail.getText().matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            txtEmail.setStyle("-fx-border-color: red;");
            return;

        } else {
            email = (txtEmail.getText());
            txtEmail.setText("");
        }

        ////////////////////////////////////////////////////////////////
        if (!txtMobileNum.getText().matches("^(070|072|074|075|076|077|078)[0-9]{7}$")) {
            txtMobileNum.setStyle("-fx-border-color: red;");
            return;

        } else {
            mobileNum = Integer.parseInt(txtMobileNum.getText());
            txtMobileNum.setText("");
        }

        if (btnAdd.getText().equalsIgnoreCase("save")) {
            try {
                if (existCustomer(id)) {
                    new Alert(Alert.AlertType.ERROR, id + "already exists").show();
                }
                customerB0.saveCustomer(new CustomerDTO(id, name, address,mobileNum, appointmentDate,  email));
                tblCustomer.getItems().add(new CustomerTM(id, name, address, mobileNum,appointmentDate,  email));

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();
        String name = txtCustomerName.getText();
        String address = txtAddress.getText();
        int mobileNum = Integer.parseInt(txtMobileNum.getText());
        String appointmentDate = txtDate.getText();
        String email = txtEmail.getText();

        try {
            if (existCustomer(id)){
                new Alert(Alert.AlertType.ERROR,"There is no such customer associated with the id"+id).show();
            }
            customerB0.updateCustomer(new CustomerDTO(id,name,address,mobileNum,appointmentDate,email));

        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,"Failed to update the customer"+id+e.getMessage()).show();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        CustomerTM selectedCustomer=tblCustomer.getSelectionModel().getSelectedItem();
        selectedCustomer.setName(name);
        selectedCustomer.setAddress(address);
        selectedCustomer.setMobileNum(mobileNum);
        selectedCustomer.setAppointmentDate(appointmentDate);
        selectedCustomer.setEmail(email);

        tblCustomer.refresh();

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblCustomer.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existCustomer(id)) {
                new Alert(Alert.AlertType.ERROR, "The is no such customer associated with the id" + id).show();
            }
            customerB0.deleteCustomer(id);
            tblCustomer.getItems().remove(tblCustomer.getSelectionModel().getSelectedItem());
            tblCustomer.getSelectionModel().clearSelection();
            initUI();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the customer" + id).show();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

    }

}

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)customerPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();
    }

    boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        return customerB0.existByID(id);
    }
}
