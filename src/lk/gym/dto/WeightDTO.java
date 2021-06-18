package lk.gym.dto;

public class WeightDTO implements SuperDTO {
    private String weightID;
    private String memberID;
    private String date;
    private double weight;

    public WeightDTO() {
    }

    public WeightDTO(String weightID, String memberID, String date, double weight) {
        this.weightID = weightID;
        this.memberID = memberID;
        this.date = date;
        this.weight = weight;
    }

    public WeightDTO(String weightID, String memberID) {
        this.weightID = weightID;
        this.memberID = memberID;
    }

    public WeightDTO(String memberID) {
        this.memberID = memberID;
    }

    public WeightDTO(String weightID, String date, double weight) {
        this.weightID = weightID;
        this.date = date;
        this.weight = weight;
    }




    public String getWeightID() {
        return weightID;
    }

    public void setWeightID(String weightID) {
        this.weightID = weightID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
