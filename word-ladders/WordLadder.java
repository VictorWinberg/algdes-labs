import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class WordLadder {
	
	public WordLadder() throws IOException {
		String[] input = readFromInput().split("\n");
		
		LinkedList<String>[] inputList = new LinkedList[input.length];
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
			System.out.println(inputList[i]);
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

	public static void main(String[] args) throws IOException {
		new WordLadder();
	}

}
