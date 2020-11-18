import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas
{
	Image currentImg,downImg,downImg0,downImg1,leftImg,leftImg0,leftImg1;
	Image upImg,upImg0,upImg1,rightImg,rightImg0,rightImg1;
	int x,y,i,lastAction;
	public MainCanvas(){
	try
	{
		currentImg=downImg=Image.createImage("/sayo10.png");
		downImg0=Image.createImage("/sayo00.png");
		downImg1=Image.createImage("/sayo20.png");
		upImg=Image.createImage("/sayo14.png");
		upImg0=Image.createImage("/sayo04.png");
		upImg1=Image.createImage("/sayo24.png");
		leftImg=Image.createImage("/sayo12.png");
		leftImg0=Image.createImage("/sayo02.png");
		leftImg1=Image.createImage("/sayo22.png");
		rightImg=Image.createImage("/sayo16.png");
		rightImg0=Image.createImage("/sayo06.png");
		rightImg1=Image.createImage("/sayo26.png");
		x=120;
		y=100;
		i=0;
		lastAction=0;
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	}
	public void paint(Graphics g){
		g.setColor(255,255,0);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,x,y,0);
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		if (lastAction!=action){
			i=0;
		}
		if(action==LEFT){
			if(i==0){
			currentImg=leftImg;
			}
			else if(i==1){
			currentImg=leftImg0;
			x=x-3;
			}
			else if(i==2){
			currentImg=leftImg1;
			x=x-3;
			}
		}
		else if(action==UP){
			if(i==0){
			currentImg=upImg;
			}
			else if(i==1){
			currentImg=upImg0;
			y=y-3;
			}
			else if(i==2){
			currentImg=upImg1;
			y=y-3;
			}
		}
		else if(action==RIGHT){
			if(i==0){
			currentImg=rightImg;
			}
			else if(i==1){
			currentImg=rightImg0;
			x=x+3;
			}
			else if(i==2){
			currentImg=rightImg1;
			x=x+3;
			}
		}
		else if(action==DOWN){
			if(i==0){
			currentImg=downImg;
			}
			else if(i==1){
			currentImg=downImg0;
			y=y+3;
			}
			else if(i==2){
			currentImg=downImg1;
			y=y+3;
			}
		}
		lastAction=action;
		if(i==0)
			i=1;
		else if(i==1)
			i=2;
		else if(i==2)
			i=1;
		repaint();
	}
}