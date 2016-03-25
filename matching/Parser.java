import java.util.*;
import java.io.*;

public class Parser {

  private int n = -1;
  private Person[] men, women;

  public Parser() throws IOException {
    String input = readFromInput();
    System.out.println("--- File ----------");
    System.out.println(input);
    buildInput(input);
    System.out.println("--- Men and women -");
    System.out.println("Men " + Arrays.toString(men) + "\nWomen: " + Arrays.toString(women));
    System.out.println("--- Person prefs --");
    for(int i = 0; i < n; i++) {
      System.out.println(men[i] + ": " + men[i].getPrefs());
      System.out.println(women[i] + ": " + women[i].getPrefs());
    }
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

  public void buildInput(String input) {
    input = input.split("n=")[1];   // Skip hashtag lines
    String[] inputs = input.split("\\r?\\n\\r?\\n");  // Group the two sections
    String[] names = inputs[0].split("\\r?\\n");       // Array of top section
    String[] botton = inputs[1].split("\\r?\\n");     // Array of botton section
    n = Integer.parseInt(names[0]);
    men = new Man[n];
    women = new Woman[n];
    for(int i = 0; i < n; i++) {
      String[] manInput = names[2 * i + 1].split(" ");
      men[i] = new Man(Integer.parseInt(manInput[0]), manInput[1]);
      String[] womanInput = names[2 * i + 2].split(" ");
      women[i] = new Woman(Integer.parseInt(womanInput[0]), womanInput[1]);
    }
    for(int i = 0; i < n; i++) {
      String[] menValues = botton[2 * i].split(": ")[1].split(" ");
      String[] womenValues = botton[2 * i + 1].split(": ")[1].split(" ");
      for(int k = 0; k < n; k ++) {
        men[i].addPref(Integer.parseInt(menValues[k]));
        women[i].addPref(Integer.parseInt(womenValues[k]));
      }
    }
  }
  public LinkedList<Man> getMen() {
    return new LinkedList<Man>(Arrays.asList((Man[])men));
  }
  public Map<Integer, Woman> getWomen() {
    Map<Integer, Woman> womenMap = new HashMap<Integer, Woman>();
    for(int i = 0; i < n; i++) {
      womenMap.put(women[i].getId(), (Woman) women[i]);
    }
    return womenMap;
  }
}
