package org.geometryTester;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import cmn.utilslib.math.geometry.AABB2f;
import cmn.utilslib.math.geometry.Circle2f;
import cmn.utilslib.math.geometry.Point2f;

public class MainWindow extends JFrame
{

	private DrawArea panel = new DrawArea(); 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MainWindow()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setLayout(null);
		
		setSize(600, 800);
		
		setVisible(true);
		
		panel.setSize(600, 800);
		
		panel.setLocation(0, 0);
		
		add(panel);
		
		repaint();
	}
	
}
