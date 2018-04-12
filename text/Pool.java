package cn.tedu.text;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/*鱼池
 *    bg, width, height
 *    很多鱼
 *    网
 */
public class Pool extends JPanel implements MouseListener, MouseMotionListener{
	BufferedImage bg;
	int bgWidth;
	int bgHeight;
	static int score = 1000;
	
	Fish[] fishes = new Fish[22];
	Net net = new Net();
	
	public Pool(){
		try {
			bg = ImageIO.read(new File("img/bg.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bgWidth = bg.getWidth();//800
		bgHeight = bg.getHeight();//480
			
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		for(int i = 0; i < 9; i++){
			fishes[i] = new Fish("fish0" + (i + 1));
			fishes[i + 9] = new Fish("fish0" + (i + 1));
		}
		
		fishes[18] = new Fish("fish13");
		fishes[19] = new Fish("fish13");
		fishes[20] = new Fish("fish14");
		fishes[21] = new Fish("fish14");
	}
	
	public int getScore(){
		return score;
	}
	
	public void start(){
		for(int i = 0; i < fishes.length; i++){
			Fish fish = fishes[i];
			Thread th = new Thread(fish);
			th.start();
		}
		while(true){
			repaint();
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//绘制鱼池
		g.drawImage(bg, 0, 0, bgWidth, bgHeight, null);
		for(int i = 0; i < fishes.length; i++){
			Fish fish = fishes[i];
			g.drawImage(fish.nowFish, fish.x, fish.y, fish.width, fish.height, null);
		}
		//绘制分数
		g.setColor(Color.WHITE);
		g.setFont(new Font("宋体", Font.BOLD, 20));
		g.drawString("分数：" + score, 30, 30);
		
		if(net.show){
			g.drawImage(net.net, net.x - net.width/2, net.y - net.height/2, null);
			
		}
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		int x = e.getX();
		int y = e.getY();
		net.moveTo(x, y);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

		for(int i = 0; i < fishes.length; i++){
			if(net.catchFish(fishes[i])){
				fishes[i].disappear();
				//增加分数
				score = score + fishes[i].width/10;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		net.show = true;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		net.show = false;
	}
}
