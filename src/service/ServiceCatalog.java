package service;

import dao.IDao;
import java.util.ArrayList;
import model.Catalog;

public class ServiceCatalog
  implements IService<Catalog>
{
  private IDao<Catalog> dao;
  
  public ServiceCatalog(IDao<Catalog> dao)
  {
    this.dao = dao;
  }
  
  public void create(Catalog e)
  {
    this.dao.create(e);
  }
  
  public Catalog read(Catalog e)
  {
    return (Catalog)this.dao.read(e);
  }
  
  public ArrayList<Catalog> readAll()
  {
    return this.dao.readAll();
  }
  
  public void update(Catalog e2)
  {
    this.dao.update(e2);
  }
  
  public void delete(int i)
  {
    this.dao.delete(i);
  }
  
  public ArrayList<Catalog> research(String string)
  {
    return this.dao.research(string);
  }
  
  public void createCopy(int i) {}
}
