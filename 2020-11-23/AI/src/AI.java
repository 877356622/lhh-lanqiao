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
class MainCanvas extends Canvas implements Runnable   //多线程
{
	Thread thread;
	int heroX,heroY,bossX,bossY,boss1X,boss1Y;
	Image heroImg[][]=new Image[4][3];
	Image bossImg;
	Image bossImg1;
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
			bossImg1=Image.createImage("/img_boss/juroujin.png");
			heroX=120;
			heroY=120;
			bossX=0;
			bossY=0;
			boss1X=200;
			boss1Y=0;
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
			try
			{
				Thread.sleep(200);//FPS：屏幕刷新率
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
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
			if(boss1X<heroX){
				boss1X++;
			}
			else{
				boss1X--;
			}
			if(boss1Y<heroY){
				boss1Y++;
			}
			else{
				boss1Y--;
			}
			repaint();
		}
	}
	public void paint(Graphics g){
		g.setColor(102,204,102);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
		g.drawImage(bossImg1,boss1X,boss1Y,0);
	}
	public void changeImg(int direction){   //自定义方法
		if(turnFlag==0){
			currentImg=heroImg[direction][1];
			turnFlag=1;
		}
		else if(actionFlag==1){
			currentImg=heroImg[direction][0];
			actionFlag=2;
		}
		else if(actionFlag==2){
			currentImg=heroImg[direction][2];
			actionFlag=1;
		}
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		if (lastAction!=action){
			turnFlag=0;
		}
		if(action==LEFT){
			changeImg(1);        //调用方法
			heroX=heroX-5;
		}
		else if(action==UP){
			changeImg(2);
			heroY=heroY-5;
		}
		else if(action==RIGHT){
			changeImg(3);
			heroX=heroX+5;
		}
		else if(action==DOWN){
			changeImg(0);
			heroY=heroY+5;
		}
		lastAction=action;
	}
}