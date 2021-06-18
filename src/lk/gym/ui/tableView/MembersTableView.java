package lk.gym.ui.tableView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class MembersTableView {
    private SimpleStringProperty memberID;
    private SimpleStringProperty firstName;
    private SimpleStringProperty lastName;
    private SimpleStringProperty nic;
    private SimpleStringProperty age;
    private SimpleStringProperty contact;
    private SimpleStringProperty address;
    private SimpleDoubleProperty weight;
    private SimpleStringProperty inDate;
    private SimpleStringProperty packageType;
    private SimpleStringProperty expireDate;


    public MembersTableView(String memberID, String firstName, String lastName, String nic, String age, String contact, String address, Double weight, String inDate, String packageType, String expireDate) {
        this.memberID = new SimpleStringProperty(memberID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nic = new SimpleStringProperty(nic);
        this.age = new SimpleStringProperty(age);
        this.contact = new SimpleStringProperty(contact);
        this.address = new SimpleStringProperty(address);
        this.weight = new SimpleDoubleProperty(weight);
        this.inDate = new SimpleStringProperty(inDate);
        this.packageType = new SimpleStringProperty(packageType);
        this.expireDate = new SimpleStringProperty(expireDate);
    }
    public MembersTableView(String memberID, String firstName, String lastName, String nic, String age, String contact, String address, Double weight, String inDate, String packageType) {
        this.memberID = new SimpleStringProperty(memberID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nic = new SimpleStringProperty(nic);
        this.age = new SimpleStringProperty(age);
        this.contact = new SimpleStringProperty(contact);
        this.address = new SimpleStringProperty(address);
        this.weight = new SimpleDoubleProperty(weight);
        this.inDate = new SimpleStringProperty(inDate);
        this.packageType = new SimpleStringProperty(packageType);
    }

    public MembersTableView(String memberID, String firstName, String lastName, String nic, String contact, Double weight, String packageType) {
        this.memberID = new SimpleStringProperty(memberID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nic = new SimpleStringProperty(nic);
        this.contact = new SimpleStringProperty(contact);
        this.weight = new SimpleDoubleProperty(weight);
        this.packageType = new SimpleStringProperty(packageType);
    }
    public MembersTableView(String memberID, String firstName, String lastName, String nic, String contact, String age) {
        this.memberID = new SimpleStringProperty(memberID);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.nic = new SimpleStringProperty(nic);
        this.contact = new SimpleStringProperty(contact);
        this.age = new SimpleStringProperty(age);
    }


    public String getMemberID() {
        return memberID.get();
    }

    public SimpleStringProperty memberIDProperty() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID.set(memberID);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getNic() {
        return nic.get();
    }

    public SimpleStringProperty nicProperty() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic.set(nic);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getContact() {
        return contact.get();
    }

    public SimpleStringProperty contactProperty() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact.set(contact);
    }

    public String getAddress() {
        return address.get();
    }

    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public double getWeight() {
        return weight.get();
    }

    public SimpleDoubleProperty weightProperty() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight.set(weight);
    }

    public String getInDate() {
        return inDate.get();
    }

    public SimpleStringProperty inDateProperty() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate.set(inDate);
    }

    public String getPackageType() {
        return packageType.get();
    }

    public SimpleStringProperty packageTypeProperty() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType.set(packageType);
    }

    public String getExpireDate() {
        return expireDate.get();
    }

    public SimpleStringProperty expireDateProperty() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate.set(expireDate);
    }
}
