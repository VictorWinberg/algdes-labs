import java.util.*;

public abstract class Person {

  protected int id;
  private String name;
  protected LinkedList<Integer> prefs;

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
    prefs = new LinkedList<Integer>();
  }

  public int getId()          { return id;     }
  @Override
  public String toString()    { return name;   }
  public void addPref(int id) { prefs.add(id); }
  public String getPrefs()    { return prefs.toString();  }
}
