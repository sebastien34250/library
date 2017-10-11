package dao;

import com.mysql.jdbc.PreparedStatement;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Author;
import model.Book;
import model.Catalog;
import model.Connect;

public class DaoCatalog
  implements IDao<Catalog>
{
  private ArrayList<Catalog> al = new ArrayList();
  private Catalog catalog;
  
  public void create(Catalog catalog)
  {
    try
    {
      PreparedStatement createCatalog = (PreparedStatement)Connect.getConnection().prepareStatement("call library2.`CREATE Catalog`(?);");
      
      createCatalog.setString(1, catalog.getName());
      ResultSet localResultSet = createCatalog.executeQuery();
    }
    catch (Exception e1)
    {
      e1.printStackTrace();
    }
  }
  
  public Catalog read(Catalog catalog)
  {
    try
    {
      String query = "call library2.`READ catalog`(?);";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.setInt(1, catalog.getId());
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        catalog = new Catalog(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return catalog;
  }
  
  public ArrayList<Catalog> readAll()
  {
    try
    {
      String query = "call library2.`BEFORE READALL Catalog`();";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Catalog catalog = new Catalog(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
        
        this.al.add(catalog);
      }
      String query2 = "call library2.`READALL Catalog`();";
      
      PreparedStatement preparedStatement2 = (PreparedStatement)Connect.getConnection().prepareStatement(query2);
      
      ResultSet resultSet2 = preparedStatement2.executeQuery();
      int i = 0;
      while (resultSet2.next()) {
        if (resultSet2.getInt(1) == ((Catalog)this.al.get(i)).getId())
        {
          Catalog catalog = new Catalog(resultSet2.getInt(1), resultSet2.getString(2), resultSet2.getInt(3));
          this.al.set(i, catalog);
          i++;
        }
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return this.al;
  }
  
  public void update(Catalog catalog)
  {
    System.out.println(catalog.toString());
    try
    {
      System.out.println(catalog.getName() + catalog.getId());
      PreparedStatement createCatalog = (PreparedStatement)Connect.getConnection().prepareStatement("call library2.`UPDATE Catalog`(?, ?);");
      createCatalog.setString(1, catalog.getName());
      createCatalog.setInt(2, catalog.getId());
      createCatalog.executeQuery();
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
      PreparedStatement createCatalog = (PreparedStatement)Connect.getConnection().prepareStatement("call library2.`DELETE Catalog`(?);");
      createCatalog.setInt(1, i);
      createCatalog.executeQuery();
    }
    catch (Exception e1)
    {
      JOptionPane.showMessageDialog(null, "Can't delete a catalog with book. The catalog must be empty before deleting !");
    }
  }
  
  public ArrayList<Catalog> research(String string)
  {
    ArrayList<Catalog> al = new ArrayList();
    try
    {
      String query = "SELECT c.catalog_id, c.catalog_name, count(distinct b.book_isbn) FROM catalog as c, book as b\r\n\twhere\tc.catalog_id=b.catalog_id\tand c.catalog_name like '%" + 
        string + "%'\r\n" + 
        "    group by \tc.catalog_id\r\n" + 
        "    UNION\r\n" + 
        "    SELECT c.catalog_id, c.catalog_name, 0 FROM catalog as c, book as b\r\n" + 
        "\twhere c.catalog_name like '%" + string + "%'\r\n" + 
        "    group by \tc.catalog_id";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Catalog catalog = new Catalog(resultSet.getInt(1), resultSet.getString(2), resultSet.getInt(3));
        
        al.add(catalog);
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
