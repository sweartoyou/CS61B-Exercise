package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private WeightedQuickUnionUF sites;
    private WeightedQuickUnionUF sites2;
    private int topSite;
    private int bottomSite;
    private boolean[][] flagOpen;
    private int numOpen = 0;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        // virtual site settings
        // site index (0, N * N - 1)
        topSite = N * N;
        bottomSite = N * N + 1;

        sites = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i ++ ) {
            sites.union(topSite, xyTo1D(0, i));
        }

        for (int i = 0; i < N; i ++ ) {
            sites.union(bottomSite, xyTo1D(N - 1, i));
        }

        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i ++ ) {
            sites2.union(topSite, xyTo1D(0, i));
        }

        flagOpen = new boolean[N][N];
        for (int i = 0; i < N; i ++ ) {
            for (int j = 0; j < N; j ++ ) {
                flagOpen[i][j] = false;
            }
        }
    }

    public void open(int row, int col) {
        if (isOpen(row, col)) {
            return ;
        }
        flagOpen[row][col] = true;
        numOpen ++ ;
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row, col + 1);
        unionOpenNeighbor(row, col, row, col - 1);
    }

    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return flagOpen[row][col];
    }

    public boolean isFull(int row, int col) {
        validateRange(row, col);

        if (!isOpen(row, col)) {
            return false;
        }

        return sites2.connected(xyTo1D(row, col), topSite);
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    public int numberOfOpenSites() {
        return numOpen;
    }

    public boolean percolates() {
        if (numOpen == 0) {
            return false;
        }
        return sites.connected(topSite, bottomSite);
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    private void unionOpenNeighbor(int row, int col, int row2, int col2) {
        if (row2 < 0 || row2 >= N || col2 < 0 || col2 >= N) {
            return ;
        }
        if (flagOpen[row2][col2]) {
            sites.union(xyTo1D(row, col), xyTo1D(row2, col2));
            sites2.union(xyTo1D(row, col), xyTo1D(row2, col2));
        }
    }

    public static void main(String[] args) {

    }
}
