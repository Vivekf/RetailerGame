import java.awt.Frame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.JPanel;



public class frame extends Frame implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0) {
		numClicks++;
        t1.setText("Button Clicked " + numClicks + " times");
	}


	public frame()
    {
		j1.add(l1);
		j1.add(t1);
		j2.add(b1);
		Frame1.add(j1);
		Frame1.add(j2);
		b1.addActionListener(this);
	    Frame1.setLayout(new FlowLayout());
	    Frame1.setSize(400,150);
	    Frame1.setVisible(true);
	    Frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	
	JFrame Frame1 = new JFrame("Test Frame");
	JPanel j2 = new JPanel();
	JButton b1 = new JButton ("Click me");
	JTextField t1 = new JTextField(20);
	JPanel j1 = new JPanel (new FlowLayout());
	JLabel l1 = new JLabel("Name here: ");
	private int numClicks =0;

}
