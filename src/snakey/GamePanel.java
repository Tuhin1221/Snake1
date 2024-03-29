package snakey;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Random;

 
 
@SuppressWarnings({ "serial" })
public class GamePanel extends JPanel implements ActionListener{
	int SCREEN_WIDTH=750;
	int SCREEN_HEIGHT=750;
	int UNIT_SIZE=25;
	int GAME_UNITS=((SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE);
	int DELAY=120;
	int x[]= new int[GAME_UNITS];
	int y[]= new int[GAME_UNITS];
	int bodyparts=6;
	int appleseaten;
	int appleX;
	int appleY;
	char direction='D';
	boolean running;
	boolean running1=true;
	Timer timer;
	Random random;
	int i=0;
     GamePanel(){
    	 random=new Random();
    	 this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
    	 this.setBackground(Color.black);
    	 this.setFocusable(true);
    	 this.addKeyListener(new MyKeyAdapter());//?
    	 startGame();
     }
     public void startGame() {
    	 newApple();//create apple when starting
    	 running=true;
    	 timer = new Timer(DELAY,this); 
    	 timer.start();
     }
     public void paintComponent(Graphics g) {//this is the syntax itself 
    	 super.paintComponent(g);
    	 draw1 (g);//continuously execute  g
     }
     public void checkApple()
     {
    	 if((x[0]==appleX) && (y[0]==appleY))
    	 
    	 {
    		 bodyparts++;
    		 appleseaten= appleseaten+1;
    		 newApple();
    	 }
    	 
     }
     public void draw1(Graphics g) //to draw on screen
  
     {
    	 if(running==true)
    	 {
    	 for(int i=0;i<SCREEN_HEIGHT/UNIT_SIZE;i++)
    	 {
    		 g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
    		 g.drawLine(0,i*UNIT_SIZE , SCREEN_WIDTH,i*UNIT_SIZE);
    	 }
    	 g.setColor(Color.RED);
    	 g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);
    	 
    	 for ( int j=0;j<bodyparts;j++)
         {
    		 if(j==0)
    		 {
    			 g.setColor(Color.WHITE);
    			 g.fillRect(x[j],y[j], UNIT_SIZE,UNIT_SIZE);
    		 }
    		 else 
    		 {
    		 g.setColor(Color.green);
			 g.fillRect(x[j],y[j], UNIT_SIZE,UNIT_SIZE);
			 
    		 }
         }
    	 g.setColor(Color.red);
 		g.setFont(new Font("Ink Free",Font.BOLD,20));
 		FontMetrics metrics = getFontMetrics(g.getFont());//to line up text in middle of screen
 		g.drawString("SCORE "+appleseaten,(SCREEN_WIDTH-metrics.stringWidth("Score"+appleseaten))/2 , g.getFont().getSize());
 	    	 
    	 }
    	 else
    	 {
    		 gameOver(g);
    	 }
     }
     
     
     public void newApple()//coordinate of apple
     {
    	 appleX=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
    	 appleY=random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
     }
     public void move() {
    
     	
    	 for(int i=bodyparts;i>0;i--)
    	 {
    		 x[i]=x[i-1];
    		 y[i]=y[i-1];
    		 
    	 }
    	 switch(direction)
    	 {
    	 case 'U' :
    		 y[0]=y[0]-UNIT_SIZE;
    		 break;
    	 case 'D' :
    		 y[0]=y[0]+UNIT_SIZE;
    		 break;
    	 case 'L' :
    		 x[0]=x[0]-UNIT_SIZE;
    		 break;
    	 case 'R' :
    		 x[0]=x[0]+UNIT_SIZE;
    		 break;
    		 
    	 }
     }
public void gameOver(Graphics g) {
	//text
	g.setColor(Color.red);
	g.setFont(new Font("Ink Free",Font.BOLD,75));
	FontMetrics metrics = getFontMetrics(g.getFont());//to line up text in middle of screen
	g.drawString("GAME OVER ",(SCREEN_WIDTH-metrics.stringWidth("GAME_OVER"))/2 , SCREEN_HEIGHT/2);
    	 
     }
     
     
     public void checkCollisions() 
     {
    	 
    	
    	 for( i = bodyparts;i>0;i--)
    	 {
    		 if((x[0]==x[i])&&(y[0]==y[i]))
    		 {
    			 running = false;
    		 }
    		 
    	 }
    	 if(x[0]<0)//head touch left border
    	 {
    		 running=false;
    	 }
    	 if(x[0]>SCREEN_WIDTH)//head touch right border
    	 {
    		 running=false;
    	 }
    	 if(y[0]<0)//head touch top border
    	 {
    		 running=false;
    	 }
    	 if(y[0]>SCREEN_HEIGHT)//head touches bottom border
    	 {
    		 running=false;
    	 }
    	 if(!running) {
    		 timer.stop();
    	 }
     }
     
     
     @Override
     public void actionPerformed(ActionEvent e)
     {
    	 if(running1==true)
         {
         	move();
         	checkApple();
         	checkCollisions();
         }
         repaint();
     }
	class MyKeyAdapter extends KeyAdapter{//this helps to add functionality to the keyboard
	   	 @Override
	   	public void keyPressed(KeyEvent e) {
	   		switch(e.getKeyCode())//this is a built in method for using keys
	   		{
	   		case KeyEvent.VK_LEFT://numberpad
	   			if(direction !='R') {
	   				direction ='L';
	   			}
	   			break;
	   		case KeyEvent.VK_RIGHT://numberpad
	   			if(direction !='L') {
	   				direction ='R';
	   			}
	   			break;
	   		case KeyEvent.VK_UP://numberpad
	   			if(direction !='D') {
	   				direction ='U';
	   			}
	   			break;
	   		case KeyEvent.VK_DOWN://numberpad
	   			if(direction !='U') {
	   				direction ='D';
	   			}
	   			break;
	   		}
	   	 }
	
	   	 }
	   	 
	}
	
		
   	 
	

	