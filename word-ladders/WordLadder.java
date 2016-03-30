import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class WordLadder {
	
	private LinkedList<String>[] inputList;
	
	public WordLadder() throws IOException {
		String[] input = readFromInput().split("\n");
		inputList = new LinkedList[input.length];
		buildStructure(input, inputList);
	}
	
	public WordLadder(String path) throws IOException {
		String[] input = readFromFile(path).split("\n");
		inputList = new LinkedList[input.length];
		buildStructure(input, inputList);
	}

	private void buildStructure(String[] inputs, LinkedList<String>[] inputList) {
		for (int i = 0; i < inputList.length; i++) {
			inputList[i] = new LinkedList<String>();
			inputList[i].add(inputs[i]);
			for (int j = 0; j < inputs.length; j++) {
				if(!inputs[j].equals(inputs[i]) && containsWithoutOrder(inputs[i], inputs[j])) {
					inputList[i].add(inputs[j]);
				}
			}
		}
	}
	
	public static boolean containsWithoutOrder(String current, String other) {
		current = current.substring(1);
		String[] currentValues = current.split("");
		String[] otherValues = other.split("");
		int n = 0;
		for (int i = 0; i < currentValues.length; i++) {
			for (int j = 0; j < otherValues.length; j++) {
				if(currentValues[i].equals(otherValues[j])) {
					otherValues[j] = "";
					n++;
					break;
				}
			}
		}
		return n == 4;
	}
	
	public boolean path(String from, String to) {
		HashSet<String> used = new HashSet<String>();
		used.add(from);
		Queue<String> queue = new LinkedList<String>();
		queue.add(from);
		int n = 0;
		while(!queue.isEmpty()) {
			String current = queue.poll();
			if(current.equals(to))
				return true;
			for(String neighbor : getNeighbors(current)) {
				if(!used.contains(neighbor)) {
					queue.add(neighbor);
					used.add(neighbor);
				}
			}
		}
		return false;
	}
	
	private LinkedList<String> getNeighbors(String string) {
		for (int i = 0; i < inputList.length; i++) {
			if(inputList[i].peek().equals(string))
				return inputList[i];
		}
		return null;
	}

	public String readFromInput() throws IOException {
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in, java.nio.charset.Charset.defaultCharset()))) {
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			return sb.toString();
		}
	}
	
	public String readFromFile(String path) throws IOException {
	  byte[] encoded = Files.readAllBytes(Paths.get(path));
	  return new String(encoded, java.nio.charset.Charset.defaultCharset());
	}
	
	public static void main(String[] args) throws IOException {
		WordLadder wl = new WordLadder("word-ladders/data/words-250.txt");
		while(true) {
    		System.out.print("Word1 Word2: ");
    		args = new Scanner(System.in).nextLine().split(" ");
			System.out.println(wl.path(args[0], args[1]));
		}
	}

}
