package service;

import dao.IDao;
import java.util.ArrayList;
import model.Book;
import model.Borrow;
import model.Subscriber;

public class ServiceBorrow
  implements IService<Borrow>
{
  private IDao<Borrow> dao;
  private IDao<Book> dao1;
  private IDao<Subscriber> dao2;
  
  public ServiceBorrow(IDao<Borrow> dao, IDao<Book> dao1, IDao<Subscriber> dao2)
  {
    this.dao = dao;
    this.dao1 = dao1;
    this.dao2 = dao2;
  }
  
  public void create(Borrow e)
  {
    this.dao.create(e);
  }
  
  public Borrow read(Borrow e)
  {
    return (Borrow)this.dao.read(e);
  }
  
  public ArrayList<Borrow> readAll()
  {
    return this.dao.readAll();
  }
  
  public void update(Borrow e)
  {
    this.dao.update(e);
  }
  
  public void delete(int i)
  {
    this.dao.delete(i);
  }
  
  public ArrayList<Borrow> research(String string)
  {
    return this.dao.research(string);
  }
  
  public void createCopy(int i)
  {
    this.dao.createCopy(i);
  }
}
