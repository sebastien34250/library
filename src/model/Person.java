package model;

public class Person
{
  private int id;
  private String firstname;
  private String name;
  
  public Person(int id, String firstname, String name)
  {
    this.id = id;
    this.firstname = firstname;
    this.name = name;
  }
  
  public int getId()
  {
    return this.id;
  }
  
  public void setId(int id)
  {
    this.id = id;
  }
  
  public String getFirstname()
  {
    return this.firstname;
  }
  
  public void setFirstname(String firstname)
  {
    this.firstname = firstname;
  }
  
  public String getName()
  {
    return this.name;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public String toString()
  {
    return "Person [id=" + this.id + ", firstname=" + this.firstname + ", name=" + this.name + "]";
  }
}
