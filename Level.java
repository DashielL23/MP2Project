package GameInfo;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

public class Level{ 
    private ArrayList<Block> blocks = new ArrayList<Block>();
    private ArrayList<MovableBlock> movableBlocks = new ArrayList<MovableBlock>();
    private ArrayList<EndBlock> endBlocks = new ArrayList<EndBlock>();
    private int playerX;
    private int playerY;
    //private Level1 level1;
    public Level(int levelNum, int playerX, int playerY, Player p){
        if(levelNum==1){
            blocks.add(new Block(0, 400, 768, 174, Color.BLUE));
            blocks.add(new Block(0, 0, 100, 576, Color.RED));
            blocks.add(new Block(718, 0, 50, 576, Color.RED));
            movableBlocks.add(new MovableBlock(200, 300, 100, 200, Color.GREEN, true, false));
            endBlocks.add(new EndBlock(708,350,50,50));
        }
        if(levelNum== 2){
            blocks.add(new Block(0,400,468,174, Color.BLUE));
            blocks.add(new Block(0,0,100,576, new Color(82,3,3)));
            blocks.add(new Block(668,0,100,576, new Color(82,3,3)));
        }
        this.playerX = playerX;
        this.playerY = playerY;
    }
    public ArrayList<Block> getBlocks(){return blocks;}
    public ArrayList<MovableBlock> getMovableBlocks(){return movableBlocks;}
    public ArrayList<EndBlock> getEndBlocks(){return endBlocks;}
    public void paintBlockComponent(Graphics g, Block i, Color h){
        Graphics g2d = (Graphics2D)g;
        g2d.setColor(h);
        g2d.fillRect(i.getX(),i.getY(),i.getLength(),i.getWidth());
    }
    public int getPlayerX(){return playerX;}
    public int getPlayerY(){return playerY;}
    public void paintMovableBlockComponent(Graphics g, MovableBlock i, Color h){
        Graphics g2d = (Graphics2D)g;
        g2d.setColor(h);
        g2d.fillRect(i.getX(),i.getY(),i.getLength(),i.getWidth());
    }
    public void paintEndBlockComponent(Graphics g, EndBlock j){
        Graphics g2d = (Graphics2D)g;
        g2d.setColor(new Color(71,179,55));
        g2d.fillRect(j.getX(),j.getY(),j.getLength(),j.getWidth());
    }
}
