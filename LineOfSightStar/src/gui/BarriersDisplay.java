package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Line2D;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BarriersDisplay extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	private GUI gui;
	JButton deleteButton;
	JButton enterButton;
	JTextField x1Prompt, y1Prompt, x2Prompt, y2Prompt;
	JTextField textPrompt;
	JTextField x1Input, y1Input, x2Input, y2Input;
	JPanel textDisplay;
	
	Line2D barrier;
	int x1, y1, x2, y2;
	int index;
		
	public BarriersDisplay(GUI gui, int index)
	{
		this.gui = gui;
		this.barrier = gui.main.fieldContent.plane.getBarriers().get(index);
		this.index = index;
		x1 = (int)barrier.getX1();
		y1 = (int)barrier.getY1();
		x2 = (int)barrier.getX2();
		y2 = (int)barrier.getY2();
		
		super.setTitle("Edit Barrier");
		super.setBackground(Color.WHITE);
		super.setLayout(new GridLayout(4,1));
		super.setSize(new Dimension(500,300));
		super.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		textDisplay = new JPanel();
		textDisplay.setBackground(Color.WHITE);
		textDisplay.setLayout(new GridLayout(2,4));
		
		deleteButton = new JButton("Delete");
		deleteButton.setBackground(Color.WHITE);
		deleteButton.setForeground(Color.RED);
		
		enterButton = new JButton("Enter");
		enterButton.setBackground(Color.WHITE);
		enterButton.setForeground(Color.GREEN);
		
		textPrompt = new JTextField("Enter Coordinates");
		textPrompt.setEditable(false);
		
		x1Prompt = new JTextField("X1:");
		x1Prompt.setEditable(false);
		
		y1Prompt = new JTextField("Y1:");
		y1Prompt.setEditable(false);
		
		x2Prompt = new JTextField("X2:");
		x2Prompt.setEditable(false);
		
		y2Prompt = new JTextField("Y2:");
		y2Prompt.setEditable(false);
		
		x1Input = new JTextField(""+barrier.getX1());
		x2Input = new JTextField(""+barrier.getX2());
		y1Input = new JTextField(""+barrier.getY1());
		y2Input = new JTextField(""+barrier.getY2());
		
		textDisplay.add(x1Prompt);
		textDisplay.add(x1Input);
		textDisplay.add(y1Prompt);
		textDisplay.add(y1Input);
		textDisplay.add(x2Prompt);
		textDisplay.add(x2Input);
		textDisplay.add(y2Prompt);
		textDisplay.add(y2Input);
		
		super.getContentPane().add(textPrompt);
		super.getContentPane().add(textDisplay);
		super.getContentPane().add(deleteButton);
		super.getContentPane().add(enterButton);
		
		inputActionListener l = new inputActionListener();
		
		enterButton.addActionListener(l);
		deleteButton.addActionListener(l);
		x1Input.addActionListener(l);
		y1Input.addActionListener(l);
		x2Input.addActionListener(l);
		y2Input.addActionListener(l);
	}
	
	private void closeWindow()
	{
		super.dispose();
	}
	
	private class inputActionListener implements ActionListener
	{
		
		public void actionPerformed(ActionEvent e) 
		{
			try
			{
				if(e.getSource().equals(x1Input))
					x1 = Integer.parseInt(x1Input.getText());
				else if(e.getSource().equals(y1Input))
					y1 = Integer.parseInt(y1Input.getText());
				else if(e.getSource().equals(x2Input))
					x2 = Integer.parseInt(x2Input.getText());
				else if(e.getSource().equals(y2Input))
					y2 = Integer.parseInt(y2Input.getText());
				barrier.setLine(x1, y1, x2, y2);
				gui.main.fieldContent.plane.recalculatePropagatedPoints();
				gui.main.fieldContent.updateUI();
				enterButton.setEnabled(true);
			} 
			catch (Exception error)
			{
				enterButton.setEnabled(false);
			}
			
			if(e.getSource().equals(enterButton))
			{
				barrier.setLine(x1, y1, x2, y2);
				gui.main.barriersContent.updateBarrierButtons();
				gui.main.barriersContent.barrierButtons.get(index).setBackground(Color.YELLOW);
				closeWindow();
			} 
			else if(e.getSource().equals(deleteButton))
			{
				gui.main.fieldContent.plane.removeBarrier(index);
				gui.main.fieldContent.selectedBarrier = null;
				gui.main.barriersContent.updateBarrierButtons();
				closeWindow();
			}
			
			
		}
		
	}
}
