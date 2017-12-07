import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

	private int CELL_OPEN = 1;
	private int CELL_BLOCK = 0;
	
	private boolean ENABLE_LOGS = false;
	
	private int TOP_VIRTUAL_CELL = 0;
	private int BOTTOM_VIRTUAL_CELL = 0;
	private int openSitesCount = 0;
	private int grid_size = 0;
	private WeightedQuickUnionUF objUF;

	private int[][] grid;

	public Percolation(int number_of_elements)                // create n-by-n grid, with all sites blocked
	{
		if(number_of_elements <= 0) 
			throw new IllegalArgumentException ("Negative grid size");
		
		grid = new int[number_of_elements][number_of_elements];
		openSitesCount = 0;
		grid_size = number_of_elements;

		for( int itrRow = 0; itrRow < number_of_elements; ++itrRow)
		{
			for( int itrCol = 0; itrCol < number_of_elements; ++itrCol)
			{
				grid[itrRow][itrCol] = CELL_BLOCK;
			}
		}
		
		// This +2 here is for the virtual sites on top of top row and last row
		objUF = new WeightedQuickUnionUF((number_of_elements * number_of_elements) + 2);
		
		// this -1 here is for the array conventions
		TOP_VIRTUAL_CELL = (number_of_elements * number_of_elements) + 1 - 1;
		BOTTOM_VIRTUAL_CELL = (number_of_elements * number_of_elements) + 2 - 1;
	}

	// This need not be called for any other private functions as its already called once by the gateway public function calls
	private int validateAndCorrectInput(int x)
	{		
		if (x <= 0 || x > grid_size) 
			throw new IllegalArgumentException ("index i out of bounds");
		
		// This is because we are going to get the inputs not in array formats (i.e they will be from 1..n rather than 0..(n-1)
		
		return --x;
	}

	private int convert2Dto1D(int row, int col)
	{
		// This turned out particularly simple or i did turn out smarter
		return (grid_size * row) + col ;
	}
	
	private boolean isValidGrid(int row, int col)
	{
		// TODO - i really wanted to call isOpen here but that would mess up with my row,col values again :-(
		if (row >= 0 && row <= grid_size-1 && col >= 0 && col <= grid_size-1 ) 
			return grid[row][col] == CELL_OPEN;
		
		return false;
	}
	
	private void mapNeighbours(int row, int col)
	{
		// Make the connections with virtual nodes
		if( row == 0) 
		{
			objUF.union(convert2Dto1D(row, col), TOP_VIRTUAL_CELL);
		}
		// writing else if here broke the corner case of n=1
	    if( row == (grid_size -1) ) 
		{
			objUF.union(convert2Dto1D(row, col), BOTTOM_VIRTUAL_CELL);
		}
		
		if(isValidGrid(row+1, col)) 
			objUF.union(convert2Dto1D(row+1, col), convert2Dto1D(row, col));
		if(isValidGrid(row, col+1)) 
			objUF.union(convert2Dto1D(row, col+1), convert2Dto1D(row, col));
		if(isValidGrid(row-1, col)) 
			objUF.union(convert2Dto1D(row-1, col), convert2Dto1D(row, col));
		if(isValidGrid(row, col-1)) 
			objUF.union(convert2Dto1D(row, col-1), convert2Dto1D(row, col));	
	}
	
	public void open(int row, int col)    // open site (row, col) if it is not open already
	{
		row = validateAndCorrectInput(row); 
		col = validateAndCorrectInput(col);

		if (grid[row][col] == CELL_OPEN )
			return;

		grid[row][col] = CELL_OPEN;
		openSitesCount++;
		
		mapNeighbours(row, col);
	}

	public boolean isOpen(int row, int col)  // is site (row, col) open?
	{
		row = validateAndCorrectInput(row); 
		col = validateAndCorrectInput(col);
		
		if(ENABLE_LOGS) System.out.println("Row=" + row + " Col=" + col);
		if(ENABLE_LOGS) System.out.println("grid.length = " + grid.length);

		
		return grid[row][col] == CELL_OPEN;
	}

	public boolean isFull(int row, int col)  // is site (row, col) full?
	{
		if(ENABLE_LOGS) 
			System.out.println("isFull Row=" + row + " Col=" + col);
		
		row = validateAndCorrectInput(row); 
		col = validateAndCorrectInput(col);
		
		return objUF.connected(convert2Dto1D(row, col), TOP_VIRTUAL_CELL);
	}

	public int numberOfOpenSites()       // number of open sites
	{
		if(ENABLE_LOGS)
			System.out.println("Open Sites Count : " + openSitesCount );
		
		return openSitesCount;
	}

	public boolean percolates()              // does the system percolate?
	{
		if(ENABLE_LOGS) 
			System.out.println("Threshold : " + (double) openSitesCount/(grid_size * grid_size));
		
		return objUF.connected(TOP_VIRTUAL_CELL, BOTTOM_VIRTUAL_CELL);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
