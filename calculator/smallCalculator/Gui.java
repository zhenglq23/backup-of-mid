import javax.swing.*;
import javax.swing.JLabel; 

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gui{
 
	public static void main(String[] args) {
		// 创建 JFrame 实例
        	JFrame frame = new JFrame("Login Example");
        	// Setting the width and height of frame
        	frame.setSize(500, 200);
        	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        	
        	JPanel panel = new JPanel();    
        	// 添加面板
        	frame.add(panel);
        	/* 
         	* 调用用户定义的方法并添加组件到面板
         	*/
        	placeComponents(panel);

        	// 设置界面可见
        	frame.setVisible(true);
	}
	
	private static void placeComponents(JPanel panel) {
		panel.setLayout(null);
		// create texure
		JTextField x1 = new JTextField(10);
        	x1.setBounds(0,0,100,100);
        	panel.add(x1);
        	JTextField x2 = new JTextField(10);
        	x2.setBounds(200,0,100,100);
        	panel.add(x2);
        	
		// create labels
		JLabel op = new JLabel("+");
		op.setBounds(100,0,100,100);
		panel.add(op);
		JLabel re = new JLabel(" ");
		re.setBounds(400,0,100,100);
		panel.add(re);
		final JLabel equ = new JLabel("=");
		equ.setBounds(300,0,100,100);
		panel.add(equ);
		
		// create buttons
        	JButton addBut = new JButton("+");
        	addBut.setBounds(0, 100, 100, 100);
        	panel.add(addBut);
        	JButton subBut = new JButton("-");
        	subBut.setBounds(100, 100, 100, 100);
        	panel.add(subBut);
        	JButton mulBut = new JButton("*");
        	mulBut.setBounds(200, 100, 100, 100);
        	panel.add(mulBut);
        	JButton divBut = new JButton("/");
        	divBut.setBounds(300, 100, 100, 100);
        	panel.add(divBut);
        	JButton okBut = new JButton("OK");
        	okBut.setBounds(400, 100, 100, 100);
        	panel.add(okBut);
        	
        	// click to change op
        	addBut.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) { 
		op.setText("+");}
		});
        	subBut.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) { 
		op.setText("-");}
		});
		mulBut.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) { 
		op.setText("*");}
		});
		divBut.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) { 
		op.setText("/");}
		});
		// click OK to calculate the result
		okBut.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) { 
		op.setText("OK");}
		});
		
	}
 }
 

