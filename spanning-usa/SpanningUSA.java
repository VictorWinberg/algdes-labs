import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class SpanningUSA {
	
	private TreeMap<String, TreeSet<Node>> map;
	private TreeSet<Node> cities;
	
	private MinimalSpanningTree mst;

	public SpanningUSA(String path) throws IOException {
		String[] lines = readFromFile(path).split("\n");
		map = new TreeMap<String, TreeSet<Node>>();
		cities = new TreeSet<Node>();
		buildAmerica(lines);
		connectAmerica(lines);
		for(Map.Entry<String, TreeSet<Node>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
		mst = new MinimalSpanningTree(map, cities, "San Diego");
	}
	
	public void buildAmerica(String[] input) {
		for(int i = 0; i < 128; i++) {
			String city = getCity(input[i]);
			map.put(city, new TreeSet<Node>());
			cities.add(new Node(city, Integer.MAX_VALUE));
		}
	}
	
	public void connectAmerica(String[] input) {
		for(int i = 128; i < input.length; i++) {
			String city = getCity(input[i].split("--")[0]);
			String neighbor = getCity(input[i].split("--")[1].split("\\[")[0]);
			int distance = Integer.parseInt(input[i].split("\\[")[1].split("\\]")[0]);
			map.get(city).add(new Node(neighbor, distance));
			map.get(neighbor).add(new Node(city, distance));
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
		SpanningUSA spanning = null;
		if(args.length == 0)
			spanning = new SpanningUSA("spanning-usa/data/USA-highway-miles.txt");
		else
			spanning = new SpanningUSA(args[0]);
			
	}
}
