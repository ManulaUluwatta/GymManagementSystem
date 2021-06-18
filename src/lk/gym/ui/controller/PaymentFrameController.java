package lk.gym.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.gym.controller.ControllerFactory;
import lk.gym.controller.custom.MemberController;
import lk.gym.controller.custom.PackageController;
import lk.gym.controller.custom.PaymentController;
import lk.gym.db.ConnectionFactory;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.PackageDTO;
import lk.gym.dto.PaymentDTO;
import lk.gym.other.Validator;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PaymentFrameController implements Initializable {

    @FXML
    private JFXTextField memberID_text;
    public static JFXTextField memberID_text3;
    @FXML
    private JFXTextField packageFee_txt;

    @FXML
    private JFXTextField packageType_txt;

    @FXML
    private JFXTextField paymentDate_txt;

    @FXML
    private JFXTextField expireDate_txt;

    @FXML
    private JFXButton makePayment_btn;

    @FXML
    private JFXTextField packageID_txt;

    @FXML
    private JFXButton select_btn;

    public static int count = 0;


    private PaymentController paymentController;
    private MemberController memberController;
    private PackageController packageController;

    String date;
   @Override
    public void initialize(URL location, ResourceBundle resources) {
        paymentController = (PaymentController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.PAYMENT);
        memberController = (MemberController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.MEMBER);
       packageController = (PackageController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.PACKAGE);

       loadDate();

       memberID_text3 = memberID_text;
   }
    @FXML
    void selectButtonAction(ActionEvent event) {
       count++;
        try {
            Parent root= FXMLLoader.load(getClass().getResource("/lk/gym/ui/fxml/SelectMemberPane.fxml"));
            Stage primaryStage=new Stage();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void makePaymentButtonAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                memberID_text.getText(),
                packageType_txt.getText(),
                packageFee_txt.getText(),
                paymentDate_txt.getText(),
                expireDate_txt.getText(),

        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please Enter Member ID and press Enter");
        }
        else {
            PaymentDTO paymentDTO = new PaymentDTO(
                    memberID_text.getText(),
                    Double. parseDouble(packageFee_txt.getText()),
                    paymentDate_txt.getText(),
                    expireDate_txt.getText()
            );
            MemberDTO memberDTO = new MemberDTO(
                    memberID_text.getText(),
                    expireDate_txt.getText()

            );
            try {
                boolean isAdd = paymentController.add(paymentDTO);
                if (isAdd){
                    boolean isUpdate = memberController.updateExDate(memberDTO);
                    if (isUpdate){
                        JOptionPane.showMessageDialog(null,"Done");
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
    @FXML
    void memberIDTextAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                memberID_text.getText()

        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please Enter Member ID");
        }else {
            String id = memberID_text.getText();
            try {
                List<MemberDTO> list = memberController.searchByID(id);
                if (list != null){
                    for (MemberDTO all :
                            list) {
                        packageType_txt.setText(all.getPackageType());

                    }
                }
                String packageType = packageType_txt.getText();
                int num = 0;
                try {
                    ArrayList<PackageDTO> packageFee = packageController.getName(packageType);
                    for (PackageDTO allType :
                            packageFee) {
                        packageFee_txt.setText(allType.getPackageFee() + "");
                        num = allType.getMonths();

                    }
                    loadExpireDate(num);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private void loadDate() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(d1);
        paymentDate_txt.setText(date);
    }

    private void loadExpireDate(int num) {
        int amount = num;
        try {

            String currentDate = date;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date cDate = dateFormat.parse(currentDate);


            Calendar c = Calendar.getInstance();
            c.setTime(cDate);

            c.add(Calendar.MONTH,amount);
            Date currentDatePlusOne = c.getTime();
            expireDate_txt.setText(dateFormat.format(currentDatePlusOne));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }



}
