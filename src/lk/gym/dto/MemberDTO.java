package lk.gym.dto;

public class MemberDTO implements SuperDTO {
    private String memberID;
    private String packageID;
    private String firstName;
    private String lastName;
    private String nic;
    private String age;
    private String contact;
    private String address;
    private Double weight;
    private String inDate;
    private String packageType;
    private String expireDate;
    private String path;

    public MemberDTO() {
    }

    public MemberDTO(String memberID, String packageID, String firstName, String lastName, String nic, String age, String contact, String address, Double weight, String inDate, String packageType, String expireDate, String path) {
        this.memberID = memberID;
        this.packageID = packageID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.age = age;
        this.contact = contact;
        this.address = address;
        this.weight = weight;
        this.inDate = inDate;
        this.packageType = packageType;
        this.expireDate = expireDate;
        this.path = path;
    }

    public MemberDTO(String memberID, String firstName, String lastName, String nic, String age, String contact, String address, Double weight, String inDate, String packageType, String expireDate) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.age = age;
        this.contact = contact;
        this.address = address;
        this.weight = weight;
        this.inDate = inDate;
        this.packageType = packageType;
        this.expireDate = expireDate;
    }

    public MemberDTO(String memberID, String firstName, String lastName, String nic, String age, String contact, String address, Double weight, String inDate, String packageType, String expireDate, String path) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.nic = nic;
        this.age = age;
        this.contact = contact;
        this.address = address;
        this.weight = weight;
        this.inDate = inDate;
        this.packageType = packageType;
        this.expireDate = expireDate;
        this.path = path;
    }

    public MemberDTO(String memberID, String expireDate) {
        this.memberID = memberID;
        this.expireDate = expireDate;
    }

    public String getMemberID() {
        return memberID;
    }

    public void setMemberID(String memberID) {
        this.memberID = memberID;
    }

    public String getPackageID() {
        return packageID;
    }

    public void setPackageID(String packageID) {
        this.packageID = packageID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
