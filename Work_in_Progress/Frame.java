import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class Frame {
    public static ArrayList<String> dealer = Game.dealerHand;
    public static ArrayList<String> player = Game.playerHand;
    public static JFrame frame = new JFrame("Blackjack");

    // Work in progress
    public static void updateFrame() {
        JPanel game = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);

                int width = 110;
                int height = 154;

                if (Game.gameEnded) {

                } else if (!Game.gameEnded) {
                    if (Game.dealerReveal) {
                        try {
                            for (int i = 0; i < dealer.size(); i++) {
                                String path = Deck.getCardImage(dealer.get(i));
                                BufferedImage cardImage = ImageIO.read(new File(path));
                                g.drawImage(cardImage, (width + 25 + (width + 5) * i), 55, width, height, null);
                            }

                            for (int i = 0; i < player.size(); i++) {
                                String path = Deck.getCardImage(player.get(i));
                                BufferedImage cardImage = ImageIO.read(new File(path));
                                g.drawImage(cardImage, (20 + (width + 5) * i), 295, width, height, null);
                            }

                            g.setFont(new Font("Arial", Font.PLAIN, 25));
                            g.setColor(Color.white);
                            g.drawString("Dealer's Hand:", 20, 40);
                            g.drawString("Player's Hand:", 20, 280);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else if (!Game.dealerReveal) {
                        try {
                            BufferedImage back = ImageIO.read(new File("Blackjack/cards/back.png"));
                            g.drawImage(back, 20, 55, width, height, null);

                            for (int i = 0; i < dealer.size(); i++) {
                                String path = Deck.getCardImage(dealer.get(i));
                                BufferedImage cardImage = ImageIO.read(new File(path));
                                g.drawImage(cardImage, (width + 25 + (width + 5) * i), 55, width, height, null);
                            }

                            for (int i = 0; i < player.size(); i++) {
                                String path = Deck.getCardImage(player.get(i));
                                BufferedImage cardImage = ImageIO.read(new File(path));
                                g.drawImage(cardImage, (20 + (width + 5) * i), 295, width, height, null);
                            }

                            g.setFont(new Font("Arial", Font.PLAIN, 25));
                            g.setColor(Color.white);
                            g.drawString("Dealer's Hand:", 20, 40);
                            g.drawString("Player's Hand:", 20, 280);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        };
        JPanel buttons = new JPanel();

        if (Game.gameEnded) {
            JButton hit = new JButton("Hit");
            JButton stay = new JButton("Stay");

            frame.setVisible(true);
            frame.setSize(1000, 625);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            game.setLayout(new BorderLayout());
            game.setBackground(new ColorUIResource(53, 101, 77));

            frame.add(game);

            hit.setContentAreaFilled(false);
            hit.setPreferredSize(new Dimension(80, 30));
            hit.setFocusable(false);

            stay.setContentAreaFilled(false);
            stay.setPreferredSize(new Dimension(80, 30));
            stay.setFocusable(false);

            buttons.add(hit);
            buttons.add(stay);

            frame.add(buttons, BorderLayout.SOUTH);
        } else if (!Game.gameEnded) {

        }
    }
}