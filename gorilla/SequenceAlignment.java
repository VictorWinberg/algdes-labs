import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.TreeMap;

public class SequenceAlignment {
	
	private Map<Character, Integer> map;
	
	public SequenceAlignment(String a, String b, String path) throws IOException {
		map = new TreeMap<Character, Integer>();
		int[][] A = blosum62(path);
		
		char[] x = a.toCharArray();
		char[] y = b.toCharArray();
		
		int[][] M = sequenceMatrix(x.length, y.length, x, y, -4, A);
		
		System.out.println(a + "--" + b + ": " + M[x.length][y.length]);
		
		tracebackAlignment(x.length, y.length, a, b, -4, M);
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

	private int[][] sequenceMatrix(int m, int n, char[] x, char[] y, int d, int[][] A) {
		int M[][] = new int[m + 1][n + 1];
		for(int i = 0; i <= m; i ++)
			M[i][0] = i * d;
		for(int j = 0; j <= n; j++)
			M[0][j] = j * d;
		
		for(int i = 1; i <= m; i++) {
			for(int j = 1; j <= n; j++) {
				int a = (map.get(x[i-1]) == null || map.get(y[j-1]) == null) ? d : A[map.get(x[i-1])][map.get(y[j-1])];
				M[i][j] = Math.max(a + M[i-1][j-1], 
						  Math.max(d + M[i-1][j], 
								   d + M[i][j-1]));
			}
		}
		
		return M;
	}
	
	public void tracebackAlignment(int m, int n, String x, String y, int d, int[][] M) {
		while(m != 0 || n != 0) {
			int up = m == 0 ? 0 : M[m-1][n];
			int left = n == 0 ? 0 : M[m][n-1];
			int current = M[m][n];
			if(m != 0 && up + d == current) {
				m--;
				y = y.substring(0, n) + "-" + y.substring(n, y.length());
			} else if(n != 0 && left + d == current) {
				n--;
				x = x.substring(0, m) + "-" + x.substring(m, x.length());
			} else {
				m--;
				n--;
			}
		}
		System.out.println(x);
		System.out.println(y);
	}
	
	public String readFromFile(String path) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, java.nio.charset.Charset.defaultCharset());
	}
	
	public static void main(String[] args) throws IOException {
		String[] s = {"VICTOR", "ANTON", "ANNIE", "HANNA"};
		if(args.length == 0) {
			for(int i = 0; i < s.length; i++) {
				for(int j = i; j < s.length; j++)
					if(i != j) new SequenceAlignment(s[i], s[j], "gorilla/data/BLOSUM62.txt");								
			}
		}
		else
			new SequenceAlignment(args[0], args[1], args[2]);
	}
}
