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
    private int boxXValue = 100;
    private int boxYValue = 100;
    private int xVelocity = 5;

    private double yVelocity = 5;
    private double gravity = .05;
    private boolean movingRight = true;
    private boolean movingDown = true;

    private Block blockA = new Block(0, 400, 768, 174, Color.BLUE, this);

    private boolean updateDetectionXAxis(boolean a){
        if(boxXValue + tileSize > screenWidth){
            a = false;
        }
        else if((boxXValue-50) + tileSize < 0 || (blockA.CollisionRight(this))){
            a = true;
        }
        movingRight = a;
        return a;
    }

    private boolean updateDetectionYAxis(boolean a){
        if(!(blockA.CollisionTop(this)) && !(boxYValue >= 526)){
            a = true;
        }
        else{
            a = false;
        }
        movingDown = a;
        return a;
    }

    public int playerXReturnMethod(){
        return boxXValue;
    }

    public int playerYReturnMethod(){
        return boxYValue;
    }

    public double playerYVelocityReturnMethod(){
        return yVelocity;
    }

    public boolean playerXDirectionReturnMethod(){
        return movingRight;
    }
    
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
            blockA.repaint();
            //delay for game loop
            
            try{
                Thread.sleep(128); //60 FPS me thinks
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics player){ // graphics class which deals with painting stuff onto things like a JFRAME.
        super.paintComponent(player); //prevents background from being F'd up
        Graphics2D player2D = (Graphics2D)player; // Take the normal graphics, cast it into a 2d Graphics which I can do more specific stuff with
        //paints the square
        player2D.setColor(Color.RED);
        player2D.fillRect(boxXValue, boxYValue, tileSize, tileSize);
        blockA.paintComponent(player2D);
        //prevents a lot of memory from being wasted lol
        player2D.dispose();
    }

    public void updateGameStuff(){
        System.out.println(boxXValue);
        updateDetectionXAxis(movingRight);
        updateDetectionYAxis(movingDown);
        //X axis movement
        if(movingRight) boxXValue+= xVelocity;
        else if(!movingRight) boxXValue-= xVelocity;
        //Y axis movement
        if(movingDown){
            yVelocity+=gravity;
            gravity+=.005; //changes gravity to have more effect
            boxYValue+= yVelocity; 
        } 
        else if(!movingDown){
            yVelocity = -(yVelocity*.8);
            if(boxYValue >= 526){
                boxYValue = 525;
            } 
            if(Math.abs(yVelocity) <= 5){
                yVelocity = 0;
                gravity = .5;
            } 
            else{
                boxYValue+= yVelocity;
            }
            
        } 
        //update Mouse Position        


    }
}