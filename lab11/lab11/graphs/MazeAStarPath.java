package lab11.graphs;

import edu.princeton.cs.algs4.MinPQ;

import java.util.Comparator;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    private class Node implements Comparable {
        private int v;
        private int priority;

        public Node(int v) {
            this.v = v;
            this.priority = distTo[v] + h(v);
        }

        @Override
        public int compareTo(Object o) {
            Node n = (Node) o;
            return this.priority - n.priority;
        }
    }


    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourcex = maze.toX(v);
        int soucexy = maze.toY(v);
        int targetx = maze.toX(t);
        int targety = maze.toY(t);
        return Math.abs(sourcex - targetx) + Math.abs(soucexy - targety);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        MinPQ<Node> pq = new MinPQ<>();
        Node start = new Node(s);
        pq.insert(start);
        marked[s] = true;
        while (!pq.isEmpty()) {
            Node cur = pq.delMin();
            for (int w : maze.adj(cur.v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    distTo[w] = distTo[cur.v] + 1;
                    edgeTo[w] = cur.v;
                    announce();
                    if (w == t) {
                        return ;
                    } else {
                        pq.insert(new Node(w));
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

