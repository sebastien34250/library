package model;

public class Subscriber
  extends Person
{
  private int id_subscriber;
  
  public Subscriber(int id, String firstname, String name, int id_subscriber)
  {
    super(id, firstname, name);
    this.id_subscriber = id_subscriber;
  }
  
  public int getId_subscriber()
  {
    return this.id_subscriber;
  }
  
  public void setId_subscriber(int id_subscriber)
  {
    this.id_subscriber = id_subscriber;
  }
  
  public String toString()
  {
    return " " + getFirstname() + " " + getName();
  }
}
