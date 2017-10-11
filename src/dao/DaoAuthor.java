package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Author;
import model.Book;
import model.Connect;

public class DaoAuthor
  implements IDao<Author>
{
  public void create(Author e)
  {
    try
    {
      String query = "call library2.`CREATE Author`('" + e.getName() + "', '" + e.getFirstname() + "', '" + e.getDate().toString() + "', @p_id, @p_id2);";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e1)
    {
      e1.printStackTrace();
    }
  }
  
  public Author read(Author e)
  {
    return null;
  }
  
  public ArrayList<Author> readAll()
  {
    ArrayList<Author> al = new ArrayList();
    try
    {
      String query = "call library2.`READALL author`();";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Author author = new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5));
        
        al.add(author);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return al;
  }
  
  public void update(Author e)
  {
    try
    {
      String query = "call library2.`UPDATE Author`('" + e.getName() + "', '" + e.getFirstname() + "', " + e.getId_author() + ", '" + e.getDate() + "');";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e1)
    {
      e1.printStackTrace();
    }
  }
  
  public void delete(int i)
  {
    try
    {
      String query = "call library2.`DELETE Author`(" + i + ");";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e1)
    {
      JOptionPane.showMessageDialog(null, "Des livres de cet auteur sont enregistr�s dans la biblioth�que. Vous ne pouvez donc pas le supprimer.");
    }
  }
  
  public ArrayList<Author> research(String string)
  {
    ArrayList<Author> al = new ArrayList();
    try
    {
      String query = "SELECT p.person_id,p.person_firstname, p.person_name, a.author_id, a.author_birthdate FROM author as a, person as p\r\nwhere\r\na.author_id=p.author_id\r\nand\r\n(p.person_firstname like '%" + 
      
        string + "%' or p.person_name like '%" + string + "%');";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Author author = new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5));
        
        al.add(author);
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
