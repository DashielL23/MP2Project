package GameInfo;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GameOverScreen extends JPanel {

    MainGameWindow player;
    JLabel deathLabel = new JLabel("You died!");
    JButton toMenu = new JButton("Menu");
    JButton restartLevel = new JButton("Restart");
    JFrameLayout jFrameLayout;

    public GameOverScreen(MainGameWindow player, JFrameLayout jFrameLayout) {
        setLayout(null); 
        this.player = player;
        this.jFrameLayout = jFrameLayout;
        initPanel(); 
    }

    public void initPanel() {
        deathLabel.setFont(new Font("Arial", Font.BOLD, 30));
        deathLabel.setBounds(50,150,200,50);
        // buttons
        toMenu.setFont(new Font("Arial", Font.BOLD, 30));
        toMenu.setBounds(50,350,200,50);

        restartLevel.setFont(new Font("Arial", Font.BOLD, 30));
        restartLevel.setBounds(50,450,200,50);

        restartLevel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                player.resetLevel();
            }
        });
        toMenu.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                player.toMenu();
            }
        });

        add(toMenu);
        add(deathLabel);
        add(restartLevel);
    }
}