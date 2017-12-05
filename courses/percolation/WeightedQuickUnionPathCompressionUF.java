
public class WeightedQuickUnionPathCompressionUF {

	private int[] id;
	private int[] tree_size;

	public WeightedQuickUnionPathCompressionUF(int number_of_elements)
	{
		id = new int[number_of_elements];
		tree_size = new int[number_of_elements];

		for( int itr = 0; itr < number_of_elements; ++itr)
		{
			id[itr] = itr;
			tree_size[itr] = 1;
		}
	}

	public boolean connected(int p, int q)
	{
		return root(p) == root(q);
	}

	public int root(int p)
	{
		// Since we are storing values as a tree, this is going to move the values up a level with each while counter all the way to root.
		while (p != id[p] )
		{
			// This little trick here is the heart of Path compression also and helps to keep the tree flat.
			// This is sort of like a lazy optimization and the beauty is it can even improve the performance of current call
			// Simpler one-pass variant:  Make every other node in path point to its grandparent (thereby halving path length).
			id[p] = id[id[p]];
			
			p = id[p];
		}

		return p;
	}

	public void union(int p, int q)
	{
		int p_id = root(p);
		int q_id = root(q);

		// Only if they both are not having the same root.
		if( p_id == q_id )
			return;

		// This tree depth will make sure the depth never reaches 'N'
		if( tree_size[p] > tree_size[q] )
		{
			id[q_id] = p_id;
			tree_size[p_id] += tree_size[q_id];
			// TODO - shouldn't we unset the tree_size of q_id ?
		}
		else
		{
			id[p_id] = q_id;
			tree_size[q_id] += tree_size[p_id];
		}
	}

	public void print()
	{
		System.out.println("---------------------");
		for( int itr = 0; itr < id.length; ++itr)
		{
			System.out.printf("id[%d] = %d   ::   tree_size[%d] = %d\n", itr, id[itr], itr, tree_size[itr]);
		}
	}


	public static void main(String[] args) {
		WeightedQuickUnionPathCompressionUF obj = new WeightedQuickUnionPathCompressionUF(10);

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
