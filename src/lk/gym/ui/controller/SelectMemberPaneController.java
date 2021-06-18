package lk.gym.ui.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import lk.gym.controller.ControllerFactory;
import lk.gym.controller.custom.MemberController;
import lk.gym.dto.MemberDTO;
import lk.gym.ui.tableView.MembersTableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class SelectMemberPaneController implements Initializable {
    @FXML
    private TableView<MembersTableView> member_tbl;

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
    private TableColumn<MembersTableView, String> age_clm;

    @FXML
    private JFXTextField search_txt;

    @FXML
    private JFXTextField searchByID_txt;

    private MemberController memberController;
    private ObservableList<MembersTableView> data;

    private StaticsFrameController staticsFrameController;
    private PaymentFrameController paymentFrameController;

    private int counter = 0;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberController = (MemberController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.MEMBER);
        data = FXCollections.observableArrayList();
        loadMember();
        counter = paymentFrameController.count;
    }

    private void loadMember() {
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
                            mem.getAge()
                    ));
                }
            }
            member_tbl.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            age_clm.setCellValueFactory(new PropertyValueFactory("age"));

            member_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchByIDTxtAction(ActionEvent event) {
        String memberID = searchByID_txt.getText();
        if (!data.isEmpty()) {
            for (int i = data.size(); i > 0; i--) {
                data.remove(i - 1);
            }
        }
        try {
            List<MemberDTO> allMembers=memberController.searchByID(memberID);
            if(allMembers != null){
                for (MemberDTO mem :
                        allMembers) {
                    data.add(new MembersTableView(
                            mem.getMemberID(),
                            mem.getFirstName(),
                            mem.getLastName(),
                            mem.getNic(),
                            mem.getContact(),
                            mem.getAge()

                    ));
                }
            }
            member_tbl.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            age_clm.setCellValueFactory(new PropertyValueFactory("age"));

            member_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchByNameTxtAction(ActionEvent event) {
        String memberName = search_txt.getText();
        if (!data.isEmpty()) {
            for (int i = data.size(); i > 0; i--) {
                data.remove(i - 1);
            }
        }
        try {
            List<MemberDTO> allMembers=memberController.getName(memberName);
            if(allMembers != null){
                for (MemberDTO mem :
                        allMembers) {
                    data.add(new MembersTableView(
                            mem.getMemberID(),
                            mem.getFirstName(),
                            mem.getLastName(),
                            mem.getNic(),
                            mem.getContact(),
                            mem.getAge()

                    ));
                }
            }
            member_tbl.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            age_clm.setCellValueFactory(new PropertyValueFactory("age"));

            member_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void memberTableAction(MouseEvent event) {
        MembersTableView membersTableView=member_tbl.getSelectionModel().getSelectedItem();
        if (counter == 0){
            staticsFrameController.memberID_txt2.setText(""+membersTableView.getMemberID());
        }
        if(counter > 0){
            paymentFrameController.memberID_text3.setText(""+membersTableView.getMemberID());
            paymentFrameController.count = 0;

        }


        ((Stage)(((TableView)event.getSource()).getScene().getWindow())).close();

    }


}
