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
		int i=0;
	Image currentImg,downImg,downImg1,leftImg,leftImg1,upImg,upImg1,rightImg,rightImg1;
	int x=120,y=120;
	public MainCanvas(){
	try
	{
		currentImg=downImg=Image.createImage("/sayo10.png");
		leftImg=Image.createImage("/sayo12.png");
		upImg=Image.createImage("/sayo14.png");
		rightImg=Image.createImage("/sayo16.png");
		upImg1=Image.createImage("/sayo04.png");
		downImg1=Image.createImage("/sayo00.png");
		leftImg1=Image.createImage("/sayo02.png");
		rightImg1=Image.createImage("/sayo06.png");
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
		if(action==LEFT){
			currentImg=leftImg;
			x=x-5;
		}
		else if(action==UP){
			if(i==0){
			currentImg=upImg;
			y=y-2;
			i=1;
			}
			else if(i==1){
			currentImg=upImg1;
			y=y-2;
			i=0;
			}
		}
		else if(action==RIGHT){
			currentImg=rightImg;
			x=x+5;
		}
		else if(action==DOWN){
			if(i==0){
			currentImg=downImg;
			y=y+2;
			i=1;
			}
			else if(i==1){
			currentImg=downImg1;
			y=y+2;
			i=0;
			}
		}
		repaint();
	}
}