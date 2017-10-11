package ui;

import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import model.Subscriber;
import service.ServiceSubscriber;

public class FenetreSubscriber
  extends Fenetre
{
  private JTextField firstnameTF;
  private JTextField nameTF;
  private JTextField numberTF;
  private ArrayList<Subscriber> al;
  
  public FenetreSubscriber(String titleFrame, Color color, String image, final ServiceSubscriber serviceSubscriber)
  {
    super(titleFrame, color, image);
    getOkBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FenetreSubscriber.this.firstnameTF.setEditable(false);
        FenetreSubscriber.this.nameTF.setEditable(false);
        if (FenetreSubscriber.this.getEtat() == "create")
        {
          FenetreSubscriber.this.create(serviceSubscriber);
        }
        else if (FenetreSubscriber.this.getEtat() == "update")
        {
          FenetreSubscriber.this.update(serviceSubscriber);
          FenetreSubscriber.this.refresh(serviceSubscriber);
        }
      }
    });
    getDeleteBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        FenetreSubscriber.this.delete(serviceSubscriber);
        FenetreSubscriber.this.refresh(serviceSubscriber);
      }
    });
    getCreateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreSubscriber.this.edit();
      }
    });
    getUpdateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreSubscriber.this.edit();
      }
    });
    getList().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent e)
      {
        Subscriber subscriber = (Subscriber)FenetreSubscriber.this.getList().getSelectedValue();
        try
        {
          FenetreSubscriber.this.firstnameTF.setText(subscriber.getFirstname());
          FenetreSubscriber.this.nameTF.setText(subscriber.getName());
          FenetreSubscriber.this.numberTF.setText(String.valueOf(subscriber.getId_subscriber()));
        }
        catch (Exception e1)
        {
          FenetreSubscriber.this.firstnameTF.setText("");
          FenetreSubscriber.this.nameTF.setText("");
          FenetreSubscriber.this.numberTF.setText("");
        }
      }
    });
    display();
    
    readAll(serviceSubscriber);
  }
  
  protected void refresh(ServiceSubscriber serviceSubscriber)
  {
    this.al.clear();
    getModel().clear();
    getList().setModel(this.model);
    readAll(serviceSubscriber);
  }
  
  protected void update(ServiceSubscriber serviceSubscriber)
  {
    Subscriber subscriber = (Subscriber)getList().getSelectedValue();
    subscriber.setFirstname(this.firstnameTF.getText());
    subscriber.setName(this.nameTF.getText());
    serviceSubscriber.update(subscriber);
  }
  
  protected void create(ServiceSubscriber serviceSubscriber)
  {
    Subscriber subscriber = new Subscriber(0, this.firstnameTF.getText(), this.nameTF.getText(), 0);
    serviceSubscriber.create(subscriber);
    this.al.clear();
    readAll(serviceSubscriber);
  }
  
  protected void delete(ServiceSubscriber serviceSubscriber)
  {
    Subscriber subscriber = (Subscriber)getList().getSelectedValue();
    int subscriberDeleteID = subscriber.getId_subscriber();
    serviceSubscriber.delete(subscriberDeleteID);
  }
  
  protected void edit()
  {
    this.firstnameTF.setEditable(true);
    this.nameTF.setEditable(true);
  }
  
  private void display()
  {
    JLabel lblNewLabel = new JLabel("Firstname :");
    lblNewLabel.setFont(new Font("Tahoma", 1, 11));
    lblNewLabel.setForeground(Color.WHITE);
    lblNewLabel.setBounds(10, 11, 200, 14);
    getInnerEastPAN().add(lblNewLabel);
    
    this.firstnameTF = new JTextField();
    this.firstnameTF.setEditable(false);
    this.firstnameTF.setBounds(10, 36, 200, 20);
    getInnerEastPAN().add(this.firstnameTF);
    this.firstnameTF.setColumns(10);
    
    JLabel lblName = new JLabel("Name :");
    lblName.setForeground(Color.WHITE);
    lblName.setFont(new Font("Tahoma", 1, 11));
    lblName.setBounds(10, 67, 200, 14);
    getInnerEastPAN().add(lblName);
    
    this.nameTF = new JTextField();
    this.nameTF.setEditable(false);
    this.nameTF.setColumns(10);
    this.nameTF.setBounds(10, 92, 200, 20);
    getInnerEastPAN().add(this.nameTF);
    
    JLabel lblNSubscriber = new JLabel("N� subscriber :");
    lblNSubscriber.setForeground(Color.WHITE);
    lblNSubscriber.setFont(new Font("Tahoma", 1, 11));
    lblNSubscriber.setBounds(10, 123, 200, 14);
    getInnerEastPAN().add(lblNSubscriber);
    
    this.numberTF = new JTextField();
    this.numberTF.setEditable(false);
    this.numberTF.setColumns(10);
    this.numberTF.setBounds(10, 148, 200, 20);
    getInnerEastPAN().add(this.numberTF);
  }
  
  private void readAll(ServiceSubscriber serviceSubscriber)
  {
    this.al = serviceSubscriber.readAll();
    for (int i = 0; i < this.al.size(); i++) {
      this.model.addElement(this.al.get(i));
    }
    getList().setModel(this.model);
  }
}
