package wordchecker;

import java.util.Arrays;

/**
 * User: Dmytro Vynokurov
 * Date: 10/16/13
 * Time: 10:28 PM
 */
public class ConsonantChecker implements WordChecker{
    private final static Character[] vowels = new Character[]{
            'a','o','u','i','e','y'
    };

    @Override
    public String check(String s) {
        String lowerCase = s.toLowerCase();

        int vowelsCount=0;
        int consonantCount=0;

        for(char c: lowerCase.toCharArray()){
            if(isVowel(c)) vowelsCount++;
            else if (isConsonant(c)) consonantCount++;
        }

        if(vowelsCount>consonantCount)return s;
        else return "None";
    }

    private boolean isVowel(char c){
        if(((int)c)>(int)'z' || ((int)c<(int)'a')) return false;
        return Arrays.asList(vowels).contains(c);
    }

    private boolean isConsonant(char c){
        if(((int)c)>(int)'z' || ((int)c<(int)'a')) return false;
        return !isVowel(c);
    }

}
