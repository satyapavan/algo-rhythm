
public class QuickUnionUF {

	private int[] id;

	public QuickUnionUF(int number_of_elements)
	{
		id = new int[number_of_elements];

		for( int itr = 0; itr < number_of_elements; ++itr)
		{
			id[itr] = itr;
		}
	}

	public boolean connected(int p, int q)
	{
		// This could be costly at times as we need to keep traversing all the way up for both the objects
		// For bigger and deeper trees, this could be as worst as the union of quick find algo
		return root(p) == root(q);
	}

	public int root(int p)
	{
		// Since we are storing values as a tree, this is going to move the values up a level with each while counter all the way to root.
		while (p != id[p] )
			p = id[p];

		return p;
	}

	public void union(int p, int q)
	{
		int p_id = root(p);
		int q_id = root(q);

		// Only if they both are not having the same root.
		if( p_id == q_id )
			return;
		
		id[p_id] = q_id;
	}

	public void print()
	{
		System.out.println("---------------------");
		for( int itr = 0; itr < id.length; ++itr)
		{
			System.out.printf("id[%d] = %d\n", itr, id[itr]);
		}
	}


	public static void main(String[] args) {
		QuickUnionUF obj = new QuickUnionUF(10);

		obj.print();

		obj.union(2, 3);
		obj.union(3, 4);
		obj.union(4, 9);

		System.out.printf("connected = %s\n", obj.connected(2, 9));
		System.out.printf("connected = %s\n", obj.connected(4, 9));
		System.out.printf("connected = %s\n", obj.connected(5, 4));

		obj.print();
	}

}
