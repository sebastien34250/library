package dao;

import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Author;
import model.Book;
import model.Connect;

public class DaoBook
  implements IDao<Book>
{
  private Author author;
  
  public void create(Book e)
  {
    try
    {
      String query = "call library2.`CREATE Book`(" + e.getIsbn() + ", '" + e.getTitle() + "', '" + e.getSubtitle() + "', " + e.isAvailable() + ", " + e.getCatalog() + ");";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      preparedStatement.executeQuery();
    }
    catch (Exception e2)
    {
      e2.printStackTrace();
    }
  }
  
  public Book read(Book book)
  {
    try
    {
      String query = "call library2.`READ Book`(" + book.getIsbn() + ")";
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        book.setIsbn(resultSet.getInt(1));
        book.setTitle(resultSet.getString(2));
        book.setSubtitle(resultSet.getString(3));
        book.setAvailable(resultSet.getBoolean(4));
        book.setCatalog(resultSet.getInt(5));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return book;
  }
  
  public ArrayList<Book> readAll()
  {
    ArrayList<Book> al = new ArrayList();
    try
    {
      String query = "call library2.`READALL Book`();";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Book book = new Book(Integer.valueOf(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getInt(5));
        
        al.add(book);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return al;
  }
  
  public void update(Book e) {}
  
  public void delete(int i)
  {
    try
    {
      String query = "call library2.`DELETE Book`(" + i + ");";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      preparedStatement.executeQuery();
    }
    catch (Exception e)
    {
      JOptionPane.showMessageDialog(null, "Ce livre est actuellement emprunt� par un ou plusieurs adh�rents. Vous ne pouvez pas le supprimer.");
    }
  }
  
  public ArrayList<Book> research(String string)
  {
    ArrayList<Book> al = new ArrayList();
    try
    {
      String query = "select b.book_isbn, b.book_title, b.book_subtitle, b.book_available, b.catalog_id from book as b, author as a, is_written_by as i, catalog as c, person as p\r\nwhere b.book_isbn=i.book_isbn \r\nand i.author_id=a.author_id\r\nand a.author_id=p.author_id\r\nand b.catalog_id=c.catalog_id\r\nand (b.book_title like '%" + 
      
        string + "%' or b.book_subtitle like '%" + string + "%');";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next())
      {
        Book book = new Book(Integer.valueOf(resultSet.getInt(1)), resultSet.getString(2), resultSet.getString(3), resultSet.getBoolean(4), resultSet.getInt(5));
        
        al.add(book);
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
    try
    {
      String query = "call library2.`READ AUTHOR(BOOK)`(" + idBook + ");";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        this.author = new Author(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5));
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return this.author;
  }
  
  public void createBook(Book book, int idAuthor)
  {
    try
    {
      String query = "call library2.`CREATE Book`(" + book.getIsbn() + ", '" + book.getTitle() + "', '" + book.getSubtitle() + "', " + book.isAvailable() + ", " + book.getCatalog() + "," + idAuthor + ");";
      
      PreparedStatement preparedStatement = (PreparedStatement)Connect.getConnection().prepareStatement(query);
      
      preparedStatement.executeQuery();
    }
    catch (Exception e2)
    {
      e2.printStackTrace();
    }
  }
  
  public void createCopy(int i) {}
}
