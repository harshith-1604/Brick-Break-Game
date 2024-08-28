package brickBreaker;
import javax.swing.JFrame;
public class Main 
{
    public static void main(String[] args) 
    {
        JFrame obj=new JFrame();
        Gameplay gameplay=new Gameplay();
        obj.setBounds(10,10,710,600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(gameplay);
        gameplay.requestFocus();
        obj.setVisible(true);
    }
}