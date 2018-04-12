package cn.tedu.text;

import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Test extends JFrame{
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		Pool pool = new Pool();
		frame.add(pool);
		frame.setSize(pool.bgWidth, pool.bgHeight);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pool.start();
		
	}
}
