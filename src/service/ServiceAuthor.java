package service;

import dao.IDao;
import java.util.ArrayList;
import model.Author;

public class ServiceAuthor
  implements IService<Author>
{
  private IDao<Author> dao;
  
  public ServiceAuthor(IDao<Author> dao)
  {
    this.dao = dao;
  }
  
  public void create(Author e)
  {
    this.dao.create(e);
  }
  
  public Author read(Author e)
  {
    return (Author)this.dao.read(e);
  }
  
  public ArrayList<Author> readAll()
  {
    return this.dao.readAll();
  }
  
  public void update(Author e)
  {
    this.dao.update(e);
  }
  
  public void delete(int i)
  {
    this.dao.delete(i);
  }
  
  public ArrayList<Author> research(String string)
  {
    return this.dao.research(string);
  }
  
  public void createCopy(int i) {}
}
