package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Author;
import service.ServiceAuthor;

public class FenetreAuthor
  extends Fenetre<Author>
{
  private static final long serialVersionUID = 1L;
  private JTextField firstnameTF;
  private JTextField nameTF;
  private JTextField birthTF;
  private JLabel lblNewLabel = new JLabel("Firstname :");
  private JLabel lblNewLabel_1 = new JLabel("Name :");
  private JLabel lblNewLabel_2 = new JLabel("Date of birth :");
  private Author author;
  private ArrayList<Author> al;
  private Font font = new Font("Tahoma", 1, 11);
  private Color color1 = new Color(255, 255, 255);
  
  public FenetreAuthor(String titleFrame, Color color, String image, final ServiceAuthor serviceAuthor)
  {
    super(titleFrame, color, image);
    getCancelBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreAuthor.this.firstnameTF.setEditable(false);
        FenetreAuthor.this.nameTF.setEditable(false);
        FenetreAuthor.this.birthTF.setEditable(false);
      }
    });
    getOkBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreAuthor.this.valid(serviceAuthor);
      }
    });
    getDeleteBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        serviceAuthor.delete(((Author)FenetreAuthor.this.getList().getSelectedValue()).getId_author());
        FenetreAuthor.this.refresh(serviceAuthor);
      }
    });
    getUpdateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreAuthor.this.edit();
      }
    });
    getCreateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreAuthor.this.edit();
        FenetreAuthor.this.firstnameTF.setText("");
        FenetreAuthor.this.nameTF.setText("");
        FenetreAuthor.this.birthTF.setText("");
      }
    });
    getList().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent e)
      {
        Author author = (Author)FenetreAuthor.this.getList().getSelectedValue();
        try
        {
          FenetreAuthor.this.firstnameTF.setText(author.getFirstname());
          FenetreAuthor.this.nameTF.setText(author.getName());
          FenetreAuthor.this.birthTF.setText(author.getDate());
        }
        catch (Exception e1)
        {
          FenetreAuthor.this.firstnameTF.setText("");
          FenetreAuthor.this.nameTF.setText("");
          FenetreAuthor.this.birthTF.setText("");
        }
      }
    });
    display();
    readAll(serviceAuthor);
  }
  
  private void valid(ServiceAuthor serviceAuthor)
  {
    this.nameTF.setEditable(false);
    this.firstnameTF.setEditable(false);
    this.birthTF.setEditable(false);
    if (getEtat() == "create") {
      create(serviceAuthor);
    } else if (getEtat() == "update") {
      update(serviceAuthor);
    }
  }
  
  private void update(ServiceAuthor serviceAuthor)
  {
    this.author = ((Author)getList().getSelectedValue());
    this.author.setFirstname(this.firstnameTF.getText());
    this.author.setName(this.nameTF.getText());
    this.author.setDate(this.birthTF.getText());
    serviceAuthor.update(this.author);
    refresh(serviceAuthor);
  }
  
  private void create(ServiceAuthor serviceAuthor)
  {
    this.author = new Author(0, this.firstnameTF.getText(), this.nameTF.getText(), 0, this.birthTF.getText());
    System.out.println(this.author.toString());
    serviceAuthor.create(this.author);
    this.al.clear();
    readAll(serviceAuthor);
  }
  
  private void refresh(ServiceAuthor serviceAuthor)
  {
    this.al.clear();
    getModel().clear();
    getList().setModel(this.model);
    readAll(serviceAuthor);
  }
  
  private void edit()
  {
    this.firstnameTF.setEditable(true);
    this.nameTF.setEditable(true);
    this.birthTF.setEditable(true);
  }
  
  private void display()
  {
    this.lblNewLabel.setFont(this.font);
    this.lblNewLabel.setForeground(this.color1);
    this.lblNewLabel.setBounds(10, 11, 200, 14);
    getInnerEastPAN().add(this.lblNewLabel);
    this.firstnameTF = new JTextField();
    this.firstnameTF.setEditable(false);
    this.firstnameTF.setBounds(10, 36, 200, 20);
    getInnerEastPAN().add(this.firstnameTF);
    this.firstnameTF.setColumns(10);
    this.lblNewLabel_1.setForeground(this.color1);
    this.lblNewLabel_1.setFont(this.font);
    this.lblNewLabel_1.setBounds(10, 67, 200, 14);
    getInnerEastPAN().add(this.lblNewLabel_1);
    this.nameTF = new JTextField();
    this.nameTF.setEditable(false);
    this.nameTF.setBounds(10, 92, 200, 20);
    getInnerEastPAN().add(this.nameTF);
    this.nameTF.setColumns(10);
    this.lblNewLabel_2.setFont(this.font);
    this.lblNewLabel_2.setForeground(this.color1);
    this.lblNewLabel_2.setBounds(10, 123, 200, 14);
    getInnerEastPAN().add(this.lblNewLabel_2);
    this.birthTF = new JTextField();
    this.birthTF.setEditable(false);
    this.birthTF.setBounds(10, 148, 200, 20);
    getInnerEastPAN().add(this.birthTF);
    this.birthTF.setColumns(10);
  }
  
  private void readAll(ServiceAuthor serviceAuthor)
  {
    this.al = serviceAuthor.readAll();
    for (int i = 0; i < this.al.size(); i++) {
      this.model.addElement((Author)this.al.get(i));
    }
    getList().setModel(this.model);
  }
}
