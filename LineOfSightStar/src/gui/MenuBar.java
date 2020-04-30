package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = -8982224271776149659L;
	
	private GUI gui;
	
	JMenu fileMenu = new JMenu("File");
		JMenu saveMenuTop = new JMenu("Save");
			JMenuItem saveMenu = new JMenuItem("Save");
			JMenuItem saveAsMenu = new JMenuItem("Save As");
		JMenuItem openMenu = new JMenuItem("Open");
	
	
	JMenu editMenu = new JMenu("Edit");
		JMenu gridMenu = new JMenu("Grid");
			JMenuItem gridSizeMenu = new JMenuItem("Dimensions");
			JMenuItem gridBackgroundMenu = new JMenuItem("Background");
			JMenuItem clearGridMenu = new JMenuItem("clear Grid");
		JMenu barrierMenu = new JMenu("Barrier");
			JMenuItem addBarrierMenu = new JMenuItem("Add Barrier");
			JMenuItem setPropagationRadiusMenu = new JMenuItem("Set Propagation Radius");
		JMenu pathMenu = new JMenu("Path");
			JMenuItem setStartMenu = new JMenuItem("Set Start");
			JMenuItem setEndMenu = new JMenuItem("Set End");
			JMenuItem addWayPointMenu = new JMenuItem("Add Waypoint");
			
	JMenu runMenu = new JMenu("Run");
		JMenuItem generateOptimalPathMenu = new JMenuItem("Generate Path");
		JMenuItem generateSplineOptimzationMenu = new JMenuItem("gnerate path splines");
	    JMenuItem simulationMenu = new JMenuItem("Simulation");
	    JMenuItem stepMenu = new JMenuItem("Step");
	
	public MenuBar(GUI gui)
	{ 
		this.gui = gui; 
		initalizeMenuBar();
		initalizeActionListeners();
	}
	
	private void initalizeMenuBar()
	{
		 super.add(fileMenu);
		 	fileMenu.add(saveMenuTop);
         		saveMenuTop.add(saveMenu);
         		saveMenuTop.add(saveAsMenu);
         	fileMenu.add(openMenu);
         	         	
     	super.add(editMenu);
     		editMenu.add(gridMenu);
     			gridMenu.add(gridSizeMenu);
     			gridMenu.add(gridBackgroundMenu);
     			gridMenu.add(clearGridMenu);
     		editMenu.add(barrierMenu);
     			barrierMenu.add(addBarrierMenu);
     			barrierMenu.add(setPropagationRadiusMenu);
 			editMenu.add(pathMenu);
 				pathMenu.add(addWayPointMenu);
 				pathMenu.add(setStartMenu);
 				pathMenu.add(setEndMenu);
     
 		super.add(runMenu);
     		runMenu.add(generateOptimalPathMenu);
     		runMenu.add(generateSplineOptimzationMenu);
     		runMenu.add(simulationMenu);
     		runMenu.add(stepMenu);
	}
	
	private void initalizeActionListeners()
	{
		FileActionListener fileListener = new FileActionListener();
		saveMenu.addActionListener(fileListener);
 		saveAsMenu.addActionListener(fileListener);
 		openMenu.addActionListener(fileListener);
 		
 		EditActionListener editListener = new EditActionListener();
 		gridSizeMenu.addActionListener(editListener);
 		gridBackgroundMenu.addActionListener(editListener);
 		clearGridMenu.addActionListener(editListener);
 		addBarrierMenu.addActionListener(editListener);
 		setPropagationRadiusMenu.addActionListener(editListener);
 		addWayPointMenu.addActionListener(editListener);
 		setStartMenu.addActionListener(editListener);
 		setEndMenu.addActionListener(editListener);
 		
 		RunActionListener runListener = new RunActionListener();
 		generateOptimalPathMenu.addActionListener(runListener);
 		generateSplineOptimzationMenu.addActionListener(runListener);
 		simulationMenu.addActionListener(runListener);
 		stepMenu.addActionListener(runListener);
	}
	
	private class FileActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(saveMenu))
				System.out.println("Save Menu Pressed");
			else if(e.getSource().equals(saveAsMenu))
				System.out.println("Save As Menu Pressed");
			else if(e.getSource().equals(openMenu))
				System.out.println("Open Menu");
		}
	}
	
	private class EditActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(gridSizeMenu))
				System.out.println("set grid size menu");
			else if(e.getSource().equals(gridBackgroundMenu))
				System.out.println("set background menu");
			else if(e.getSource().equals(clearGridMenu))
				System.out.println("clear background menu");
			else if(e.getSource().equals(addBarrierMenu))
			{
				gui.main.fieldContent.editingBarrierMode = true;
				gui.main.barriersContent.setVisible(true);
			}
			else if(e.getSource().equals(setPropagationRadiusMenu))
			{
				ChangePropagationMagnitudeMenu menu = new ChangePropagationMagnitudeMenu(gui);
				menu.showPrompt();
			}
			else if(e.getSource().equals(addWayPointMenu))
				System.out.println("add way point menu");
			else if(e.getSource().equals(setStartMenu))
				System.out.println("set start menu");
			else if(e.getSource().equals(stepMenu))
				System.out.println("Set end Menu");
		}
	}
	
	private class RunActionListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e) 
		{
			if(e.getSource().equals(generateOptimalPathMenu))
				System.out.println("generate optimal path");
			else if(e.getSource().equals(generateSplineOptimzationMenu))
				System.out.println("generate splines");
			else if(e.getSource().equals(simulationMenu))
				System.out.println("simulation menu");
			else if(e.getSource().equals(stepMenu))
				System.out.println("step");
		}
	}
}
