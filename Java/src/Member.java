public class Member {

    // Information Attribute
    private int memberID;
    private String firstName, lastName, address, email, phoneNum;
    private boolean gender; // True is male, False is female

    // Constructor
    public Member(int memberID, String firstName, String lastName, String address, String email, String phoneNum, boolean gender) {
        this.memberID = memberID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNum = phoneNum;
        this.gender = gender;
    }

    public void setInformation(String firstName, String lastName, String address, String email, String phoneNum, boolean gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.email = email;
        this.phoneNum = phoneNum;
        this.gender = gender;
    }

    // Getter
    public int getMemberID() {
        return memberID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public boolean isGender() {
        return gender;
    }

}
