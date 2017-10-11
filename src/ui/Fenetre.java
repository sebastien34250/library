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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Fenetre<E>
  extends JDialog
{
  private static final long serialVersionUID = 1L;
  protected DefaultListModel<E> model = new DefaultListModel();
  protected DefaultListModel<E> modelResearch = new DefaultListModel();
  private JPanel panel_1;
  private JTextField researchTF;
  private Button updateBTN;
  private Button deleteBTN;
  private Button okBTN;
  private Button cancelBTN;
  private String etat;
  private Button createBTN;
  protected int first = 0;
  private String titleFrame;
  private Color color;
  private String image;
  private JList<E> list;
  private JPanel inEastPAN;
  private JPanel eastPAN;
  private JPanel northPAN;
  private JPanel panel;
  private JPanel innerEastPAN;
  private JLabel label_1 = new JLabel();
  private String research;
  
  public Fenetre(String titleFrame, Color color, String image)
  {
    setLocation(new Point(400, 150));
    
    setModal(true);
    setSize(790, 790);
    getContentPane().setLayout(null);
    
    this.panel = new JPanel();
    this.panel.setOpaque(false);
    this.panel.setBounds(0, 100, 400, 662);
    getContentPane().add(this.panel);
    SpringLayout sl_panel = new SpringLayout();
    this.panel.setLayout(sl_panel);
    
    this.panel_1 = new JPanel();
    this.panel_1.setBorder(null);
    this.panel_1.setBackground(color);
    sl_panel.putConstraint("North", this.panel_1, 30, "North", this.panel);
    sl_panel.putConstraint("West", this.panel_1, 30, "West", this.panel);
    sl_panel.putConstraint("South", this.panel_1, -30, "South", this.panel);
    sl_panel.putConstraint("East", this.panel_1, -30, "East", this.panel);
    this.panel.add(this.panel_1);
    this.panel_1.setLayout(null);
    
    this.label_1 = new JLabel("Research :");
    this.label_1.setForeground(new Color(255, 255, 255));
    this.label_1.setBounds(10, 11, 320, 14);
    this.panel_1.add(this.label_1);
    
    this.researchTF = new JTextField();
    this.researchTF.addMouseListener(new MouseAdapter()
    {
      public void mouseClicked(MouseEvent arg0)
      {
        System.out.println("hey");
        Fenetre.this.deleteBTN.setEnabled(false);
        Fenetre.this.updateBTN.setEnabled(false);
        Fenetre.this.list.setModel(Fenetre.this.model);
      }
    });
    this.researchTF.addKeyListener(new KeyAdapter()
    {
      public void keyReleased(KeyEvent arg0)
      {
        Fenetre.this.setResearch(Fenetre.this.researchTF.getText());
        Fenetre.this.modelResearch.clear();
        for (int i = 0; i < Fenetre.this.model.size(); i++) {
          if (Fenetre.this.model.get(i).toString().toLowerCase().indexOf(Fenetre.this.getResearch().toLowerCase()) != -1) {
            Fenetre.this.modelResearch.addElement(Fenetre.this.model.get(i));
          }
        }
        Fenetre.this.list.setModel(Fenetre.this.modelResearch);
      }
    });
    this.researchTF.setColumns(10);
    this.researchTF.setBounds(10, 36, 225, 20);
    this.panel_1.add(this.researchTF);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(10, 67, 320, 524);
    this.panel_1.add(scrollPane);
    
    this.list = new JList();
    this.list.addListSelectionListener(new ListSelectionListener()
    {
      public void valueChanged(ListSelectionEvent arg0)
      {
        if (!Fenetre.this.list.isSelectionEmpty())
        {
          Fenetre.this.updateBTN.setEnabled(true);
          Fenetre.this.deleteBTN.setEnabled(true);
        }
      }
    });
    scrollPane.setViewportView(this.list);
    
    this.northPAN = new JPanel();
    this.northPAN.setOpaque(false);
    this.northPAN.setBorder(new MatteBorder(0, 0, 2, 0, color));
    this.northPAN.setBounds(0, 0, 784, 101);
    getContentPane().add(this.northPAN);
    SpringLayout sl_northPAN = new SpringLayout();
    this.northPAN.setLayout(sl_northPAN);
    
    JLabel titleLBL = new JLabel(titleFrame);
    sl_northPAN.putConstraint("North", titleLBL, 10, "North", this.northPAN);
    sl_northPAN.putConstraint("East", titleLBL, -29, "East", this.northPAN);
    titleLBL.setForeground(color);
    this.northPAN.add(titleLBL);
    titleLBL.setFont(new Font("Papyrus", 1, 49));
    
    this.eastPAN = new JPanel();
    this.eastPAN.setOpaque(false);
    this.eastPAN.setBorder(new MatteBorder(0, 2, 0, 0, color));
    this.eastPAN.setBounds(406, 100, 378, 662);
    getContentPane().add(this.eastPAN);
    SpringLayout sl_eastPAN = new SpringLayout();
    this.eastPAN.setLayout(sl_eastPAN);
    
    this.inEastPAN = new JPanel();
    this.inEastPAN.setBorder(null);
    this.inEastPAN.setOpaque(false);
    sl_eastPAN.putConstraint("North", this.inEastPAN, 30, "North", this.eastPAN);
    sl_eastPAN.putConstraint("West", this.inEastPAN, 30, "West", this.eastPAN);
    sl_eastPAN.putConstraint("South", this.inEastPAN, -29, "South", this.eastPAN);
    sl_eastPAN.putConstraint("East", this.inEastPAN, -30, "East", this.eastPAN);
    this.eastPAN.add(this.inEastPAN);
    this.inEastPAN.setLayout(null);
    
    this.createBTN = new Button("CREATE");
    this.createBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent arg0)
      {
        Fenetre.this.list.setEnabled(false);
        Fenetre.this.etat = "create";
        Fenetre.this.fulfill2();
      }
    });
    this.createBTN.setForeground(new Color(255, 255, 255));
    this.createBTN.setBackground(color);
    this.createBTN.setBounds(10, 10, 90, 25);
    this.inEastPAN.add(this.createBTN);
    
    this.updateBTN = new Button("UPDATE");
    this.updateBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Fenetre.this.list.setEnabled(false);
        Fenetre.this.etat = "update";
        Fenetre.this.fulfill2();
      }
    });
    this.updateBTN.setEnabled(false);
    this.updateBTN.setForeground(new Color(255, 255, 255));
    this.updateBTN.setBackground(color);
    this.updateBTN.setBounds(10, 41, 90, 25);
    this.inEastPAN.add(this.updateBTN);
    
    this.deleteBTN = new Button("DELETE");
    this.deleteBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Fenetre.this.updateBTN.setEnabled(false);
        Fenetre.this.deleteBTN.setEnabled(false);
      }
    });
    this.deleteBTN.setEnabled(false);
    this.deleteBTN.setForeground(new Color(255, 255, 255));
    this.deleteBTN.setBackground(color);
    this.deleteBTN.setBounds(10, 72, 90, 25);
    this.inEastPAN.add(this.deleteBTN);
    
    this.innerEastPAN = new JPanel();
    this.innerEastPAN.setBackground(new Color(0, 0, 0));
    this.innerEastPAN.setBorder(new LineBorder(new Color(0, 0, 0)));
    this.innerEastPAN.setBounds(10, 129, 298, 463);
    this.inEastPAN.add(this.innerEastPAN);
    this.innerEastPAN.setLayout(null);
    
    this.okBTN = new Button("OK");
    this.okBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Fenetre.this.fulfill();
        Fenetre.this.list.setEnabled(true);
        Fenetre.this.model = new DefaultListModel();
        Fenetre.this.updateBTN.setEnabled(false);
        Fenetre.this.deleteBTN.setEnabled(false);
      }
    });
    this.okBTN.setVisible(false);
    this.okBTN.setForeground(new Color(255, 255, 255));
    this.okBTN.setBackground(color);
    this.okBTN.setBounds(100, 428, 90, 25);
    this.innerEastPAN.add(this.okBTN);
    
    this.cancelBTN = new Button("Cancel");
    this.cancelBTN.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e)
      {
        Fenetre.this.fulfill();
        Fenetre.this.list.setEnabled(true);
      }
    });
    this.cancelBTN.setVisible(false);
    this.cancelBTN.setForeground(new Color(255, 255, 255));
    this.cancelBTN.setBackground(color);
    this.cancelBTN.setBounds(198, 428, 90, 25);
    this.innerEastPAN.add(this.cancelBTN);
    
    JLabel label = new JLabel("");
    
    label.setIcon(new ImageIcon(image));
    label.setBounds(-109, 0, 1148, 971);
    getContentPane().add(label);
  }
  
  public DefaultListModel<E> getModel()
  {
    return this.model;
  }
  
  public void setModel(DefaultListModel<E> model)
  {
    this.model = model;
  }
  
  public JPanel getPanel_1()
  {
    return this.panel_1;
  }
  
  public JPanel getInEastPAN()
  {
    return this.inEastPAN;
  }
  
  public void setInEastPAN(JPanel inEastPAN)
  {
    this.inEastPAN = inEastPAN;
  }
  
  public JPanel getEastPAN()
  {
    return this.eastPAN;
  }
  
  public void setEastPAN(JPanel eastPAN)
  {
    this.eastPAN = eastPAN;
  }
  
  public JPanel getNorthPAN()
  {
    return this.northPAN;
  }
  
  public void setNorthPAN(JPanel northPAN)
  {
    this.northPAN = northPAN;
  }
  
  public JPanel getPanel()
  {
    return this.panel;
  }
  
  public void setPanel(JPanel panel)
  {
    this.panel = panel;
  }
  
  public JPanel getInnerEastPAN()
  {
    return this.innerEastPAN;
  }
  
  public void setInnerEastPAN(JPanel innerEastPAN)
  {
    this.innerEastPAN = innerEastPAN;
  }
  
  public JLabel getLabel_1()
  {
    return this.label_1;
  }
  
  public void setLabel_1(JLabel label_1)
  {
    this.label_1 = label_1;
  }
  
  public void setPanel_1(JPanel panel_1)
  {
    this.panel_1 = panel_1;
  }
  
  public JTextField getResearchTF()
  {
    return this.researchTF;
  }
  
  public void setResearchTF(JTextField researchTF)
  {
    this.researchTF = researchTF;
  }
  
  public Button getUpdateBTN()
  {
    return this.updateBTN;
  }
  
  public void setUpdateBTN(Button updateBTN)
  {
    this.updateBTN = updateBTN;
  }
  
  public Button getDeleteBTN()
  {
    return this.deleteBTN;
  }
  
  public void setDeleteBTN(Button deleteBTN)
  {
    this.deleteBTN = deleteBTN;
  }
  
  public Button getOkBTN()
  {
    return this.okBTN;
  }
  
  public void setOkBTN(Button okBTN)
  {
    this.okBTN = okBTN;
  }
  
  public Button getCancelBTN()
  {
    return this.cancelBTN;
  }
  
  public void setCancelBTN(Button cancelBTN)
  {
    this.cancelBTN = cancelBTN;
  }
  
  public String getEtat()
  {
    return this.etat;
  }
  
  public void setEtat(String etat)
  {
    this.etat = etat;
  }
  
  public Button getCreateBTN()
  {
    return this.createBTN;
  }
  
  public void setCreateBTN(Button createBTN)
  {
    this.createBTN = createBTN;
  }
  
  public int getFirst()
  {
    return this.first;
  }
  
  public void setFirst(int first)
  {
    this.first = first;
  }
  
  public String getTitleFrame()
  {
    return this.titleFrame;
  }
  
  public void setTitleFrame(String titleFrame)
  {
    this.titleFrame = titleFrame;
  }
  
  public Color getColor()
  {
    return this.color;
  }
  
  public void setColor(Color color)
  {
    this.color = color;
  }
  
  public String getImage()
  {
    return this.image;
  }
  
  public void setImage(String image)
  {
    this.image = image;
  }
  
  public JList<E> getList()
  {
    return this.list;
  }
  
  public void setList(JList<E> list)
  {
    this.list = list;
  }
  
  public String getResearch()
  {
    return this.research;
  }
  
  public void setResearch(String research)
  {
    this.research = research;
  }
  
  protected void fulfill2()
  {
    this.createBTN.setEnabled(false);
    this.updateBTN.setEnabled(false);
    this.deleteBTN.setEnabled(false);
    this.okBTN.setVisible(true);
    this.cancelBTN.setVisible(true);
  }
  
  protected void fulfill()
  {
    this.createBTN.setEnabled(true);
    this.updateBTN.setEnabled(false);
    this.deleteBTN.setEnabled(false);
    this.okBTN.setVisible(false);
    this.cancelBTN.setVisible(false);
  }
}
