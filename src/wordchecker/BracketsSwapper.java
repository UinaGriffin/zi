package wordchecker;

/**
 * User: Dmytro Vynokurov
 * Date: 10/16/13
 * Time: 10:40 PM
 */
public class BracketsSwapper implements WordChecker {
    @Override
    public String check(String s) {
        return s.replaceAll("\\Q[\\E","(").replaceAll("\\Q]\\E",")");
    }
}
