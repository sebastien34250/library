package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import model.Author;
import model.Book;
import model.Borrow;
import model.Connect;

public class DaoBorrow
  implements IDao<Borrow>
{
  public void create(Borrow borrow)
  {
    try
    {
      String query = "call library2.`CREATE Borrow`(" + borrow.getIdBook() + ", " + borrow.getIdSubscriber() + 
        ", @p_copy_id);";
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public Borrow read(Borrow e)
  {
    return null;
  }
  
  public ArrayList<Borrow> readAll()
  {
    ArrayList<Borrow> al = new ArrayList();
    try
    {
      String query = "call library2.`READALL Borrow`();";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Borrow borrow = new Borrow(resultSet.getInt(1), resultSet.getInt(2), resultSet.getInt(3));
        
        al.add(borrow);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return al;
  }
  
  public void update(Borrow borrow)
  {
    try
    {
      String query = "call library2.`DELETE Borrow`(" + borrow.getIdBook() + ", " + borrow.getIdSubscriber() + 
        ");";
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      ResultSet localResultSet = preparedStatement.executeQuery();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  
  public void delete(int i) {}
  
  public ArrayList<Borrow> research(String string)
  {
    return null;
  }
  
  public Author readAuthor(int idBook)
  {
    return null;
  }
  
  public void createBook(Book book, int idAuthor) {}
  
  public void createCopy(int i)
  {
    try
    {
      String query = "call library2.`CREATE COPY`(" + i + ");";
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
}
