package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Book;
import model.Borrow;
import model.Subscriber;
import service.ServiceBook;
import service.ServiceBorrow;
import service.ServiceSubscriber;

public class FenetreBorrow
  extends JDialog
{
  private static final long serialVersionUID = 1L;
  private JTextField subscriberNameTF;
  private JTextField numberSubscriberTF;
  private JTextField bookNameTF;
  private JTextField isbnTF;
  private DefaultListModel<Subscriber> subscriberListMODEL = new DefaultListModel();
  private DefaultListModel<Book> bookListMODEL = new DefaultListModel();
  private DefaultListModel<Book> borrowListMODEL = new DefaultListModel();
  private JList<Book> borrowLIST;
  private Button returnBTN;
  private Button borrowBTN;
  private JList<Subscriber> subscriberLIST;
  private JList<Book> bookLIST;
  private ArrayList<Subscriber> al;
  private ArrayList<Book> alBook;
  private ArrayList<Borrow> alBorrow;
  private ArrayList<Book> alBookBorrow;
  
  public FenetreBorrow(final ServiceBorrow serviceBorrow, final ServiceBook serviceBook, final ServiceSubscriber serviceSubscriber)
  {
    setLocation(new Point(450, 150));
    setSize(800, 540);
    getContentPane().setLayout(null);
    
    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.setBorder(new MatteBorder(0, 0, 0, 2, new Color(233, 150, 122)));
    panel.setBounds(-8, 0, 499, 512);
    getContentPane().add(panel);
    SpringLayout sl_panel = new SpringLayout();
    panel.setLayout(sl_panel);
    
    JPanel panel_2 = new JPanel();
    sl_panel.putConstraint("South", panel_2, 272, "North", panel);
    panel_2.setBackground(new Color(255, 182, 193));
    panel_2.setBorder(null);
    sl_panel.putConstraint("North", panel_2, 30, "North", panel);
    sl_panel.putConstraint("West", panel_2, 30, "West", panel);
    sl_panel.putConstraint("East", panel_2, -30, "East", panel);
    panel.add(panel_2);
    panel_2.setLayout(null);
    
    JLabel lblNewLabel = new JLabel("Enter name :");
    lblNewLabel.setBounds(10, 83, 200, 14);
    panel_2.add(lblNewLabel);
    
    this.subscriberNameTF = new JTextField();
    this.subscriberNameTF.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent e)
      {
        FenetreBorrow.this.subscriberListMODEL.clear();
        String research = FenetreBorrow.this.subscriberNameTF.getText();
        ArrayList<Subscriber> al = new ArrayList();
        al = serviceSubscriber.research(research);
        FenetreBorrow.this.subscriberLIST.setModel(FenetreBorrow.this.subscriberListMODEL);
        for (Subscriber subscriber : al) {
          FenetreBorrow.this.subscriberListMODEL.addElement(subscriber);
        }
      }
    });
    this.subscriberNameTF.setBounds(10, 108, 200, 20);
    panel_2.add(this.subscriberNameTF);
    this.subscriberNameTF.setColumns(10);
    
    JLabel lblNewLabel_1 = new JLabel("Or N° Subscriber :");
    lblNewLabel_1.setBounds(10, 139, 200, 14);
    panel_2.add(lblNewLabel_1);
    
    this.numberSubscriberTF = new JTextField();
    this.numberSubscriberTF.setText("EN CONSTRUCTION");
    this.numberSubscriberTF.setBounds(10, 164, 200, 20);
    panel_2.add(this.numberSubscriberTF);
    this.numberSubscriberTF.setColumns(10);
    
    JLabel lblNewLabel_2 = new JLabel("Select a subscriber");
    lblNewLabel_2.setFont(new Font("Papyrus", 1, 20));
    lblNewLabel_2.setBounds(10, 11, 226, 32);
    panel_2.add(lblNewLabel_2);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBorder(null);
    scrollPane.setBounds(234, 11, 193, 220);
    panel_2.add(scrollPane);
    
    this.subscriberLIST = new JList();
    this.alBook = serviceBook.readAll();
    this.subscriberLIST.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent arg0)
      {
        FenetreBorrow.this.returnBTN.setEnabled(false);
        FenetreBorrow.this.borrowBTN.setEnabled(false);
        FenetreBorrow.this.readAllBorrowedBooks();
      }
    });
    this.al = serviceSubscriber.readAll();
    for (Subscriber subscriber : this.al) {
      this.subscriberListMODEL.addElement(subscriber);
    }
    this.subscriberLIST.setModel(this.subscriberListMODEL);
    scrollPane.setViewportView(this.subscriberLIST);
    
    JPanel panel_3 = new JPanel();
    sl_panel.putConstraint("North", panel_3, 23, SpringLayout.SOUTH, panel_2);
    sl_panel.putConstraint(SpringLayout.SOUTH, panel_3, -30, SpringLayout.SOUTH, panel);
    panel_3.setBackground(new Color(255, 182, 193));
    sl_panel.putConstraint("West", panel_3, 30, "West", panel);
    sl_panel.putConstraint("East", panel_3, -30, "East", panel);
    panel.add(panel_3);
    panel_3.setLayout(null);
    
    JLabel lblBorrows = new JLabel("Borrow(s)");
    lblBorrows.setFont(new Font("Papyrus", 1, 20));
    lblBorrows.setBounds(10, 11, 226, 32);
    panel_3.add(lblBorrows);
    
    this.returnBTN = new Button("RETURN");
    this.returnBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FenetreBorrow.this.retour(serviceBorrow);
      }
    });
    this.returnBTN.setEnabled(false);
    this.returnBTN.setBounds(337, 152, 90, 25);
    panel_3.add(this.returnBTN);
    
    this.borrowLIST = new JList();
    this.borrowLIST.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent arg0)
      {
        FenetreBorrow.this.returnBTN.setEnabled(true);
      }
    });
    this.alBorrow = serviceBorrow.readAll();
    
    this.borrowLIST.setBounds(10, 43, 417, 98);
    panel_3.add(this.borrowLIST);
    
    JPanel panel_1 = new JPanel();
    panel_1.setOpaque(false);
    panel_1.setBounds(501, 0, 283, 512);
    getContentPane().add(panel_1);
    SpringLayout sl_panel_1 = new SpringLayout();
    panel_1.setLayout(sl_panel_1);
    
    JPanel panel_4 = new JPanel();
    panel_4.setBackground(new Color(255, 182, 193));
    sl_panel_1.putConstraint("North", panel_4, 30, "North", panel_1);
    sl_panel_1.putConstraint("West", panel_4, 30, "West", panel_1);
    sl_panel_1.putConstraint("South", panel_4, -30, "South", panel_1);
    sl_panel_1.putConstraint("East", panel_4, -30, "East", panel_1);
    panel_1.add(panel_4);
    panel_4.setLayout(null);
    
    JLabel lblSelectABook = new JLabel("Select a book");
    lblSelectABook.setFont(new Font("Papyrus", 1, 20));
    lblSelectABook.setBounds(10, 11, 203, 32);
    panel_4.add(lblSelectABook);
    
    JLabel label = new JLabel("Enter name :");
    label.setBounds(10, 54, 200, 14);
    panel_4.add(label);
    
    this.bookNameTF = new JTextField();
    this.bookNameTF.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        FenetreBorrow.this.bookListMODEL.clear();
        String research = FenetreBorrow.this.bookNameTF.getText();
        ArrayList<Book> al = new ArrayList();
        al = serviceBook.research(research);
        FenetreBorrow.this.bookLIST.setModel(FenetreBorrow.this.bookListMODEL);
        for (Book book : al) {
          FenetreBorrow.this.bookListMODEL.addElement(book);
        }
      }
    });
    this.bookNameTF.setColumns(10);
    this.bookNameTF.setBounds(10, 79, 200, 20);
    panel_4.add(this.bookNameTF);
    
    JLabel lblOrIsbn = new JLabel("Or ISBN :");
    lblOrIsbn.setBounds(10, 110, 200, 14);
    panel_4.add(lblOrIsbn);
    
    this.isbnTF = new JTextField();
    this.isbnTF.setText("EN CONSTRUCTION");
    this.isbnTF.setColumns(10);
    this.isbnTF.setBounds(10, 135, 200, 20);
    panel_4.add(this.isbnTF);
    
    this.borrowBTN = new Button("BORROW");
    this.borrowBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Borrow borrow = new Borrow(0, ((Book)FenetreBorrow.this.bookLIST.getSelectedValue()).getIsbn(), 
          ((Subscriber)FenetreBorrow.this.subscriberLIST.getSelectedValue()).getId_subscriber());
        serviceBorrow.create(borrow);
      }
    });
    this.borrowBTN.setEnabled(false);
    this.borrowBTN.setBounds(123, 417, 90, 25);
    panel_4.add(this.borrowBTN);
    
    JScrollPane scrollPane_1 = new JScrollPane();
    scrollPane_1.setHorizontalScrollBarPolicy(31);
    scrollPane_1.setBounds(10, 166, 203, 234);
    panel_4.add(scrollPane_1);
    
    this.bookLIST = new JList();
    this.bookLIST.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent arg0)
      {
        Book book = (Book)FenetreBorrow.this.bookLIST.getSelectedValue();
        if ((book.isAvailable()) && (FenetreBorrow.this.borrowListMODEL.size() < 4) && (!FenetreBorrow.this.subscriberLIST.isSelectionEmpty())) {
          FenetreBorrow.this.borrowBTN.setEnabled(true);
        } else {
          FenetreBorrow.this.borrowBTN.setEnabled(false);
        }
      }
    });
    for (Book book : this.alBook) {
      this.bookListMODEL.addElement(book);
    }
    this.bookLIST.setModel(this.bookListMODEL);
    scrollPane_1.setViewportView(this.bookLIST);
    
    JLabel label_1 = new JLabel("");
    label_1.setIcon(new ImageIcon("C:\\Users\\34011-79-21\\Pictures\\img\\Old_book_bindings.jpg"));
    label_1.setBounds(-1987, -438, 3443, 1799);
    getContentPane().add(label_1);
  }
  
  protected void readAllBorrowedBooks()
  {
    this.alBookBorrow = new ArrayList();
    Subscriber subscriber = (Subscriber)this.subscriberLIST.getSelectedValue();
    int idSubscriber = subscriber.getId_subscriber();
    int idBook;
    for (int i = 0; i < this.alBorrow.size(); i++) {
      if (((Borrow)this.alBorrow.get(i)).getIdSubscriber() == idSubscriber)
      {
        idBook = ((Borrow)this.alBorrow.get(i)).getIdBook();
        for (int j = 0; j < this.alBook.size(); j++) {
          if (((Book)this.alBook.get(j)).getIsbn() == idBook) {
            this.alBookBorrow.add((Book)this.alBook.get(j));
          }
        }
      }
    }
    this.borrowListMODEL.clear();
    for (Book book3 : this.alBookBorrow) {
      this.borrowListMODEL.addElement(book3);
    }
    this.borrowLIST.setModel(this.borrowListMODEL);
  }
  
  protected void retour(ServiceBorrow serviceBorrow)
  {
    Book book = (Book)this.borrowLIST.getSelectedValue();
    for (int i = 0; i < this.alBorrow.size(); i++) {
      if (((Borrow)this.alBorrow.get(i)).getIdBook() == book.getIsbn())
      {
        Borrow borrow = (Borrow)this.alBorrow.get(i);
        serviceBorrow.update(borrow);
        readAllBorrowedBooks();
        this.bookListMODEL.clear();
        this.returnBTN.setEnabled(false);
      }
    }
  }
}
