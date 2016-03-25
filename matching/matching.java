import java.util.*;
import java.io.*;

public class matching {

  private String input = null;
  private int n = -1;
  private List<String>[] list;

  public matching() throws IOException {
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in, java.nio.charset.Charset.defaultCharset()))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
           sb.append(line + "\n");
        }
        input = sb.toString();
    }
    System.out.println("--- File ----------");
    System.out.println(input);
    System.out.println("--- End of file ---");
    buildInput(input);
  }

  public static void main(String[] args) throws IOException {
    new matching();
  }

  public void buildInput(String input) {
    input = input.split("n=")[1];   // Skip hashtag lines
    String[] inputs = input.split("\\r?\\n\\r?\\n");  // Group the two sections
    String[] names = inputs[0].split("\\r?\\n");       // Array of top section
    String[] botton = inputs[1].split("\\r?\\n");     // Array of botton section
    n = Integer.parseInt(names[0]);
    list = (List<String>[]) new List[n * 2];
    for (int i = 0; i < n * 2; i++)
      list[i] = new ArrayList<String>();
    for (int i = 0; i < n * 2; i++) {
      String[] values = botton[i].split(": ")[1].split(" ");
      for(int k = 0; k < values.length; k++)
        list[i].add(values[k]);
    }
  }
}
