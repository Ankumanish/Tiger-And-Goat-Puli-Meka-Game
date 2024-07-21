package pack;

import javax.swing.*;
import java.awt.*;

public class TigerAndGoat {
	JFrame frame;
    public static void main(String[] args) {
        new TigerAndGoat();
    }
    
    public TigerAndGoat() {
    	frame = new JFrame("Tiger And Goat / Puli Meka");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(510, 830);
        frame.setResizable(false);

        BackgroundPanel backgroundPanel = new BackgroundPanel("background_image.png");

        GameBoard gameBoard = new GameBoard();
        gameBoard.setOpaque(false); // Make the game board panel transparent

        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(gameBoard, BorderLayout.CENTER);

        frame.setContentPane(backgroundPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        ImageIcon icon = new ImageIcon(getClass().getResource(imagePath));
        backgroundImage = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
