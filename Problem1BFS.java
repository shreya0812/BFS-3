// Time Complexity: O(2^n) In the worst case, we may have to generate all possible combinations of the string by removing each parenthesis, which gives us 2^n possibilities (n = length of the string).
// Space Complexity: O(2^n) This is due to the space used by the queue and the hash set to store all possible intermediate states of the string.
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// We use Breadth-First Search (BFS) to generate all valid expressions with the minimum number of removals.
// Start with the original string. In each iteration, remove one parenthesis at every possible position (ignore letters).
// Add each generated string to the queue and a hash set to prevent duplicates.
// If we find any valid string, we add it to the result list and set a flag.
// We do not generate further children once a valid string is found (to ensure minimal removal).
// The `isValid` function checks if the parentheses are balanced using a counter.

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        //List to store result
        List<String> result = new ArrayList<>();

        Queue<String> q = new LinkedList<>();
        //So that no duplicates are added into the queue
        HashSet<String> hs = new HashSet<>();
        boolean flag = false;

        //Add 1st string to the queue
        q.add(s);
        hs.add(s);

        while (!q.isEmpty()){
            String curr =  q.poll();
            //If valid string; then add it to the result and mark flag as true
            if(isValid(curr)){
                result.add(curr);
                flag = true;
            }else{//Else if we haven't found any valid strings yet; generate the babies and add them to the queue.
                if(!flag){
                    for (int k = 0; k < curr.length(); k++) {
                        //If the char is an alphabet just ignore and go to next iteration
                        if(Character.isAlphabetic(curr.charAt(k))) continue;
                        String baby = curr.substring(0,k) + curr.substring(k+1);
                        if(!hs.contains(baby)){
                            q.add(baby);
                            hs.add(baby);
                        }
                    }
                }
            }
        }

        return result;
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