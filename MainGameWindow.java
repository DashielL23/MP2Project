import javax.swing.*;
import java.awt.*;
//uses jpanel stuff

//ok so the Main window extends JPanel, part of JFRAME, so i don't need to do whole shenanigans when defining, and the implements Runnable means I can use threads to execute stuff via threads instead of something like a timer
public class MainGameWindow extends JPanel implements Runnable{

    //display settings
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

    private boolean movingRight = true;
    private boolean movingDown = true;
    private boolean updateDetectionXAxis(boolean a){
        if(boxXValue + tileSize > screenWidth){
            a = false;
        }
        else if((boxXValue-50) + tileSize < 0){
            a = true;
        }
        movingRight = a;
        return a;
    }
    private boolean updateDetectionYAxis(boolean a){
        if(boxYValue + tileSize > screenHeight){
            a = false;
        }
        if((boxYValue-50) + tileSize < 0){
            a = true;
            
        }
        movingDown = a;
        return a;
    }

    private Thread gameThread;

    //Constructor for game window
    public MainGameWindow(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }

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
            //delay for game loop
            
            try{
                Thread.sleep(16); //60 FPS me thinks
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            
        }
    }

    public void paintComponent(Graphics graphics){ // graphics class which deals with painting stuff onto things like a JFRAME.
      
        super.paintComponent(graphics); //prevents background from being F'd up

        Graphics2D graphics2d = (Graphics2D)graphics; // Take the normal graphics, cast it into a 2d Graphics which I can do more specific stuff with

        //paints the square
        graphics2d.setColor(Color.RED);
        graphics2d.fillRect(boxXValue, boxYValue, tileSize, tileSize);

        //prevents a lot of memory from being wasted lol
        graphics2d.dispose();
    }
    public void updateGameStuff(){
        updateDetectionXAxis(movingRight);
        updateDetectionYAxis(movingDown);
        //X axis movement
        if(movingRight) boxXValue+= xVelocity;
        else if(!movingRight) boxXValue-= xVelocity;
        //Y axis movement
        if(movingDown) boxYValue+= yVelocity;
        if(!movingDown) boxYValue-= yVelocity;



    }
}