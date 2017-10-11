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
import model.Catalog;
import service.ServiceCatalog;

public class FenetreCatalog
  extends Fenetre<Catalog>
{
  private static final long serialVersionUID = 1L;
  private JLabel lblNewLabel;
  private JTextField nameTF;
  private ArrayList<Catalog> al;
  private Catalog catalog;
  
  public FenetreCatalog(String titleFrame, Color color, String image, final ServiceCatalog serviceCatalog)
  {
    super(titleFrame, color, image);
    getCancelBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreCatalog.this.nameTF.setEditable(false);
      }
    });
    getDeleteBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        serviceCatalog.delete(((Catalog)FenetreCatalog.this.getList().getSelectedValue()).getId());
        FenetreCatalog.this.refresh(serviceCatalog);
      }
    });
    getOkBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreCatalog.this.valid(serviceCatalog);
      }
    });
    getUpdateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreCatalog.this.edit();
      }
    });
    getCreateBTN().addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        FenetreCatalog.this.edit();
        FenetreCatalog.this.nameTF.setText("");
      }
    });
    getList().addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent arg0)
      {
        Catalog catalog = (Catalog)FenetreCatalog.this.getList().getSelectedValue();
        try
        {
          FenetreCatalog.this.nameTF.setText(catalog.getName());
        }
        catch (Exception e)
        {
          FenetreCatalog.this.nameTF.setText("");
        }
      }
    });
    display();
    readAll(serviceCatalog);
  }
  
  private void valid(ServiceCatalog serviceCatalog)
  {
    this.nameTF.setEditable(false);
    if (getEtat() == "create") {
      create(serviceCatalog);
    } else if (getEtat() == "update") {
      update(serviceCatalog);
    }
  }
  
  private void update(ServiceCatalog serviceCatalog)
  {
    this.catalog = ((Catalog)getList().getSelectedValue());
    this.catalog.setName(this.nameTF.getText());
    serviceCatalog.update(this.catalog);
    refresh(serviceCatalog);
  }
  
  private void create(ServiceCatalog serviceCatalog)
  {
    this.catalog = new Catalog(0, this.nameTF.getText(), 0);
    serviceCatalog.create(this.catalog);
    this.al.clear();
    readAll(serviceCatalog);
  }
  
  private void refresh(ServiceCatalog serviceCatalog)
  {
    this.al.clear();
    getModel().clear();
    getList().setModel(this.model);
    readAll(serviceCatalog);
  }
  
  private void edit()
  {
    this.nameTF.setEditable(true);
  }
  
  private void display()
  {
    this.lblNewLabel = new JLabel("Name :");
    this.lblNewLabel.setFont(new Font("Tahoma", 1, 11));
    this.lblNewLabel.setForeground(Color.WHITE);
    this.lblNewLabel.setBounds(10, 11, 200, 14);
    getInnerEastPAN().add(this.lblNewLabel);
    this.nameTF = new JTextField();
    this.nameTF.setEditable(false);
    this.nameTF.setBounds(10, 36, 200, 20);
    getInnerEastPAN().add(this.nameTF);
    this.nameTF.setColumns(10);
  }
  
  private void readAll(ServiceCatalog serviceCatalog)
  {
    this.al = serviceCatalog.readAll();
    for (int i = 0; i < this.al.size(); i++) {
      this.model.addElement((Catalog)this.al.get(i));
    }
    getList().setModel(this.model);
  }
}
