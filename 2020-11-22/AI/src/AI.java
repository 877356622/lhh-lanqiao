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
	int heroX,heroY,bossX,bossY;
	Image heroImg[][]=new Image[4][3];
	Image bossImg;
	Image currentImg;
	int i,j,k=0,actionFlag,turnFlag,lastAction;
	public MainCanvas(){
		try
		{
			for(i=0;i<heroImg.length;i++){   //0：下 1：左 2：上 3：右
				for(j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+j+k+".png");
				}
				k=k+2;
			}
			currentImg=heroImg[0][1];
			bossImg=Image.createImage("/img_boss/benzaiten.png");
			heroX=120;
			heroY=120;
			bossX=120;
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
			repaint();
		}
	}
	public void paint(Graphics g){
		g.setColor(102,204,102);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);
		g.drawImage(bossImg,bossX,bossY,0);
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		int m=0;
		if (lastAction!=action){
			turnFlag=0;
		}
		if(action==LEFT){
			m=1;
		}
		else if(action==UP){
			m=2;
		}
		else if(action==RIGHT){
			m=3;
		}
		else if(action==DOWN){
			m=0;
		}
		if(turnFlag==0){
			currentImg=heroImg[m][1];
			turnFlag=1;
		}
		else if(actionFlag==1){
			currentImg=heroImg[m][0];
			actionFlag=2;
		}
		else if(actionFlag==2){
			currentImg=heroImg[m][2];
			actionFlag=1;
		}
		if(m==0){
			heroY=heroY+5;
		}
		else if(m==1){
			heroX=heroX-5;
		}
		else if(m==2){
			heroY=heroY-5;
		}
		else if(m==3){
			heroX=heroX+5;
		}
		lastAction=action;
	}
}