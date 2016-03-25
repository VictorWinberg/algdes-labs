import java.util.*;

public abstract class Person {

  private int id;
  private String name;
  protected LinkedList<Integer> prefs;

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
    prefs = new LinkedList<Integer>();
  }

  public void addPref(int id) { prefs.add(id);  }

  public int getId()          { return id;      }

  @Override
  public String toString() {  return id + "." + name;   }

  public String getPrefs() {  return prefs.toString();  }

}
