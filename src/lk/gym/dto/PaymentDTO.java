package lk.gym.dto;

public class PaymentDTO implements SuperDTO {
    private String paymnetID;
    private String memberID;
    private double paymentFee;
    private String paymentDate;
    private String expireDate;

    public PaymentDTO() {
    }

    public PaymentDTO(String paymnetID, String memberID, double paymentFee, String paymentDate, String expireDate) {
        this.paymnetID = paymnetID;
        this.memberID = memberID;
        this.paymentFee = paymentFee;
        this.paymentDate = paymentDate;
        this.expireDate = expireDate;
    }

    public PaymentDTO(String memberID, double paymentFee, String paymentDate, String expireDate) {
        this.memberID = memberID;
        this.paymentFee = paymentFee;
        this.paymentDate = paymentDate;
        this.expireDate = expireDate;
    }

    public String getPaymnetID() {
        return paymnetID;
    }

    public void setPaymnetID(String paymnetID) {
        this.paymnetID = paymnetID;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public double getPaymentFee() {
        return paymentFee;
    }

    public void setPaymentFee(double paymentFee) {
        this.paymentFee = paymentFee;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }
}
