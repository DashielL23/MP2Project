package GameInfo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class EndBlock extends JPanel{
    private int blockX = 0;
    private int blockY;
    private int blockLength = 1;
    private int blockWidth = 1;
    private Color blockColor = Color.RED;


    public EndBlock(int blockXParam, int blockYParam, int blockLengthParam, int blockWidthParam){
        blockX = blockXParam;
        blockY = blockYParam;
        blockLength = blockLengthParam;
        blockWidth = blockWidthParam;
    }

    public int getY(){return blockY;}
    public int getX(){return blockX;}
    public int getLength(){return blockLength;}
    public int getWidth(){return blockWidth;}
    public Color getColor(){return blockColor;}

    public void paintComponent(Graphics blockGraphics){
        super.paintComponent(blockGraphics); //see MainGameWindow for notes on this specific part

        Graphics2D blockGraphics2D = (Graphics2D)blockGraphics;
        blockGraphics2D.setColor(blockColor);  
        blockGraphics2D.fillRect(blockX, blockY, blockLength, blockWidth);
    }

    public Rectangle getBounds(){ // For collisions
        return new Rectangle(blockX,blockY,blockLength,blockWidth); // returns invisible rectangle the size of the block to detect collision
    }

}