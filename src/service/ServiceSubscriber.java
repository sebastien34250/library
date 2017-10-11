package service;

import dao.IDao;
import java.util.ArrayList;
import model.Subscriber;

public class ServiceSubscriber
  implements IService<Subscriber>
{
  private IDao<Subscriber> dao;
  
  public ServiceSubscriber(IDao<Subscriber> dao)
  {
    this.dao = dao;
  }
  
  public void create(Subscriber e)
  {
    this.dao.create(e);
  }
  
  public Subscriber read(Subscriber e)
  {
    return (Subscriber)this.dao.read(e);
  }
  
  public ArrayList<Subscriber> readAll()
  {
    return this.dao.readAll();
  }
  
  public void update(Subscriber e)
  {
    this.dao.update(e);
  }
  
  public void delete(int i)
  {
    this.dao.delete(i);
  }
  
  public ArrayList<Subscriber> research(String string)
  {
    return this.dao.research(string);
  }
  
  public void createCopy(int i) {}
}
