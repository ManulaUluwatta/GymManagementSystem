package lk.gym.ui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import lk.gym.controller.ControllerFactory;
import lk.gym.controller.custom.MemberController;
import lk.gym.controller.custom.WeightController;
import lk.gym.dto.MemberDTO;
import lk.gym.dto.WeightDTO;
import lk.gym.other.IDGenarator;
import lk.gym.other.Validator;
import lk.gym.ui.tableView.PackageTableView;
import lk.gym.ui.tableView.WeightTableView;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class StaticsFrameController implements Initializable {

    @FXML
    private JFXTextField date_txt;

    @FXML
    private JFXTextField weight_txt;

    @FXML
    private JFXTextField memberID_txt;
    public static JFXTextField memberID_txt2;

    @FXML
    private JFXButton select_btn;

    @FXML
    private JFXButton add_btn;

    @FXML
    private JFXButton update_btn;

    @FXML
    private TableView<WeightTableView> weight_tbl;

    @FXML
    private TableColumn<WeightTableView, String> date_clm;

    @FXML
    private TableColumn<WeightTableView, String> weight_clm;

    @FXML
    private JFXTextField memberIDOne_txt;

    @FXML
    private ImageView imageView;

    @FXML
    private JFXButton delete_btn;

    @FXML
    private LineChart<?, ?> weightChart;

    @FXML
    private TableColumn<WeightTableView, String> weightID_clm;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private JFXTextField weightID_txt;

    private ObservableList<WeightTableView> data;

    private WeightController weightController;
    private MemberController memberController;

    private String photoPath;

    private String prefix;
    private String tableName;
    private String columnName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        weightController = (WeightController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.WEIGHT);
        memberController = (MemberController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.MEMBER);
        photoPath = "lk/gym/ui/images/notAvalable.png";
        data= FXCollections.observableArrayList();
        loadWeight();
        loadDate();
        loadWeightID();

        memberID_txt2 = memberID_txt;

    }

    @FXML
    void addButtonAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                weightID_txt.getText(),
                memberID_txt.getText(),
                weight_txt.getText(),
                date_txt.getText()

        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please fill all text field");
        }
        WeightDTO weightDTO = new WeightDTO(
                weightID_txt.getText(),
                memberID_txt.getText(),
                date_txt.getText(),
                Double.parseDouble(weight_txt.getText())
        );
        try {
            if(weightController.add(weightDTO)){
                JOptionPane.showMessageDialog(null,"Success");
            }else {
                JOptionPane.showMessageDialog(null,"Failed");
            }
            loadWeightID();
            clearText();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void deleteButtonAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                weightID_txt.getText(),
                memberID_txt.getText(),
        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please fill all text field");
        }

        String weightID = weightID_txt.getText();
        try {
            boolean isDelete = weightController.delete(weightID);
            if(isDelete){
                JOptionPane.showMessageDialog(null,"Deleted");
            }else {
                JOptionPane.showMessageDialog(null,"Delete Failed");
            }
            loadWeightID();
            clearText();
            loadWeight();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void memberIDTextAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                memberIDOne_txt.getText()
        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please Enter Member ID");
        }else {
            loadWeight();
            loadChart();

            String id = memberIDOne_txt.getText();


            try {
                List<MemberDTO> allMembers = memberController.searchByID(id);
                for (MemberDTO mem :
                        allMembers) {
                    photoPath=mem.getPath();
                }

                Image image=new Image(photoPath,384,576,false,false);
                imageView.setImage(image);

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }



    }

    @FXML
    void memberTextAction(ActionEvent event) {
        weight_txt.requestFocus();

    }

    @FXML
    void selectButtonAction(ActionEvent event) {
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
    void updateButtonAction(ActionEvent event) {
        Boolean isValidatorOk = Validator.isNotEmpty(new String[]{
                weightID_txt.getText(),
                memberID_txt.getText(),
                weight_txt.getText(),
                date_txt.getText()

        });
        if(!isValidatorOk){
            JOptionPane.showMessageDialog(null,"Please fill all text field");
        }
        WeightDTO weightDTO = new WeightDTO(
                weightID_txt.getText(),
                memberID_txt.getText(),
                date_txt.getText(),
                Double.parseDouble(weight_txt.getText())
        );
        try {
            boolean isUpdate = weightController.update(weightDTO);
            if(isUpdate){
                JOptionPane.showMessageDialog(null,"Success");
            }else {
                JOptionPane.showMessageDialog(null,"Failed");
            }
            loadWeightID();
            clearText();
            loadWeight();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    @FXML
    void weightTableAction(MouseEvent event) {

        WeightTableView weightTableView = weight_tbl.getSelectionModel().getSelectedItem();
        weightID_txt.setText(""+weightTableView.getWeightID());
        date_txt.setText(""+weightTableView.getDate());
        weight_txt.setText(""+weightTableView.getWeight());

        String id = weightID_txt.getText();
        try {
            List<WeightDTO> list = weightController.getID(id);
            for (WeightDTO all :
                    list) {
                memberID_txt.setText(all.getMemberID());
                System.out.printf(all.getMemberID());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }
    @FXML
    void weightTextAction(ActionEvent event) {
        boolean result  = Validator.isNumber(weight_txt.getText());
        if (result){
            weight_txt.setFocusColor(Paint.valueOf("#4059a9"));
        }else {
            weight_txt.setFocusColor(Paint.valueOf("red"));
        }
    }
    private void loadWeight() {
        if (!data.isEmpty()) {
            for (int i = data.size(); i > 0; i--) {
                data.remove(i - 1);
            }
        }

        String id = memberIDOne_txt.getText();

        try {
            List<WeightDTO> allWeights = weightController.getName(id);
            if(allWeights != null){
                for (WeightDTO w :
                        allWeights) {
                    data.add(new WeightTableView(
                            w.getWeightID(),
                            w.getDate(),
                            w.getWeight()
                    ));
                }
            }
            weight_tbl.getItems().removeAll();
            date_clm.setCellValueFactory(new PropertyValueFactory("date"));
            weight_clm.setCellValueFactory(new PropertyValueFactory("weight"));
            weightID_clm.setCellValueFactory(new PropertyValueFactory("weightID"));


            weight_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void loadDate() {
        Date d1              = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String date          = sdf.format(d1);
        date_txt.setText(date);

    }

    private void loadChart() {
        weightChart.getData().clear();
        XYChart.Series series = new XYChart.Series<String,Double>();


        String month = "january";
        String id = memberIDOne_txt.getText();
        try {
            List<WeightDTO> allWeights = weightController.getName(id);
            if(allWeights != null){
                for (WeightDTO w :
                        allWeights) {
                    series.getData().add(new XYChart.Data<String,Double>(w.getDate(), w.getWeight()));
                }
            }

//            for(int i = 1 ; i < 10; i++){
//                series.getData().add(new XYChart.Data(i+"", 70+i));
//            }

            series.setName("Weight");
            weightChart.getData().addAll(series);

//            series.getData().add(new XYChart.Data("jan", 67));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // weightChart.getData().addAll(series);
    }

    private void loadWeightID() {
        prefix = "W";
        tableName = "weight";
        columnName = "weightID";
        weightID_txt.setText(IDGenarator.getNextIdGenaretor(tableName,columnName,prefix));
    }

    void clearText(){
        weight_txt.setText("");
        memberID_txt.setText("");
    }


}
