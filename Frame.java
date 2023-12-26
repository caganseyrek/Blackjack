import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.plaf.ColorUIResource;

public class Frame {
    
    // Work in progress
    public static void setFrame() {
        ArrayList<String> dealer = Game.dealerHand;
        ArrayList<String> player = Game.playerHand;

        JFrame frame = new JFrame("Blackjack");
        JPanel background = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = 110;
                int height = 154;
                int i = 0;

                try {
                    BufferedImage back = ImageIO.read(new File("Blackjack/cards/back.png"));
                    g.drawImage(back, 20, 20, width, height, null);

                    for (String card : dealer) {
                        String path = Deck.getCardImage(card);
                        BufferedImage cardImage = ImageIO.read(new File(path));
                        g.drawImage(cardImage, (width + 25 + (width+5)*i), 20, width, height, null);
                        i++;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        JPanel buttons = new JPanel();

        JButton hit = new JButton("Hit");
        JButton stay = new JButton("Stay");

        frame.setVisible(true);
        frame.setSize(1000, 625);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        background.setLayout(new BorderLayout());
        background.setBackground(new ColorUIResource(53, 101, 77));

        frame.add(background);

        hit.setContentAreaFilled(false);
        hit.setPreferredSize(new Dimension(80, 30));
        hit.setFocusable(false);

        stay.setContentAreaFilled(false);
        stay.setPreferredSize(new Dimension(80, 30));
        stay.setFocusable(false);

        buttons.add(hit);
        buttons.add(stay);

        frame.add(buttons, BorderLayout.SOUTH);
    }
}
