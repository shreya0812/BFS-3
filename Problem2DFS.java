// Time Complexity: O(V + E) We traverse each node (V) and its edges (E) exactly once.
// Space Complexity: O(V) HashMap to store the mapping of original to cloned nodes.
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// We use a Depth-First Search (DFS) traversal to clone the graph.
// A HashMap is used to store the already cloned nodes to avoid revisiting and to handle cycles.
// For each node, we clone it if it hasn't been cloned yet and then recurse on its neighbors.
// After recursion, we add the cloned neighbors to the current node’s clone.

/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    HashMap<Node,Node> hm;
    public Node cloneGraph(Node node) {
        //If node is null just return null
        if(node == null) return null;
        this.hm = new HashMap<>();

        dfs(node);
        return hm.get(node);
    }

    private void dfs(Node curr) {

        //Get the deep copy
        Node currCopy = clone(curr);
        for(Node ne : curr.neighbors){
            //If the neighbour is not in the map; add it in queue and clone it
            if(!hm.containsKey(ne)){
                //recurse
                dfs(ne);
            }
            //Add its neighbours to the copy
            currCopy.neighbors.add(clone(ne));
        }
    }

    private Node clone(Node node){
        if(node == null) return null;
        if(hm.containsKey(node)) return hm.get(node);
        hm.put(node, new Node(node.val));
        return hm.get(node);

    }
}