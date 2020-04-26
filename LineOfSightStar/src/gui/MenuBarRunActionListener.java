package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuBarRunActionListener implements ActionListener
{
    public void actionPerformed(ActionEvent e) {
    	if(e.getSource().equals(MenuBar.generatePathMenu)){
    		if(FieldDisplay.start != null && FieldDisplay.end != null)
    		{
    			FieldDisplay.pathfinder.setStart(FieldDisplay.start);
    			FieldDisplay.pathfinder.setEnd(FieldDisplay.end);
    			FieldDisplay.path = FieldDisplay.pathfinder.generatePath(FieldDisplay.propagationMagnitude);
    		}
    		else
    		{
    			FieldDisplay.unableToGeneratePathErrorMessage();
    		}
    	} else if(e.getSource().equals(MenuBar.simulationMenu)) {
            System.out.println("simulation menu clicked");
        } else if(e.getSource().equals(MenuBar.stepMenu)) {
            System.out.println("step menu clicked");
        }
    }
}
