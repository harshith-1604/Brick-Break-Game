package brickBreaker;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
public class Mapgenerator 
{
    public int[][] map;
    public int brickWidth;
    public int brickHeight;
    public Mapgenerator(int rows,int cols) 
    {
        map=new int[rows][cols];
        for (int i=0;i<map.length;i++) 
        {
            for (int j=0;j<map[0].length;j++) 
            {
                map[i][j]=1;
            }
        }
        brickWidth=540/cols;
        brickHeight=150/rows;
    }
    public void draw(Graphics2D g) 
    {
        for (int i=0;i<map.length;i++) 
        {
            for (int j=0;j<map[0].length;j++) 
            {
                if (map[i][j]>0) 
                {
                    int x=j*brickWidth+80;
                    int y=i*brickHeight+50;
                    g.setColor(Color.WHITE);
                    g.fillRect(x,y,brickWidth,brickHeight);
                    g.setStroke(new BasicStroke(3));
                    g.setColor(Color.BLACK);
                    g.drawRect(x,y,brickWidth,brickHeight);
                }
            }
        }
    }
    public void setBrickValue(int value,int row,int col) 
    {
        map[row][col]=value;
    }
}