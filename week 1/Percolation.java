import java.util.Arrays;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
	   private boolean [][] grid;
	   private WeightedQuickUnionUF WUF ;
	   private WeightedQuickUnionUF p ;
	   private int N ;
	   private boolean alreadyPercolates;
	   public Percolation(int n) {
	       if (n < 1) throw new IllegalArgumentException("Illeagal Argument");
		   N=n;
		   grid=new boolean[N+1][N+1] ;
           WUF = new WeightedQuickUnionUF(N*N+2);
           p = new WeightedQuickUnionUF(N*N+2);
           for(int i=0;i<=N;i++){
        	   for(int j=0;j<=N;j++){
        		   grid[i][j]=false;
        	   }
           }
	   }               // create n-by-n grid, with all sites blocked
		private boolean isInGrid(int i,int j){
			if((i<=0||i>N)||(j<=0||j>N))
				return false; 
			else
				return true; 
		}
		private void valid(int i,int j){
			if((i<=0||i>N)||(j<=0||j>N))
				throw new IndexOutOfBoundsException("Illeagal Argument");
		}
		
	   public    void open(int row, int col) {
		   valid(row, col);
		   int key = (row-1)*N + col; 
		   grid[row][col]=true;
	        if (row == 1) {
	        	WUF.union(key, 0);
	        	p.union(key, 0);
	        }
	        if (row == N) {
	        	WUF.union(key, N*N+1);
	        } 
			if(isInGrid(row-1, col)){
					if(grid[row-1][col]==true){
				WUF.union((row-1)*N+col,(row-2)*N+col);
			p.union((row-1)*N+col,(row-2)*N+col);}}
			
			if(isInGrid(row, col+1)){
					if(grid[row][ col+1]==true){
						p.union((row-1)*N+col, (row-1)*N+col+1);
				WUF.union((row-1)*N+col, (row-1)*N+col+1);}}
			if(isInGrid(row,col-1)){
				if(grid[row][col-1]==true){
					p.union((row-1)*N+col, (row-1)*N+col-1);
				WUF.union((row-1)*N+col, (row-1)*N+col-1);}}
			if(isInGrid(row+1, col)){
				if(grid[row+1][col]==true){
					p.union((row-1)*N+col,(row)*N+col);
				WUF.union((row-1)*N+col,(row)*N+col);}}
		   
	   }    // open site (row, col) if it is not open already
	   public boolean isOpen(int row, int col)  {
		   valid(row, col);
		   return  grid[row][col];
	   } // is site (row, col) open?
	   public boolean isFull(int row, int col) {
		   valid(row, col);
		   int key = (row-1)*N + col; 
	        if (p.find(key) == p.find(0)) return true;
	        return false;
	   }  // is site (row, col) full?
	   public     int numberOfOpenSites()   {
		   int num=0;
           for(int i=1;i<=N;i++){
        	   for(int j=1;j<=N;j++){
        		   if (grid[i][j]==true){
        			   num++;
        		   }
        	   }
           }
           return num;
		   
	   }    // number of open sites
	   public boolean percolates()    {
	        if (alreadyPercolates) return true;
	        if (WUF.find(0) == WUF.find(N*N+1)) {
	            alreadyPercolates = true;
	            return true;
	        } 
	        return false;
		   
	   }          // does the system percolate?



	public static void main(String[] args) {
        Percolation perc = new Percolation(2);
        perc.open(1, 1);
        perc.open(1, 2);
        perc.open(2, 1);
        perc.open(2, 2);
        System.out.println(perc.percolates());
    }
    
}