// Time Complexity: O(2^n) In the worst case, we may have to generate all possible combinations of the string by removing each parenthesis, which gives us 2^n possibilities (n = length of the string).
// Space Complexity: O(2^n) This is due to the HashSet storing visited strings and the recursion stack (in the worst case)
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// We use a depth-first search (DFS) approach to recursively generate all possible strings by removing one parenthesis at a time.
// The recursion avoids duplicate processing using a HashSet (`hs`).
// We track the maximum length (`max`) of valid expressions encountered so far to ensure we only keep the longest valid strings (i.e., those with the minimum removals).
// If a valid string is found with a length greater than the current max, we update max and reset the result list.
// The `isValid` function checks if the parentheses are balanced using a counter.
class Solution {
    List<String> result;
    HashSet<String> hs;
    int max;
    public List<String> removeInvalidParentheses(String s) {
        //List to store result
        this.result = new ArrayList<>();
        //So that no duplicates are added into the queue
        this.hs = new HashSet<>();
        max = 0;

        dfs(s);

        return result;
    }

    private void dfs(String curr) {
        //If the string length is smaller than the one we have; do not do anything
        if(curr.length() < max) return;
        //If its valid check if the length is greater than max; update max; clear result and add new curr;
        if(isValid(curr)){
            if(curr.length() > max){
                max = curr.length();
                result.clear();
            }
            result.add(curr);
        }

        //generate the babies and call dfs on them
        for (int k = 0; k < curr.length(); k++) {
            //If the char is an alphabet just ignore and go to next iteration
            if(Character.isAlphabetic(curr.charAt(k))) continue;
            String baby = curr.substring(0,k) + curr.substring(k+1);
            if(!hs.contains(baby)){
                hs.add(baby);
                //recurse
                dfs(baby);
            }
        }
    }

    private boolean isValid(String curr) {
        int count = 0 ;
        for (int i = 0; i < curr.length(); i++) {
            if(Character.isAlphabetic(curr.charAt(i))) continue;
            if(curr.charAt(i) == '('){
                count++;
            }else{
                count--;
            }
            if(count < 0) return false;
        }
        return count == 0;
    }
}