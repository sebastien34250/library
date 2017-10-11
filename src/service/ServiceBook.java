package service;

import dao.IDao;
import java.util.ArrayList;
import model.Author;
import model.Book;

public class ServiceBook
  implements IService<Book>
{
  private IDao<Book> dao;
  
  public ServiceBook(IDao<Book> dao)
  {
    this.dao = dao;
  }
  
  public void create(Book e)
  {
    this.dao.create(e);
  }
  
  public Book read(Book e)
  {
    return (Book)this.dao.read(e);
  }
  
  public ArrayList<Book> readAll()
  {
    return this.dao.readAll();
  }
  
  public void update(Book e)
  {
    this.dao.update(e);
  }
  
  public void delete(int i)
  {
    this.dao.delete(i);
  }
  
  public ArrayList<Book> research(String string)
  {
    return this.dao.research(string);
  }
  
  public Author readAuthor(int idBook)
  {
    return this.dao.readAuthor(idBook);
  }
  
  public void createBook(Book book, int idAuthor)
  {
    this.dao.createBook(book, idAuthor);
  }
  
  public void createCopy(int i) {}
}
