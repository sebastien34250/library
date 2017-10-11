package dao;

import java.util.ArrayList;
import model.Author;
import model.Book;

public abstract interface IDao<E>
{
  public abstract void create(E paramE);
  
  public abstract E read(E paramE);
  
  public abstract ArrayList<E> readAll();
  
  public abstract void update(E paramE);
  
  public abstract void delete(int paramInt);
  
  public abstract ArrayList<E> research(String paramString);
  
  public abstract Author readAuthor(int paramInt);
  
  public abstract void createBook(Book paramBook, int paramInt);
  
  public abstract void createCopy(int paramInt);
}
