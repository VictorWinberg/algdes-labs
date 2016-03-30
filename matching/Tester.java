import java.io.*;
import java.util.*;

public class Tester {

  public Tester(String path) throws Exception {
    boolean same = true;
    File file = new File(path);
    Scanner scan = new Scanner(file);
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in, java.nio.charset.Charset.defaultCharset()))) {
        StringBuilder sb = new StringBuilder();
        String buffered;
        String scanner;
        while (scan.hasNextLine() && (buffered = reader.readLine()) != null) {
          scanner = scan.nextLine();
          if(!scanner.equals(buffered)) {
            same = false;
            break;
          }
        }
    }
    System.out.println("Filerna är " + (same ? "identiska" : "ej identiska"));
  }

  public static void main(String[] args) throws Exception {
    new Tester(args[0]);
  }
}
