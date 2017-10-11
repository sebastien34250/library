package app;

import ui.FenetreMenu;

public class Main
{
  public static void main(String[] args)
  {
    FenetreMenu fenetreMenu = new FenetreMenu();
    fenetreMenu.setResizable(false);
    fenetreMenu.setTitle("Library");
    fenetreMenu.setVisible(true);
    fenetreMenu.setDefaultCloseOperation(3);
  }
}
