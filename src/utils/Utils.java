package utils;

public class Utils {

    public static boolean isName(String name) {
        String expression = "^[a-zA-Z\\s]+";
        return name.matches(expression);
    }

}
