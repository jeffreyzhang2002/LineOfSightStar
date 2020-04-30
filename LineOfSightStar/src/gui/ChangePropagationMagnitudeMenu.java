package gui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class ChangePropagationMagnitudeMenu extends JFrame
{
	private static final long serialVersionUID = -4264593800370008086L;
	
	private GUI gui;
	public ChangePropagationMagnitudeMenu(GUI gui)
	{
		this.gui = gui;
	}
	
	public void showPrompt()
	{
		double value;
		String string = (String)JOptionPane.showInputDialog( this, "Enter a Postive Number",
            "Change Propagation Magnitude",
            JOptionPane.PLAIN_MESSAGE,
            null,
            null,
            gui.main.fieldContent.plane.getPropagationMagnitude() + "");
		
		try 
		{
			value = Double.parseDouble(string);
			if(value > 0)
			{
				 gui.main.fieldContent.plane.setPropagationMagnitude(value);
				 gui.main.fieldContent.updateUI();
			}
			else
				changePropagationErrorMessage(this);
		}
		catch(Exception e)
		{
			changePropagationErrorMessage(this);
		}
	}
	
	private void changePropagationErrorMessage(JFrame frame)
    {
    	JOptionPane.showMessageDialog(frame,
    		    "Propagation magnitude must be a postive number",
    		    "ERROR",
    		    JOptionPane.ERROR_MESSAGE);
    }
	
}
