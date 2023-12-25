import javax.swing.JFrame;
import javax.swing.JPanel;

public class Frame {
    public static JFrame frame = new JFrame();

    // Work in progress - currently not working
    
    frame.setLayout(new BorderLayout(10, 5));
    frame.setSize(1200, 750);
    frame.setLocationRelativeTo(null);
    frame.setTitle("Blackjack - Main Menu");
    frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    JPanel background = new JPanel();
    background.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
    background.setBackground(new ColorUIResource(53, 101, 77));

    frame.add(background);
    frame.setVisible(true);
    Deck.getCardImage(Deck.generateDeck().get(0));
}
