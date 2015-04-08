import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Controller {

    // Database
    private Map<Integer,DVD> dbDVD;
    private int pointerDVD;
    private Map<Integer,Member> dbMember;
    private Map<Integer,String> dbCategory;
    private int pointerCategory;

    // Rate of Store
    private int newTypeDays = 1;
    private int oldTypeDays = 3;
    private int rentalPrice = 20;
    private int finePerDays = 10;


    // Start Program
    public Controller() {
        // Prepare Database
        dbDVD = new HashMap<Integer,DVD>();
        pointerDVD = 0;
        dbMember = new HashMap<Integer,Member>();
        dbCategory = new HashMap<Integer,String>();
        pointerCategory = 0;
        testData();
        new Program(this);
    }

    // Add DVD
    public void addDVD(String name, int category, boolean type, int price) {
        dbDVD.put(pointerDVD, new DVD(pointerDVD, name, category, type, price));
        pointerDVD++;
    }


    // Edit DVD
    public void editDVD(int dvdID, String name, int category, boolean type, int price) {
        DVD dvd = findDVD(dvdID);
        if(dvd != null) {
            dvd.setInformation(name,category,type,price);
        }
    }

    // Remove DVD
    public void removeDVD(int dvdID) {
        dbDVD.remove(dvdID);
    }

    // Find DVD
    public DVD findDVD(int dvdID) {
        DVD dvd = dbDVD.get(dvdID);
        return dvd;
    }

    // Rental DVD [Must to pay]
    // -1 for DVD rented | -2 for Not Found DVD
    public int rentalDVD(int dvdID, int memberID, int rentalDay, int rentalMonth, int rentalYear) {
        DVD dvd = findDVD(dvdID);
        if(dvd != null) {
            if(dvd.getMemberID() == -1)
                dvd.setRental(memberID,rentalDay,rentalMonth,rentalYear);
            else
                return -1;
            return rentalPrice;
        }
        return -2;
     }

    // Return DVD [Have to pay]
    // -1 for DVD never rented | -2 for Not Found DVD | -3 For Incorrect Date
    public int returnDVD(int dvdID, int returnDay, int returnMonth, int returnYear, boolean isBroken) {
        DVD dvd = findDVD(dvdID);
        if(dvd != null) {
            if(dvd.getMemberID() != -1) {
                int totalDay = dvd.compareRentDay(returnDay,returnMonth,returnYear);
                if(totalDay < 0) {
                    return -3;
                }
                int totalBalance = 0;
                if(dvd.getType())
                    totalDay -= newTypeDays;
                else
                    totalDay -= oldTypeDays;
                if(totalDay > 0) {
                    totalBalance = totalDay * finePerDays;
                }
                if(isBroken) {
                    totalBalance += dvd.getPrice() * 2; // If DVD is broken | 2 Times of Price Baht
                }
                dvd.setRental(-1,0,0,0);
                return totalBalance;
            }
            else {
                return -1;
            }
        }
        return -2;
    }

    // Add Category
    public void addCategory(String categoryName) {
        dbCategory.put(pointerCategory,categoryName);
        pointerCategory++;
    }

    // Find Category
    public String findCategory(int categoryID) {
        return dbCategory.get(categoryID);
    }

    public Map<Integer,String> getCategory() {
        return this.dbCategory;
    }

    // Delete Category
    public void deleteCategory(int categoryID) {
        dbCategory.remove(categoryID);
    }

    // Add Member
    public void addMember(String firstName, String lastName, String address, String email, String phoneNum, boolean gender) {
        dbMember.put(dbMember.size(), new Member(dbMember.size(), firstName, lastName, address, email, phoneNum, gender));
    }

    // Edit Member
    public void editMember(int memberID, String firstName, String lastName, String address, String email, String phoneNum, boolean gender) {
        Member member = findMember(memberID);
        if(member != null) {
            member.setInformation(firstName,lastName,address,email,phoneNum,gender);
        }
    }

    // Find Member
    public Member findMember(int memberID) {
        Member member = dbMember.get(memberID);
        return member;
    }

    ////////////////////////////////////////////////////////////

    // Show list of DVD
    public ArrayList<String> showDVDList() {
        ArrayList<String> list = new ArrayList<String>();
        for(DVD dvd : dbDVD.values()) {
            list.add("ID : " + dvd.getDvdID() + " | " + dvd.getName());
        }
        return list;
    }


    // Show list of DVD from Category
    public ArrayList<String> showDVDList_C(int categoryID) {
        ArrayList<String> list = new ArrayList<String>();
        for(DVD dvd : dbDVD.values()) {
            if(dvd.getCategoryID() == categoryID) {
                list.add("ID : " + dvd.getDvdID() + " | " + dvd.getName());
            }
        }
        return list;
    }

    // Show list of DVD from Member
    public ArrayList<String> showDVDList_M(int memberID) {
        ArrayList<String> list = new ArrayList<String>();
        for(DVD dvd : dbDVD.values()) {
            if(dvd.getMemberID() == memberID) {
                list.add("ID : " + dvd.getDvdID() + " | " + dvd.getName());
            }
        }
        return list;
    }

    // Count amount of DVD
    public int countDVD()  {
        return dbDVD.size();
    }

    // Count amount of Member
    public int countMember() {
        return dbMember.size();
    }

    ////////////////////////////////////////////////////////////

    // Change Rate of Store
    public void changeRate(int newTypeDays, int oldTypeDays, int rentalPrice, int finePerDays) {
        this.newTypeDays = newTypeDays;
        this.oldTypeDays = oldTypeDays;
        this.rentalPrice = rentalPrice;
        this.finePerDays = finePerDays;
    }

    ////////////////////////////////////////////////////////////

    // Show Error Message
    public void showError(String header, String message) {
        JOptionPane.showMessageDialog(null, "Error : " + message, header, JOptionPane.ERROR_MESSAGE);
    }

    ///////////////////////////////////////////////////////////

    // Start Point -> Start Program
    public static void main(String[] args) {
        new Controller();
    }

    // Test Data
    private void testData() {
        addCategory("Cate 1");
        addDVD("Movie", 0, true, 200);
        addMember("Zenon", "SI", "ee", "email", "00003", true);
        rentalDVD(0,0,1,1,2001);
    }

    public Collection<DVD> getListDVD(int categoryID) {
        if(categoryID >= 0) {
            Collection<DVD> collectionDVD = new ArrayList<DVD>();
            for(DVD dvd : dbDVD.values()) {
                if(dvd.getCategoryID() == categoryID) {
                    collectionDVD.add(dvd);
                }
            }
            return collectionDVD;
        }
        return dbDVD.values();
    }

}
