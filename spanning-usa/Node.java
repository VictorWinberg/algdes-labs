
public class Node implements Comparable<Node> {
	private String city;
	private int weight;

	public Node(String city, int weight) {
		this.city = city;
		this.weight = weight;
	}

	public String city() 	{	return city;	}
	public int weight() 	{	return weight;	}
	
	public String toString() {
		return city + ":" + weight;
	}

	@Override
	public int compareTo(Node o) {
		return weight - o.weight;
	}
}