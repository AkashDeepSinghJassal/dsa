
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class AdjacentFrequencySort {
    /**
     * Given string and number 
     * a1cb32
     * a1b2c3
     * if same length then number first
     * else alpha number alpha(odd length)
     * 
     * sort character 
     * sort number 1. descending
     * 2. with higher frequency
     * 
     * 
     */
    public static void main(String[] args) {
        String s = "z2z3x1";
        AdjacentFrequencySort afs = new AdjacentFrequencySort();
        String result = afs.sortFrequencyAdjacentNumber(s);
        System.out.println("Adjacent number frequency sort " + result);
    }

    public String sortFrequencyAdjacentNumber(String s){
        // get alpha number
        HashMap<Character, Integer> freqAlpha = new HashMap<>();
        List<Character> alphaL = new ArrayList<>();
        List<Character> numberL = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if(isNumber(c)){
                numberL.add(c);
            } else {
                freqAlpha.put(c, freqAlpha.getOrDefault(c, 0) + 1);
                alphaL.add(c);
            }
        }
        Collections.sort(numberL, (a, b) -> {
            if(freqAlpha.get(a) != freqAlpha.get(b)){
                return freqAlpha.get(b) - freqAlpha.get(a);
            }
            return b - a;
        });
        Collections.sort(alphaL, (a, b) -> {
            return b - a;
        });
        System.out.println(numberL);
        System.out.println(alphaL);
        StringBuilder result = new StringBuilder();
        int i, j;
        i = j = 0;
        if(alphaL.size() - numberL.size() == 1) {
            result.append(alphaL.get(i));
            i++;
        }
        while(i < alphaL.size()){
            result.append(numberL.get(j));
            result.append(alphaL.get(i));
            i++;
            j++;
        }
        return result.toString();
    }

    public boolean isNumber(Character c){
        return c >= '0' && c <= '9';
    }
}
