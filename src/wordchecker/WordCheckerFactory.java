package wordchecker;

/**
 * User: Dmytro Vynokurov
 * Date: 10/16/13
 * Time: 10:52 PM
 */
public class WordCheckerFactory {
    public final static String CONSONANT="consonant";
    public final static String BRACKETS="brackets";

    public static WordChecker getWordChecker(String wordCheckerType){
        switch (wordCheckerType){
            case CONSONANT: return new ConsonantChecker();
            case BRACKETS: return new BracketsSwapper();
            default: throw new IllegalArgumentException("Word checker type does not exist");
        }
    }
}
