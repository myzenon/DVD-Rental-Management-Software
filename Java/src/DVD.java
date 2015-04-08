import java.util.Date;

public class DVD {

    // Information Attribute
    private int dvdID, categoryID, price;
    private String name;
    private boolean type; // True is new, False is old

    // Rental Attribute
    private int memberID, rentDay, rentMonth, rentYear;

    // Constructor
    public DVD(int dvdID, String name, int categoryID, boolean type, int price) {
        this.dvdID = dvdID;
        this.name = name;
        this.categoryID = categoryID;
        this.type = type;
        this.price = price;
        this.memberID = -1;
    }

    // Method to set Rental Attribute
    public void setRental(int memberID, int rentDay, int rentMonth, int rentYear) {
        this.memberID = memberID;
        this.rentDay = rentDay;
        this.rentMonth = rentMonth;
        this.rentYear = rentYear;
    }

    public void setInformation(String name, int categoryID, boolean type, int price) {
        this.name = name;
        this.categoryID = categoryID;
        this.type = type;
        this.price = price;
    }

    // Method to compare between rent date <-> return date
    public int compareRentDay(int returnDay, int returnMonth, int returnYear) {
        Date startDate = new Date(this.rentYear-1900,this.rentMonth-1,this.rentDay); // Start 1900-01-00
        Date endDate = new Date(returnYear-1900,returnMonth-1,returnDay); // Start 1900-01-00
        /*
             Divided 1000 for change to seconds
             Divided 3600 for change to hours
             Divided 24 for change to days
         */
        return (int)((endDate.getTime() - startDate.getTime()) /  1000 / 3600 / 24);
    }

    // Getter

    public int getDvdID() {
        return dvdID;
    }

    public String getName() {
        return name;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public boolean getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    public int getMemberID() {
        return memberID;
    }

    public boolean isType() {
        return type;
    }

    public int getRentDay() {
        return rentDay;
    }

    public int getRentMonth() {
        return rentMonth;
    }

    public int getRentYear() {
        return rentYear;
    }

    public String toString() {
        return "{" + dvdID + "} " + name;
    }
}
