package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarFileActionListener implements ActionListener
{
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(MenuBar.saveMenu)) {
            System.out.println("save menu clicked");
        } else if (e.getSource().equals(MenuBar.saveAsMenu)) {
            System.out.println("save as menu clicked");
        } else if (e.getSource().equals(MenuBar.openMenu)) {
            System.out.println("open menu clicked");
        }
    }
}
