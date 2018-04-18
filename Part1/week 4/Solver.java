import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

public class Solver { 
    private Node goal = null; // if can not find a solution, goal is null

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        boolean found = false; // found solution for board or twin board
        MinPQ<Node> pq = new MinPQ<Node>();
        MinPQ<Node> twinPQ = new MinPQ<Node>();
        
        // start A star search simultaneously for two priority queues
        Node root = new Node(initial);
        Node twinRoot = new Node(initial.twin());
        
        pq.insert(root);
        twinPQ.insert(twinRoot);
        
        while (!found) {
            Node curr = pq.delMin();
            Node twinCurr = twinPQ.delMin();
            
            // find a solution 
            if (curr.board.isGoal() || twinCurr.board.isGoal()) {
                if (curr.board.isGoal()) goal = curr; // goal is not null
                found = true;
                break;
            }
            
            // if not, get the neighbors, put them in the priority queue
            for (Board board : curr.board.neighbors()) {
                if (curr.prev != null && board.equals(curr.prev.board)) {
                    continue; // skip the same node
                } else {
                    Node node = new Node(board);
                    node.moves = curr.moves + 1;
                    node.prev = curr;
                    pq.insert(node);
                }
            }
            // same for twin priority queue
            for (Board board : twinCurr.board.neighbors()) {
                if (twinCurr.prev != null && board.equals(twinCurr.prev.board)) {
                    continue; // skip the same node
                } else {
                    Node node = new Node(board);
                    node.moves = twinCurr.moves + 1;
                    node.prev = twinCurr;
                    twinPQ.insert(node);
                }
            }
        }
    }
    
    // is the initial board solvable?
    public boolean isSolvable() {
        return goal != null;
    }
    
    // min number of moves to solve initial board; -1 if no solution
    public int moves() {
        if (goal == null) return -1;
        return goal.moves;
    }
    
    // sequence of boards in a shortest solution; null if no solution
    public Iterable<Board> solution() {
        if (goal == null) return null;
        Stack<Board> stk = new Stack<Board>();
        // reconstruct the solution
        Node curr = goal;
        while (curr.prev != null) {
            stk.push(curr.board);
            curr = curr.prev;
        }
        stk.push(curr.board);
        return stk;
    }
    
    //solve a slider puzzle (given below)

    	public static void main(String[] args) {

    	    // create initial board from file
    	    In in = new In("C:/Users/123/Desktop/8puzzle/puzzle04.txt");
    	    int n = in.readInt();
    	    int[][] blocks = new int[n][n];
    	    for (int i = 0; i < n; i++)
    	        for (int j = 0; j < n; j++)
    	            blocks[i][j] = in.readInt();
    	    Board initial = new Board(blocks);

    	    // solve the puzzle
    	    Solver solver = new Solver(initial);

    	    // print solution to standard output
    	    if (!solver.isSolvable())
    	        StdOut.println("No solution possible");
    	    else {
    	        StdOut.println("Minimum number of moves = " + solver.moves());
    	        for (Board board : solver.solution())
    	            StdOut.println(board);
    	    }
}

    private class Node implements Comparable<Node> {
    public Board board; // point to the current board
    public int moves; // N moves to the current search node
    public Node prev; // point to the previous search node
 
    // constructor
    public Node (Board board) {
        this.board = board;
        prev = null;
        moves = 0;
    }

    @Override
	public String toString() {
		return "Node [board=" + board + ", moves=" + moves + ", prev=" + prev + "]";
	}

	@Override
    public int compareTo(Node that) {
        return (this.board.manhattan() + this.moves) - (that.board.manhattan() + that.moves);
    }   
}
}
