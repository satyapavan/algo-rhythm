
public class QuickFindUF {

	private int[] id;
	
	public QuickFindUF(int number_of_elements)
	{
		id = new int[number_of_elements];
		
		for( int itr = 0; itr < number_of_elements; ++itr)
		{
			id[itr] = itr;
		}
	}
	
	public boolean connected(int p, int q)
	{
		// This is going to be super fast O(1)
		return id[p] == id[q];
	}
	
	public void union(int p, int q)
	{
		int p_id = id[p];
		int q_id = id[q];
		
		// This is going to be very slow, almost to the length of 'N' for worst cases
		for (int itr = 0; itr < id.length; ++itr )
		{
			if (p_id == id[itr])
				id[itr] = q_id;
		}
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
		QuickFindUF obj = new QuickFindUF(10);
		
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
