package GameInfo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.JLayeredPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadPoolExecutor;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class MenuScreen extends JLayeredPane{
    

    private JButton helpButton = new JButton("HELP");
    private JButton startButton = new JButton("START");
    private JLabel title = new JLabel("BLOCKED");
    private JPanel buttonPanel = new JPanel(null);
    private JPanel animationPanel = new JPanel(null){
        private Block  bottomBlock = new Block(0,450,768,126,new Color(0,0,139));
        private Block leftBlock = new Block (0,0,100,576, new Color(139,0,0));
        private Block rightBlock = new Block(668,0,100,576, new Color(139,0,0));
        private Block[] blocks = {bottomBlock,leftBlock,rightBlock};
        private Player player = new Player();
        
        public void startAnimation(){
            menuThread = new Thread("anim");
            menuThread.start();
        }

    
        public void run(){
            player.playerSetX(101);
            player.playerSetY(399);
            while(menuThread != null && inMenu){
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
            player.playerXIncrement();
            player.playerYIncrement();
            updateCollision();
        }
        public void stopAnim(){
            inMenu = false;
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
    };
    private boolean inMenu = true;
    private Thread menuThread = new Thread();

    public MenuScreen() {
        setLayout(null);
        this.add(buttonPanel);
        this.add(animationPanel);
        this.setBackground(Color.BLACK);
        initPanel();
        setVisible(true);
    }

    public void initPanel(){
        

        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setBounds(324, 100, 120, 60);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.setBounds(344,350,80,50);
        helpButton.setFont(new Font("Arial", Font.BOLD, 30));
        helpButton.setBounds(344,250,80,50);
        
        animationPanel.setLayout(null);
        buttonPanel.setLayout(null);

        buttonPanel.add(title);
        buttonPanel.add(startButton);
        buttonPanel.add(helpButton);
        buttonPanel.setOpaque(true);
        animationPanel.setOpaque(true);
        buttonPanel.setBounds(0,0, getWidth(), getHeight());
        animationPanel.setBounds(0,0, getWidth(), getHeight());
    }

   
}