package lab11.graphs;

import java.util.Random;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] pathTo;
    private boolean circleFound = false;

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        pathTo = new int[maze.V()];
        Random rand = new Random();
        int sourceX = rand.nextInt(maze.N());
        int sourceY = rand.nextInt(maze.N());
        int s = maze.xyTo1D(sourceX, sourceY);
        pathTo[s] = s;
        dfs(s);
        announce();
    }

    // Helper methods go here
    private void dfs(int v) {
        marked[v] = true;

        for (int w : maze.adj(v)) {
            if (circleFound) {
                return;
            }

            if (!marked[w]) {
                pathTo[w] = v;
                dfs(w);
            } else if (w != pathTo[v]) {
                pathTo[w] = v;

                int cur = v;
                edgeTo[cur] = pathTo[cur];
                while (cur != w) {
                    cur = pathTo[cur];
                    edgeTo[cur] = pathTo[cur];
                }
                circleFound = true;
                return;
            }
        }
    }
}

