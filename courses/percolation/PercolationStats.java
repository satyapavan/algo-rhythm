import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] percoThreshold;

	private int trails;

	public PercolationStats(int number_of_elements, int trials)    // perform trials independent experiments on an n-by-n grid
	{
		if(number_of_elements <= 0) 
			throw new IllegalArgumentException ("Negative grid size");

		if(trials <= 0) 
			throw new IllegalArgumentException ("Negative trials size");

		this.trails = trials;

		percoThreshold = new double[trials];

		for(int itr = 0; itr < trails ; itr++)
		{
			int open_sites = invokeTrail(number_of_elements);
			percoThreshold[itr] = ( ((double) open_sites ) / (number_of_elements * number_of_elements) );
		}

	}

	private int invokeTrail(int n)
	{
		Percolation obj = new Percolation(n);

		int row ;
		int col ;

		while(!obj.percolates()){
			row = StdRandom.uniform(1, n+1);	
			col = StdRandom.uniform(1, n+1);
			obj.open(row, col);
		}

		return obj.numberOfOpenSites();
	}

	private void printer()
	{
		for(int itr = 0; itr < trails ; itr++)
		{
			System.out.println("percoThreshold[" + itr + "] = " + percoThreshold[itr]);
		}
	}
	
	public double mean()                          // sample mean of percolation threshold
	{
		return StdStats.mean(percoThreshold);
	}

	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return StdStats.stddev(percoThreshold);
	}

	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return StdStats.mean(percoThreshold) - ( (1.96 * StdStats.stddev(percoThreshold)) / Math.sqrt(trails) );
	}

	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return StdStats.mean(percoThreshold) + ( (1.96 * StdStats.stddev(percoThreshold)) / Math.sqrt(trails) );
	}

	public static void main(String[] args) {

		//PercolationStats obj = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[0]));
		PercolationStats obj = new PercolationStats(4, 4);
		//obj.printer();
		System.out.println("mean                    = " + obj.mean());
		System.out.println("stddv                   = " + obj.stddev());
		System.out.println("95% confidence interval = [" + obj.confidenceLo() + ", " + obj.confidenceHi() + "]" );
	}

}
