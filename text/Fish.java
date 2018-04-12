package cn.tedu.text;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class Fish implements Runnable{
	//����
	//����ÿһ�����ʮ��ͼƬ
	BufferedImage[] biFishes = new BufferedImage[10];
	BufferedImage nowFish;
	int x;
	int y;
	int width;
	int height;
	int index;//�л�ͼƬ
	int speed;//�ٶ�
	
	Random rand = new Random();
	
	public Fish(String name){
		for(int i = 0; i < 10; i++){			
			try {
				biFishes[i] = ImageIO.read(new File("img/" + name + "_0" + i + ".png"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		nowFish = biFishes[0];
		width = nowFish.getWidth();
		height = nowFish.getHeight();
		index = 0;
		x = rand.nextInt(800 - width);
		y = rand.nextInt(480 - height);
		speed = rand.nextInt(10) + 2;
	}
	
	//����
	public void move(){
		x = x - speed;
		nowFish = biFishes[index++%biFishes.length];
		if(x < -width){
			disappear();
			Pool.score = Pool.score - 5;
		}
		if(Pool.score < 0){
			JOptionPane.showMessageDialog(null, "��Ϸ����");
			System.exit(0);
		}
		
	}

	public void disappear() {
		x = 800;
		y = rand.nextInt(480 - height);
		speed = rand.nextInt(10) + 2;
	}

	@Override
	public void run() {
		while(true){
			move();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
}
