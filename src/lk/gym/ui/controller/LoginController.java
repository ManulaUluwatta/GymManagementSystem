package lk.gym.ui.controller;
import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {



    @FXML
    private JFXTextField userName_txt;

    @FXML
    private JFXPasswordField password_txt;

    @FXML
    private JFXButton login_btn;

    private AnchorPane dash;

    private String userName;
    private String password;



    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    void loginButtonAction(ActionEvent event) {

        userName = userName_txt.getText();
        password = password_txt.getText();

        if(userName.equals("admin") && password.equals("admin")){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/MainFrame.fxml"));
                Stage primaryStage=new Stage();
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
                primaryStage.setTitle("Golden Power Gym");
                Image image = new Image("/lk/gym/ui/images/jys.png");
                primaryStage.getIcons().add(image);
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/RegistrationFrame.fxml"));
                MainFrameController.rootPane.getChildren().setAll(dash.getChildren());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
        }
    }
    @FXML
    void loginKeyAction(KeyEvent event) {
        userName = userName_txt.getText();
        password = password_txt.getText();

        if(userName.equals("admin") && password.equals("admin")){
            try {
                Parent root = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/MainFrame.fxml"));
                Stage primaryStage=new Stage();
                primaryStage.setScene(new Scene(root));
                primaryStage.show();
                primaryStage.setTitle("Golden Power Gym");
                Image image = new Image("/lk/gym/ui/images/jys.png");
                primaryStage.getIcons().add(image);
                ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                dash = FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/RegistrationFrame.fxml"));
                MainFrameController.rootPane.getChildren().setAll(dash.getChildren());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            JOptionPane.showMessageDialog(null,"Incorrect Username or Password");
        }
    }

    @FXML
    void passwordTxtAction(ActionEvent event) {
        login_btn.requestFocus();
    }

    @FXML
    void userNameTxtAction(ActionEvent event) {
        password_txt.requestFocus();

    }
}
