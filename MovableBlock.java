import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.awt.Rectangle;

public class MovableBlock {
    private int blockX,blockY,blockLength,blockWidth;
    private boolean movableHorizontal,movableVertical;
    public boolean getVMovable(){
        return movableVertical;
    }
    public boolean getHMovable(){
        return movableHorizontal;
    }
    public void moveBlockY(int x){
        blockY += x;
    }

    private Color blockColor;
    public MovableBlock(int blockXParam, int blockYParam, int blockLengthParam, int blockWidthParam, Color blockColorParam, JPanel blockPanelParam, boolean canMoveVertical, boolean canMoveHorizontal){
        blockX = blockXParam;
        blockY = blockYParam;
        blockLength = blockLengthParam;
        blockWidth = blockWidthParam;
        blockColor = blockColorParam;
        movableHorizontal = canMoveHorizontal;
        movableVertical = canMoveVertical;
    }

    public void paintComponent(Graphics movableBlockGraphics){

        Graphics2D movableBlockGraphics2D = (Graphics2D)movableBlockGraphics;
        movableBlockGraphics2D.setColor(blockColor);
        movableBlockGraphics2D.fillRect(blockX,blockY,blockLength,blockWidth);

        movableBlockGraphics2D.dispose();
    }

    public Rectangle getBounds(){ // For collisions
        return new Rectangle(blockX,blockY,blockLength,blockWidth); // returns invisible rectangle the size of the block to detect collision
    }



}
