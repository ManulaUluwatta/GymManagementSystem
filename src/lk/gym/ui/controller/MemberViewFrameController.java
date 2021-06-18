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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.gym.controller.ControllerFactory;
import lk.gym.controller.custom.MemberController;
import lk.gym.dto.MemberDTO;
import lk.gym.ui.tableView.MembersTableView;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MemberViewFrameController implements Initializable {
    @FXML
    private TableView<MembersTableView> members_tbl;

    @FXML
    private TableColumn<MembersTableView, String> memberID_clm;

    @FXML
    private TableColumn<MembersTableView, String> firstName_clm;

    @FXML
    private TableColumn<MembersTableView, String> lastName_clm;

    @FXML
    private TableColumn<MembersTableView, String> nic_clm;

    @FXML
    private TableColumn<MembersTableView, String> age_clm;

    @FXML
    private TableColumn<MembersTableView, String> contact_clm;

    @FXML
    private TableColumn<MembersTableView, String> address_clm;

    @FXML
    private TableColumn<MembersTableView, Double> weight_clm;

    @FXML
    private TableColumn<MembersTableView, String> inDate_clm;

    @FXML
    private TableColumn<MembersTableView, String> package_clm;

    @FXML
    private TableColumn<MembersTableView, String> exDate_clm;

    @FXML
    private JFXTextField memberName_txt;

    @FXML
    private ImageView imageView;


    @FXML
    private JFXTextField searchByName_txt;

    @FXML
    private JFXTextField searchByID_txt;


    private JFXTextField memberName_txt1;

    private MemberController memberController;

    private ObservableList<MembersTableView> data;

    private String photoPath;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        memberController = (MemberController) ControllerFactory.getInstance().getController(ControllerFactory.controllerType.MEMBER);
        data= FXCollections.observableArrayList();

        loadMembers();

    }
    @FXML
    void SearchByIDAction(ActionEvent event) {
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
                            mem.getAge(),
                            mem.getContact(),
                            mem.getAddress(),
                            mem.getWeight(),
                            mem.getInDate(),
                            mem.getPackageType(),
                            mem.getExpireDate()

                    ));
                }
            }
            members_tbl.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            age_clm.setCellValueFactory(new PropertyValueFactory("age"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            address_clm.setCellValueFactory(new PropertyValueFactory("address"));
            weight_clm.setCellValueFactory(new PropertyValueFactory("weight"));
            inDate_clm.setCellValueFactory(new PropertyValueFactory("inDate"));
            package_clm.setCellValueFactory(new PropertyValueFactory("packageType"));
            exDate_clm.setCellValueFactory(new PropertyValueFactory("expireDate"));


            members_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void searchByNameAction(ActionEvent event) {
        String memberName = searchByName_txt.getText();
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
                            mem.getAge(),
                            mem.getContact(),
                            mem.getAddress(),
                            mem.getWeight(),
                            mem.getInDate(),
                            mem.getPackageType(),
                            mem.getExpireDate()

                    ));
                }

            }
            members_tbl.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            age_clm.setCellValueFactory(new PropertyValueFactory("age"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            address_clm.setCellValueFactory(new PropertyValueFactory("address"));
            weight_clm.setCellValueFactory(new PropertyValueFactory("weight"));
            inDate_clm.setCellValueFactory(new PropertyValueFactory("inDate"));
            package_clm.setCellValueFactory(new PropertyValueFactory("packageType"));
            exDate_clm.setCellValueFactory(new PropertyValueFactory("expireDate"));


            members_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void memberTableAction(MouseEvent event) {
        MembersTableView membersTableView=members_tbl.getSelectionModel().getSelectedItem();
        searchByName_txt.setText(""+membersTableView.getFirstName());

        String name = searchByName_txt.getText();


        try {
            List<MemberDTO> allMembers=memberController.getName(name);
            for (MemberDTO cust :
                    allMembers) {
                photoPath=cust.getPath();
                System.out.println(photoPath);
            }

            Image image=new Image(photoPath,200,200,true,false);
            imageView.setImage(image);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
                            mem.getAge(),
                            mem.getContact(),
                            mem.getAddress(),
                            mem.getWeight(),
                            mem.getInDate(),
                            mem.getPackageType(),
                            mem.getExpireDate()
                    ));
                }
            }
            members_tbl.getItems().removeAll();
            memberID_clm.setCellValueFactory(new PropertyValueFactory("memberID"));
            firstName_clm.setCellValueFactory(new PropertyValueFactory("firstName"));
            lastName_clm.setCellValueFactory(new PropertyValueFactory("lastName"));
            nic_clm.setCellValueFactory(new PropertyValueFactory("nic"));
            age_clm.setCellValueFactory(new PropertyValueFactory("age"));
            contact_clm.setCellValueFactory(new PropertyValueFactory("contact"));
            address_clm.setCellValueFactory(new PropertyValueFactory("address"));
            weight_clm.setCellValueFactory(new PropertyValueFactory("weight"));
            inDate_clm.setCellValueFactory(new PropertyValueFactory("inDate"));
            package_clm.setCellValueFactory(new PropertyValueFactory("packageType"));
            exDate_clm.setCellValueFactory(new PropertyValueFactory("expireDate"));

            members_tbl.setItems(data);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
