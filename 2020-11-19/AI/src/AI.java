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
	Image heroUpImg[]=new Image[3];
	Image heroDownImg[]=new Image[3];
	Image heroLeftImg[]=new Image[3];
	Image heroRightImg[]=new Image[3];
	Image currentImg;
	int i,x,y,actionFlag,lastAction;
	public MainCanvas(){
	try
	{
		for(i=0;i<heroUpImg.length;i++){
			heroUpImg[i]=Image.createImage("/sayo"+i+"4.png");
		}
		for(i=0;i<heroDownImg.length;i++){
			heroDownImg[i]=Image.createImage("/sayo"+i+"0.png");
		}
		for(i=0;i<heroLeftImg.length;i++){
			heroLeftImg[i]=Image.createImage("/sayo"+i+"2.png");
		}
		for(i=0;i<heroRightImg.length;i++){
			heroRightImg[i]=Image.createImage("/sayo"+i+"6.png");
		}
		currentImg=heroDownImg[1];
		x=120;
		y=100;
		actionFlag=0;
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
			actionFlag=0;
		}
		if(action==LEFT){
			if(actionFlag==0){
				currentImg=heroLeftImg[1];
			}
			else if(actionFlag==1){
				currentImg=heroLeftImg[0];
				x=x-3;
			}
			else if(actionFlag==2){
				currentImg=heroLeftImg[2];
				x=x-3;
			}
		}
		else if(action==UP){
			if(actionFlag==0){
				currentImg=heroUpImg[1];
			}
			else if(actionFlag==1){
				currentImg=heroUpImg[0];
				y=y-3;
			}
			else if(actionFlag==2){
				currentImg=heroUpImg[2];
				y=y-3;
			}
		}
		else if(action==RIGHT){
			if(actionFlag==0){
				currentImg=heroRightImg[1];
			}
			else if(actionFlag==1){
				currentImg=heroRightImg[0];
				x=x+3;
			}
			else if(actionFlag==2){
				currentImg=heroRightImg[2];
				x=x+3;
			}
		}
		else if(action==DOWN){
			if(actionFlag==0){
				currentImg=heroDownImg[1];
			}
			else if(actionFlag==1){
				currentImg=heroDownImg[0];
				y=y+3;
			}
			else if(actionFlag==2){
				currentImg=heroDownImg[2];
				y=y+3;
			}
		}
		lastAction=action;
		if(actionFlag==0)
			actionFlag=1;
		else if(actionFlag==1)
			actionFlag=2;
		else if(actionFlag==2)
			actionFlag=1;
		repaint();
	}
}