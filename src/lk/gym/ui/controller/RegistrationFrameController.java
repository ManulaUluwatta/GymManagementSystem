package lk.gym.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.FileChooser;
import lk.gym.controller.ControllerFactory;
import lk.gym.controller.custom.MemberController;
import lk.gym.controller.custom.PackageController;
import lk.gym.controller.custom.PaymentController;
import lk.gym.controller.custom.WeightController;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.PackageDTO;
import lk.gym.dto.PaymentDTO;
import lk.gym.dto.WeightDTO;
import lk.gym.other.IDGenarator;
import lk.gym.other.Validator;
import lk.gym.ui.tableView.MembersTableView;
import lk.gym.ui.tableView.PackageTableView;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RegistrationFrameController implements Initializable {

    @FXML
    private TableView<MembersTableView> memberTable;

    @FXML
    private TableColumn<MembersTableView, String> memberID_clm;

    @FXML
    private TableColumn<MembersTableView, String> firstName_clm;

    @FXML
    private TableColumn<MembersTableView, String> lastName_clm;

    @FXML
    private TableColumn<MembersTableView, String> nic_clm;

    @FXML
    private TableColumn<MembersTableView, String> contact_clm;

    @FXML
    private TableColumn<MembersTableView, Double> weight_clm;

    @FXML
    private TableColumn<MembersTableView, String> packageType_clm;

    @FXML
    private JFXTextField memberID_txt;

    @FXML
    private JFXTextField firstName_txt;

    @FXML
    private JFXTextField lastName_txt;

    @FXML
    private JFXTextField nic_txt;

    @FXML
    private JFXTextField age_txt;

    @FXML
    private JFXTextField weight_txt;

    @FXML
    private JFXTextField packageFee_tx;

    @FXML
    private JFXTextField inDate_txt;

    @FXML
    private JFXTextField contact_txt;

    @FXML
    private JFXTextArea address_txt;

    @FXML
    private JFXTextField exDate_txt;

    @FXML
    private JFXButton brows_btn;

    @FXML
    private JFXButton remove_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private JFXButton update_btn;

    @FXML
    private JFXButton delete_btn;


    @FXML
    private JFXTextField weightID_txt;

    @FXML
    private JFXComboBox<String> packageCombo;

    @FXML
    private JFXTextField searchByID_txt;

    @FXML
    private ImageView imageView;

    private PackageController packageController;
    private MemberController memberController;
    private WeightController weightController;
    private PaymentController paymentController;
    ObservableList<String> listacombo= FXCollections.observableArrayList();

    private ObservableList<MembersTableView> data;

    String date;
    private String photoPath;

    private String prefix;
    private String tableName;
    private String columnName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        packageController = (PackageController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.PACKAGE);
        memberController = (MemberController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.MEMBER);
        weightController = (WeightController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.WEIGHT);
        paymentController = (PaymentController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.PAYMENT);
        data = FXCollections.observableArrayList();
        photoPath = "lk/gym/ui/images/notAvalable.png";
        loadMembers();
        loadMemberID();
        loadWeightID();
        loadCombo();
        loadDate();
    }



    private void loadCombo() {

        try {
            List<PackageDTO> allPackage = packageController.view();
            if(allPackage != null){
                for (PackageDTO pac :
                        allPackage) {
                    listacombo.add(pac.getPackageType());

                }
            }
            packageCombo.setItems(listacombo);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void BrowseButtonAction(ActionEvent event) {
        FileChooser file=new FileChooser();


        File selectedFile=file.showOpenDialog(null);

        if(selectedFile !=null){
            photoPath="file:"+selectedFile.getAbsolutePath();
            System.out.println(photoPath);
            Image image=new Image(photoPath,270,270,true,false);
            System.out.println("width"+ image.getWidth());
            System.out.println("height"+ image.getHeight());
            imageView.setImage(image);
        }
    }

    @FXML
    void addButtonAction(ActionEvent event) {
        String packageType = packageCombo.getValue();
        String packageID = "";
        try {
            ArrayList<PackageDTO> packageList = packageController.getName(packageType);
            for (PackageDTO packageDTO :
                    packageList) {
                packageID = packageDTO.getPackageID();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                memberID_txt.getText(),
                firstName_txt.getText(),
                lastName_txt.getText(),
                nic_txt.getText(),
                contact_txt.getText(),
                address_txt.getText(),
                weight_txt.getText(),
                packageFee_tx.getText(),
                inDate_txt.getText(),
                age_txt.getText(),
                packageCombo.getValue()

        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please fill all text field");
        }
        else {


            MemberDTO memberDTO = new MemberDTO(
                    memberID_txt.getText(),
                    packageID,
                    firstName_txt.getText(),
                    lastName_txt.getText(),
                    nic_txt.getText(),
                    age_txt.getText(),
                    contact_txt.getText(),
                    address_txt.getText(),
                    Double.parseDouble(weight_txt.getText()),
                    inDate_txt.getText(),
                    packageCombo.getValue(),
                    exDate_txt.getText(),
                    photoPath

            );
            WeightDTO weightDTO = new WeightDTO(
                    weightID_txt.getText(),
                    memberID_txt.getText(),
                    inDate_txt.getText(),
                    Double.parseDouble(weight_txt.getText())
            );

            PaymentDTO paymentDTO = new PaymentDTO(
                    memberID_txt.getText(),
                    Double.parseDouble(packageFee_tx.getText()),
                    inDate_txt.getText(),
                    exDate_txt.getText()
            );



            try {
                Boolean isAdd = memberController.add(memberDTO);

                if(isAdd){
                    Boolean rst = weightController.add(weightDTO);
                    if(rst){
                        Boolean isPaymentAdd = paymentController.add(paymentDTO);
                        if (isPaymentAdd){
                            JOptionPane.showMessageDialog(null,"Successfully Added");
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Failed");
                }
                loadMembers();
                loadMemberID();
                loadWeightID();
                clearText();

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        String memberID=memberID_txt.getText();
        try {
            if(memberController.delete(memberID)){
                JOptionPane.showMessageDialog(null,"Deleted");
            }else {
                JOptionPane.showMessageDialog(null,"Delete Failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadMembers();
        loadMemberID();
        loadWeightID();
        loadCombo();
        clearText();


    }



    @FXML
    void memberTableAction(MouseEvent event) {
        MembersTableView membersTableView=memberTable.getSelectionModel().getSelectedItem();
        memberID_txt.setText(""+membersTableView.getMemberID());
        firstName_txt.setText(""+membersTableView.getFirstName());
        lastName_txt.setText(""+membersTableView.getLastName());
        nic_txt.setText(""+membersTableView.getNic());
        contact_txt.setText(""+membersTableView.getContact());
        weight_txt.setText(""+membersTableView.getWeight());
        packageCombo.setValue(""+membersTableView.getPackageType());

        String name = firstName_txt.getText();

        String packageType = packageCombo.getValue();
        try {
            ArrayList<PackageDTO> packageFee = packageController.getName(packageType);
            for (PackageDTO allType :
                    packageFee) {
                packageFee_tx.setText(allType.getPackageFee() + "");

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        try {
            List<MemberDTO> allMembers=memberController.getName(name);
            for (MemberDTO cust :
                    allMembers) {
                photoPath=cust.getPath();
                address_txt.setText(cust.getAddress());
                age_txt.setText(cust.getAge());
                inDate_txt.setText(cust.getInDate());
                exDate_txt.setText(cust.getExpireDate());
                System.out.println(photoPath);
            }

            Image image=new Image(photoPath,270,270,true,false);
            imageView.setImage(image);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void removeButtonAction(ActionEvent event) {
        photoPath = "lk/gym/ui/images/notAvalable.png";
        Image image=new Image(photoPath,300,300,true,false);
        imageView.setImage(image);

    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        String packageType = packageCombo.getValue();
        String packageID = "";
        try {
            ArrayList<PackageDTO> packageList = packageController.getName(packageType);
            for (PackageDTO packageDTO :
                    packageList) {
                packageID = packageDTO.getPackageID();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                memberID_txt.getText(),
                firstName_txt.getText(),
                lastName_txt.getText(),
                nic_txt.getText(),
                contact_txt.getText(),
                address_txt.getText(),
                weight_txt.getText(),
                packageFee_tx.getText(),
                inDate_txt.getText(),
                age_txt.getText(),
                packageCombo.getValue()

        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please fill all text field");
        }
        else {


            MemberDTO memberDTO = new MemberDTO(
                    memberID_txt.getText(),
                    packageID,
                    firstName_txt.getText(),
                    lastName_txt.getText(),
                    nic_txt.getText(),
                    age_txt.getText(),
                    contact_txt.getText(),
                    address_txt.getText(),
                    Double.parseDouble(weight_txt.getText()),
                    inDate_txt.getText(),
                    packageCombo.getValue(),
                    exDate_txt.getText(),
                    photoPath

            );
            WeightDTO weightDTO = new WeightDTO(
                    weightID_txt.getText(),
                    memberID_txt.getText(),
                    inDate_txt.getText(),
                    Double.parseDouble(weight_txt.getText())
            );

            PaymentDTO paymentDTO = new PaymentDTO(
                    memberID_txt.getText(),
                    Double.parseDouble(packageFee_tx.getText()),
                    inDate_txt.getText(),
                    exDate_txt.getText()
            );



            try {
                Boolean isUpdate = memberController.update(memberDTO);

                if(isUpdate){
                    Boolean rst = weightController.update(weightDTO);
                    if(rst){
                        Boolean isPaymentUpdate = paymentController.update(paymentDTO);
                        if (isPaymentUpdate){
                            JOptionPane.showMessageDialog(null,"Successfully Updated");
                        }
                    }
                }else {
                    JOptionPane.showMessageDialog(null,"Failed");
                }

                loadMembers();
                loadMemberID();
                loadWeightID();
                clearText();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @FXML
    void inDateAction(ActionEvent event) {
        String packageType = packageCombo.getValue();
        int num = 0;
        try {
            ArrayList<PackageDTO> packageFee = packageController.getName(packageType);
            for (PackageDTO allType :
                    packageFee) {
                packageFee_tx.setText(allType.getPackageFee() + "");
                num = allType.getMonths();

            }
            loadExpireDate(num);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    @FXML
    void packageComboAction(ActionEvent event) {
        String packageType = packageCombo.getValue();
        int num = 0;
        try {
            ArrayList<PackageDTO> packageFee = packageController.getName(packageType);
            for (PackageDTO allType :
                    packageFee) {
                packageFee_tx.setText(allType.getPackageFee() + "");
                num = allType.getMonths();

            }
            loadExpireDate(num);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    @FXML
    void weightAction(ActionEvent event) {
        boolean result  = Validator.isNumber(weight_txt.getText());
        if (result){
            age_txt.requestFocus();
            weight_txt.setFocusColor(Paint.valueOf("#4059a9"));
        }else {
            weight_txt.setFocusColor(Paint.valueOf("red"));
        }

    }
    @FXML
    void memberIDAction(ActionEvent event) {
        firstName_txt.requestFocus();

    }

    @FXML
    void searchBYIDAction(ActionEvent event) {
        String memberID = searchByID_txt.getText();
        if (!data.isEmpty()) {
            for (int i = data.size(); i > 0; i--) {
                data.remove(i - 1);
            }
        }
        try {
            List<MemberDTO> allMembers = memberController.searchByID(memberID);
            if(allMembers != null){
                for (MemberDTO mem :
                        allMembers) {
                    data.add(new MembersTableView(
                            mem.getMemberID(),
                            mem.getFirstName(),
                            mem.getLastName(),
                            mem.getNic(),
                            mem.getContact(),
                            mem.getWeight(),
                            mem.getPackageType()
                    ));
                }
            }
            memberTable.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            weight_clm.setCellValueFactory(new PropertyValueFactory("weight"));
            packageType_clm.setCellValueFactory(new PropertyValueFactory("packageType"));

            memberTable.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    @FXML
    void nicAction(ActionEvent event) {
        boolean result  = Validator.isNumber(nic_txt.getText());
        if (result){
            nic_txt.setFocusColor(Paint.valueOf("#4059a9"));
            address_txt.requestFocus();
        }else {
            nic_txt.setFocusColor(Paint.valueOf("red"));
        }

    }
    @FXML
    void firstNameAction(ActionEvent event) {
        boolean result  = Validator.isAlpha(firstName_txt.getText());
        if (result) {
            System.out.println("supiri");
            lastName_txt.requestFocus();
            firstName_txt.setFocusColor(Paint.valueOf("#4059a9"));
        } else {
            firstName_txt.setFocusColor(Paint.valueOf("red"));
            //JOptionPane .  showMessageDialog(null, "Invalid Number");
        }

    }

    @FXML
    void lastNameAction(ActionEvent event) {
        boolean result  = Validator.isAlpha(lastName_txt.getText());
        if (result) {
            System.out.println("supiri");
            nic_txt.requestFocus();
            lastName_txt.setFocusColor(Paint.valueOf("#4059a9"));
        } else {
            lastName_txt.setFocusColor(Paint.valueOf("red"));
            //JOptionPane .  showMessageDialog(null, "Invalid Number");
        }

    }
    @FXML
    void ageAction(ActionEvent event) {
        boolean result  = Validator.isNumber(age_txt.getText());
        if (result){
            age_txt.setFocusColor(Paint.valueOf("#4059a9"));
        }else {
            age_txt.setFocusColor(Paint.valueOf("red"));
        }
    }

    @FXML
    void contactAction(ActionEvent event) {
        boolean result  = Validator.isPhoneNumber(contact_txt.getText());
        if (result) {
            System.out.println("Contact supiri");
            weight_txt.requestFocus();
            contact_txt.setFocusColor(Paint.valueOf("#4059a9"));
        } else {
            contact_txt.setFocusColor(Paint.valueOf("red"));
            //JOptionPane .  showMessageDialog(null, "Invalid Number");
        }

    }

    private void loadDate() {
        Date d1 = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(d1);
        inDate_txt.setText(date);
    }

    private void loadExpireDate(int num) {
        int amount = num;
        try {

            String currentDate = inDate_txt.getText();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date cDate = dateFormat.parse(currentDate);


            Calendar c = Calendar.getInstance();
            c.setTime(cDate);

            c.add(Calendar.MONTH,amount);
            Date currentDatePlusOne = c.getTime();
            exDate_txt.setText(dateFormat.format(currentDatePlusOne));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    private void loadMemberID() {
        prefix = "M";
        tableName = "members";
        columnName = "memberID";
        memberID_txt.setText(IDGenarator.getNextIdGenaretor(tableName,columnName,prefix));
    }

    private void loadMembers() {
        if (!data.isEmpty()) {
            for (int i = data.size(); i > 0; i--) {
                data.remove(i - 1);
            }
        }


        try {
            List<MemberDTO> allMembers = memberController.view();
            if(allMembers != null){
                for (MemberDTO mem :
                        allMembers) {
                    data.add(new MembersTableView(
                            mem.getMemberID(),
                            mem.getFirstName(),
                            mem.getLastName(),
                            mem.getNic(),
                            mem.getContact(),
                            mem.getWeight(),
                            mem.getPackageType()
                    ));
                }
            }
            memberTable.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            weight_clm.setCellValueFactory(new PropertyValueFactory("weight"));
            packageType_clm.setCellValueFactory(new PropertyValueFactory("packageType"));

            memberTable.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    private void loadWeightID() {
        prefix = "W";
        tableName = "weight";
        columnName = "weightID";
        weightID_txt.setText(IDGenarator.getNextIdGenaretor(tableName,columnName,prefix));
    }

    public void clearText(){
        firstName_txt.setText("");
        lastName_txt.setText("");
        nic_txt.setText("");
        address_txt.setText("");
        contact_txt.setText("");
        weight_txt.setText("");
        age_txt.setText("");
        exDate_txt.setText("");
        packageFee_tx.setText("");
        loadDate();
    }


}
