package ui;

import dao.DaoAuthor;
import dao.DaoBook;
import dao.DaoBorrow;
import dao.DaoCatalog;
import dao.DaoSubscriber;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import service.ServiceAuthor;
import service.ServiceBook;
import service.ServiceBorrow;
import service.ServiceCatalog;
import service.ServiceSubscriber;

public class FenetreMenu
  extends JFrame
{
  private static final long serialVersionUID = 1L;
  private String titleAuthor = "AUTHOR";
  private Color colorAuthor = new Color(0, 191, 255);
  private String imageAuthor = "C:\\Users\\34011-79-21\\Documents\\Bibliothèque\\img\\aaa.jpg";
  private String titleBook = "BOOK";
  private Color colorBook = new Color(199, 21, 133);
  private String imageBook = "C:\\Users\\34011-79-21\\Documents\\Bibliothèque\\img\\bbb.jpg";
  private String titleSubscriber = "SUBSCRIBER";
  private Color colorSubscriber = new Color(0, 255, 127);
  private String imageSubscriber = "C:\\Users\\34011-79-21\\Documents\\Bibliothèque\\img\\ddd.png";
  private String titleCatalog = "CATALOG";
  private Color colorCatalog = new Color(210, 105, 30);
  private String imageCatalog = "C:\\Users\\34011-79-21\\Documents\\Bibliothèque\\img\\ccc.jpg";
  
  public FenetreMenu()
  {
    setLocation(new Point(500, 200));
    setSize(445, 400);
    getContentPane().setLayout(null);
    
    Button button = new Button("CATALOG");
    button.setFont(new Font("Papyrus", 0, 12));
    button.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        DaoCatalog daoCatalog = new DaoCatalog();
        ServiceCatalog serviceCatalog = new ServiceCatalog(daoCatalog);
        FenetreCatalog fenetreCatalog = new FenetreCatalog(FenetreMenu.this.titleCatalog, FenetreMenu.this.colorCatalog, FenetreMenu.this.imageCatalog, serviceCatalog);
        fenetreCatalog.setResizable(false);
        fenetreCatalog.setTitle("Library: View Catalog");
        fenetreCatalog.setVisible(true);
      }
    });
    button.setBounds(10, 31, 90, 25);
    getContentPane().add(button);
    
    Button button_1 = new Button("AUTHOR");
    button_1.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        DaoAuthor daoAuthor = new DaoAuthor();
        ServiceAuthor serviceAuthor = new ServiceAuthor(daoAuthor);
        FenetreAuthor fenetreAuthor = new FenetreAuthor(FenetreMenu.this.titleAuthor, FenetreMenu.this.colorAuthor, FenetreMenu.this.imageAuthor, serviceAuthor);
        fenetreAuthor.setResizable(false);
        fenetreAuthor.setTitle("Library: View Author");
        fenetreAuthor.setVisible(true);
      }
    });
    button_1.setBounds(106, 31, 90, 25);
    getContentPane().add(button_1);
    
    Button button_2 = new Button("BOOK");
    button_2.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        DaoBook daoBook = new DaoBook();
        ServiceBook serviceBook = new ServiceBook(daoBook);
        DaoCatalog daoCatalog = new DaoCatalog();
        ServiceCatalog serviceCatalog = new ServiceCatalog(daoCatalog);
        DaoBorrow daoBorrow = new DaoBorrow();
        ServiceBorrow serviceBorrow = new ServiceBorrow(daoBorrow, daoBook, null);
        DaoAuthor daoAuthor = new DaoAuthor();
        ServiceAuthor serviceAuthor = new ServiceAuthor(daoAuthor);
        FenetreBook fenetreBook = new FenetreBook(FenetreMenu.this.titleBook, FenetreMenu.this.colorBook, FenetreMenu.this.imageBook, serviceAuthor, serviceBook, serviceCatalog, serviceBorrow);
        fenetreBook.setResizable(false);
        fenetreBook.setTitle("Library: View Book");
        fenetreBook.setVisible(true);
      }
    });
    button_2.setBounds(202, 31, 90, 25);
    getContentPane().add(button_2);
    
    Button button_3 = new Button("SUBSCRIBER");
    button_3.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        DaoSubscriber daoSubscriber = new DaoSubscriber();
        ServiceSubscriber serviceSubscriber = new ServiceSubscriber(daoSubscriber);
        FenetreSubscriber fenetreSubscriber = new FenetreSubscriber(FenetreMenu.this.titleSubscriber, FenetreMenu.this.colorSubscriber, FenetreMenu.this.imageSubscriber, serviceSubscriber);
        
        fenetreSubscriber.setResizable(false);
        fenetreSubscriber.setTitle("Library: View Subscriber");
        fenetreSubscriber.setVisible(true);
      }
    });
    button_3.setBounds(298, 31, 90, 25);
    getContentPane().add(button_3);
    
    JLabel lblSelectAView = new JLabel("Select a view to create, read, update or delete :");
    lblSelectAView.setForeground(SystemColor.text);
    lblSelectAView.setBounds(10, 11, 364, 14);
    getContentPane().add(lblSelectAView);
    
    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.setBorder(new LineBorder(Color.WHITE, 2));
    panel.setBounds(-22, -28, 466, 106);
    getContentPane().add(panel);
    
    Button button_4 = new Button("BORROW and/or RETURN BOOK");
    
    button_4.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        DaoBorrow daoBorrow = new DaoBorrow();
        DaoBook daoBook = new DaoBook();
        ServiceBook serviceBook = new ServiceBook(daoBook);
        DaoSubscriber daoSubscriber = new DaoSubscriber();
        ServiceSubscriber serviceSubscriber = new ServiceSubscriber(daoSubscriber);
        
        ServiceBorrow serviceBorrow = new ServiceBorrow(daoBorrow, daoBook, daoSubscriber);
        FenetreBorrow fenetreBorrow = new FenetreBorrow(serviceBorrow, serviceBook, serviceSubscriber);
        fenetreBorrow.setResizable(false);
        fenetreBorrow.setTitle("Barrow and return");
        fenetreBorrow.setVisible(true);
      }
    });
    button_4.setBounds(115, 153, 200, 110);
    getContentPane().add(button_4);
    
    JLabel label = new JLabel("");
    label.setIcon(new ImageIcon("C:\\Users\\34011-79-21\\Pictures\\img\\la-bibliotheque.jpg"));
    label.setBounds(-88, -39, 639, 669);
    getContentPane().add(label);
  }
}
