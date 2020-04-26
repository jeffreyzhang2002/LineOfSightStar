package gui;

import javax.swing.*;

public class MenuBar extends JMenuBar
{
	private static final long serialVersionUID = 1L;
	
	static final JMenu fileMenu = new JMenu("File");
        static final JMenuItem saveMenu = new JMenuItem("Save");
        static final JMenuItem saveAsMenu = new JMenuItem("Save As");
        static final JMenuItem openMenu = new JMenuItem("Open");
    static final JMenu editMenu = new JMenu("Edit");
        static final JMenu gridMenu = new JMenu("Grid");
            static final JMenuItem gridSizeMenu = new JMenuItem("Propagation Radius");
            static final JMenuItem gridBackgroundMenu = new JMenuItem("Background");
        static final JMenuItem addBarrierMenu = new JMenuItem("Add Barrier");
        static final JMenuItem setStartMenu = new JMenuItem("Set Start");
        static final JMenuItem setEndMenu = new JMenuItem("Set End");
        static final JMenuItem clearGridMenu = new JMenuItem("clear Grid");
    static final JMenu runMenu = new JMenu("Run");
    	static final JMenuItem generatePathMenu = new JMenuItem("Generate Path");
        static final JMenuItem simulationMenu = new JMenuItem("Simulation");
        static final JMenuItem stepMenu = new JMenuItem("Step");

    public MenuBar()
    {
        super.add(fileMenu);
            fileMenu.add(saveMenu);
            fileMenu.add(saveAsMenu);
            fileMenu.add(openMenu);
        super.add(editMenu);
            editMenu.add(gridMenu);
                gridMenu.add(gridSizeMenu);
                gridMenu.add(gridBackgroundMenu);
            editMenu.add(addBarrierMenu);
            editMenu.add(setStartMenu);
            editMenu.add(setEndMenu);
            editMenu.add(clearGridMenu);
        super.add(runMenu);
        	runMenu.add(generatePathMenu);
            runMenu.add(simulationMenu);
            runMenu.add(stepMenu);

       initializeActionListeners();
    }

    private void initializeActionListeners()
    {
        MenuBarFileActionListener fileActionListener = new MenuBarFileActionListener();
        saveMenu.addActionListener(fileActionListener);
        saveAsMenu.addActionListener(fileActionListener);
        openMenu.addActionListener(fileActionListener);

        MenuBarEditActionListener editActionListener = new MenuBarEditActionListener();
        gridSizeMenu.addActionListener(editActionListener);
        gridBackgroundMenu.addActionListener(editActionListener);
        addBarrierMenu.addActionListener(editActionListener);
        setStartMenu.addActionListener(editActionListener);
        setEndMenu.addActionListener(editActionListener);
        clearGridMenu.addActionListener(editActionListener);

        MenuBarRunActionListener runActionListener = new MenuBarRunActionListener();
        generatePathMenu.addActionListener(runActionListener);
        simulationMenu.addActionListener(runActionListener);
        stepMenu.addActionListener(runActionListener);
    }
}
