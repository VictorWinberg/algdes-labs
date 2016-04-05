import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordLadder {

	private SortedMap<String, SortedSet<String>> endingsMap;
	private SortedMap<String, SortedSet<String>> neighborsMap;

	public WordLadder(String path) throws IOException {
		String[] input = readFromFile(path).split("\n");
		endingsMap = new TreeMap<String, SortedSet<String>>();
		neighborsMap = new TreeMap<String, SortedSet<String>>();
		buildStructure(input);
	}

	public WordLadder(String path, String testpath) throws IOException {
		this(path);
		String[] lines = readFromFile(testpath).split("\n");
		for(String line : lines) {
			System.out.println(this.path(line.split(" ")[0], line.split(" ")[1]));
		}
	}

	private void buildStructure(String[] inputs) {
		for (int i = 0; i < inputs.length; i++) {
			makeEndingsToMap(inputs[i]);
		}
		for(int i = 0; i < inputs.length; i++) {
			SortedSet<String> neighbors = findNeighbors(inputs[i]);
			neighborsMap.put(inputs[i], neighbors);
		}
	}

	private void makeEndingsToMap(String word) {
		SortedSet<String> endings = getEndings(word);
		for(String ending : endings) {
			SortedSet<String> set = endingsMap.get(ending);
			if(set == null) {
				set = new TreeSet<String>();
				set.add(word);
				endingsMap.put(ending, set);				
			} else {
				set.add(word);
			}
		}
	}
	
	private SortedSet<String> getEndings(String word) {
		SortedSet<String> set = new TreeSet<String>();
		String c = word;
		for(int j = 0; j < 5; j++) {
			char[] chars = (c.substring(0, j) + c.substring(j + 1, 5)).toCharArray();
			Arrays.sort(chars);
			set.add(new String(chars));
		}
		return set;
	}

	private SortedSet<String> findNeighbors(String word) {
		SortedSet<String> set = new TreeSet<String>();
		char[] endings = word.substring(1).toCharArray();
		Arrays.sort(endings);
		String ending = new String(endings);
		if(endingsMap.get(ending) == null)
			return set;
		for(String neighbor : endingsMap.get(ending)) {
			if(!neighbor.equals(word))
				set.add(neighbor);
		}
		return set;
	}

	public int path(String from, String to) {
		HashMap<String, Integer> used = new HashMap<String, Integer>();
		used.put(from, 0);
		Queue<String> queue = new LinkedList<String>();
		queue.add(from);
		while (!queue.isEmpty()) {
			String current = queue.poll();
			if (current.equals(to)) {
				return used.get(current);				
			}
			for (String neighbor : findNeighbors(current)) {
				if (!used.containsKey(neighbor)) {
					used.put(neighbor, used.get(current)+1);
					queue.add(neighbor);
				}
			}
		}
		return -1;
	}
	
	public String readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, java.nio.charset.Charset.defaultCharset());
	}

	public static void main(String[] args) throws IOException {
		WordLadder wl = null;
		if(args.length == 0) {
			String[] filepath = new String[2];
			filepath[0] = "word-ladders/data/words-5757.txt";
			filepath[1] = "word-ladders/data/words-5757-in.txt";
			main(filepath);
		}
		else if(args.length == 1) {
			wl = new WordLadder(args[0]);
			while (true) {
				System.out.print("word1 word2: ");
				String[] words = new Scanner(System.in).nextLine().split(" ");
				if(words.length == 2)
					System.out.println(wl.path(words[0], words[1]));
				else
					System.out.println("Please input 2 words seperated by space.");
			}
		} else if(args.length == 2) {
			wl = new WordLadder(args[0], args[1]);
		}
	}

}
