package za.ac.cput.communitystoreplatform.util;

import java.util.regex.Pattern;

public class Helper {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidEmail(String email) {
        return !isNullOrEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isValidInt(int value) {
        return value > 0;
    }

    public static boolean isPositive(double value) {
        return value > 0;
    }

    public static boolean isValidCode(int code){
        if(code <1000 || code> 9999){
            return false;
        }
        return true;
    }
}
