package brickBreaker;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.Timer;
public class Gameplay extends JPanel implements KeyListener, ActionListener 
{
    private boolean play=false;
    private int score=0;
    private int totalBricks=21;
    private Timer timer;
    private int delay=8;
    private int playerX=310;
    private int ballPosX=120;
    private int ballPosY=350;
    Random random=new Random();
    int n=random.nextInt(2+1-2)-2;  
    private int ballXDir=n;
    private int ballYDir=-2;
    private Mapgenerator map;
    public Gameplay() 
    {
        map=new Mapgenerator(3, 7);
        addKeyListener(this);
        setFocusable(true);
        timer=new Timer(delay,this);
        timer.start();
    }
    public void paint(Graphics g) 
    {
        super.paint(g);
        Graphics2D g2d=(Graphics2D)g;
        g.setColor(new Color(32,32,32));
        g.fillRect(1,1,692,592);
        map.draw(g2d);
        g.setColor(new Color(255,215,0));
        g.fillRect(0,0,3,592); 
        g.fillRect(0,0,692,3);
        g.fillRect(691,0,3,592);
        g.setColor(Color.white);
        g.setFont(new Font("serif",Font.BOLD,25));
        g.drawString(""+score,590,30);
        g.setColor(new Color(0,128,0));
        g.fillRect(playerX,550,100,8);
        g.setColor(Color.YELLOW);
        g.fillOval(ballPosX,ballPosY,20,20);
        if (totalBricks==0)
        {
            play=false;
            ballXDir=0;
            ballYDir=0;
            g.setColor(Color.GREEN);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("You Won!!",190,300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press ENTER to restart.",230,350);
        }
        if (ballPosY>570)
        {
            play=false;
            ballXDir=0;
            ballYDir=0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif",Font.BOLD,30));
            g.drawString("Game Over, Score:"+score,190,300);
            g.setFont(new Font("serif",Font.BOLD,20));
            g.drawString("Press ENTER to restart.",230,350);
        }
    }
    public void actionPerformed(ActionEvent e) 
    {
        if (play) 
        {
            if (new Rectangle(ballPosX,ballPosY,20,20).intersects(new Rectangle(playerX,550,100,8))) 
            {
                ballYDir=-ballYDir;
                ballXDir*=1.05;
                ballYDir*=1.05;
            }
            A: for (int i=0;i<map.map.length;i++) 
            {
                for (int j=0;j<map.map[0].length;j++) 
                {
                    if (map.map[i][j]>0) 
                    {
                        int brickX=j*map.brickWidth+80;
                        int brickY=i*map.brickHeight+50;
                        Rectangle brickRect=new Rectangle(brickX,brickY,map.brickWidth,map.brickHeight);
                        Rectangle ballRect=new Rectangle(ballPosX,ballPosY,20,20);
                        if (ballRect.intersects(brickRect)) 
                        {
                            map.setBrickValue(0,i,j);
                            totalBricks--;
                            score+=5;
                            if (ballPosX+19<=brickRect.x||ballPosX+1>=brickRect.x+brickRect.width) 
                            {
                                ballXDir=-ballXDir;
                            } else {
                                ballYDir=-ballYDir;
                            }
                            ballXDir*=1.05;
                            ballYDir*=1.05;
                            break A;
                        }
                    }
                }
            }
            ballPosX+=ballXDir;
            ballPosY+=ballYDir;
            if (ballPosX<0||ballPosX>670) 
            {
                ballXDir=-ballXDir;
            }
            if (ballPosY<0) 
            {
                ballYDir=-ballYDir;
            }
            if (ballPosY>570) 
            {
                play=false;
            }
        }
        repaint();
    }
    public void keyTyped(KeyEvent e) {}
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) 
        {
            if (playerX>=600) 
            {
                playerX=600;
            }
            else 
            {
                moveRight();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_LEFT) 
        {
            if (playerX<10) 
            {
                playerX=10;
            } else 
            {
                moveLeft();
            }
        }
        if (e.getKeyCode()==KeyEvent.VK_ENTER)
        {
            if (!play)
            {
                play=true;
                ballPosX=120;
                ballPosY=350;
                ballXDir=-1;
                ballYDir=-2;
                playerX=310;
                score=0;
                totalBricks=21;
                map=new Mapgenerator(3,7);
                repaint();
            }
        }
    }
    public void moveRight() 
    {
        play=true;
        playerX+=20;
    }
    public void moveLeft() 
    {
        play=true;
        playerX-=20;
    }
    public void keyReleased(KeyEvent e) 
    {
    }
}