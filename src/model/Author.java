package model;

public class Author
  extends Person
{
  private int id_author;
  private String date;
  
  public Author(int id, String firstname, String name, int id_author, String date)
  {
    super(id, firstname, name);
    this.id_author = id_author;
    this.date = date;
  }
  
  public int getId_author()
  {
    return this.id_author;
  }
  
  public void setId_author(int id_author)
  {
    this.id_author = id_author;
  }
  
  public String getDate()
  {
    return this.date;
  }
  
  public void setDate(String date)
  {
    this.date = date;
  }
  
  public String toString()
  {
    return " " + getFirstname() + " " + getName();
  }
}
