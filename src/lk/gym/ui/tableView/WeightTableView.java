package lk.gym.ui.tableView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;

public class WeightTableView {
    private SimpleStringProperty weightID;
    private SimpleStringProperty memberID;
    private SimpleStringProperty date;
    private SimpleDoubleProperty weight;

    public WeightTableView(String date, double weight) {
        this.date = new SimpleStringProperty(date);
        this.weight = new SimpleDoubleProperty(weight);
    }

    public WeightTableView(String weightID, String date, Double weight) {
        this.weightID = new SimpleStringProperty(weightID);
        this.date = new SimpleStringProperty(date);
        this.weight = new SimpleDoubleProperty(weight);
    }



    public WeightTableView(String weightID, String memberID, String date, Double weight) {
        this.weightID = new SimpleStringProperty(weightID);
        this.memberID = new SimpleStringProperty(memberID);
        this.date = new SimpleStringProperty(date);
        this.weight = new SimpleDoubleProperty(weight);
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

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
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

    public String getWeightID() {
        return weightID.get();
    }

    public SimpleStringProperty weightIDProperty() {
        return weightID;
    }

    public void setWeightID(String weightID) {
        this.weightID.set(weightID);
    }
}
