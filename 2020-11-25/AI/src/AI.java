import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;
import java.util.*;
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
class MainCanvas extends Canvas implements Runnable   //多线程
{
	Thread thread;
	int heroX,heroY,bossX,bossY;
	Image heroImg[][]=new Image[4][3];
	Image bossImg;
	Random rd=new Random();
	Image currentImg;
	int k=0,actionFlag,turnFlag,lastAction;
	public MainCanvas(){
		try
		{
			for(int i=0;i<heroImg.length;i++){   //0：下 1：左 2：上 3：右
				for(int j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+j+k+".png");
				}
				k=k+2;
			}
			currentImg=heroImg[0][1];
			bossImg=Image.createImage("/img_boss/benzaiten.png");
			heroX=120;
			heroY=120;
			bossX=0;
			bossY=0;
			actionFlag=1;
			turnFlag=0;
			lastAction=0;
			thread=new Thread(this);
			thread.start();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public void run(){
		while(true){
			int rdNumber=rd.nextInt(10);
			try
			{
				Thread.sleep(200);//FPS：屏幕刷新率
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(rdNumber%3==0){
				if(bossX<heroX){
					bossX++;
				}
				else{
					bossX--;
				}
				if(bossY<heroY){
					bossY++;
				}
				else{
					bossY--;
				}
				repaint();
			}
		}
	}
	public void paint(Graphics g){
		g.setColor(102,204,102);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}
	public void changeImgAndMove(int direction){   //自定义方法
		if(turnFlag==0){
			currentImg=heroImg[direction][1];
			turnFlag=1;
		}
		else if(actionFlag==1){
			currentImg=heroImg[direction][0];
			heroMove(direction);
			actionFlag=2;
		}
		else if(actionFlag==2){
			currentImg=heroImg[direction][2];
			heroMove(direction);
			actionFlag=1;
		}
	}
	public void heroMove(int direction){
		if(direction==1){
			heroX=heroX-5;
		}
		else if(direction==2){
			heroY=heroY-5;
		}
		else if(direction==3){
			heroX=heroX+5;
		}
		else{
			heroY=heroY+5;
		}
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		if (lastAction!=action){
			turnFlag=0;
		}
		if(action==LEFT){
			changeImgAndMove(1);        //调用方法
		}
		else if(action==UP){
			changeImgAndMove(2);;
		}
		else if(action==RIGHT){
			changeImgAndMove(3);
		}
		else if(action==DOWN){
			changeImgAndMove(0);
		}
		lastAction=action;
	}
}