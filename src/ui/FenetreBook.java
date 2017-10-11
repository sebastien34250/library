package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Author;
import model.Book;
import model.Borrow;
import model.Catalog;
import service.ServiceAuthor;
import service.ServiceBook;
import service.ServiceBorrow;
import service.ServiceCatalog;

public class FenetreBook
  extends Fenetre<Book>
{
  private static final long serialVersionUID = 1L;
  private JTextField titleTF;
  private JTextField subtitleTF;
  private JTextField isbnTF;
  private JTextField authorTF;
  private JTextField availableTF;
  private JTextField catalogTF;
  private Font font = new Font("Tahoma", 1, 11);
  private Color color = Color.white;
  private ArrayList<Book> al;
  private JComboBox<Author> authorCB;
  private JSpinner availableSP;
  private JComboBox<Catalog> catalogCB;
  private ArrayList<Author> alAuthor;
  private ArrayList<Catalog> alCatalog;
  
  public FenetreBook(String titleFrame, Color color, String image, final ServiceAuthor serviceAuthor, final ServiceBook serviceBook, final ServiceCatalog serviceCatalog, final ServiceBorrow serviceBorrow)
  {
    super(titleFrame, color, image);
    getCancelBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreBook.this.echange2();
      }
    });
    getOkBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreBook.this.valid(serviceAuthor, serviceBook, serviceCatalog, serviceBorrow);
        FenetreBook.this.echange2();
      }
    });
    getDeleteBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        serviceBook.delete(((Book)FenetreBook.this.getList().getSelectedValue()).getIsbn());
        
        FenetreBook.this.refresh(serviceBook);
      }
    });
    getUpdateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreBook.this.edit();
        FenetreBook.this.echange();
      }
    });
    getCreateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreBook.this.titleTF.setText("");
        FenetreBook.this.subtitleTF.setText("");
        FenetreBook.this.isbnTF.setText("");
        FenetreBook.this.authorTF.setText("");
        FenetreBook.this.availableTF.setText("");
        FenetreBook.this.catalogTF.setText("");
        FenetreBook.this.edit();
        FenetreBook.this.echange();
      }
    });
    getList().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent e)
      {
        FenetreBook.this.read(serviceBook, serviceCatalog);
      }
    });
    display();
    readAll(serviceBook);
    readAll(serviceAuthor);
    readAll(serviceCatalog);
  }
  
  protected void echange2()
  {
    this.authorTF.setVisible(true);
    this.catalogTF.setVisible(true);
    this.availableTF.setVisible(true);
    this.authorCB.setVisible(false);
    this.catalogCB.setVisible(false);
    this.availableSP.setVisible(false);
    this.titleTF.setEditable(false);
    this.subtitleTF.setEditable(false);
    this.isbnTF.setEditable(false);
    this.authorTF.setEditable(false);
    this.availableTF.setEditable(false);
    this.catalogTF.setEditable(false);
  }
  
  protected void echange()
  {
    this.authorTF.setVisible(false);
    this.catalogTF.setVisible(false);
    this.availableTF.setVisible(false);
    this.authorCB.setVisible(true);
    this.catalogCB.setVisible(true);
    this.availableSP.setVisible(true);
  }
  
  private void readAll(ServiceCatalog serviceCatalog)
  {
    this.alCatalog = serviceCatalog.readAll();
    for (int i = 0; i < this.alCatalog.size(); i++) {
      this.catalogCB.addItem((Catalog)this.alCatalog.get(i));
    }
  }
  
  private void readAll(ServiceAuthor serviceAuthor)
  {
    this.alAuthor = serviceAuthor.readAll();
    for (int j = 0; j < this.alAuthor.size(); j++) {
      this.authorCB.addItem((Author)this.alAuthor.get(j));
    }
  }
  
  protected void edit()
  {
    this.titleTF.setEditable(true);
    this.subtitleTF.setEditable(true);
    this.isbnTF.setEditable(true);
    this.authorTF.setEditable(true);
    this.availableTF.setEditable(true);
    this.catalogTF.setEditable(true);
  }
  
  private void unEdit()
  {
    this.titleTF.setEditable(false);
    this.subtitleTF.setEditable(false);
    this.isbnTF.setEditable(false);
    this.authorTF.setEditable(false);
    this.availableTF.setEditable(false);
    this.catalogTF.setEditable(false);
  }
  
  protected void refresh(ServiceBook serviceBook)
  {
    this.al.clear();
    getModel().clear();
    getList().setModel(this.model);
    readAll(serviceBook);
  }
  
  protected void valid(ServiceAuthor serviceAuthor, ServiceBook serviceBook, ServiceCatalog serviceCatalog, ServiceBorrow serviceBorrow)
  {
    unEdit();
    if (getEtat() == "create") {
      create(serviceBook, serviceBorrow);
    } else if (getEtat() == "update") {
      update(serviceBook);
    }
    refresh(serviceBook);
  }
  
  private void update(ServiceBook serviceBook)
  {
    Book book = (Book)getList().getSelectedValue();
    System.out.println(book.toString2());
    book.setTitle(this.titleTF.getText());
    book.setSubtitle(this.subtitleTF.getText());
    book.setCatalog(this.catalogCB.getSelectedIndex());
    book.setAvailable(true);
    book.setIsbn(Integer.valueOf(this.isbnTF.getText()).intValue());
    serviceBook.update(book);
  }
  
  private void create(ServiceBook serviceBook, ServiceBorrow serviceBorrow)
  {
    boolean available = true;
    Author author = (Author)this.authorCB.getSelectedItem();
    Catalog catalog = (Catalog)this.catalogCB.getSelectedItem();
    int copy = ((Integer)this.availableSP.getValue()).intValue();
    Borrow borrow = new Borrow(0, Integer.valueOf(this.isbnTF.getText()).intValue(), 0);
    Book book = new Book(Integer.valueOf(this.isbnTF.getText()), this.titleTF.getText(), this.subtitleTF.getText(), available, 
      catalog.getId());
    serviceBook.createBook(book, author.getId_author());
    for (int i = 0; i < copy; i++) {
      serviceBorrow.createCopy(book.getIsbn());
    }
  }
  
  protected void read(ServiceBook serviceBook, ServiceCatalog serviceCatalog)
  {
    try
    {
      Book book = (Book)getList().getSelectedValue();
      int catalogID = book.getCatalog();
      String catalogName = null;
      for (int i = 0; i < this.alCatalog.size(); i++) {
        if (catalogID == ((Catalog)this.alCatalog.get(i)).getId()) {
          catalogName = ((Catalog)this.alCatalog.get(i)).getName();
        }
      }
      this.titleTF.setText(book.getTitle());
      this.subtitleTF.setText(book.getSubtitle());
      this.isbnTF.setText(String.valueOf(book.getIsbn()));
      this.authorTF.setText("???");
      this.availableTF.setText(String.valueOf(book.isAvailable()));
      this.catalogTF.setText(catalogName);
      
      Author author = serviceBook.readAuthor(book.getIsbn());
      this.authorTF.setText(author.toString());
      for (int i = 0; i < this.alAuthor.size(); i++) {
        if (author.getId() == ((Author)this.alAuthor.get(i)).getId()) {
          this.authorCB.setSelectedIndex(i);
        }
      }
    }
    catch (Exception e)
    {
      this.titleTF.setText("");
      this.subtitleTF.setText("");
      this.isbnTF.setText("");
      this.authorTF.setText("");
      this.availableTF.setText("");
      this.catalogTF.setText("");
    }
  }
  
  private void display()
  {
    JLabel lblNewLabel = new JLabel("Title :");
    lblNewLabel.setFont(this.font);
    lblNewLabel.setForeground(this.color);
    lblNewLabel.setBounds(10, 11, 200, 14);
    getInnerEastPAN().add(lblNewLabel);
    
    this.titleTF = new JTextField();
    this.titleTF.setEditable(false);
    this.titleTF.setBounds(10, 36, 200, 20);
    getInnerEastPAN().add(this.titleTF);
    
    JLabel lblSubtitle = new JLabel("Subtitle :");
    lblSubtitle.setForeground(this.color);
    lblSubtitle.setFont(this.font);
    lblSubtitle.setBounds(10, 67, 200, 14);
    getInnerEastPAN().add(lblSubtitle);
    
    this.subtitleTF = new JTextField();
    this.subtitleTF.setEditable(false);
    this.subtitleTF.setBounds(10, 92, 200, 20);
    getInnerEastPAN().add(this.subtitleTF);
    
    JLabel lblIsbn = new JLabel("ISBN :");
    lblIsbn.setForeground(this.color);
    lblIsbn.setFont(this.font);
    lblIsbn.setBounds(10, 123, 200, 14);
    getInnerEastPAN().add(lblIsbn);
    
    this.isbnTF = new JTextField();
    this.isbnTF.setEditable(false);
    this.isbnTF.setBounds(10, 148, 200, 20);
    getInnerEastPAN().add(this.isbnTF);
    
    JLabel lblAuthor = new JLabel("Author :");
    lblAuthor.setForeground(this.color);
    lblAuthor.setFont(this.font);
    lblAuthor.setBounds(10, 179, 200, 14);
    getInnerEastPAN().add(lblAuthor);
    
    this.authorTF = new JTextField();
    this.authorTF.setEditable(false);
    this.authorTF.setBounds(10, 204, 200, 20);
    getInnerEastPAN().add(this.authorTF);
    
    JLabel lblAvailable = new JLabel("Available :");
    lblAvailable.setForeground(this.color);
    lblAvailable.setFont(this.font);
    lblAvailable.setBounds(10, 235, 200, 14);
    getInnerEastPAN().add(lblAvailable);
    
    this.availableTF = new JTextField();
    this.availableTF.setEditable(false);
    this.availableTF.setBounds(10, 260, 200, 20);
    getInnerEastPAN().add(this.availableTF);
    
    JLabel lblCatalog = new JLabel("Catalog :");
    lblCatalog.setForeground(this.color);
    lblCatalog.setFont(this.font);
    lblCatalog.setBounds(10, 291, 200, 14);
    getInnerEastPAN().add(lblCatalog);
    
    this.catalogTF = new JTextField();
    this.catalogTF.setEditable(false);
    this.catalogTF.setBounds(10, 316, 200, 20);
    getInnerEastPAN().add(this.catalogTF);
    
    this.availableSP = new JSpinner();
    this.availableSP.setModel(new SpinnerNumberModel(1, 0, 200, 1));
    
    this.availableSP.setVisible(false);
    this.availableSP.setBounds(10, 260, 43, 20);
    getInnerEastPAN().add(this.availableSP);
    
    this.authorCB = new JComboBox();
    this.authorCB.setVisible(false);
    this.authorCB.setBounds(10, 204, 180, 20);
    getInnerEastPAN().add(this.authorCB);
    
    this.catalogCB = new JComboBox();
    this.catalogCB.setVisible(false);
    this.catalogCB.setBounds(10, 316, 180, 20);
    getInnerEastPAN().add(this.catalogCB);
  }
  
  private void readAll(ServiceBook serviceBook)
  {
    this.al = serviceBook.readAll();
    for (int i = 0; i < this.al.size(); i++) {
      this.model.addElement((Book)this.al.get(i));
    }
    getList().setModel(this.model);
  }
}
