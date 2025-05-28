// Time Complexity: O(V + E) We traverse each node (V) and its edges (E) exactly once.
// Space Complexity: O(V) HashMap to store the mapping of original to cloned nodes. The queue can also hold up to V nodes in the worst case.
// Did this code successfully run on Leetcode: Yes
// Any problem you faced while coding this: No

// Your code here along with comments explaining your approach:
// We use Breadth-First Search (BFS) to traverse the graph level by level starting from the input node.
// A HashMap is used to store already cloned nodes and to avoid cycles or revisiting nodes.
// For each node, we clone it if not already cloned and then process its neighbors:
//   - If a neighbor hasn't been cloned yet, we enqueue it for processing.
//   - We then add the cloned neighbor to the neighbor list of the current node's clone.
// The helper function `clone()` creates or returns the cloned version of a node from the HashMap.

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

        Queue<Node> q = new LinkedList<>();
        q.add(node);

        while (!q.isEmpty()){
            Node curr = q.poll();
            //Get the deep copy
            Node currCopy = clone(curr);

            //Go through its neighbours
            for(Node ne : curr.neighbors){
                //If the neighbour is not in the map; add it in queue and clone it
                if(!hm.containsKey(ne)){
                    q.add(ne);
                }
                //Add its neighbours to the copy
                currCopy.neighbors.add(clone(ne));
            }
        }
        return hm.get(node);
    }

    private Node clone(Node node){
        if(node == null) return null;
        //If hashmap already contains it, return the deep copy
        if(hm.containsKey(node)) return hm.get(node);
        //else create one and add it to the hash map
        hm.put(node, new Node(node.val));
        return hm.get(node);

    }
}