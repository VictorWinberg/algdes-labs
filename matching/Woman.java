import java.util.*;

public class Woman extends Person {

  private Man husband, ex;
  private Map<Integer, Integer> prefs;
  private int i = 0;

  public Woman(int id, String name) {
    super(id, name);
    prefs = new HashMap<Integer, Integer>();
  }

  public void setHusband(Man husband) {
    ex = this.husband;
    this.husband = husband;
  }

  public boolean prefer(Man proposer) {
    int hid = husband.getId();
    int pid = proposer.getId();
    int hRank = prefs.get(hid);
    int pRank = prefs.get(pid);
    return pRank < hRank;
  }


  public void addPref(int id)   { prefs.put(id, i++); }
  public Man getDumpedHusband() { return ex;              }
  public boolean isEngaged()    { return husband != null; }
}
