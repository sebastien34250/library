package service;

import java.util.ArrayList;

public abstract interface IService<E>
{
  public abstract void create(E paramE);
  
  public abstract E read(E paramE);
  
  public abstract ArrayList<E> readAll();
  
  public abstract void update(E paramE);
  
  public abstract void delete(int paramInt);
  
  public abstract ArrayList<E> research(String paramString);
  
  public abstract void createCopy(int paramInt);
}
