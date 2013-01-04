import static Option.*;

/*
 * Part of Option monad example in Java 8 from http://java.dzone.com/articles/no-more-excuses-use-null
 *
 * @author Mario Fusco
 */
public class FunctionUtils {
    public static Option<Integer> stringToInt(String s) {
        try {
            return some(Integer.parseInt(s));
        } catch (NumberFormatException nfe) {
            return none();
        }
    }
}

