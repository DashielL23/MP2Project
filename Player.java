package GameInfo;

import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

public class Player{
    private int playerX = 100;
    private double playerY = 100;
    private int xVel = 5;
    private double yVel = 5;
    private double gravity = .01;

    public int getPlayerX(){
        return playerX;
    }
    public double getPlayerY(){
        return playerY;
    }
    public double getPlayerYVel(){
        return yVel;
    }
    public int getPlayerXVel(){
        return xVel;
    }
    public void playerXIncrement(){
        playerX = playerX + xVel;
    }
    public void playerYIncrement(){
        yVel= yVel + gravity;
        playerY = playerY + yVel;
        gravity+=.01;
    }
    public void playerSetYVel(int x){
        yVel = x;
    }
    public void playerSetXVel(int x){
        xVel = x;
    }
    public void resetPlayerGravity(){
        gravity = 0.01;
    }
    public void playerSetY(int x){
        playerY = x;
    }
    public void playerSetX(int x){
        playerX = x;
    }
    public void noGravity(){
        gravity = 0;
    }

    public Rectangle getPlayerHorizontalBounds(){ // For collisions
        int boxX = playerX;
        int boxY = (int)playerY+5;
        int boxLength = 50 + (xVel/2);
        int boxWidth = 40;

        return new Rectangle(boxX,boxY,boxLength,boxWidth); // returns invisible rectangle the size of the block to detect collision
    }

    public Rectangle getPlayerVerticalBounds(){ // For collisions
        int boxX = playerX+5;
        int boxY = (int)playerY;
        int boxLength = 40;
        int boxWidth = 50 + (int)yVel/2;

        return new Rectangle(boxX,boxY,boxLength,boxWidth); // returns invisible rectangle the size of the block to detect collision
    }
    public Rectangle getPlayerHeadForMovableBlocks(){
        return new Rectangle(playerX,(int)playerY,50,10);
    }
    public void paintComponent(Graphics g){
        Graphics2D player2D = (Graphics2D)g; 
        player2D.setColor(Color.RED);
        player2D.fillRect(playerX,(int)playerY,50,50);

        player2D.dispose();
    }
}