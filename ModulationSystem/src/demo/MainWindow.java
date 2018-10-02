package demo;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.mod.DrawArea;

public class MainWindow extends JFrame
{

	private static final long serialVersionUID = 1L;

	private DrawArea drawArea = new DrawArea();	
	
	private ConstantInputElement in = new ConstantInputElement(1);
	
	private DefaultOutputElement out = new DefaultOutputElement(10);
	
	private TestElement e = new TestElement(1); 
	
	private JButton b = new JButton();
	
	public MainWindow()
	{
		setSize(200, 200);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setVisible(true);
		
		
		this.drawArea.setLayout(null);
		this.drawArea.setLocation(0, 0);
		this.drawArea.setSize(400,400);
		this.drawArea.setBackground(Color.CYAN);
		this.drawArea.addDragable(this.in);
		this.drawArea.addDragable(this.out);
		this.drawArea.addDragable(this.e);
		this.drawArea.setVisible(true);
		
		add(this.drawArea);
		
		this.b.setLocation(500, 0);
		this.b.setSize(150, 50);
		this.b.addActionListener((a) -> { System.out.println(this.out.getDataFromDock(this.out.getInputDock(0))); });
		
		add(this.b);
	}
}
