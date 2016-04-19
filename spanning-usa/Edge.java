
public class Edge implements Comparable<Edge> {
	private String from, to;
	private int weight;

	public Edge(String from, String to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	public String from() 	{	return from;	}
	public String to() 		{	return to;	}
	public int weight() 	{	return weight;	}
	
	public String toString() {
		return from + "--" + to + " [" + weight + "]";
	}

	@Override
	public int compareTo(Edge o) {
		return weight - o.weight;
	}
}