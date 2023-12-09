import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
//uses jpanel stuff


//ok so the Main window extends JPanel, part of JFRAME, so i don't need to do whole shenanigans when defining, and the implements Runnable means I can use threads to execute stuff via threads instead of something like a timer
public class MainGameWindow extends JPanel implements Runnable{

    //All the objects!
    private Thread gameThread;
    private MouseActions mouseAction;

    private final int originalTileSize = 16;
    private final int scale = 3;
    private final int tileSize = originalTileSize * scale; // 48x48 pixels
    private final int maxScreenWidth = 16;
    private final int maxScreenHeight = 12;
    private final int screenWidth = maxScreenWidth * tileSize; // 768 px
    private final int screenHeight = maxScreenHeight * tileSize; //  576 px

    private Block blockA = new Block(0, 400, 768, 174, Color.BLUE, this);
    private Block blockB = new Block(668, 0, 100, 576, Color.RED, this);
    private Block blockC = new Block(0, 0, 100, 576, Color.RED, this);
    private MovableBlock movableBlockA = new MovableBlock(100, 500, 100, 50, Color.GREEN, this, true, false);

    private Block[] blocks = {blockA,blockB,blockC};
    private MovableBlock[] movableBlocks = {movableBlockA};
    private Player player = new Player();

    //Constructor for game window
    public MainGameWindow(MouseActions a){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addMouseListener(a);
        this.addMouseMotionListener(a);
        mouseAction = a;
    }
    //initializes thread
    public void startGame(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){
        //runs while object exists
        while(gameThread != null){
            updateGameStuff();
            //repaint (update screen)
            repaint();
            // blockA.repaint();
            //delay for game loop
            
            try{
                Thread.sleep(16); //60 FPS me thinks
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics player){ // graphics class which deals with painting stuff onto things like a JFRAME.
        super.paintComponent(player); //prevents background from being F'd up
        Graphics2D player2D = (Graphics2D)player; // Take the normal graphics, cast it into a 2d Graphics which I can do more specific stuff with
        // blockA.paintComponent(player2D);
        blockA.paintComponent(player);
        blockB.paintComponent(player);
        blockC.paintComponent(player);
        this.player.paintComponent(player);
        movableBlockA.paintComponent(player);
        player2D.dispose();
    }

    public void updateGameStuff(){
        player.playerXIncrement();
        player.playerYIncrement();
        updatePlayerCollision();
        updateMovableBlock();
    }

    public void updatePlayerCollision(){
        for(Block i: blocks){
            if(player.getPlayerVerticalBounds().intersects(i.getBounds())){
                if(player.getPlayerYVel() <= 3){
                    player.playerSetYVel(0);
                    player.noGravity();
                }
                else{
                    player.playerSetYVel(-(int)(player.getPlayerYVel() * .5));
                    player.playerSetY(i.getY()-51);
                    player.resetPlayerGravity();
                }
            }
            if(player.getPlayerHorizontalBounds().intersects(i.getBounds())){
                player.playerSetXVel(-player.getPlayerXVel());
            }
        }
    }
    public void updateMovableBlock(){
        for(MovableBlock i: movableBlocks){
            if(mouseAction.getMouseBounds().intersects(i.getBounds())) i.moveBlockY(-5);
        }
    }
}
