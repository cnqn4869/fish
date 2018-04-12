package cn.tedu.text;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

public class Net{
	BufferedImage net;
	int width;
	int height;
	int x;//网中心点的坐标，就是鼠标的坐标
	int y;
	boolean show;
	
	public Net(){
		try {
			net = ImageIO.read(new File("img/net09.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		width = net.getWidth();
		height = net.getHeight();
		x = 0;
		y = 0;
		show = false;
	}
	
	public void moveTo(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public boolean catchFish(Fish fish){
		int dx = this.x - fish.x;
		int dy = this.y - fish.y;
		return Math.abs(dx) < width/2 && Math.abs(dy) < height/2;
	}
	
}
