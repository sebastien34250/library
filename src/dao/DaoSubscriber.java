package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Author;
import model.Book;
import model.Connect;
import model.Subscriber;

public class DaoSubscriber
  implements IDao<Subscriber>
{
  public void create(Subscriber e)
  {
    try
    {
      String query = "call library2.`CREATE Subscriber`('" + e.getName() + "', '" + e.getFirstname() + "', @p_id, @p_idsubscriber);";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e2)
    {
      e2.printStackTrace();
    }
  }
  
  public Subscriber read(Subscriber e)
  {
    return null;
  }
  
  public ArrayList<Subscriber> readAll()
  {
    ArrayList<Subscriber> al = new ArrayList();
    try
    {
      String query = "call library2.`READALL subscriber`();";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Subscriber subscriber = new Subscriber(resultSet.getInt(4), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(1));
        
        al.add(subscriber);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return al;
  }
  
  public void update(Subscriber e)
  {
    try
    {
      String query = "call library2.`UPDATE subscriber`('" + e.getName() + "', '" + e.getFirstname() + "', " + e.getId() + ");";
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e2)
    {
      e2.printStackTrace();
    }
  }
  
  public void delete(int i)
  {
    try
    {
      String query = "call library2.`DELETE subscriber`(" + i + ");";
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e2)
    {
      JOptionPane.showMessageDialog(null, "Cet adh�rent ne peut �tre supprim� de la base de donn�es tant qu'il a des livres en emprunt.");
    }
  }
  
  public ArrayList<Subscriber> research(String string)
  {
    ArrayList<Subscriber> al = new ArrayList();
    try
    {
      String query = "SELECT s.subscriber_id, p.person_firstname, p.person_name, p.person_id  FROM subscriber as s, person as p where s.person_id=p.person_id and (p.person_firstname like '%" + 
      
        string + "%' or p.person_name like  '%" + string + "%');";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Subscriber subscriber = new Subscriber(resultSet.getInt(4), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(1));
        
        al.add(subscriber);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return al;
  }
  
  public Author readAuthor(int idBook)
  {
    return null;
  }
  
  public void createBook(Book book, int idAuthor) {}
  
  public void createCopy(int i) {}
}
