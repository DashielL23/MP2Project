package GameInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.CardLayout;

public class JFrameLayout extends JFrame{
    private CardLayout cardLayout = new CardLayout(); 
    private JPanel cardPanel/*to hold the other windows in one window(Shuffle between them)*/ = new JPanel();
    public CardLayout getCardLayout(){
        return cardLayout;
    }
    public JPanel getCardPanel(){
        return cardPanel;
    }

    public void layout(){
        JFrame window = new JFrame();
        MouseActions mouseAction = new MouseActions();
        MainGameWindow gameWindow = new MainGameWindow(mouseAction, this);       
        GameOverScreen gameOverWindow = new GameOverScreen(gameWindow, this);
        MenuScreen menuWindow = new MenuScreen();
        //handling the window switching for death, menu, etc
        cardPanel.setLayout(cardLayout);
        cardPanel.add(gameWindow,"Game");
        cardPanel.add(gameOverWindow, "Game_Over");
        cardPanel.add(menuWindow, "Menu");
        gameOverWindow.initPanel();
        //defining stuff for window
        window.getContentPane().add(cardPanel);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Blocked Off!");
        window.setLocationRelativeTo(null);
        //adds window
        window.setVisible(true);
        window.setSize(768,576);
        cardLayout.show(cardPanel,"Menu"); 
        menuWindow.startAnimation();
    }
}
