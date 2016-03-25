import java.io.IOException;
import java.util.*;

public class Matcher {
  private Parser parser;
  public Matcher() {
    try {
      parser = new Parser();
    } catch(IOException e) {
      System.out.println("You fucked up.");
    }
    LinkedList<Man> singleMen = parser.getMen();
    Map<Integer, Woman> women = parser.getWomen();
    System.out.println("Matcher ------");
    while(!singleMen.isEmpty()) {
      Man man = singleMen.pop();
      Woman woman = women.get(man.getPrefId());
      if(man.propose(woman)) {
        System.out.print(man + " --- " + woman + "\n");
      } else {
        singleMen.addFirst(man);
      }
    }
  }

  public static void main(String[] args) {
    new Matcher();
  }
}
