import java.util.*;

public class ListExercises {

    /**
     * Returns the total sum in a list of integers
     */
    public static int sum(List<Integer> L) {
        int total = 0;
        int j = L.size();
        if (j == 0) {
            return 0;
        }
        for (int i : L) {
            total += i;
        }
        return total;
    }

    /**
     * Returns a list containing the even numbers of the given list
     */
    public static List<Integer> evens(List<Integer> L) {
        List<Integer> E = new ArrayList<>();
        int EvenNum = E.size();
        for (int i : L) {
            if (i % 2 == 0) {
                E.add(i);
            }
        }
        return E;
    }

    /**
     * Returns a list containing the common item of the two given lists
     */
    public static List<Integer> common(List<Integer> L1, List<Integer> L2) {
        List<Integer> C1 = new ArrayList<>();
        List<Integer> C2 = new ArrayList<>();
        C1.addAll(L1);
        C2.addAll(L2);
        C1.retainAll(C2);
        return C1;
    }


    /**
     * Returns the number of occurrences of the given character in a list of strings.
     */
    public static int countOccurrencesOfC(List<String> words, char c) {
        int count = 0;
        StringBuilder a = new StringBuilder();
        for (String str : words) {
            a.append(str);
        }
        String str = a.toString();
        int len = str.length();
        for (int i = 0; i < len; i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }
}
