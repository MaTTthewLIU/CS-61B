import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class MapExercises {
    /** Returns a map from every lower case letter to the number corresponding to that letter, where 'a' is
     * 1, 'b' is 2, 'c' is 3, ..., 'z' is 26.
     */
    public static Map<Character, Integer> letterToNum() {
        Map<Character,Integer> a = new HashMap<>();
        char[] letter = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
        for (int i = 0; i < 26; i++){
            a.put(letter[i], i + 1);
        }
        return a;

    }

    /** Returns a map from the integers in the list to their squares. For example, if the input list
     *  is [1, 3, 6, 7], the returned map goes from 1 to 1, 3 to 9, 6 to 36, and 7 to 49.
     */
    public static Map<Integer, Integer> squares(List<Integer> nums) {
        Map<Integer, Integer> b = new HashMap<>();
        for(int i: nums){
            b.put(i, i * i);
        }
        return b;
    }

    /** Returns a map of the counts of all words that appear in a list of words. */
    public static Map<String, Integer> countWords(List<String> words) {
        Map<String, Integer> c =new TreeMap<>();
        int count = 0;
        for (int i =0; i < words.size(); i++){
            for(int j = 0; j < words.size(); j++){
                if(words.get(i) == words.get(j)){
                    count ++;
                }
            }
            if (! c.containsKey(words.get(i))){
                c.put(words.get(i), count);
            }
            count =0;
        }
        return c;
    }
}
