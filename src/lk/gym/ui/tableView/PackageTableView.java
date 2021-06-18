package lk.gym.ui.tableView;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class PackageTableView {
    private SimpleStringProperty packageID;
    private SimpleStringProperty packageType;
    private SimpleDoubleProperty packageFee;
    private SimpleIntegerProperty months;



    public PackageTableView(String packageID, String packageType, Double packageFee) {
        this.packageID = new SimpleStringProperty(packageID);
        this.packageType = new SimpleStringProperty(packageType);
        this.packageFee = new SimpleDoubleProperty(packageFee);
    }

    public PackageTableView(String packageID, String packageType, Double packageFee, Integer months) {
        this.packageID = new SimpleStringProperty(packageID);
        this.packageType = new SimpleStringProperty(packageType);
        this.packageFee = new SimpleDoubleProperty(packageFee);
        this.months = new SimpleIntegerProperty(months);
    }

    public String getPackageID() {
        return packageID.get();
    }

    public SimpleStringProperty packageIDProperty() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID.set(packageID);
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

    public double getPackageFee() {
        return packageFee.get();
    }

    public SimpleDoubleProperty packageFeeProperty() {
        return packageFee;
    }

    public void setPackageFee(double packageFee) {
        this.packageFee.set(packageFee);
    }

    public int getMonths() {
        return months.get();
    }

    public SimpleIntegerProperty monthsProperty() {
        return months;
    }

    public void setMonths(int months) {
        this.months.set(months);
    }
}
