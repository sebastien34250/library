package model;

public class Borrow
{
  private int idCopy;
  private int idBook;
  private int idSubscriber;
  
  public Borrow(int idCopy, int idBook, int idSubscriber)
  {
    this.idCopy = idCopy;
    this.idBook = idBook;
    this.idSubscriber = idSubscriber;
  }
  
  public int getIdCopy()
  {
    return this.idCopy;
  }
  
  public void setIdCopy(int idCopy)
  {
    this.idCopy = idCopy;
  }
  
  public int getIdBook()
  {
    return this.idBook;
  }
  
  public void setIdBook(int idBook)
  {
    this.idBook = idBook;
  }
  
  public int getIdSubscriber()
  {
    return this.idSubscriber;
  }
  
  public void setIdSubscriber(int idSubscriber)
  {
    this.idSubscriber = idSubscriber;
  }
}
