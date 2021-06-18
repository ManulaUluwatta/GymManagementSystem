package lk.gym.ui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainFrameController implements Initializable {
    @FXML
    private JFXButton registration_btn;

    @FXML
    private JFXButton viewMember_btn;

    @FXML
    private JFXButton statics_btn;

    @FXML
    private JFXButton viewPackage_btn;

    @FXML
    private AnchorPane anchorView;

    @FXML
    private JFXButton payment_btn;

    public static AnchorPane rootPane;

    private AnchorPane dash;




    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rootPane = anchorView;

    }

    @FXML
    void buttonAction(ActionEvent event) {
        try {
            dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/RegistrationFrame.fxml"));
            anchorView.getChildren().setAll(dash.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewMemberButtonAction(ActionEvent event) {
        try {
            dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/MemberViewFrame.fxml"));
            anchorView.getChildren().setAll(dash.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @FXML
    void staticButtonAction(ActionEvent event) {
        try {
            dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/StaticsFrame.fxml"));
            anchorView.getChildren().setAll(dash.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void viewPackageButtonAction(ActionEvent event) {
        try {
            dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/PackagePlanFrame.fxml"));
            anchorView.getChildren().setAll(dash.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void paymentButtonAction(ActionEvent event) {
        try {
            dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/PaymentFrame.fxml"));
            anchorView.getChildren().setAll(dash.getChildren());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
