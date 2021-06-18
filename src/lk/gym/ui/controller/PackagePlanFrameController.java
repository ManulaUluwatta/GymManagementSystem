package lk.gym.ui.controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import lk.gym.controller.ControllerFactory;
import lk.gym.controller.custom.PackageController;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.PackageDTO;
import lk.gym.other.IDGenarator;
import lk.gym.other.Validator;
import lk.gym.ui.tableView.MembersTableView;
import lk.gym.ui.tableView.PackageTableView;

import javax.swing.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class PackagePlanFrameController implements Initializable {
    @FXML
    private JFXTextField packageID_txt;

    @FXML
    private JFXTextField packageType_txt;

    @FXML
    private JFXTextField packageFee_txt;

    @FXML
    private JFXButton delete_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private JFXButton update_btn;

    @FXML
    private TableView<PackageTableView> packageTable;

    @FXML
    private TableColumn<PackageTableView, String> packageID_clm;

    @FXML
    private TableColumn<PackageTableView, String> packageType_clm;

    @FXML
    private TableColumn<PackageTableView, Double> packageFee_clm;

    @FXML
    private TableColumn<PackageTableView, Integer> month_clm;

    @FXML
    private JFXTextField month_txt;

    private PackageController packageController;

    private ObservableList<PackageTableView> data;
    private String prefix;
    private String tableName;
    private String columnName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        packageController = (PackageController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.PACKAGE);
        data = FXCollections.observableArrayList();

        loadPackageID();


        loadPackage();
    }

    private void loadPackageID() {
        prefix = "P";
        tableName = "package";
        columnName = "packageID";
        packageID_txt.setText(IDGenarator.getNextIdGenaretor(tableName,columnName,prefix));
    }


    @FXML
    void addButtonAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                packageID_txt.getText(),
                packageType_txt.getText(),
                packageFee_txt.getText(),
                month_txt.getText()
        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please fill all text field");
        }else {
            PackageDTO packageDTO = new PackageDTO(
                    packageID_txt.getText(),
                    packageType_txt.getText(),
                    Double.parseDouble(packageFee_txt.getText()),
                    Integer.parseInt(month_txt.getText())
            );

            try {
                Boolean isAdded = packageController.add(packageDTO);
                if (isAdded){
                    loadPackage();
                    loadPackageID();
                    clearText();
                }else {
                    JOptionPane.showMessageDialog(null,"Something Wrong");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    private void clearText() {
        packageFee_txt.setText("");
        packageType_txt.setText("");
        month_txt.setText("");
    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        String packageID = packageID_txt.getText();
        try {
            if(packageController.delete(packageID)){
                JOptionPane.showMessageDialog(null,"Deleted");
            }else {
                JOptionPane.showMessageDialog(null,"Delete Failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadPackageID();
        loadPackage();
        clearText();

    }

    @FXML
    void updateButtonAction(ActionEvent event) {
        PackageDTO packageDTO = new PackageDTO(
                packageID_txt.getText(),
                packageType_txt.getText(),
                Double.parseDouble(packageFee_txt.getText()),
                Integer.parseInt(month_txt.getText())

        );
        try {
            if(packageController.update(packageDTO)){

            }else {
                JOptionPane.showMessageDialog(null,"update failed");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        loadPackage();
        loadPackageID();
        clearText();

    }
    @FXML
    void packageTableAction(MouseEvent event) {
        PackageTableView packageTableView = packageTable.getSelectionModel().getSelectedItem();
        packageID_txt.setText(""+packageTableView.getPackageID());
        packageType_txt.setText(""+packageTableView.getPackageType());
        packageFee_txt.setText(""+packageTableView.getPackageFee());
        month_txt.setText(""+packageTableView.getMonths());

    }

    @FXML
    void packageTypeTextAction(ActionEvent event) {
        packageFee_txt.requestFocus();

    }
    @FXML
    void packageFeeTxtAction(ActionEvent event) {
        boolean result  = Validator.isNumber(packageFee_txt.getText());
        if (result){
            packageFee_txt.setFocusColor(Paint.valueOf("#4059a9"));
            month_txt.requestFocus();
        }else {
            packageFee_txt.setFocusColor(Paint.valueOf("red"));
        }

    }
    @FXML
    void monthTextAction(ActionEvent event) {

        boolean result  = Validator.isNumber(month_txt.getText());
        if (result){
            month_txt.setFocusColor(Paint.valueOf("#4059a9"));
            add_btn.requestFocus();
        }else {
            month_txt.setFocusColor(Paint.valueOf("red"));
        }
    }


    private void loadPackage() {
        if (!data.isEmpty()) {
            for (int i = data.size(); i > 0; i--) {
                data.remove(i - 1);
            }
        }


        try {
            List<PackageDTO> allPackages = packageController.view();
            if(allPackages != null){
                for (PackageDTO pac :
                        allPackages) {
                    data.add(new PackageTableView(
                            pac.getPackageID(),
                            pac.getPackageType(),
                            pac.getPackageFee(),
                            pac.getMonths()
                    ));
                }
            }
            packageTable.getItems().removeAll();
            packageID_clm.setCellValueFactory(new PropertyValueFactory("packageID"));
            packageType_clm.setCellValueFactory(new PropertyValueFactory("packageType"));
            packageFee_clm.setCellValueFactory(new PropertyValueFactory("packageFee"));
            month_clm.setCellValueFactory(new PropertyValueFactory("months"));

            packageTable.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
