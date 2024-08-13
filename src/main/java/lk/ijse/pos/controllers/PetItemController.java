package lk.ijse.pos.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.pos.bo.BoFactory;
import lk.ijse.pos.bo.custom.ItemBO;
import lk.ijse.pos.entity.PetItem;
import lk.ijse.pos.view.tdm.PetItemTM;


import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PetItemController implements Initializable {
    @FXML
    private Button btnBack;

    @FXML
    private Button btnOk;

    @FXML
    private TableColumn<?, ?> colBrandName;

    @FXML
    private TableColumn<?, ?> colDateOfIssue;

    @FXML
    private TableColumn<?, ?> colExpiryDate;

    @FXML
    private TableColumn<?, ?> colItemId;

    @FXML
    private TableColumn<?, ?> colItemName;

    @FXML
    private TableColumn<?, ?> colPurchasePrice;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colSellingPrice;

    @FXML
    private AnchorPane petItemPane;

    @FXML
    private TableView<PetItemTM> tblPetItem;



    ItemBO itemBO = BoFactory.getBoFactory().getBO(BoFactory.BOTypes.ITEM_BO);

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/homepage.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) petItemPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();
    }

    public void btnOkOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/view/petItemDeatils.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) petItemPane.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Home Page");
        stage.centerOnScreen();

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        getAll();
        setCellValueFactory();
    }

    private void setCellValueFactory() {

        colItemId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colBrandName.setCellValueFactory(new PropertyValueFactory<>("brand"));
        colDateOfIssue.setCellValueFactory(new PropertyValueFactory<>("dateOfIssue"));
        colExpiryDate.setCellValueFactory(new PropertyValueFactory<>("expiryDate"));
        colPurchasePrice.setCellValueFactory(new PropertyValueFactory<>("purchasePrice"));
        colSellingPrice.setCellValueFactory(new PropertyValueFactory<>("sellingPrice"));

    }

    private void getAll() {
        try {
            ObservableList<PetItemTM> obList = FXCollections.observableArrayList();
            List<PetItem> itemList = itemBO.getAll();

            for (PetItem item : itemList) {
                obList.add(new PetItemTM(
                        item.getId(),
                        item.getName(),
                        item.getBrand(),
                        item.getDatOfIssue(),
                        item.getExpiryDate(),
                        item.getPurchasePrice(),
                        item.getSellingPrice(),
                        item.getQty()
                ));
            }
            tblPetItem.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }
}
