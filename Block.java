import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Block extends JPanel{
    private int blockX = 0;
    private int blockY = 0;
    private int blockLength = 1;
    private int blockWidth = 1;

    private Color blockColor = Color.RED;
    private Graphics blockGraphics;
    private JPanel blockWindow;

    public Block(int blockXParam, int blockYParam, int blockLengthParam, int blockWidthParam, Color blockColorParam, JPanel blockPanelParam){
        blockX = blockXParam;
        blockY = blockYParam;
        blockLength = blockLengthParam;
        blockWidth = blockWidthParam;
        blockColor = blockColorParam;
        blockWindow = blockPanelParam;
    }

    public void paintComponent(Graphics blockGraphics){
        super.paintComponent(blockGraphics); //see MainGameWindow for notes on this specific part

        Graphics2D blockGraphics2D = (Graphics2D)blockGraphics;
        blockGraphics2D.setColor(blockColor);  
        blockGraphics2D.fillRect(blockX, blockY, blockLength, blockWidth);

    }

    public boolean CollisionTop(MainGameWindow playerParam){
        if((playerParam.playerYReturnMethod() >= (blockY-(blockWidth/2)+40)) && (playerParam.playerYVelocityReturnMethod() > 0) && ((playerParam.playerXReturnMethod() <= blockX+ (blockLength)) && (playerParam.playerXReturnMethod() >= blockX - (blockLength)))) return true; //returns true if player is touching the top of the box, and only the top of that box.
        return false;
    }

    public boolean CollisionLeft(MainGameWindow playerParam){
        if((playerParam.playerXReturnMethod() <= ((blockLength/2) + blockX)) && (playerParam.playerYReturnMethod() <= blockY + blockWidth) && (playerParam.playerYReturnMethod() >= blockY - blockWidth)) return true;
        return false;
    }

    public boolean CollisionRight(MainGameWindow playerParam){
        if((playerParam.playerXReturnMethod() < (blockX + (blockLength)) && (!playerParam.playerXDirectionReturnMethod()) && (playerParam.playerYReturnMethod() <= (blockY + (blockWidth)-40) && (playerParam.playerYReturnMethod() >= blockY-40)))) return true;
        return false;
    }

}