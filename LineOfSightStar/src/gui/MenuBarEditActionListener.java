package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarEditActionListener implements ActionListener
{
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(MenuBar.gridSizeMenu)) {
            System.out.println("grid size menu clicked");
        } else if (e.getSource().equals(MenuBar.gridBackgroundMenu)) {
            System.out.println("grid background menu clicked");
        } else if (e.getSource().equals(MenuBar.addBarrierMenu)){
        	FieldDisplay.canEditLine = true;
        } else if (e.getSource().equals(MenuBar.setStartMenu)){
        	FieldDisplay.canSetStart = true;
        } else if (e.getSource().equals(MenuBar.setEndMenu)){
        	FieldDisplay.canSetEnd = true;
        }
    }
}
