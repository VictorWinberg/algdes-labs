public class Woman extends Person {

  private Man husband, ex;

  public Woman(int id, String name) {
    super(id, name);
  }

  public void setHusband(Man husband) {
    ex = this.husband;
    this.husband = husband;
  }

  public boolean prefer(Man proposer) {
    int hid = husband.getId();
    int pid = proposer.getId();
    for(int id : prefs) {
      if(id == hid || id == pid)
        return pid == id;
    }
    return false;
  }

  public Man getDumpedHusband() { return ex;              }
  public boolean isEngaged()    { return husband != null; }
}
