import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Flow {
	
	private Map<Integer, List<Edge>> map;
	private Map<Edge, Integer> flow;
	
	public Flow(String path) throws IOException {
		map = new HashMap<Integer, List<Edge>>();
		flow = new HashMap<Edge, Integer>();
		String[] input = readFromFile(path).split("\n");
		buildFlow(input);
	}

	private void buildFlow(String[] input) {
		int n = Integer.parseInt(input[0]);
		for(int i = 1; i <= n; i++) {
			map.put(i - 1, new ArrayList<Edge>());
		}

		int m = Integer.parseInt(input[n + 1]);
		for(int i = n + 2; i < n + m + 2; i++) {
			String[] line = input[i].trim().split(" ");
			addEdge(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]));
		}
	}
	
	private int bfsPath(int source, int sink) {
		
		return -1;
	}
	
	private int dfsPath(int source, int sink) {
		
		return -1;
	}
	
	private void addEdge(int u, int v, int c) {
		Edge edge = new Edge(u, v, c);
		map.get(u).add(edge);
		map.get(v).add(edge.reverse);
		flow.put(edge, 0);
		flow.put(edge.reverse, 0);
	}

	public String readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, java.nio.charset.Charset.defaultCharset());
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length == 0)
			new Flow("flow/data/rail.txt");
		else
			new Flow(args[0]);
	}

	private class Edge implements Comparable<Edge> {
		private int source, sink, capacity;
		private Edge reverse;
	
		private Edge(int source, int sink, int capacity) {
			this.source = source;
			this.sink = sink;
			this.capacity = capacity;
			reverse = new Edge(this);
		}
		
		public Edge(Edge edge) {
			this.source = edge.sink;
			this.sink = edge.source;
			this.capacity = edge.capacity;
		}
		
		public String toString() {
			return source + " " + sink + " " + capacity;
		}

		@Override
		public int compareTo(Edge other) {
			return capacity - other.capacity;
		}
	}
}
