import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SpanningUSA {
	
	private TreeMap<String, List<Edge>> map;
	private List<Edge> result;

	public SpanningUSA(String path) throws IOException {
		String[] lines = readFromFile(path).split("\n");
		map = new TreeMap<String, List<Edge>>();
		result = new LinkedList<Edge>();
		buildAmerica(lines);
		System.out.println(prims());
		switch (0) {
		case 1:
			for(Map.Entry<String, List<Edge>> entry : map.entrySet()) {
				System.out.println(entry.getKey() + ":" + entry.getValue());
			}
			break;
		case 2:
			for(Edge edge : result) {
				System.out.println(edge);
			}
		default:
			break;
		}
	}
	
	private int prims() {
		int weight = 0;
		HashSet<String> visited = new HashSet<String>();
		String firstEdge = map.firstKey();
		visited.add(firstEdge);
		PriorityQueue<Edge> queue = new PriorityQueue<Edge>();
		queue.addAll(map.get(firstEdge));
		while (!queue.isEmpty()) {
			Edge edge = queue.poll();
			if (!visited.contains(edge.to())) {
				weight += edge.weight();
				result.add(edge);
				visited.add(edge.to());
				queue.addAll(map.get(edge.to()));
			}
		}
		return weight;
	}
	
	public void buildAmerica(String[] input) {
		for(int i = 0; i < input.length; i++) {
			if(!input[i].contains("--")) {
				String city = getCity(input[i]);
				map.put(city, new LinkedList<Edge>());				
			} else {
				String from = getCity(input[i].split("--")[0]);
				String to = getCity(input[i].split("--")[1].split("\\[")[0]);
				int weight = Integer.parseInt(input[i].split("\\[")[1].split("\\]")[0]);
				Edge edge1 = new Edge(from, to, weight);
				map.get(from).add(edge1);
				Edge edge2 = new Edge(to, from, weight);
				map.get(to).add(edge2);
			}
		}
	}
	
	public String getCity(String input) {
		if(input.charAt(0) == '\"')
			return input.split("\"")[1].trim();
		else
			return input.trim();
	}

	public String readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, java.nio.charset.Charset.defaultCharset());
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length == 0)
			new SpanningUSA("spanning-usa/data/tinyEWG-alpha.txt");
		else
			new SpanningUSA(args[0]);
			
	}
}
