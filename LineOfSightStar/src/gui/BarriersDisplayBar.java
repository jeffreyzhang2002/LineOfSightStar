package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Line2D;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;

public class BarriersDisplayBar extends JFrame
{
	private static final long serialVersionUID = 5055412526916410636L;

	private GUI gui;
	
	ArrayList<JButton> barrierButtons;
	JButton addBarrierButton;
	
	BarriersDisplay barrierPopUpEdit;
	AddBarrierDisplayMenu addBarrierPopUpMenu;
	
	private ButtonPushedActionListener actionListener;
	private int counter = 1;
	
	public BarriersDisplayBar(GUI gui)
	{
		super("Barriers Display");
		super.setSize(new Dimension(300,700));
		super.setLayout(new FlowLayout());
		super.setBackground(Color.WHITE);
		super.setVisible(false);
		super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		super.addWindowListener(new closingOperations());
	
		this.gui = gui; 
		barrierButtons = new ArrayList<>();
		actionListener = new ButtonPushedActionListener();
		
		addBarrierButton = new JButton("---ADD NEW BARRIER---");
		addBarrierButton.addActionListener(actionListener);
		addBarrierButton.setBackground(Color.GREEN);
		
		super.getContentPane().add(addBarrierButton);
	}
	
	public void updateBarrierButtons()
	{
		super.getContentPane().removeAll();
		super.getContentPane().add(addBarrierButton);
		barrierButtons = new ArrayList<>();
		
		counter = 1;
		for(Line2D line: gui.main.fieldContent.plane.getBarriers())
			createNewButton(line);
		super.repaint();
		super.revalidate();
	}
		
	public void addBarrier(Line2D line)
	{
		createNewButton(line);
		super.repaint();
		super.revalidate();
	}
	
	private void createNewButton(Line2D line)
	{
		JButton button = new JButton(String.format(counter++ + ": (" + (int)line.getX1() 
		+ "," + (int)line.getY1() + ") --> (" + (int)line.getX2() + "," + (int)line.getY2() + ")"));
		button.setBackground(Color.WHITE);
		button.addActionListener(actionListener);
		barrierButtons.add(button);
		super.getContentPane().add(button);
	}
	
	private class ButtonPushedActionListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(addBarrierButton))
			{
				addBarrierPopUpMenu = new AddBarrierDisplayMenu(gui);
				addBarrierPopUpMenu.setVisible(true);
			}
			else
			{
				for(int i=0; i<barrierButtons.size(); i++)
				{
					if(barrierButtons.get(i).equals(e.getSource()))
					{
						barrierButtons.get(i).setBackground(Color.yellow);
						gui.main.fieldContent.selectedBarrier = (Double) gui.main.fieldContent.plane.getBarriers().get(i);
						gui.main.fieldContent.selectedBarrierindex = i;
						gui.main.fieldContent.updateUI();
						barrierPopUpEdit = new BarriersDisplay(gui,i);
						barrierPopUpEdit.setVisible(true);
					}
					else
					{
						barrierButtons.get(i).setBackground(Color.WHITE);
					}
				}
			}
		}
	}
	
	private class closingOperations extends WindowAdapter
	{
		public void windowClosing(WindowEvent e) {
            gui.main.fieldContent.editingBarrierMode = false;
        }
	}
}
