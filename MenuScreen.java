package GameInfo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;


public class MenuScreen extends JLayeredPane{
   
    private JButton helpButton = new JButton("HELP");
    private JButton startButton = new JButton("START");
    private JLabel title = new JLabel("BLOCKED");
    private JPanel buttonPanel = new JPanel(null);
    private MenuAnimPanel animationPanel = new MenuAnimPanel();
    private JFrameLayout jFrameLayout = new JFrameLayout();
    private MainGameWindow game;

    public MenuScreen(JFrameLayout jFrameLayout) {
        setPreferredSize(new Dimension(768,526));
        add(animationPanel, 0);
        add(buttonPanel, 1);
        setBackground(Color.BLACK);
        initPanel();
        setVisible(true);
        this.jFrameLayout = jFrameLayout;
    }
    public void setGameWindow(MainGameWindow a){
        game = a;
    }

    public void initPanel(){
        title.setFont(new Font("Arial", Font.BOLD, 60));
        title.setBounds(244, 100, 500, 60);
        title.setForeground(Color.WHITE);
        startButton.setFont(new Font("Arial", Font.BOLD, 30));
        startButton.setBounds(304,350,160,50);
        helpButton.setFont(new Font("Arial", Font.BOLD, 30));
        helpButton.setBounds(304,250,160,50);
        
        startButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                game.startGame();
                game.setInMenu(false);
                jFrameLayout.getCardLayout().show(jFrameLayout.getCardPanel(), "Game");
            }
        });
        animationPanel.setLayout(null);
        buttonPanel.setLayout(null);

        buttonPanel.add(title);
        buttonPanel.add(startButton);
        buttonPanel.add(helpButton);

        buttonPanel.setOpaque(true);
        animationPanel.setOpaque(false);
        buttonPanel.setBounds(0,0, 768, 576);
        buttonPanel.setBackground(null);
    }


    public void restartAnimation(){
        animationPanel.restartAnimation();
    }
    


  
}