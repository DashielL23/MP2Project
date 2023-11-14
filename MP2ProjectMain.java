import javax.swing.*;
public class MP2ProjectMain {
    public static void main(String[] args){
        JFrame window = new JFrame();
        MainGameWindow gameWindow = new MainGameWindow();

        //defining stuff for window
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Blocked Off!");
        window.setLocationRelativeTo(null);

        //adds window
        window.setVisible(true);
        window.add(gameWindow);
        window.pack();


    }
}
