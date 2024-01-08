package GameInfo;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuAnimPanel extends JPanel implements Runnable {
    private Block  bottomBlock = new Block(0,450,768,126,new Color(0,0,139));
    private Block leftBlock = new Block (0,0,100,576, new Color(139,0,0));
    private Block rightBlock = new Block(668,0,100,576, new Color(139,0,0));
    private Block[] blocks = {bottomBlock,leftBlock,rightBlock};
    private Player player = new Player();


    private boolean inMenu = true;
    private Thread menuThread = new Thread();

    public MenuAnimPanel() {
        setBackground(Color.BLACK);
        initializePanel();
        setVisible(true);
    }

    public void initializePanel(){
        player.playerSetX(101);
        player.playerSetY(399);
        setBounds(0,0, 768, 576);
    }


    public void restartAnimation(){
        inMenu = true;
        menuThread = new Thread(this);
        menuThread.start();
    }


    @Override
    public void run(){
        while(inMenu){
            updateAnim();
            repaint();
            try{
                Thread.sleep(16); //60 FPS me thinks
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for(Block i: blocks){
            i.paintComponent(g2d);
        }
        this.player.paintComponent(g2d);
    }
    public void updateAnim(){
        System.out.println("test");
        player.playerXIncrement();
        player.playerYIncrement();
        updateCollision();
    }
    

    public void updateCollision(){
        for(Block i : blocks){
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
}

