public class Woman extends Person {

  private boolean engaged;

  public Woman(int id, String name) {
    super(id, name);
    engaged = false;
  }

  public void setEngaged(boolean status) {
    engaged = status;
  }

  public boolean isEngaged() {
    return engaged;
  }
}
