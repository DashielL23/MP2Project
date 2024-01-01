package GameInfo;
import javax.swing.*;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.util.ArrayList;

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

    private Level currentLevel;
    private Player player = new Player();
    private ArrayList<Level> levels = new ArrayList<>();
    private int levelCounter = 0;
    //levels
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
    //add levels
    public void addLevels(){
        levels.add(new Level(1, 110, 350, player));
        levels.add(new Level(2, 110, 350, player));
        currentLevel = levels.get(0);
    }
    public boolean isDead(Player p){
        if(p.getPlayerY()>576){return true;}
        return false;
    }

    //game loop
    @Override
    public void run(){
        addLevels();
        //runs while object exists
        while(gameThread != null){
            System.out.println(levelCounter);
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
        for(Block i: currentLevel.getBlocks()){
            currentLevel.paintBlockComponent(player, i, i.getColor());
        }
        for(MovableBlock j: currentLevel.getMovableBlocks()){
            currentLevel.paintMovableBlockComponent(player, j, j.getColor());
        }
        for(EndBlock k: currentLevel.getEndBlocks()){
            currentLevel.paintEndBlockComponent(player, k);
        }
        this.player.paintComponent(player);
        player2D.dispose();
    }

    public void updateGameStuff(){
        player.playerXIncrement();
        player.playerYIncrement();
        updatePlayerCollision();
        updateMovableBlock();
        updateEndBlockCollision();
    }

    public void updatePlayerCollision(){
        for(Block i : currentLevel.getBlocks()){
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
        for(MovableBlock i: currentLevel.getMovableBlocks()){
            if(mouseAction.getMouseBounds().intersects(i.getBounds()) && mouseAction.getDragging()) i.moveBlockY(mouseAction.getMouseY()-(i.getWidth()/2));
            if(player.getPlayerVerticalBounds().intersects(i.getBounds())){
                if(player.getPlayerYVel() <= 3 && !player.getPlayerHeadForMovableBlocks().intersects(i.getBounds())){
                    System.out.println(player.getPlayerYVel());
                    player.playerSetY(i.getY()-50);
                    player.noGravity();
                    player.playerSetYVel(0);
                }
                else if(!player.getPlayerHeadForMovableBlocks().intersects(i.getBounds())){
                    System.out.println(player.getPlayerYVel());
                    player.playerSetYVel(-(int)(player.getPlayerYVel() * .5));
                    player.playerSetY(i.getY()-51);
                    player.resetPlayerGravity();
                }
                if(player.getPlayerHeadForMovableBlocks().intersects(i.getBounds())){
                    i.moveBlockY((int)player.getPlayerY()-i.getWidth()-1);
                }

            }
            if(player.getPlayerHorizontalBounds().intersects(i.getBounds())){
                player.playerSetXVel(-player.getPlayerXVel());
            }
        }
    }
    public void updateEndBlockCollision(){
        for(EndBlock i: currentLevel.getEndBlocks()){
            if(player.getPlayerHorizontalBounds().intersects(i.getBounds())){
                levelCounter++;
                currentLevel = levels.get(levelCounter);
                player.playerSetX(currentLevel.getPlayerX());
                player.playerSetY(currentLevel.getPlayerY());

            }
        }
    }
}

