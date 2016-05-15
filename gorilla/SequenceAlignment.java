import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class SequenceAlignment {
	
	private Map<Character, Integer> map;
	
	public SequenceAlignment(String a, String b) throws IOException {
		map = new TreeMap<Character, Integer>();
		int[][] A = blosum62("gorilla/data/BLOSUM62.txt");
		
		char[] x = a.toCharArray();
		char[] y = b.toCharArray();
		
		System.out.println(sequenceAlignment(x.length, y.length, x, y, -4, A));
	}
	
	private int[][] blosum62(String path) throws IOException {
		String[] lines = readFromFile(path).split("\n");
		int offset = 0;
		while(lines[offset].trim().charAt(0) == '#') {
			offset++;
		}
		String[] keys = lines[offset].trim().split("\\s+");
		int size = keys.length;
		for(int i = 0; i < size; i++) {
			map.put(keys[i].charAt(0), i);
		}
		int A[][] = new int[size][size];
		for(int i = 1; i + offset < lines.length; i++) {
			String[] line = lines[i + offset].trim().split("\\s+");
			for(int j = 1; j <= size; j++) {
				A[i - 1][j - 1] = Integer.parseInt(line[j]);
			}
		}
		return A;
	}

	private int sequenceAlignment(int m, int n, char[] x, char[] y, int d, int[][] A) {
		int M[][] = new int[m + 1][n + 1];
		for(int i = 0; i <= m; i ++)
			M[i][0] = i * d;
		for(int j = 0; j <= n; j++)
			M[0][j] = j * d;
		
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				M[i][j] = Math.max(A[map.get(x[i-1])][map.get(y[j-1])] + M[i-1][j-1], 
						  Math.max(d + M[i-1][j], 
								   d + M[i][j-1]));
			}
		}
		return M[m][n];
	}
	
	public String readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, java.nio.charset.Charset.defaultCharset());
	}
	
	public static void main(String[] args) throws IOException {
		if(args.length == 0)
			new SequenceAlignment("KQRK", "KQRIKAAKABK");
		else
			new SequenceAlignment(args[0], args[1]);
	}
}
