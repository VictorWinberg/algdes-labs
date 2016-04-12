import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class SpanningUSA {
	
	private TreeMap<String, TreeMap<String, Integer>> map;
	MinimalSpanningTree mst;

	public SpanningUSA(String path) throws IOException {
		String[] lines = readFromFile(path).split("\n");
		map = new TreeMap<String, TreeMap<String, Integer>>();
		TreeMap<String, TreeMap<String, Integer>> cities = getCities(lines);
		buildAmerica(lines);
		for(Map.Entry<String, TreeMap<String, Integer>> entry : map.entrySet()) {
			System.out.println(entry.getKey() + ":" + entry.getValue());
		}
	}
	
	public String getCity(String input) {
		if(input.charAt(0) == '\"')
			return input.split("\"")[1].trim();
		else
			return input.trim();
	}
	
	public TreeMap<String, TreeMap<String, Integer>> getCities(String[] input) {
		TreeMap<String, TreeMap<String, Integer>> cities = new TreeMap<String, TreeMap<String, Integer>>();
		for(int i = 0; i < 128; i++) {
			String city = getCity(input[i]);
			map.put(city, new TreeMap<String, Integer>());
		}
		return cities;
	}
	
	public void buildAmerica(String[] input) {
		for(int i = 128; i < input.length; i++) {
			String city = getCity(input[i].split("--")[0]);
			String neighbor = getCity(input[i].split("--")[1].split("\\[")[0]);
			int distance = Integer.parseInt(input[i].split("\\[")[1].split("\\]")[0]);
			map.get(city).put(neighbor, distance);
			map.get(neighbor).put(city, distance);
		}
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
