package module;

public class Checker {
    public static boolean isNumber(String text) {
        try {
            Integer.parseInt(text);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    public static boolean isLeapYear(int year) {
        if(( ((year % 4) == 0) && ((year % 100) != 0)) || ((year % 400) == 0) )
            return true;
        else
            return false;
    }
    public static int endDayofMonth(int month, int year) {
        if(month == 2) {
            if(isLeapYear(year))
                return 29;
            else
                return 28;
        }
        else if((month == 1) || (month == 3) || (month == 5) || (month == 7) || (month == 8) || (month == 10) || (month == 12)) {
            return 31;
        }
        else {
            return 30;
        }
    }
}
