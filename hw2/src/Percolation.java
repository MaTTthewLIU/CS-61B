import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private int n;
    private int numberOfOpenSites;
    private boolean[] openFlag;
    private int virtualTopSite;
    private int virtualBottomSite;

    private WeightedQuickUnionUF quf;

    private WeightedQuickUnionUF backwashQUF;

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException("N smaller than 0 is error.");
        }
        this.n = N;
        openFlag = new boolean[n * n];
        virtualTopSite = n * n;
        virtualBottomSite = n * n + 1;
        quf = new WeightedQuickUnionUF(n * n + 2);
        backwashQUF = new WeightedQuickUnionUF(n * n + 1);
    }

    public void open(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("row and col range from 0 to n-1.");
        }
        int position = xyTo1D(row, col);
        if (openFlag[position]) {
            return;
        }
        openFlag[position] = true;
        numberOfOpenSites++;
        if (row == 0) {
            quf.union(virtualTopSite, position);
            backwashQUF.union(virtualTopSite, position);
        }
        if (row == n - 1) {
            quf.union(virtualBottomSite, position);
        }

        if (row > 0 && isOpen(row - 1, col)) {
            quf.union(position - n, position);
            backwashQUF.union(position - n, position);
        }
        if (row < n - 1 && isOpen(row + 1, col)) {
            quf.union(position + n, position);
            backwashQUF.union(position + n, position);
        }
        if (col > 0 && isOpen(row, col - 1)) {
            quf.union(position - 1, position);
            backwashQUF.union(position - 1, position);
        }
        if (col < n - 1 && isOpen(row, col + 1)) {
            quf.union(position + 1, position);
            backwashQUF.union(position + 1, position);
        }

    }

    public boolean isOpen(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("row and col range from 0 to n-1.");
        }
        int position = xyTo1D(row, col);
        return openFlag[position];
    }

    public boolean isFull(int row, int col) {
        if (row < 0 || row >= n || col < 0 || col >= n) {
            throw new IndexOutOfBoundsException("row and col range from 0 to n-1.");
        }

        return backwashQUF.connected(virtualTopSite, xyTo1D(row, col));
    }

    public int numberOfOpenSites() {
        return this.numberOfOpenSites;
    }

    public boolean percolates() {
        return quf.connected(virtualTopSite, virtualBottomSite);
    }

    private int xyTo1D(int row, int col) {
        return row * n + col;
    }
}
