package model;

public class Book
{
  private int isbn;
  private String title;
  private String subtitle;
  private boolean available;
  private int catalog;
  
  public Book(Integer isbn, String title, String subtitle, boolean available, int catalog)
  {
    this.isbn = isbn.intValue();
    this.title = title;
    this.subtitle = subtitle;
    this.available = available;
    this.catalog = catalog;
  }
  
  public int getIsbn()
  {
    return this.isbn;
  }
  
  public void setIsbn(int isbn)
  {
    this.isbn = isbn;
  }
  
  public String getTitle()
  {
    return this.title;
  }
  
  public void setTitle(String title)
  {
    this.title = title;
  }
  
  public String getSubtitle()
  {
    return this.subtitle;
  }
  
  public void setSubtitle(String subtitle)
  {
    this.subtitle = subtitle;
  }
  
  public boolean isAvailable()
  {
    return this.available;
  }
  
  public void setAvailable(boolean available)
  {
    this.available = available;
  }
  
  public int getCatalog()
  {
    return this.catalog;
  }
  
  public void setCatalog(int catalog)
  {
    this.catalog = catalog;
  }
  
  public String toString()
  {
    String toString = null;
    String available = "";
    if (!this.available) {
      available = "INDISPONIBLE";
    }
    toString = "  " + this.title + "  " + this.subtitle + " " + available;
    
    return toString;
  }
  
  public String toString2()
  {
    return 
      "Book [isbn=" + this.isbn + ", title=" + this.title + ", subtitle=" + this.subtitle + ", available=" + this.available + ", catalog=" + this.catalog + "]";
  }
}
