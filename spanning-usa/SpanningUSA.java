import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class SpanningUSA {
	
	private TreeMap<String, LinkedList<Node>> map;

	public SpanningUSA(String path) throws IOException {
		String[] lines = readFromFile(path).split("\n");
		map = new TreeMap<String, LinkedList<Node>>();
		buildAmerica(lines);
		/*for(Map.Entry<String, LinkedList<Node>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}*/
		System.out.println(prims());
	}
	
	private int prims() {
		int weight = 0;
		HashSet<String> visited = new HashSet<String>();
		String firstCity = map.firstKey();
		visited.add(firstCity);
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		queue.addAll(map.get(firstCity));
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			if (!visited.contains(node.city())) {
				weight += node.weight();
				visited.add(node.city());
				queue.addAll(map.get(node.city()));
			}
		}
		return weight;
	}
	
	public void buildAmerica(String[] input) {
		for(int i = 0; i < input.length; i++) {
			if(!input[i].contains("--")) {
				String city = getCity(input[i]);
				map.put(city, new LinkedList<Node>());				
			} else {
				String from = getCity(input[i].split("--")[0]);
				String to = getCity(input[i].split("--")[1].split("\\[")[0]);
				int weight = Integer.parseInt(input[i].split("\\[")[1].split("\\]")[0]);
				Node node1 = new Node(to, weight);
				map.get(from).add(node1);
				Node node2 = new Node(from, weight);
				map.get(to).add(node2);
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
			new SpanningUSA("spanning-usa/data/USA-highway-miles.txt");
		else
			new SpanningUSA(args[0]);
			
	}
}
