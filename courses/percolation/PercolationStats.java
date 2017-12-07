import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

	private double[] percoThreshold;
	private double mMean;
	private double mStdDev;
	private double mConfidenceLo;
	private double mConfidenceHi;
	private double CONFIDENCE_196 = 1.96;

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

		mMean = StdStats.mean(percoThreshold);
		mStdDev = StdStats.stddev(percoThreshold);
		double lTrailsSqrt = Math.sqrt(trails);
		mConfidenceLo = mMean - ( (CONFIDENCE_196 * mStdDev ) / lTrailsSqrt );
		mConfidenceHi = mMean + ( (CONFIDENCE_196 * mStdDev ) / lTrailsSqrt );
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
			//System.out.println("percoThreshold[" + itr + "] = " + percoThreshold[itr]);
		}
	}
	
	public double mean()                          // sample mean of percolation threshold
	{
		return mMean;
	}

	public double stddev()                        // sample standard deviation of percolation threshold
	{
		return mStdDev;
	}

	public double confidenceLo()                  // low  endpoint of 95% confidence interval
	{
		return mConfidenceLo;
	}

	public double confidenceHi()                  // high endpoint of 95% confidence interval
	{
		return mConfidenceHi;
	}

	public static void main(String[] args) {

		PercolationStats obj = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[0]));
		obj.printer();
		System.out.println("mean                    = " + obj.mean());
		System.out.println("stddv                   = " + obj.stddev());
		System.out.println("95% confidence interval = [" + obj.confidenceLo() + ", " + obj.confidenceHi() + "]" );
	}

}
