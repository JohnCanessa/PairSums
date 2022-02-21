import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


/**
 * Pair Sums
 * https://www.facebookrecruiting.com/portal/coding_practice_question/?problem_id=840934449713537
 */
public class PairSums {


    /**
     * Given a list of n integers arr[0..(n-1)], determine the number of different
     * pairs of elements within it which sum to k.
     */
    static int numberOfWays(int[] arr, int k) {

        // **** initialization ****
        int pairs                               = 0;
        HashMap<Integer, List<Integer>> valLoc  = new HashMap<>();
        List<Integer> locs                      = null;

        // **** populate hashmap of value : location O(n) ****
        for (var i = 0; i < arr.length; i++) {
            
            // **** get locations for this value ****
            locs = valLoc.get(arr[i]);
            if (locs == null) {
                locs = new ArrayList<Integer>();
                locs.add(i);
                valLoc.put(arr[i], locs);
            } else {
                locs.add(i);
            }
        }

        // ???? ????
        // System.out.println("numberOfWays <<< valLoc: " + valLoc.toString());

        // **** count pairs (pairs will be double counted) ****
        for (var i = 0; i < arr.length; i++) {

            // **** skip this value (if needed) ****
            if (arr[i] > k) continue;

            // **** compute the needed value ****
            var val = k - arr[i];

            // **** look up the value in the hashmap O(n) ****
            locs = valLoc.get(val);
            if (locs != null) {

                // **** double count pairs (a,b) & (b,a) ****
                pairs += locs.size();

                // **** decrement count (a,a) ****
                if (val == arr[i]) pairs--;

                // ???? ????
                // System.out.println("numberOfWays <<< pairs: " + pairs);
            }
        }

        // **** pairs were double counted ****
        return pairs / 2;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        
        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read array of strings ****
        String[] strArr = br.readLine()
                            .trim()
                            .split(",");

        // **** read k ****
        int k = Integer.parseInt(br.readLine().trim());

        // **** close buffered reder ****
        br.close();

        // **** populate int[] arr ****
        int[] arr = Arrays.stream(strArr)
                            .map(x -> x.trim())
                            .mapToInt(Integer::parseInt)
                            .toArray();
        
        // ???? ????
        System.out.println("main <<<    arr: " + Arrays.toString(arr));
        System.out.println("main <<<      k: " + k);

        // **** compute and display result ****
        System.out.println("main <<< output: " + numberOfWays(arr, k));
    }
}