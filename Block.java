import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class Block extends JPanel{
    private int blockX = 0;
    private int blockY;
    private int blockLength = 1;
    private int blockWidth = 1;

    private Color blockColor = Color.RED;


    public Block(int blockXParam, int blockYParam, int blockLengthParam, int blockWidthParam, Color blockColorParam, JPanel blockPanelParam){
        blockX = blockXParam;
        blockY = blockYParam;
        blockLength = blockLengthParam;
        blockWidth = blockWidthParam;
        blockColor = blockColorParam;
    }

    public int getY(){
        return blockY;
    }

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