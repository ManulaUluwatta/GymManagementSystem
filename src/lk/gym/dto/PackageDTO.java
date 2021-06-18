package lk.gym.dto;

public class PackageDTO implements SuperDTO{
    private String packageID;
    private String packageType;
    private Double packageFee;
    private int months;



    public PackageDTO(String packageType) {
        this.packageType = packageType;
    }

    public PackageDTO(String packageID, String packageType, Double packageFee) {
        this.packageID = packageID;
        this.packageType = packageType;
        this.packageFee = packageFee;
    }

    public PackageDTO(String packageID, String packageType, Double packageFee, int months) {
        this.packageID = packageID;
        this.packageType = packageType;
        this.packageFee = packageFee;
        this.months = months;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public Double getPackageFee() {
        return packageFee;
    }

    public void setPackageFee(Double packageFee) {
        this.packageFee = packageFee;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }
}
