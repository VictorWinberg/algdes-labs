import java.io.*;
import java.util.*;

public class Tester {

	private boolean same;

	public Tester(String path) throws Exception {
		same = true;
		File file = new File(path);
		Scanner scan = new Scanner(file);
		String buffered = null;
		String scanner = null;
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(System.in, java.nio.charset.Charset.defaultCharset()))) {
			while (scan.hasNextLine() && (buffered = reader.readLine()) != null) {
				scanner = scan.nextLine();
				if (!scanner.equals(buffered)) {
					same = false;
					break;
				}
			}
		}
		scan.close();
		System.out.println(
				"Filerna är " + (same ? "identiska" : "ej identiska\nBuffer:\n" + buffered + "\nInput:\n" + scanner));
	}

	public boolean isEqual() {
		return same;
	}

	public static void main(String[] args) throws Exception {
		new Tester(args[0]);
	}
}
